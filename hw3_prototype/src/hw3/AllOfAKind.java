package hw3;

/**
 * Scoring category for a "yahtzee".  A hand
 * with N dice satisfies this category only if all N
 * values are the same.  For a hand that satisfies
 * this category, the score is a fixed value specified in the constructor;
 * otherwise, the score is zero.
 */
public class AllOfAKind extends FixedScoreCategory
{
  /**
   * Constructs an AllOfAKind with the given display name
   * and score.
   * @param displayName
   *   name of this category
   * @param points
   *   points awarded for a hand that satisfies this category
   */  
  public AllOfAKind(String name, int points)
  {
    super(name, points);
  }
  
  @Override
  public boolean isSatisfiedBy(Hand dice)
  {   
    // Dice are sorted, so they're the same if 
    // first and last are the same
    int[] values = dice.getAll();
    return values[0] == values[values.length - 1];  
  }
}
