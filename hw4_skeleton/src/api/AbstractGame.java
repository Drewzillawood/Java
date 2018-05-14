package api;

import java.util.List;

/**
 * A partial implementation of the Game interface for 
 * Tetris-like falling block games. Subclasses must implement
 * the determinePositionsToCollapse() and determineScore() methods.
 */
public abstract class AbstractGame implements Game
{
  /**
   * Width of the game grid.
   */
  private final int width;

  /**
   * Height of the game grid.
   */
  private final int height;

  /**
   * The shape that is subject to motion during the step() method
   * or via invocations of the shiftXXX() or rotate() methods.
   */
  private Shape current;

  /**
   * A grid whose positions may be occupied by the blocks
   * the current falling shape or blocks that can no longer
   * be moved.  Unoccupied cells are null.
   */
  private Block[][] grid;

  /**
   * Status of the game after each invocation of step(), as described
   * in the GameStatus documentation.
   */
  private GameStatus gameStatus;

  /**
   * Generator for new shapes.  (The BasicGenerator implementation
   * will uniformly select one of the six shape types).
   */
  private Generator generator;

  /**
   * State variable indicating which blocks are to be deleted when the
   * status is COLLAPSING.  The implementation maintains the invariant that
   * positionsToCollapse.size() is nonzero if and only if gameStatus is COLLAPSING.
   */
  private List<Position> positionsToCollapse;

  /**
   * Constructs a new AbstractBlockGame.
   */
  protected AbstractGame(int givenWidth, int givenHeight, Generator generator)
  {
    width = givenWidth;
    height = givenHeight;
    grid = new Block[getHeight()][getWidth()];
    this.generator = generator;
    current = generator.getNext(getWidth());
    gameStatus = GameStatus.NEW_SHAPE;
  }

  /**
   * Returns a list of locations for all cells that form part of
   * a collapsible set.  This list may contain duplicates.
   * @return 
   *   list of locations for positions to be collapsed
   */
  protected abstract List<Position> determinePositionsToCollapse();
  
  /**
   * Returns the current score.
   * @return 
   *   the current score
   */
  protected abstract int determineScore();

  @Override
  public Block getBlock(int row, int col)
  {
    return grid[row][col];
  }
  
  /**
   * Sets a block at the given row and column.
   * @param row
   * @param col
   * @param value
   */
  public void setBlock(int row, int col, Block value)
  {
    grid[row][col] = value;
  }
  
  @Override
  public int getHeight()
  {
    return height;
  }

  @Override
  public Shape getCurrent()
  {
    if (gameStatus == GameStatus.COLLAPSING || gameStatus == GameStatus.GAME_OVER)
    {
      throw new IllegalStateException();
    }
    return current;
  }

  @Override
  public int getWidth()
  {
    return width;
  }

  @Override
  public List<Position> getPositionsToCollapse()
  {
    if (positionsToCollapse.size() == 0)
    {
      throw new IllegalStateException();
    }
    return positionsToCollapse;
  }

  @Override
  public boolean transform()
  {
    boolean ret = canTransform();
    if (ret)
    {
      current.transform();
    }
    return ret;
  }

  @Override
  public void cycle()
  {
    current.cycle();
  }
  
  @Override
  public boolean shiftLeft()
  {
    boolean ret = canShiftLeft();
    if (ret)
    {
      current.shiftLeft();
    }
    return ret;
  }

  @Override
  public boolean shiftRight()
  {
    boolean ret = canShiftRight();
    if (ret)
    {
      current.shiftRight();
    }
    return ret;
  }

  @Override
  public Long getScore()
  {
	  return Long.MAX_VALUE;
//    return determineScore();
  }

  @Override
  public boolean gameOver()
  {
    return gameStatus == GameStatus.GAME_OVER;
  }

