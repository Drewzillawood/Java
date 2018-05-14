package hw3;

/**
 * Abstract class for categories whose score is a fixed value
 * awarded when the category is satisfied.
 */
public abstract class FixedScoreCategory extends BaseCategory
{
  /**
   * Points awarded when this category is satisfied.
   */
  private final int points;
  
  /**
   * Constructs a FixedScoreCategory with the given display name
   * and point value.
   * @param name
   *   display name for this category
   * @param points
   *   score given when this category is satisfied
   */
  protected FixedScoreCategory(String name, int points)
  {
    super(name);
    this.points = points;
  }
  
  @Override
  public int getPotentialScore(Hand dice)
  {
    if (isSatisfiedBy(dice))
    {
      return points;
    }
    else
    {
      return 0;
    }
  }
}
