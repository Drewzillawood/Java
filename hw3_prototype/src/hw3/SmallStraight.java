package hw3;

/**
 * Scoring category for a "small straight".  A hand
 * with N dice satisfies this category only if it includes
 * N - 1 distinct consecutive values.  For a hand that satisfies
 * this category, the score is a fixed value specified in the constructor;
 * otherwise, the score is zero.
 */
public class SmallStraight extends FixedScoreCategory
{
  /**
   * Constructs a SmallStraight category with the given display name
   * and score.
   * @param displayName
   *   name of this category
   * @param points
   *   points awarded for a dice group that satisfies this category
   */  
  public SmallStraight(String name, int points)
  {
    super(name, points);
  }
  
  @Override
  public boolean isSatisfiedBy(Hand dice)
  {
    int[] values = dice.getAll();
    return maxRunLength(values) >= values.length - 1;
  }
  
  /**
   * Helper method returns the length of the longest run
   * of sequential values in a sorted array, where duplicates
   * are allowed.
   * @param values
   *   sorted array
   * @return
   *   length of longest run of sequential values
   */
  protected int maxRunLength(int[] values)
  {
    // iterate over the dice, adding 1 to count if value is previous
    // value plus 1, restarting count at 1 if value is greater than
    // previous value plus 1, and doing nothing if values are the same
    int count = 1;
    int maxCount = 1;
    for (int i = 1; i < values.length; ++i)
    {
      if (values[i] == values[i - 1] + 1)
      {
        count += 1;
        if (count > maxCount)
        {
          maxCount = count;
        }
      }
      else if (values[i] > values[i - 1] + 1)
      {
        // start over
        count = 1;
      }
    }
    
    return maxCount;
  }
}
