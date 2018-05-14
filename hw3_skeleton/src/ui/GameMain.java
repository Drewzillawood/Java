package ui;


import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import hw3.JewelFactory;
import hw3.Game;


/**
 * Main class for a GUI for a Bejeweled-like game sets up a 
 * GamePanel instance in a frame.
 */
public class GameMain
{
  /**
   * Cell size in pixels.
   */
  public static final int SIZE = 25; 

  /**
   * Font size for displaying score.
   */
  public static final int SCORE_FONT = 24; 

  /**
   * Colors for icon types (types outside range 0 - 6 will display as black).
   */
  public static final Color[] COLORS = {
      Color.RED,
      Color.GREEN,
      Color.CYAN,
      Color.YELLOW,
      Color.ORANGE,
      Color.MAGENTA,
      Color.BLUE
  };
  
  /**
   * Background color.
   */
  public static final Color BACKGROUND_COLOR = Color.GRAY;
  
  public static final String[] TEST1 = 
    {
      "3 3 2 1 0",
      "3 3 0 0 1",
      "2 2 3 2 0"
    };
  
  /**
   * Helper method for instantiating the components.  This
   * method should be executed in the context of the Swing
   * event thread only.
   */
  private static void create()
  {
    // create a game with 5 types of icons
    //Game game = new Game(10, 8, new JewelFactory(5));

    // create a very small test game
    Game game = new Game(TEST1, new JewelFactory(4));
    
    // create the two UI panels
    ScorePanel scorePanel = new ScorePanel();
    GamePanel panel = new GamePanel(game, scorePanel);
    
    // arrange the two panels horizontally
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.LINE_AXIS));
    mainPanel.add(scorePanel);
    mainPanel.add(panel);
    
    // put main panel in a window
    JFrame frame = new JFrame("Com S 227 Jewels Game");
    frame.getContentPane().add(mainPanel);

    // give panels a nonzero size
    Dimension d = new Dimension(game.getWidth() * GameMain.SIZE, game.getHeight() * GameMain.SIZE);   
    panel.setPreferredSize(d);
    scorePanel.setPreferredSize(d);
    frame.pack();
    
    // we want to shut down the application if the 
    // "close" button is pressed on the frame
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     
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
