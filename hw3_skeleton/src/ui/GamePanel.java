package ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

import api.GridPosition;
import api.Jewel;
import hw3.Game;

/**
 * User interface for the main grid of a Bejeweled-like game.
 */
public class GamePanel extends JPanel
{
  /**
   * Serial ID to eliminate compiler warning.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Number of pixels each icon falls per frame when animating.
   */
  private int fallPerFrame = 4; 
  
  /**
   * Interval between frames when animating falling icons,
   * in milliseconds.
   */
  private int fallingSpeed = 15; 

  /**
   * Number of steps in which to flash cells or fade out when a pair is selected.
   */
  private int numberOfFlashes = 5;
  
  /**
   * Interval between flash steps, in milliseconds.
   */
  private int flashingSpeed = 50;
  
  /**
   * Score panel associated with the game.
   */
  private ScorePanel scorePanel;

  /**
   * The javax.swing.Timer instance used to animate the UI.
   */
  private Timer timer;

  /**
   * The Game instance for which this is the UI.
   */
  private Game game;
  
  /**
   * State variable counts down to zero while flashing 
   * the cells to be collapsed.
   */
  private int flashingState = 0;
  
  /**
   * Cells about to be collapsed are flashed or fade out briefly
   * before disappearing.
   */
  private ArrayList<GridPosition> cellsToCollapse = null;

  /**
   * Flag indicates whether we are currently moving cells down.
   */
  private boolean collapsing = false;
  
  /**
   * Cells currently being moved down during a collapse.
   */
  private ArrayList<AnimatedCell> movingCells = null;

  /**
   * Flag indicates whether we are currently filling new cells.
   */
  private boolean filling = false;
  
  /**
   * New cells filled from top.
   */
  private ArrayList<AnimatedCell> fillingCells = null;

  /**
   * Cell currently selected by a mouse down event.
   */
  private GridPosition currentCell;
  
  /**
   * Cell currently selected by a mouse drag event.
   */
  private GridPosition nextCell; 

  /**
   * Constructs a GamePanel with the given game associated ScorePanel.
   * @param game 
   *   the Game instance for which this is the UI
   * @param scorePanel
   *   panel for displaying scores associated with the game
   */
  public GamePanel(Game game, ScorePanel scorePanel)
  {
    this.game = game;
    this.scorePanel = scorePanel;
    addMouseListener(new MyMouseListener());
    addMouseMotionListener(new MyMouseMotionListener());
    timer = new Timer(fallingSpeed, new TimerCallback());
  }
  
  // The paintComponent is invoked by the Swing framework whenever
  // the panel needs to be rendered on the screen.  In this application,
  // repainting is normally triggered by the calls to the repaint() 
  // method in the timer callback and the keyboard event handler (see below).
  
  @Override
  public void paintComponent(Graphics g)
  {
    // clear background
    g.setColor(GameMain.BACKGROUND_COLOR);
    g.fillRect(0, 0, getWidth(), getHeight());
    
    // paint occupied cells of the grid
    for (int row = 0; row < game.getHeight(); ++row)
    {
      for (int col = 0; col < game.getWidth(); ++col)
      {
        Jewel t = game.getJewel(row, col);
        if (t != null)
        { 
          paintOneCell(g, row, col, t);
        }
      }
    }
    
    if (currentCell != null)
    {
      highlightOneCell(g, currentCell.row(), currentCell.col());
    }
    if (nextCell != null)
    {
      highlightOneCell(g, nextCell.row(), nextCell.col());
    }
    
    if (flashingState > 0)
    {   
      // need to paint the cells, since they are nulled out in game
      for (GridPosition p : cellsToCollapse)
      {
        paintOneCellFlashing(g, p.row(), p.col(), p.getJewel());
      }    
    }
    
    if (collapsing)
    {
      // first grey out the column where cells are moving
      // in order to match the background
      for (AnimatedCell c : movingCells)
      {
        int col = c.col();
        int row = c.row();
        paintOneCell(g, row, col, GameMain.BACKGROUND_COLOR);
      }
      
      for (AnimatedCell c : movingCells)
      {
        int col = c.col();
        int pixel = c.currentPixel;
        paintOneCellByPixel(g, pixel, col, c.getJewel());
      }
    }
    
    if (filling)
    {
      // first grey out the column where cells are moving
      for (AnimatedCell c : fillingCells)
      {
        int col = c.col();
        int row = c.row();
        paintOneCell(g, row, col, GameMain.BACKGROUND_COLOR);
      }
      
      for (AnimatedCell c : fillingCells)
      {
        int col = c.col();
        int pixel = c.currentPixel;
        paintOneCellByPixel(g, pixel, col, c.getJewel());
      }
      
    }
  }
  
