package hw3;

/**
 * Scoring category for a generalized full house.  A hand
 * with N dice satisfies this category only in the following cases:
 * If N is even, there are two different values, each occurring exactly N/2 times.
 * If N is odd, there are two different values, one of them occurring N/2 times and
 * the other occurring N/2 + 1 times.  For a hand that satisfies
 * this category, the score is a fixed value specified in the constructor;
 * otherwise, the score is zero.
 */
public class FullHouse extends FixedScoreCategory
{
  /**
   * Constructs a FullHouse category with the given display name
   * and score.
   * @param displayName
   *   name of this category
   * @param points
   *   points awarded for a hand that satisfies this category
   */  
  public FullHouse(String name, int points)
  {
    super(name, points);
  }

  @Override
  public boolean isSatisfiedBy(Hand dice)
  {
    int[] values = dice.getAll();
    if (values.length <= 2)
    {
      return true;
    }
    
    int mid = values.length / 2;
    int end = values.length - 1;

    // Dice are sorted, so check the following:
    //   - all values up to the middle are the same
    //   - all values to the right of the middle are the same
    //   - if length is odd, middle value matches either the left half or the right half
    if (values.length % 2 == 0)
    {
      return sameNumber(values, 0, mid - 1) && 
             sameNumber(values, mid, end);
    }
    else
    {
      return sameNumber(values, 0, mid - 1) && 
             sameNumber(values, mid + 1, end) &&
             (values[mid] == values[mid - 1] ||
              values[mid] == values[mid + 1]);
    }
  }
  
  /**
   * Helper method determines whether all values in the sorted array at
   * start through end are the same.
   * @param arr
   *   given sorted array
   * @param start
   *   starting index
   * @param end
   *   ending index
   * @return
   *   true if arr[start] through arr[end] are the same
   */
  private boolean sameNumber(int[] arr, int start, int end)
  {
    return arr[start] == arr[end];
  }

}
