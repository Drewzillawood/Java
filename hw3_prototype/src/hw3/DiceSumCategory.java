package hw3;

/**
 * Abstract class for categories whose score is determined
 * by adding up all the die values (provided that the 
 * category is satisfied).
 */
public abstract class DiceSumCategory extends BaseCategory
{
  /**
   * Constructs a DiceSumCategory with the given display name.
   * @param name
   *   display name for this category
   */
  public DiceSumCategory(String name)
  {
    super(name);
  }
  
  @Override
  public int getPotentialScore(Hand dice)
  {
    if (isSatisfiedBy(dice))
    {
      int total = 0;
      int[] values = dice.getAll();
      for (int i = 0; i < values.length; ++i)
      {
        total += values[i];
      }
      return total;
    }
    else
    {
      return 0;
    }
  }
}
