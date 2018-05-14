import java.util.Arrays;

import api.Cell;
import api.Position;
import api.Shape;
import hw4.OShape;

/**
 * Sample test code for Shape rotation, shift, and cycle.
 * The comments in the code below specifically refer to 
 * testing the LShape, but you can adapt the code to test any shape.
 */
public class ExampleShapeTests
{
  /**
   * Helper method to create a shape at the given position 
   * for testing.  EDIT THIS CODE FOR THE SHAPE YOU WANT TO TEST.
   */
  private static Shape makeShape(int row, int col, boolean magic)
  {
    //return new LShape(new Position(row, col), magic);
    return new OShape(new Position(row, col), magic);
  }
  
  public static void main(String[] args)
  {
    Shape shape = makeShape(0, 0, false);
    Cell[] cells = shape.getCells();
    
    // expected [(0, 0), (1, -2), (1, -1), (1, 0)]
    System.out.println(Arrays.toString(cells));
    
    // check the color of the blocks?
    for (Cell c : cells)
    {
      System.out.println(c.getBlock());
    }
    System.out.println();
    
    shape.shiftDown();
    // expected [(1, 0), (2, -2), (2, -1), (2, 0)]
    System.out.println("After shiftDown");
    System.out.println(Arrays.toString(shape.getCells()));
    System.out.println();
        
    // rotation about (0, 0)
    //    [(0, 0), (1, -2), (1, -1), (1, 0)]
    //    [(0, 0), (2, 1), (1, 1), (0, 1)]
    //    [(0, 0), (-1, 2), (-1, 1), (-1, 0)]
    //    [(0, 0), (-2, -1), (-1, -1), (0, -1)]    
    System.out.println("Rotations about (0, 0)");
    shape = makeShape(0, 0, false);
    System.out.println(Arrays.toString(shape.getCells()));
    shape.transform();
    System.out.println(Arrays.toString(shape.getCells()));
    shape.transform();
    System.out.println(Arrays.toString(shape.getCells()));
    shape.transform();
    System.out.println(Arrays.toString(shape.getCells()));
    System.out.println();
    
    // rotation about (2, 3)
    //    [(2, 3), (3, 1), (3, 2), (3, 3)]
    //    [(2, 3), (4, 4), (3, 4), (2, 4)]
    //    [(2, 3), (1, 5), (1, 4), (1, 3)]
    //    [(2, 3), (0, 2), (1, 2), (2, 2)]
    System.out.println("Rotations about (2, 3)");
    shape = makeShape(2, 3, false);
    System.out.println(Arrays.toString(shape.getCells()));
    shape.transform();
    System.out.println(Arrays.toString(shape.getCells()));
    shape.transform();
    System.out.println(Arrays.toString(shape.getCells()));
    shape.transform();
    System.out.println(Arrays.toString(shape.getCells()));
    System.out.println();
    
    // try cycling - the asterisk means that cell is "magic"
    //    [(0, 0)*, (1, -2), (1, -1), (1, 0)]
    //    [(0, 0), (1, -2)*, (1, -1), (1, 0)]
    //    [(0, 0), (1, -2), (1, -1)*, (1, 0)]
    //    [(0, 0), (1, -2), (1, -1), (1, 0)*]
    //    [(0, 0)*, (1, -2), (1, -1), (1, 0)]
    System.out.println("cycle() with a magic block");
    shape = makeShape(0, 0, true);
    System.out.println(Arrays.toString(shape.getCells()));
    shape.cycle();
    System.out.println(Arrays.toString(shape.getCells()));
    shape.cycle();
    System.out.println(Arrays.toString(shape.getCells()));
    shape.cycle();
    System.out.println(Arrays.toString(shape.getCells()));
    shape.cycle();
    System.out.println(Arrays.toString(shape.getCells()));
    System.out.println();
    
    // check the clone method...
    shape = makeShape(0, 0, false);
    Shape copy = shape.clone();
    System.out.println(copy.getClass());  // should be LShape
    copy.shiftDown();
    
    // after moving the copy, original should be unmodified
    // expected [(0, 0), (1, -2), (1, -1), (1, 0)]
    System.out.println(Arrays.toString(shape.getCells()));
  }
}