  /**
   * Renders a single cell of the grid.
   * 
   * @param g the Swing graphics context
   * @param row y-coordinate of the cell to render
   * @param col x-coordinate of the cell to render
   * @param t the icon to render, normally used
   *   to determine the color with which to render the cell
   */
  private void paintOneCell(Graphics g, int row, int col, Jewel t)
  {
    // scale everything up by the SIZE
    int x = GameMain.SIZE * col;
    int y = GameMain.SIZE * row;
    g.setColor(getColorForIcon(t));
    g.fillRect(x, y, GameMain.SIZE, GameMain.SIZE);
    g.setColor(Color.GRAY);;
    g.drawRect(x, y, GameMain.SIZE - 1, GameMain.SIZE - 1);
  }

  /**
   * Renders a single cell of the grid the given color.
   * 
   * @param g the Swing graphics context
   * @param row y-coordinate of the cell to render
   * @param col x-coordinate of the cell to render
   * @param color the color to render
   */
  private void paintOneCell(Graphics g, int row, int col, Color color)
  {
    // scale everything up by the SIZE
    int x = GameMain.SIZE * col;
    int y = GameMain.SIZE * row;
    g.setColor(color);
    g.fillRect(x, y, GameMain.SIZE, GameMain.SIZE);
    g.setColor(Color.GRAY);;
    g.drawRect(x, y, GameMain.SIZE - 1, GameMain.SIZE - 1);
  }

  /**
   * Renders a single cell of the grid specifying its vertical
   * position in pixels.
   * 
   * @param g the Swing graphics context
   * @param rowPixel y-coordinate of pixel at which to render the cell
   * @param col x-coordinate of the cell to render
   * @param t the icon to render, normally used
   *   to determine the color with which to render the cell
   */
  private void paintOneCellByPixel(Graphics g, int rowPixel, int col, Jewel t)
  {
    // scale everything up by the SIZE
    int x = GameMain.SIZE * col;
    int y = rowPixel;
    g.setColor(getColorForIcon(t));
    g.fillRect(x, y, GameMain.SIZE, GameMain.SIZE);
    g.setColor(Color.GRAY);;
    g.drawRect(x, y, GameMain.SIZE - 1, GameMain.SIZE - 1);
  }

  /**
   * Renders a single cell of the grid based on the current flashingState.
   * 
   * @param g the Swing graphics context
   * @param row y-coordinate of the cell to render
   * @param col x-coordinate of the cell to render
   * @param t the icon to render, normally used
   *   to determine the color with which to render the cell
   */
  private void paintOneCellFlashing(Graphics g, int row, int col, Jewel t)
  {
    // background
    paintOneCell(g, row, col, Color.BLACK);

    // draw the cell smaller depending on the flashing state
    double percent = ((double) flashingState) / (numberOfFlashes * 2);
    int cellSize = (int) Math.round(percent * GameMain.SIZE);
    int offset = (GameMain.SIZE - cellSize) / 2;
    
    // scale everything up by the SIZE
    int x = GameMain.SIZE * col;
    int y = GameMain.SIZE * row;
    g.setColor(getColorForIcon(t));
    g.fillRect(x + offset, y + offset, cellSize, cellSize);
    g.setColor(Color.GRAY);;
    g.drawRect(x, y, GameMain.SIZE - 1, GameMain.SIZE - 1);
  }
  
  /**
   * Draws a white border around one cell.
   * 
   * @param g the Swing graphics context
   * @param row y-coordinate of the cell to highlight
   * @param col x-coordinate of the cell to highlight
   */
  private void highlightOneCell(Graphics g, int row, int col)
  {
    g.setColor(Color.WHITE);
    ((Graphics2D) g).setStroke(new BasicStroke(2));
    g.drawRect(col * GameMain.SIZE, row * GameMain.SIZE, GameMain.SIZE, GameMain.SIZE);
    ((Graphics2D) g).setStroke(new BasicStroke(1));
  }