  //
  // Main game logic - see Game interface documentation
  //
  @Override
  public GameStatus step()
  {
    switch (gameStatus)
    {
      case GAME_OVER:
        // do nothing
        break;
      case NEW_SHAPE:
      case FALLING:
        if (gameStatus == GameStatus.NEW_SHAPE)
        {
          gameStatus = GameStatus.FALLING;
        }
        if (canShiftDown())
        {
          current.shiftDown();
        }
        else
        {
          // Add blocks of the current shape to the grid, maybe 
          // temporarily, in order to check whether it has completed
          // a collapsible group
          for (Cell c : current.getCells())
          {
            int x = c.getCol();
            int y = c.getRow();
            if (y >= 0 && y < height && x >= 0 && x < width)
            {
              grid[y][x] = c.getBlock();
            }
          }
          positionsToCollapse = determinePositionsToCollapse();
          if (positionsToCollapse.size() != 0)
          {
            // current shape completes a collapsible set,
            // so prepare to collapse
            gameStatus = GameStatus.COLLAPSING;
          }
          else
          {
            // current shape is stopped, but has not completed a
            // collapsible set, so it might be moved sideways; 
            // take its blocks back out of the grid
            for (Cell c : current.getCells())
            {
              int x = c.getCol();
              int y = c.getRow();
              if (y >= 0 && y < height && x >= 0 && x < width)
              {
                grid[y][x] = null;
              }
            }
            gameStatus = GameStatus.STOPPED;
          }
        }
        break;
      case STOPPED:
        // If the shape was previously stopped, it still may be possible
        // to shift it downwards since it could have been moved to the side
        // during the last step
        if (canShiftDown())
        {
          current.shiftDown();
          gameStatus = GameStatus.FALLING;
        }
        else
        {
          // we only get in the stopped state when the shape doesn't complete
          // a collapsible set; start a new shape at the top
          for (Cell c : current.getCells())
          {
            int x = c.getCol();
            int y = c.getRow();
            if (y >= 0 && y < height && x >= 0 && x < width)
            {
              grid[y][x] = c.getBlock();
            }
          }
          current = generator.getNext(getWidth());
          if (collides(current))
          {
            gameStatus = GameStatus.GAME_OVER;
          }
          else
          {
            gameStatus = GameStatus.NEW_SHAPE;
          }
        }
        break;
      case COLLAPSING:
        collapsePositions(positionsToCollapse); 
        positionsToCollapse = determinePositionsToCollapse();       
        if (positionsToCollapse.size() == 0)
        {
          // done collapsing, try to start a new shape
          current = generator.getNext(getWidth());
          if (collides(current))
          {
            gameStatus = GameStatus.GAME_OVER;
          }
          else
          {
            gameStatus = GameStatus.NEW_SHAPE;
          }
        }
        // else, we'll do some more collapsing during the next call to step()
        break;
    }
    return gameStatus;
  }


  /**
   * Deletes the blocks at the indicated positions and shifts
   * blocks above them down.  Only blocks lying within a column
   * above a deleted block are shifted down.  This method 
   * does not update the game state and should normally be called
   * only from the step() method.
   * @param positionsToCollapse 
   *   list of positions to collapse, may contain duplicates.
   */
  public void collapsePositions(List<Position> positionsToCollapse)
  {
    for (int col = 0; col < getWidth(); ++col)
    {
      collapseOneColumn(positionsToCollapse, col);
    }
  }
  
  /**
   * Collapses one column using the given positions.
   * @param positionsToCollapse
   * @param col
   */
  private void collapseOneColumn(List<Position> positionsToCollapse, int col)
  {
    // flag the positions in this column that need to be collapsed
    boolean[] marked = new boolean[getHeight()];
    for (Position p : positionsToCollapse)
    {
      if (p.col() == col)
      {
        marked[p.row()] = true;
      }
    }
    int index = getHeight() - 1;
    for(int row = getHeight() - 1; row >= 0; --row)
    {
      if (!marked[row])
      {
        grid[index][col] = grid[row][col];
        index -= 1;
      }
    }
    for (int row = index; row >= 0; --row)
    {
      grid[row][col] = null;
    }
  }
  
  /**
   * Determines whether the current shape can be shifted down. Does not
   * modify the game state.
   * @return true if the current shape can be shifted down, false otherwise
   */
  private boolean canShiftDown()
  {
    Shape t = (Shape) current.clone();
    t.shiftDown();
    return !collides(t);
  }

  /**
   * Determines whether the current shape can be shifted right. Does not
   * modify the game state.
   * @return true if the current shape can be shifted right, false otherwise
   */
  private boolean canShiftRight()
  {
    Shape t = (Shape) current.clone();
    t.shiftRight();
    return !collides(t);
  }

  /**
   * Determines whether the current shape can be shifted left. Does not
   * modify the game state.
   * @return true if the current shape can be shifted left, false otherwise
   */
  private boolean canShiftLeft()
  {
    Shape t = (Shape) current.clone();
    t.shiftLeft();
    return !collides(t);
  }

  /**
   * Determines whether the current shape can be transform. Does not
   * modify the game state.
   * @return true if the current shape can be transformed, false otherwise
   */
  private boolean canTransform()
  {
    Shape t = (Shape) current.clone();
    t.transform();
    return !collides(t);
  }

  /**
   * Determines whether the given shape overlaps
   * with the occupied cells of the grid, or extends beyond the sides
   * or bottom of the grid.  (A shape in its initial position
   * MAY extend above the grid.)
   *
   * @param t a shape
   * @return true if the cells of the given shape extend beyond the
   *   sides or bottom of the grid or overlap with any occupied cells of
   *   the grid
   */
  private boolean collides(Shape t)
  {
    for (Cell c : t.getCells())
    {
      int x = c.getCol();
      int y = c.getRow();
      if (x < 0 || x > width - 1 || y > height - 1)
      {
        return true;
      }

      // row, column
      if (y >= 0 && grid[y][x] != null)
      {
        return true;
      }
    }
    return false;
  }



}
