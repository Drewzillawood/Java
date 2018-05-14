package hw3;

/**
 * Scoring category for (N-1) of a kind.  A hand
 * with N dice satisfies this category only if at least N - 1 of
 * the values are the same.  For a hand that satisfies
 * this category, the score the sum of all die values;
 * otherwise the score is zero.
 */
public class AllButOneOfAKind extends DiceSumCategory
{
  /**
   * Constructs an AllButOneOfAKind category with the given display name.
   * @param displayName
   *   name of this category
   */
  public AllButOneOfAKind(String name)
  {
    super(name);
  }
  
  @Override
  public boolean isSatisfiedBy(Hand dice)
  {   
    // Dice are sorted, so either they're all the same except
    // the last one, or else all the same except the first one
    int[] values = dice.getAll();
    if (values.length <= 2)
    {
      return true;
    }
    int end = values.length - 1;
    return (values[0] == values[end - 1] ||
            values[1] == values[end]);
  }
}
