package ui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import api.BasicPlayLevel;
import api.Game;
import api.PlayLevel;
import hw4.MagicTetris;

/**
 * Main class for a GUI for a Tetris game sets up a 
 * GamePanel instance in a frame.
 */
public class GameMain
{
  /**
   * Cell size in pixels.
   */
  public static final int SIZE = 25; 
  
  /**
   * Font for displaying score.
   */
  public static final int SCORE_FONT = 24; 
  
  /**
   * Grid background color.
   */
  public static final Color BACKGROUND_COLOR = Color.LIGHT_GRAY;
  
  
  /**
   * Helper method for instantiating the components.  This
   * method should be executed in the context of the Swing
   * event thread only.
   */
  private static void create()
  {
    // EDIT HERE TO CHANGE THE GAME
    Game game = new MagicTetris(12, 20);
    //Game game = new examples.SampleGame();

    // create the two panels for the game
    PlayLevel level = new BasicPlayLevel();
    ScorePanel scorePanel = new ScorePanel();
    GamePanel panel = new GamePanel(game, scorePanel, level);
     
    // arrange the two panels 
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
    mainPanel.add(scorePanel);
    mainPanel.add(panel);
    
    // put main panel in a window
    JFrame frame = new JFrame("Com S 227 Magic Tetris");
    frame.getContentPane().add(mainPanel);

    // give panels a nonzero size
    Dimension d = new Dimension(game.getWidth() * GameMain.SIZE, game.getHeight() * GameMain.SIZE);   
    panel.setPreferredSize(d);
    d = new Dimension(game.getWidth() * GameMain.SIZE, 3 * GameMain.SIZE);   
    scorePanel.setPreferredSize(d);
    frame.pack();
    
    // we want to shut down the application if the 
    // "close" button is pressed on the frame
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    // be sure key events get to the panel
    panel.requestFocus();
    
    // rock and roll...
    frame.setVisible(true);
  }
  
  /**
   * Entry point.  Main thread passed control immediately
   * to the Swing event thread.
   * @param args not used
   */
  public static void main(String[] args)
  {
    Runnable r = new Runnable()
    {
      public void run()
      {
        create();
      }
    };
    SwingUtilities.invokeLater(r);
  }
}
