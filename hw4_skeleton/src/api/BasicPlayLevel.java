package api;

/**
 * Minimal implementation of PlayLevel interface speeds up the
 * game as the score increases.
 */
public class BasicPlayLevel implements PlayLevel
{
  private int[] scores = {10, 20, 30, 50};
  private int[] speeds = {800, 600, 400, 200, 100};
  private int[] fastDropSpeeds = {80, 60, 40, 20, 5};
  
  @Override
  public int fastDropSpeed(int score)
  {
    int i = 0;
    while (i < scores.length && score >= scores[i])
    {
      ++i;
    }
    // here score < scores[i]
    return fastDropSpeeds[i];
  }

  @Override
  public int speed(int score)
  {
    int i = 0;
    while (i < scores.length && score >= scores[i])
    {
      ++i;
    }
    // score < scores[i]
    return speeds[i];
  }

}
