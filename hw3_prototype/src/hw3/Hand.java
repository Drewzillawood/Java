  package hw3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 * This class represents values of a group of dice for a dice game such as Yahtzee in which 
 * multiple rolls per turn are allowed. The number of faces on the dice, 
 * the number of dice in the Hand, and the maximum number of rolls are configurable 
 * via the constructor. At any time some of the dice may be <em>available</em>
 * to be rolled, and the other dice are <em>fixed</em>.  Calls to the 
 * <code>roll()</code> method will select new, random values for the available
 * dice only.  After the maximum number of rolls, all dice are automatically
 * fixed; before that, the client can select which dice to "keep" (change from
 * available to fixed) and which dice to "free" (change from fixed to
 * available).
 * <p>
 * Note that valid die values range from 1 through the given
 * <code>maxValue</code>. 
 */
public class Hand
{
  /**
   * List of available die values in ascending order.
   */
  private ArrayList<Integer> available;
  
  /**
   * List of fixed die values in ascending order.
   */
  private ArrayList<Integer> fixed;
  
  /**
   * Number of dice.
   */
  private final int numDice;
  
  /**
   * Maximum die value.
   */
  private final int maxValue;
  
  /**
   * Maximum number of rolls.
   */
  private final int maxRolls;
  
  /**
   * Number of times roll() has been called.
   */
  private int rollCount;
  
  /**
   * Constructs a new Hand in which each die initially has 
   * the (invalid) value zero. 
   * @param numDice
   *   number of dice in this group
   * @param maxValue
   *   largest possible die value, where values range from 1
   *   through <code>maxValue</code>
   * @param maxRolls
   *   maximum number of total rolls
   */
  public Hand(int numDice, int maxValue, int maxRolls)
  {
    this(numDice, maxValue, maxRolls, new int[]{});
  }   
  
  /**
   * Constructs a new Hand in which each die initially has 
   * the value given by the <code>initialValues</code> array.
   * If the length of the array is greater than the number of dice, the
   * extra values are ignored.  If the length of the array is smaller
   * than the number of dice, remaining dice
   * will be initialized to the (invalid) value 0.
   * <p>
   * This version of the constructor is primarily intended for testing.
   * @param numDice
   *   number of dice in this hand
   * @param maxValue
   *   largest possible die value, where values range from 1
   *   through <code>maxValue</code>
   * @param maxRolls
   *   maximum number of total rolls
   * @param initialValues
   *   initial values for the dice
   */
  public Hand(int numDice, int maxValue, int maxRolls, int[] initialValues)
  {
    this.numDice = numDice;
    this.maxValue = maxValue;
    this.maxRolls = maxRolls;
    rollCount = 0;
    available = new ArrayList<Integer>();
    fixed = new ArrayList<Integer>();
    for (int i = 0; i < numDice; ++i)
    {
      if (i < initialValues.length)
      {
        available.add(initialValues[i]);
      }
      else
      {
        // fill in with zeros if not enough values are supplied
        available.add(0);
      }
    }
    Collections.sort(available);
  }  
  
  /**
   * Returns the number of dice in this hand.
   * @return
   *   number of dice in this hand
   */
  public int getNumDice()
  {
    return numDice;
  }
  
  /**
   * Returns the maximum die value in this hand.
   * Valid values start at 1.
   * @return
   *   maximum die value
   */
  public int getMaxValue()
  {
    return maxValue;
  }
  
  /**
   * Rolls all available dice; that is, each available
   * die value in this hand is replaced by a randomly generated
   * value produced by the given random number generator.
   * @param rand
   *   random number generator to be used for rolling dice
   */
  public void roll(Random rand)
  {
    for (int i = 0; i < available.size(); ++i)
    {
      available.set(i, rand.nextInt(maxValue) + 1);
    }
    Collections.sort(available);
    
    rollCount += 1;
    if (rollCount >= maxRolls)
    {
      for (int value : available)
      {
        fixed.add(value);
      }
      available.clear();
      Collections.sort(fixed);
    }
  }

