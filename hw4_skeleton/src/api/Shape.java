package api;

/**
 * Interface for shapes used by Tetris-like games.  Each shape
 * has a position and, implicitly, a set of Cells. The initial position and cell 
 * locations are assigned in a constructor, and thereafter the 
 * position can be modified by the shiftXXX() methods. The constructor also
 * establishes a relative ordering of the cells.  The getCells() method always returns the
 * cells in this ordering, and the cycle() method always uses this ordering.
 * No bounds checking is ever done in implementations of this interface; 
 * therefore, the position and the cell positions can have negative coordinates.  
 */
public interface Shape extends Cloneable
{
  /**
   * Returns a new array of Cell objects representing the blocks 
   * in this shape along with their absolute positions.
   * (Note that modifications to the array or Cell objects returned
   * by this method should NOT affect this shape.)
   * @return 
   *   the cells occupied by this shape
   */
  Cell[] getCells();
  
  /**
   * Shifts the position of this shape down (increasing the row) 
   * by one.  No bounds checking is done.
   */
  void shiftDown();
  
  /**
   * Shifts the position of this shape left (decreasing the column) 
   * by one.  No bounds checking is done.
   */
  void shiftLeft();
  
  /**
   * Shifts the position of this shape right (increasing the column) 
   * by one.  No bounds checking is done. 
   */  
  void shiftRight();
  
  /**
   * Transforms this shape without altering its position
   * according to the rules of the game to be implemented.  
   * Typical operations are rotation and reflection. 
   * No bounds checking is done.
   */    
  void transform();
  
  /**
   * Cycles the blocks within the cells of this shape.  Each 
   * block is shifted forward to the next cell (in the original ordering
   * of the cells).  The last block wraps around to the first cell.  
   */
  void cycle();
  
  /**
   * Returns a deep copy of this object having the correct runtime type.
   * @return 
   *   a deep copy of this object
   */
  Shape clone();
}
