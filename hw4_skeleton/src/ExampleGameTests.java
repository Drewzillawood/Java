import java.awt.Color;
import java.util.List;

import api.AbstractGame;
import api.Block;
import api.Position;
import hw4.MagicTetris;

public class ExampleGameTests
{
  public static void main(String[] args)
  {
    MagicTetris game = new MagicTetris(4, 6);
    List<Position> result = game.determinePositionsToCollapse();
    System.out.println(result);  // should be empty
    
    // fill some cells to try out, see method below
    setUpExample(game, false);
    
    // print it out, should be:
    //    - - - -
    //    - x - x
    //    - - - -
    //    x - x -
    //    x x x x
    //    x x x -
    printGrid(game);
    
    // try our method
    result = game.determinePositionsToCollapse();
    System.out.println(result);  // expected [(4, 0), (4, 1), (4, 2), (4, 3)]
    System.out.println();
    
    // try collapsing, should get:
    //    - - - -
    //    - - - -
    //    - x - x
    //    - - - -
    //    x - x -
    //    x x x -
    game.collapsePositions(result);
    printGrid(game);
    
    // since we aren't in gravity mode, next call should return empty list
    result = game.determinePositionsToCollapse();
    System.out.println(result);  // expected []
    System.out.println();
    
    // try out gravity mode, put three magic blocks in completed row
    //    - - - -
    //    - x - x
    //    - - - -
    //    x - x -
    //    * x * *
    //    x x x -
    System.out.println("Try gravity mode (no collapse)");
    game = new MagicTetris(4, 6);
    setUpExample(game, true);
    printGrid(game);
    
    // this should put us in "gravity" mode
    result = game.determinePositionsToCollapse();
    System.out.println(result); // [(4, 0), (4, 1), (4, 2), (4, 3)]
    
    // Now in gravity mode, this call finds empty positions below nonempty ones
    // (notice we haven't collapsed anything!)
    result = game.determinePositionsToCollapse();
    System.out.println(result);  // [(2, 1), (3, 1), (2, 3), (3, 3), (5, 3)]

    // Now should be out of gravity mode, we just get row 4 again
    result = game.determinePositionsToCollapse();
    System.out.println(result);  // [(4, 0), (4, 1), (4, 2), (4, 3)]
    System.out.println();
    
    // try the same thing, with collapsing
    System.out.println("Try gravity mode (with collapse)");
    game = new MagicTetris(4, 6);
    setUpExample(game, true);
    result = game.determinePositionsToCollapse();
    game.collapsePositions(result);
    printGrid(game);
    result = game.determinePositionsToCollapse();
    System.out.println(result);  // [(3, 1), (4, 1), (3, 3), (4, 3), (5, 3)]
    System.out.println();
    
    game.collapsePositions(result);
    printGrid(game);
   
    // Now should be out of gravity mode, get bottom row
    result = game.determinePositionsToCollapse();
    System.out.println(result);  // [(5, 0), (5, 1), (5, 2), (5, 3)]
    System.out.println();
    
    // check score
    System.out.println("Check score update");
    game = new MagicTetris(4, 6);
    setUpExample(game, true);
    result = game.determinePositionsToCollapse();
    game.collapsePositions(result);
    
    // should be 8 (3 magic blocks, so we get 2 to power 3)
    System.out.println(game.getScore()); 
        
    // after collapsing, in gravity mode, the score doesn't change
    result = game.determinePositionsToCollapse();    
    System.out.println(game.getScore()); // still expected 8
    game.collapsePositions(result);
    
    // we get one point for the bottom row, since it has no magic blocks
    result = game.determinePositionsToCollapse();    
    System.out.println(game.getScore()); // expected 9
  }
  
  public static void printGrid(AbstractGame game)
  {
    final String fmt = "%2s";
    for (int row = 0; row < game.getHeight(); row += 1)
    {
      for (int col = 0; col < game.getWidth(); col += 1)
      {
        Block b = game.getBlock(row, col);
        String s;
        if (b == null)
        {
          s = "-";
        }
        else if (b.isMagic())
        {
          s = "*";
        }
        else
        {
          s = "x";
        }
        System.out.printf(fmt, s);
      }
      System.out.println();
    }
  }
  
  private static void setUpExample(MagicTetris game, boolean addMagic)
  {
    Block b = new Block(Color.RED, false);
    game.setBlock(5, 0, b);
    game.setBlock(5, 1, b);
    game.setBlock(5, 2, b);
    
    game.setBlock(4, 0, b);
    game.setBlock(4, 1, b);
    game.setBlock(4, 2, b);
    game.setBlock(4, 3, b);
    
    game.setBlock(3, 0, b);
    game.setBlock(4, 1, b);
    game.setBlock(3, 2, b);
    game.setBlock(4, 3, b);
    
    game.setBlock(1, 1, b);
    game.setBlock(1, 3, b);    
    
    if (addMagic)
    {
      Block b2 = new Block(Color.RED, true);  // magic
      game.setBlock(4, 0, b2);
      game.setBlock(4, 2, b2);
      game.setBlock(4, 3, b2);
    }
  }
}
