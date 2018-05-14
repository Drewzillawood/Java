package api;
import java.util.Arrays;

/**
 * A Hand represents a collection of cards satisfying some
 * criteria defined by an instance of <code>IEvaluator</code>.
 * A hand has zero or more <em>main cards</em> that determine
 * whether it satisfies the evaluator's criteria, plus zero
 * or more <em>side cards</em> that are irrelevant to the
 * evaluator's criteria, but may be used to determine a winning
 * hand when the main cards are the same (i.e. to break ties).
 * 
 * This class is immutable.
 */
public class Hand implements Comparable<Hand>
{
  /**
   * Main cards for this hand.
   */
  private final Card[] mainCards;
  
  /**
   * Side cards for this hand.
   */
  private final Card[] sideCards;
  
  /**
   * Name for this hand (name of the evaluator used to construct it).
   */
  private final String name;
  
  /**
   * Ranking for this hand (as given by the evaluator used to construct it).
   */
  private final int ranking;

  /**
   * Constructs a Hand with the given main cards and side cards.
   * The ordering of the two arrays is not changed.  This constructor
   * copies the two arrays and does not retain a reference to them.
   * @param mainCards
   *   main cards for the hand
   * @param sideCards
   *   side cards for the hand
   * @param name
   *   name for this hand (normally determined by an IEvaluator)
   * @param ranking
   *   ranking for this hand (normally determined by an IEvaluator)
   */
  public Hand(Card[] mainCards, Card[] sideCards, String name, int ranking)
  {
    this.mainCards = mainCards;
    this.sideCards = sideCards;
    this.name = name;
    this.ranking = ranking;
  }
  
  /**
   * Constructs a new Hand from the given cards, using the given array of 
   * indices to determine which are the main cards.  The relative ordering of 
   * cards is not changed (regardless of the ordering of the indices).
   *   
   * @param allCards
   *   array of all cards for this hand
   * @param indices
   *   indices of the main cards
   * @param evaluator
   *   reference to an IEvaluator whose criteria are satisfied by this hand
   * @throws IllegalArgumentException
   *   if the number of main cards is incorrect or the number of
   *   side cards is insufficient
   */
  public Hand(Card[] allCards, int[] indices, String name, int ranking)
  {
    // call other constructor
    this(Util.getCardSubset(allCards, indices),
         Util.getCardNonSubset(allCards, indices),
         name,
         ranking);
  }
  

  /**
   * Returns the ranking of this hand according to the evaluator
   * used in its construction.
   * @return
   *   ranking of this hand
   */
  public int getRanking()
  {
    return ranking;
  }
  
  /**
   * Returns the name of this hand according to the evaluator 
   * used in its construction.
   * @return
   *   name of this hand
   */
  public String getName()
  {
    return name;
  }
  
  /**
   * Returns a copy of the main cards for this hand.
   * @return
   *   main cards for this hand
   */
  public Card[] getMainCards()
  {
    return Arrays.copyOf(mainCards, mainCards.length);
  }
  
  /**
   * Returns a copy of the side cards for this hand.
   * @return
   *   side cards for this hand
   */
  public Card[] getSideCards()
  {
    return Arrays.copyOf(sideCards, sideCards.length);
  }
  
  /**
   * Returns a string representation of this object.
   */
  public String toString()
  {
    StringBuilder sb = new StringBuilder();
    sb.append(getName());
    sb.append(" (");
    sb.append(getRanking());
    sb.append(") [");
    if (mainCards.length > 0)
    {
      sb.append(mainCards[0].toString());
      for (int i = 1; i < mainCards.length; ++i)
      {
        sb.append(" ");
        sb.append(mainCards[i].toString());
      }
    }
    if (sideCards.length > 0)
    {
      sb.append(" :");
      for (int i = 0; i < sideCards.length; ++i)
      {
        sb.append(" ");
        sb.append(sideCards[i].toString());
      }
    }
    
    sb.append("]");
    return sb.toString();
  }
  
  @Override
  public int compareTo(Hand other)
  {
    if (getRanking() != other.getRanking())
    {
      return getRanking() - other.getRanking();
    } 
    
    int c = compareCardArrays(mainCards, other.mainCards);
    if (c != 0)
    {
      return c;
    }
    return compareCardArrays(sideCards, other.sideCards);
  }
  
  /**
   * Lexicographically compares two arrays of cards having
   * the same length, ignoring suits.
   * @param lhs
   * @param rhs
   * @return
   */
  private int compareCardArrays(Card[] lhs, Card[] rhs)
  {
    if (lhs.length != rhs.length)
    {
      throw new IllegalArgumentException();
    }
        
    // Lexicographic ordering, note both should be the same length
    for (int i = 0; i < lhs.length; ++i)
    {
      int comp = lhs[i].compareToIgnoreSuit(rhs[i]);
      if (comp != 0)
      {
        return comp;
      }
    }
    return 0;
  }

}
