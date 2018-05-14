package hw3;

/**
 * Scoring category that is based on counting occurrences
 * of a particular target value (specified in the constructor).
 * This category is satisfied by any hand.  The score
 * is the sum of just the die values that match the target value.
 */
public class CountOccurrences extends BaseCategory
{
  /**
   * Target value to count occurrences of.
   */
  private int number;
  
  /**
   * Constructs a CountOccurrences category with the given display name and 
   * target value.
   * @param displayName
   *   name for this category
   * @param whichValue
   *   target value that must be matched for a die to count toward the
   *   score
   */
  public CountOccurrences(String name, int number)
  {
    super(name);
    this.number = number;
  } 
  
  @Override
  public boolean isSatisfiedBy(Hand group)
  {
    return true;
  }

  @Override
  public int getPotentialScore(Hand group)
  {
    // adds up all the die values that match the target
    int total = 0;
    int[] values = group.getAll();
    for (int i = 0; i < values.length; ++i)
    {
      if (values[i] == number)
      {
        total += values[i];
      }
    }
    return total;
  }
}