  /**
   * Listener for timer events.  The actionPerformed method
   * is invoked each time the timer fires and the call to
   * repaint() at the bottom of the method causes the panel
   * to be redrawn.
   */
  private class TimerCallback implements ActionListener
  {
    @Override
    public void actionPerformed(ActionEvent arg0)
    {

      // State may be flashing, collapsing or filling.
      // Timer is not stopped until all cascading collapses are finished.

      if (flashingState == 0 && !collapsing && !filling)
      {
        // Either we are just starting execution of the timer, or
        // have finished a previous collapse and fill and need to check
        // whether there is a cascading collapse. If so,
        // set the flashing state and fall through

        cellsToCollapse = game.findAndMarkAllRuns();
        if (cellsToCollapse.size() != 0)
        {
          flashingState = numberOfFlashes * 2;
          timer.setDelay(flashingSpeed);
          timer.restart();

          // update the score too
          scorePanel.updateScore(game.getScore());
        }
        else
        {
          // nothing more to collapse
          cellsToCollapse = null;
          timer.stop();
        }
      }

      // intentional fall through, may have set flashing state above
      if (flashingState > 0)
      {
        flashingState--;
        if (flashingState == 0)
        {
          // Done flashing, start collapsing and filling
          timer.setDelay(fallingSpeed);
          timer.restart();
          collapsing = true;
          initializeCellsToCollapse();
     
        }
      }

      // intentional fall through, may have set collapsing state above
      if (collapsing)
      {
        // see if there are still cells moving 
        boolean found = false;
        for (AnimatedCell cell : movingCells)
        {
          if (!cell.done())
          {
            found = true;
            cell.animate(fallPerFrame);
          }
        }
        if (!found)
        {
          // done collapsing, start filling 
          collapsing = false;
          movingCells = null;
          filling = true;
         
          initializeCellsToFill();
          if (fillingCells != null) 
          {
            if (fillingCells.size() == 0)
            {
              System.out.println("WARNING: Cannot animate new cell motion. " + 
                  "fillAll did not return cells to fill columns.");
              filling = false;
              fillingCells = null;
            }
          }
          else
          {
            System.out.println("WARNING: Cannot animate new cell motion. fillAll returned null.");
            filling = false;
          }
        }
      }

      // intentional fall through, may have set filling state above
      if (filling)
      {
        // see if we're done
        boolean found = false;
        for (AnimatedCell cell : fillingCells)
        {
          if (!cell.done())
          {
            found = true;
            cell.animate(fallPerFrame);
          }
        }
        if (!found)
        {
          filling = false;
          fillingCells = null;
        }
      }

      repaint();
    }
  }
  
  /**
   * Calls shiftDown for each column of the game and builds
   * a list of AnimatedCells representing the cells to be 
   * collapsed down.
   */
  private void initializeCellsToCollapse()
  {
    // First clone the grid before collapsing everything,
    // so we can try to tell where things were before they moved
    Jewel[][] grid = new Jewel[game.getHeight()][game.getWidth()];
    for (int row = 0; row < grid.length; ++row)
    {
      for (int col = 0; col < grid[0].length; ++col)
      {
        grid[row][col] = game.getJewel(row, col);
      }
    }
    
    movingCells = new ArrayList<>();
    boolean foundNull = false;
    for (int col = 0; col < game.getWidth(); ++col)
    {
      ArrayList<GridPosition> moved = game.shiftColumnDown(col);
      if (moved != null)
      {
        if (moved.size() > 0)
        {
          movingCells.addAll(createAnimatedCells(col, grid, moved));
        }
      }
      else
      {
        foundNull = true;
      }
    }
    if (foundNull)
    {
      System.out.println("WARNING: Cannot animate cell motion. " + ""
          + "shiftColumnDown returned null. ");
    }
  }
  
  /**
   * Attempts to make each given Cell into an AnimatedCell by determining,
   * from the given grid, what the previous row was for the same icon (jewel).
   * @param col
   * @param grid
   * @param moved
   * @return
   */
  private ArrayList<AnimatedCell> createAnimatedCells(int col, Jewel[][] grid, ArrayList<GridPosition> moved)
  {
    ArrayList<AnimatedCell> ret = new ArrayList<>();
    int current = 0;
    boolean error = false;
    for (GridPosition c : moved)
    {
      Jewel icon = c.getJewel();
      
      // make sure the next non-null icon matches the moved icon c
      while (current < grid.length && grid[current][col] == null)
      {
        current += 1;
      }
      if (current < grid.length)
      {
        if (icon.getType() == grid[current][col].getType())
        {
          AnimatedCell ac = new AnimatedCell(c, current);
          ret.add(ac);
          current += 1;
        }
        else
        {
          error = true;
          String msg = "WARNING: Cannot animate cell motion. " + 
              "Cell list returned by shiftColumnDown for column " + col +
              " is inconsistent with previous state of grid";          
          System.out.println(msg);
          ArrayList<Jewel> previous = new ArrayList<>();
          for (int row = 0; row < grid.length; ++row)
          {
            previous.add(grid[row][col]);
          }
          System.out.println("Previous: " + previous.toString());
          System.out.println("Moved cells: " + moved.toString());
        }
      }
      else
      {
        error = true;
        String msg = "WARNING: Cannot animate cell motion. " + 
            "Cell list returned by shiftColumnDown for column " + col +
            " is inconsistent with previous state of grid";          
        System.out.println(msg);
        ArrayList<Jewel> previous = new ArrayList<>();
        for (int row = 0; row < grid.length; ++row)
        {
          previous.add(grid[row][col]);
        }
        System.out.println("Previous: " + previous.toString());
        System.out.println("Moved cells: " + moved.toString());
      }
    }
    if (error)
    {
      // just return an animated cell with same starting position
      ret.clear();
      for (GridPosition c : moved)
      {
        ret.add(new AnimatedCell(c, c.row()));
      }
    }
    return ret;
  }
   
