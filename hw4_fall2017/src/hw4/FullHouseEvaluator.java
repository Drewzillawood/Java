package hw4;

import api.Card;

/**
 * Evaluator for a generalized full house. The number of main cards is equal to
 * the total cards. If the total cards is an odd number n, then there must be (n
 * / 2) + 1 cards of matching rank and the remaining (n / 2) cards must be of
 * matching rank. In this case, when creating a hand, <strong>the larger group
 * must be listed first even if of lower rank than the smaller group</strong>
 * (e.g. as [3 3 3 5 5] rather than [5 5 3 3 3]). If the hand size is even, then
 * half the cards must be of matching rank and the remaining half of matching
 * rank. A group of cards of the same rank always satisfies this evaluator.
 * 
 * The name of this evaluator is "Full House".
 */
// Note: You must edit this declaration to extend AbstractEvaluator
// or to extend some other class that extends AbstractEvaluator
public class FullHouseEvaluator extends AbstractEvaluator
{
	/**
	 * Constructs the evaluator.
	 * 
	 * @param ranking
	 *            ranking of this type of hand
	 * @param handSize
	 *            total number of cards in a hand
	 */
	public FullHouseEvaluator(int ranking, int handSize)
	{
		super(ranking, handSize, "Full House", handSize);
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

		for (int i = 0, j = mainCards.length / 2; j < mainCards.length - 1; i++, j++)
		{
			if (mainCards[i].compareToIgnoreSuit(mainCards[i + 1]) != 0 || 
				mainCards[j].compareToIgnoreSuit(mainCards[j + 1]) != 0)
					return false;
		}
		
		if(mainCards.length % 2 == 1 && 
		   mainCards[mainCards.length/2] != 
		   mainCards[mainCards.length/2 + 1])
					return false;

		return mainCards.length == numMainCards();
	}
}
