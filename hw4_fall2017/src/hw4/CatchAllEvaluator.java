package hw4;

import api.Card;

/**
 * Evaluator satisfied by any set of cards. The number of main cards is equal to
 * the total cards.
 * 
 * The name of this evaluator is "Catch All".
 */
// Note: You must edit this declaration to extend AbstractEvaluator
// or to extend some other class that extends AbstractEvaluator
public class CatchAllEvaluator extends AbstractEvaluator
{
	/**
	 * Constructs the evaluator.
	 * 
	 * @param ranking
	 *            ranking of this type of hand
	 * @param handSize
	 *            total number of cards in a hand
	 */
	public CatchAllEvaluator(int ranking, int handSize)
	{
		super(ranking, handSize, "Catch All", handSize);
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
		return mainCards.length == numMainCards();
	}

}
