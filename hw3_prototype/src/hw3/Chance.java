package hw3;

/**
 * Scoring category that is satisfied by any hand.
 * The score is the sum of all die values.
 */
public class Chance extends DiceSumCategory
{
  /**
   * Constructs a Chance category with the given display name.
   * @param displayName
   *   name for this category
   */
  public Chance(String name)
  {
    super(name);
  }

  @Override
  public boolean isSatisfiedBy(Hand group)
  {
    return true;
  }
}
