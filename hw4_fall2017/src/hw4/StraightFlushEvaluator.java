package hw4;

import api.Card;

/**
 * Evaluator for a hand consisting of a "straight" in which the card ranks are
 * consecutive numbers AND the cards all have the same suit. The number of main
 * cards is equal to the total cards. An ace (card of rank 1) may be treated as
 * the highest possible card or as the lowest (not both) To evaluate a straight
 * containing an ace it is necessary to know what the highest card rank will be
 * in a given game; therefore, this value must be specified when the evaluator
 * is constructed. In a hand created by this evaluator the cards are listed in
 * descending order with high card first, e.g. [10 9 8 7 6] or [A K Q J 10],
 * with one exception: In case of an ace-low straight, the ace must appear last,
 * as in [5 4 3 2 A]
 * 
 * The name of this evaluator is "Straight Flush".
 */
// Note: You must edit this declaration to extend AbstractEvaluator
// or to extend some other class that extends AbstractEvaluator
public class StraightFlushEvaluator extends AbstractEvaluator
{
	/**
	 * Constructs the evaluator. Note that the maximum rank of the cards to be
	 * used must be specified in order to correctly evaluate a straight with ace
	 * high.
	 * 
	 * @param ranking
	 *            ranking of this type of hand
	 * @param handSize
	 *            total number of cards in a hand
	 * @param maxCardRank
	 *            largest rank of any card to be used
	 */
	public StraightFlushEvaluator(int ranking, int handSize, int maxCardRank)
	{
		super(ranking, handSize, "Straight Flush", handSize);
	}

	/**
	 * Determines whether the given group of cards satisfies the criteria for
	 * this evaluator. The length of the given array must exactly match the
	 * value returned by numMainCards(). The caller must ensure that the given
	 * array is sorted with highest-ranked card first according to
	 * Card.compareTo(). The array is not modified by this operation.
	 * 
	 * @param mainCards
	 *            - array of cards
	 * 
	 * @return true if the given cards satisfy this evaluator
	 */
	public boolean satisfiedBy(Card[] mainCards)
	{
		return false;
	}

}
