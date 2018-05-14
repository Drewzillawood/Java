package hw4;

import api.Card;

/**
 * Evaluator for a hand that contains at least one card from each suit. The
 * number of main cards is four.
 * 
 * The name of this evaluator is "All Suits".
 */
// Note: You must edit this declaration to extend AbstractEvaluator
// or to extend some other class that extends AbstractEvaluator
public class AllSuitsEvaluator extends AbstractEvaluator
{
	/**
	 * Constructs the evaluator.
	 * 
	 * @param ranking
	 *            ranking of this type of hand
	 * @param handSize
	 *            total number of cards in a hand
	 */
	public AllSuitsEvaluator(int ranking, int handSize)
	{
		super(ranking, handSize, "All Suits", 4);
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
		return mainCards.length == numMainCards() && 
			   mainCards[0].getSuit() == mainCards[1].getSuit() &&
			   mainCards[0].getSuit() == mainCards[2].getSuit() &&
			   mainCards[0].getSuit() == mainCards[3].getSuit();
	}
	
}
