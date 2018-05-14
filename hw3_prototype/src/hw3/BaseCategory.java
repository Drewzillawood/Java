package hw3;

import hw3.api.Category;

/**
 * Abstract base class containing attributes and operations that
 * are common to all score categories.
 */
public abstract class BaseCategory implements Category
{
  /**
   * The dice group that is set for this category (initially null).
   */
  private Hand diceGroup;
  
  /**
   * The display name for this category.
   */
  private String name;
  
  /**
   * Constructs a BaseCategory with the given display name.
   * @param name
   *   display name for this category
   */
  protected BaseCategory(String name)
  {
    this.name = name;
  }
  
  @Override
  public boolean isFilled()
  {
    return diceGroup != null;
  }

  @Override
  public int getScore()
  {
    if (diceGroup == null)
    {
      return 0;
    }
    else
    {
      return getPotentialScore(diceGroup);
    }
  }

  @Override
  public Hand getHand()
  {
    return diceGroup;
  }

  @Override
  public String getDisplayName()
  {
    return name;
  }

  @Override
  public void fill(Hand dice)
  {
    if (isFilled() || !dice.isComplete())
    {
      throw new IllegalStateException();
    }
    diceGroup = dice;
  }

}
