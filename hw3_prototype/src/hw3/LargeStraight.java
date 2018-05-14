package hw3;

/**
 * Scoring category for a "large straight".  A hand
 * with N dice satisfies this category only if there are
 * N distinct consecutive values.  For a hand that satisfies
 * this category, the score is a fixed value specified in the constructor;
 * otherwise, the score is zero.
 */
public class LargeStraight extends SmallStraight
{
  /**
   * Constructs a LargeStraight category with the given display name
   * and score.
   * @param displayName
   *   name of this category
   * @param points
   *   points awarded for a hand that satisfies this category
   */  
  public LargeStraight(String name, int points)
  {
    super(name, points);
  }

  @Override
  public boolean isSatisfiedBy(Hand dice)
  {
    int[] values = dice.getAll();
        
    // a large straight is just a small straight in which
    // the range of values is equal to the number of dice. That is,
    // the difference between the first and last values, plus 1, 
    // is equal to the number of dice. 
    int diff = values[values.length - 1] - values[0] + 1;
    return super.isSatisfiedBy(dice) && diff == values.length;
  }

}
