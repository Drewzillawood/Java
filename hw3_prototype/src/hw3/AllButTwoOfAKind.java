package hw3;

/**
 * Scoring category for (N-2) of a kind.  A hand
 * with N dice satisfies this category only if at least N - 2 of
 * the values are the same.  For a hand that satisfies
 * this category, the score the sum of all die values;
 * otherwise the score is zero.
 */
public class AllButTwoOfAKind extends DiceSumCategory
{
  /**
   * Constructs an AllButTwoOfAKind with the given display name.
   * @param displayName
   *   name of this category
   */  
  public AllButTwoOfAKind(String name)
  {
    super(name);
  }
  
  @Override
  public boolean isSatisfiedBy(Hand dice)
  {   
    // Dice are sorted, so either
    //  - they're all the same except the last two, or
    //  - they're all the same except the first and last, or
    //  - they're all the same except the first two
    int[] values = dice.getAll();
    if (values.length <= 3)
    {
      return true;
    }
    int end = values.length - 1;
    return (values[0] == values[end - 2] ||
            values[1] == values[end - 1] ||
            values[2] == values[end]);
  }
}
