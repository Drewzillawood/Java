package hw4;

import api.Card;

/**
 * Evaluator for a hand in which the rank of each card is a prime number. The
 * number of main cards required is equal to the total cards.
 * 
 * The name of this evaluator is "All Primes".
 */
// Note: You must edit this declaration to extend AbstractEvaluator
// or to extend some other class that extends AbstractEvaluator
public class AllPrimesEvaluator extends AbstractEvaluator
{
	/**
	 * Constructs the evaluator.
	 * 
	 * @param ranking
	 *            ranking of this type of hand
	 * @param handSize
	 *            total number of cards in a hand
	 */
	public AllPrimesEvaluator(int ranking, int handSize)
	{
		super(ranking, handSize, "All Primes", handSize);
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
		for (Card c : mainCards)
			if (!isPrime(c.getRank()))
				return false;

		return mainCards.length == numMainCards();
	}

	/**
	 * Checks to see if a number is prime or not
	 * 
	 * @param n
	 *            - integer value
	 * 
	 * @return true if the number is prime
	 */
	private boolean isPrime(int n)
	{
		// check if n is a multiple of 2
		if (n % 2 == 0)
			return false;
		// if not, then just check the odds
		for (int i = 3; i * i <= n; i += 2)
		{
			if (n % i == 0)
				return false;
		}
		return true;
	}

}