  /**
   * Selects a die value to be moved from the available dice to the
   * fixed dice. Has no effect if the given value is 
   * not among the values in the available dice.  Has no effect if
   * the number of rolls has reached the maximum.
   * @param value
   *   die value to be moved from available to fixed
   */
  public void keep(int value)
  {
    int index = available.indexOf(value);
    if (index >= 0)
    {
      available.remove(index);
      fixed.add(value);    
      Collections.sort(fixed);
    }
  }

  /**
   * Selects a die value to be moved from the fixed dice to
   * the available dice, so it will be re-rolled in the
   * next call to <code>roll()</code>. Has no effect if the given value is 
   * not among the values in the fixed dice. Has no effect if
   * the number of rolls has reached the maximum.
   * @param value
   *   die value to be moved
   */
  public void free(int value)
  {
    if (rollCount >= maxRolls)
    {
      return;
    }
    int index = fixed.indexOf(value);
    if (index >= 0)
    {
      fixed.remove(index);
      available.add(value);    
      Collections.sort(available);
    }
  }
  
  /**
   * Causes all die values be moved from the available dice to the
   * fixed dice. Has no effect if
   * the number of rolls has reached the maximum.
   */
  public void keepAll()
  {
    fixed.addAll(available);
    available.clear();
    Collections.sort(fixed);
  }
  
  /**
   * Causes all die values be moved from the fixed dice to the
   * available dice. Has no effect if
   * the number of rolls has reached the maximum.
   */
  public void freeAll()
  {
    if (rollCount >= maxRolls)
    {
      return;
    }
    available.addAll(fixed);
    fixed.clear();
    Collections.sort(available);
  }
  
  /**
   * Determines whether there are any dice available to be 
   * rolled in this hand.
   * @return
   *   true if there are no available dice, false otherwise
   */
  public boolean isComplete()
  {
    return available.size() == 0;
  }

  /**
   * Returns the values of the dice that are currently fixed (not
   * available to be rerolled) in ascending order.
   * @return
   *   values of the dice that are currently fixed
   */
  public int[] getFixedDice()
  {
    int[] ret = new int[fixed.size()];
    for (int i = 0; i < fixed.size(); ++i)
    {
      ret[i] = fixed.get(i);
    }
    return ret;
  }
  
  /**
   * Returns the values of the dice that are currently available to
   * be rerolled by a subsequent call to <code>roll()</code>,
   * in ascending order.
   * @return
   *   dice that are available to be rerolled
   */
  public int[] getAvailableDice()
  {
    int[] ret = new int[available.size()];
    for (int i = 0; i < available.size(); ++i)
    {
      ret[i] = available.get(i);
    }
    return ret;
  }
 
  /**
   * Returns all die values in this hand, in ascending order.
   * @return
   *   all die values in this hand
   */
  public int[] getAll()
  {
    int[] ret = new int[numDice];
    int i = 0;
    for (int value : available)
    {
      ret[i++] = value;
    }
    for (int value : fixed)
    {
      ret[i++] = value;
    }
    Arrays.sort(ret);
    return ret;
  }
  
  /**
   * Returns a string representation of the die values in
   * this hand, in the form <em>available</em>(<em>fixed</em>).
   * (For example, if there are two fixed dice with values 2
   * and 4, and there are 3 available dice with values 1, 1, and 6,
   * then the method returns the string
   * <pre>
   * 1 1 6 (2 4)
   * </pre>
   * If all dice are available, the parentheses should be empty.
   * @return 
   *   string representation of the available and completed dice,
   *   with the completed values in parentheses
   */
  @Override
  public String toString()
  {
    StringBuilder sb = new StringBuilder();
    int[] availableDice = getAvailableDice();
    for (int value : availableDice)
    {
      sb.append(value + " ");
    }

    sb.append("(");
    int[] fixedDice = getFixedDice();
    if (fixedDice.length > 0)
    {
      // use an index so we can add the first one without a leading space
      sb.append(fixedDice[0]);
      for (int i = 1; i < fixedDice.length; ++i)
      {
        sb.append(" " + fixedDice[i]);
      }
    }
    sb.append(")");
    return sb.toString();
  }
  
}
