package api;

/**
 * Abstraction of a generator for game pieces in a Tetris-like
 * video game.  
 */
public interface Generator
{
  /**
   * Returns a new Shape instance according to this generator's 
   * strategy.
   * @param width
   *   the width the game grid
   * @return 
   *   a new Shape 
   */
  Shape getNext(int width);
}