  /**
   * Sets up the fillingCells list.
   */
  private void initializeCellsToFill()
  {
    ArrayList<GridPosition> currentNewCells = game.fillAll();
    if (currentNewCells != null)
    {
      fillingCells = new ArrayList<AnimatedCell>();
      if (currentNewCells != null)
      {
        for (GridPosition c : currentNewCells)
        {
          fillingCells.add(new AnimatedCell(c, -1));
        }
      }
    }
    else
    {
      fillingCells = null;
    }
  }

  /**
   * Returns a color for the given icon.
   * @param icon
   * @return
   */
  private Color getColorForIcon(Jewel icon)
  {
    int index = icon.getType();
    if (index < 0 || index >= GameMain.COLORS.length)
    {
      return Color.BLACK;
    }
    return GameMain.COLORS[index];
  }
  
  /**
   * Callback for mouse events.
   */
  private class MyMouseListener implements MouseListener
  {

    @Override
    public void mouseClicked(MouseEvent event)
    {
    }

    @Override
    public void mousePressed(MouseEvent event)
    {
      if (flashingState > 0 || collapsing || filling) return;
      
      int row = event.getY() / GameMain.SIZE;
      int col = event.getX() / GameMain.SIZE;
      //System.out.println(row + ", " + col);
      currentCell = new GridPosition(row, col, game.getJewel(row, col));
      repaint();
    }

    @Override
    public void mouseReleased(MouseEvent event)
    {
      if (flashingState > 0 || collapsing || filling) return;     
      
      
       // If we have selected two adjacent cells, then tell the game to try
       // to swap them.
      if (currentCell != null)
      {
        if (nextCell != null)
        {
          boolean swapped = game.select(currentCell, nextCell);
          //System.out.println(swapped);
          repaint();
          if (swapped)
          {
            // Successful move, start up the timer
            timer.setDelay(flashingSpeed);
            timer.restart();
          }
        }
      }
      currentCell = null;
      nextCell = null;

    }

    @Override
    public void mouseEntered(MouseEvent e)
    {
    }

    @Override
    public void mouseExited(MouseEvent e)
    {
    }
  }
  
  /**
   * Callback for mouse motion events.
   */
  private class MyMouseMotionListener implements MouseMotionListener
  {

    @Override
    public void mouseDragged(MouseEvent e)
    {
      int row = e.getY() / GameMain.SIZE;
      int col = e.getX() / GameMain.SIZE;
      if (currentCell != null)
      {
        //nextCell = new Cell(row, col, game.getJewel(row, col));
        
        // optionally: restrict selection to adjacent cells only
        // if the cell is adjacent to the one in which mouse was pressed,
        // record it as the nextCell
        if ((currentCell.col() == col && Math.abs(currentCell.row() - row) == 1) ||
            (currentCell.row() == row && Math.abs(currentCell.col() - col) == 1))
        {
          nextCell = new GridPosition(row, col, game.getJewel(row, col));
        }
        else
        {
          nextCell = null;
        }
        repaint();
      }

    }

    @Override
    public void mouseMoved(MouseEvent e)
    {
    }
    
  }
  
  /**
   * Subclass of Cell that keeps track of a current vertical
   * offset at which it should actually be drawn.
   */
  private class AnimatedCell extends GridPosition
  {
    public int startPixel; // pixel coordinates
    public int endPixel;
    public int currentPixel;

    /**
     * Constructs an AnimatedCell with the same attributes
     * as the given Cell, with a starting location determined
     * by the given starting row.
     * @param cell
     */
    public AnimatedCell(GridPosition cell, int startRow)
    {
      super(cell.row(), cell.col(), cell.getJewel());
      startPixel = startRow * GameMain.SIZE;
      currentPixel = startPixel;
      endPixel = cell.row() * GameMain.SIZE;
    }

    /**
     * Determines whether this cell has reached its
     * ending location.
     * @return
     */
    public boolean done()
    {
      return currentPixel == endPixel;
    }
    
    /**
     * Moves this cell's current position by the given amount.
     * @param pixels
     */
    public void animate(int pixels)
    {
      currentPixel += pixels;
      if (currentPixel > endPixel)
      {
        currentPixel = endPixel;
      }
    }
  }
}
