package api;

/**
 * An IEvaluator is a utility for evaluating groups of cards in 
 * card games based on ranking of hands.  Each evaluator 
 * represents a type of hand based on some evaluation criteria,
 * such as pairs of matching cards or numerical runs of cards.  
 * An evaluator also has a <em>ranking</em> relative to other evaluators
 * that might be used in a given game. In traditional poker games,
 * ranking of hands is based on mathematical probability, but 
 * there is no particular requirement here other than that
 * different evaluators should be constructed with different rankings.
 * <p>
 * Within a given ranking the hands are also ordered.  The ordering
 * is defined by the Hand class.  Each hand consists of an array of 
 * <em>main</em> cards and a (possibly empty) array of <em>side cards</em>.
 * The Hand class orders hands within the same ranking by first
 * lexicographically comparing the main cards, and if they are the same,
 * lexicographically comparing the side cards.  Therefore, it is 
 * up to the evaluator to correctly order the cards in these groups.
 * The side cards can always be sorted according to the Card class
 * compareTo method, which orders cards in descending order with 
 * higher cards first.  In most cases it is sufficient to sort the main cards
 * using the Card class; however, this may not work for particular 
 * evaluators. It is up to the evaluator's createHand method to
 * ensure that the main and side cards are correctly ordered.
 */
public interface IEvaluator
{
  /**
   * Returns a name for this evaluator.
   * @return
   *   name of this evaluator
   */
  String getName();
  
  /**
   * Returns the ranking for this evaluator, where a lower number
   * is considered "better".
   * @return
   *   ranking for this evaluator
   */
  int getRanking();
  
  /**
   * Returns the number of main cards needed to satisfy this
   * evaluator.
   * @return
   */
  int numMainCards();
  
  /**
   * Returns the total number of cards in a hand satisfying this evaluator
   * (main cards plus side cards).  This value 
   * is generally determined by the type of game being played, and
   * may be larger than the value returned by <code>cardsRequired</code>.
   * (main cards plus side cards).
   * @return
   *   number of cards in a hand
   */
  int totalCards();
  
  /**
   * Determines whether the given group of cards satisfies the
   * criteria for this evaluator.  The length
   * of the given array must exactly match the value 
   * returned by <code>numMainCards()</code>.  The caller must ensure
   * that the given array is sorted with highest-ranked card first
   * according to <code>Card.compareTo()</code>.  The array
   * is not modified by this operation.
   * @param mainCards
   *   array of cards
   * @return
   *   true if the given cards satisfy this evaluator
   */
  boolean satisfiedBy(Card[] mainCards);
  
  /**
   * Determines whether there exists a subset of the given cards
   * that satisfies the criteria for this evaluator.  The length of
   * the given array must be greater than or equal to the value
   * returned by <code>numMainCards()</code>. The caller must ensure
   * that the given array is sorted with highest-ranked card first
   * according to <code>Card.compareTo()</code>.  The array
   * is not modified by this operation.
   * @param allCards
   *   list of cards to evaluate
   * @return
   *   true if some subset of the given cards satisfy this evaluator
   */
  boolean canSubsetSatisfy(Card[] allCards);
  
  /**
   * Returns a hand whose main cards consist of the indicated subset
   * of the given cards.  If the indicated subset does
   * not satisfy the criteria for this evaluator, this
   * method returns null. The subset is described as
   * an ordered array of indices to be selected from the given
   * Card array.  The number of main cards in the hand
   * will be the value of <code>numMainCards()</code>
   * and the total number of cards in the hand will
   * be the value of <code>totalCards()</code>.  If the length
   * of the given array of cards is not equal to <code>totalCards()</code>,
   * this method returns null.   The
   * given array must be sorted with highest-ranked card first
   * according to <code>Card.compareTo()</code>.  The array
   * is not modified by this operation.
   * 
   * @param allCards
   *   list of cards from which to select the main cards for the hand
   * @param subset
   *   list of indices of cards to be selected, in ascending order
   * @return
   *   hand whose main cards consist of the indicated subset, or null
   *   if the indicated subset does not satisfy this evaluator
   */
   Hand createHand(Card[] allCards, int[] subset);
  
  /**
   * Returns the best possible hand satisfying this evaluator's 
   * criteria that can be formed from the given list of cards.
   * "Best" is defined to be first according to the compareTo() method of 
   * Hand.  Returns null if there is no such hand.  The number of main cards 
   * in the hand will be the value of <code>numMainCards()</code>.
   * If the length
   * of the given array of cards is less than <code>totalCards()</code>,
   * this method returns null.  The
   * given array must be sorted with highest-ranked card first
   * according to <code>Card.compareTo()</code>.  The array
   * is not modified by this operation.
   *  
   * @param allCards
   *   list of cards from which to create the hand
   * @return
   *   best possible hand satisfying this evaluator that can be formed
   *   from the given cards
   */
  Hand createBestHand(Card[] allCards);
}
