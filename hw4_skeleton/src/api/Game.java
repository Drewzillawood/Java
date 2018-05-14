package api;

import java.util.List;

/**
 * Interface for a Tetris-like game. Implementations of this 
 * interface should maintain all aspects of the game state.  The state
 * consists of a grid of Block objects representing occupied 
 * and unoccupied positions, plus a current Shape whose position is 
 * normally updated by the step() method and can be altered by the 
 * transform(), shiftDown(), shiftLeft(), and shiftRight() methods.
 * An additional part of the game state is the status that is returned by 
 * each invocation of the step() method.  The status values are described 
 * in detail in the GameStatus documentation.
 */
public interface Game
{
  /**
   * Transition the game through one discrete step.  A step may consist of
   * <ul>
   *   <li>shifting the current shape down by one cell
   *   <li>changing the status to COLLAPSING when when the current shape cannot
   *       be dropped further and completes a collapsible set (e.g.,
   *       in a standard Tetris game, a collapsible set would be a
   *       completed horizontal row)
   *   <li>changing the status to STOPPED when the current shape cannot
   *       be dropped further, but does not complete a collapsible set
   *   <li>changing the status to NEW_SHAPE when a new shape 
   *       is started at the top of the grid
   *   <li>deleting all cells to be collapsed
   *   <li>changing the status to GAME_OVER when a new shape collides with
   *       occupied positions in the top row
   * </ul>
   * @return the game status after the step
   */
  GameStatus step();
  
  /**
   * Performs a transform() operation on the current shape if it is 
   * possible to do so without letting it extend beyond the sides or 
   * bottom of the grid and without colliding with occupied cells in the grid.
   * 
   * @return true if the current shape was moved, false otherwise
   */
  boolean transform();
  
  /**
   * Performs a cycle() operation on the current shape. 
   */
  void cycle();
  
  /**
   * Shifts the current shape one cell to the left (decreasing the column), 
   * if it is possible to do so without letting it extend beyond the sides or 
   * bottom of the grid and without colliding with occupied cells in the grid.
   * 
   * @return true if the current shape was moved, false otherwise
   */ 
  boolean shiftLeft();
  
  /**
   * Shifts the current shape one cell to the right (increasing the column), 
   * if it is possible to do so without letting it extend beyond the sides or 
   * bottom of the grid and without colliding with occupied cells in the grid.
   * 
   * @return true if the current shape was moved, false otherwise
   */   
  boolean shiftRight();
  
  /**
   * Returns the block associated with the given row and column, or null if
   * the location is unoccupied. 
   * @param row 
   *   the row of the cell
   * @param col 
   *   the column of the cell
   * @return the block associated with the given row/column in the grid 
   */
  Block getBlock(int row, int col);
  
  /**
   * Returns the current shape.
   * @return 
   *   the current shape.
   * @throws IllegalStateException if the game status is COLLAPSING or GAME_OVER
   */
  Shape getCurrent();
  
  /**
   * Returns the width of the grid.
   * @return 
   *   the width of the grid.
   */
  int getWidth();  
  
  /**
   * Returns the height of the grid.
   * @return 
   *   the height of the grid.
   */
  int getHeight();
  
  /**
   * Returns the grid positions to be collapsed.  This method can only be
   * called when the game state is COLLAPSING. The returned list is in no
   * particular order and may contain duplicates. (The purpose of this method is
   * to allow clients to apply special rendering or animation to the 
   * collapsing positions.)
   * @return 
   *   list of Position objects representing the the grid positions to be 
   *   collapsed
   * @throws IllegalStateException if the game status is not COLLAPSING
   */
  List<Position> getPositionsToCollapse();
  
  
  /**
   * Returns the current score for this game.
   * @return 
   *   the current score for this game
   */
  Long getScore();
  
  /**
   * Determines whether the game is over, which occurs when a new
   * shape in its initial position collides with occupied cells
   * of the grid.
   * @return 
   *   true if the game is over, false otherwise
   */
  boolean gameOver();
}
