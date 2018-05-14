package api;

/**
 * An IPlayLevel is an abstraction of various attributes of
 * a level of difficulty for a Tetris-like video game.
 */
public interface PlayLevel
{
  /**
   * Returns the time in milliseconds between frames or 
   * steps of the game.  This value may depend on the current
   * score.
   * @param currentScore current score for the game
   * @return time in milliseconds
   */
  int speed(int currentScore);
  
  /**
   * Time in milliseconds between frames or steps of the game
   * when the dropping of a shape is accelerated. This value 
   * may depend on the current score.
   * @param currentScore current score for the game
   * @return time in milliseconds
   */
  int fastDropSpeed(int currentScore);
  
}
