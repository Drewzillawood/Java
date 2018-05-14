package hw4;

import java.util.ArrayList;

import api.Card;
import api.Hand;
import api.IEvaluator;
import api.SubsetFinder;
import api.Util;

/**
 * The class AbstractEvaluator includes common code for all evaluator types.
 * 
 * TODO: Expand this comment with an explanation of how your class hierarchy is
 * organized.
 */
public abstract class AbstractEvaluator implements IEvaluator
{
	/**
	 * Rank of this evaluation
	 */
	private int	rank;

	/**
	 * Size of the hand being evaluated
	 */
	private int	sizeOfHand;
	
	/**
	 * Name of evaluator
	 */
	private String evalName;
	
	/**
	 * Number of main cards to satisfy this evaluator
	 */
	private int mainCards;

	/**
	 * Constructs the evaluator.
	 * 
	 * @param ranking
	 *            ranking of this type of hand
	 * @param handSize
	 *            total number of cards in a hand
	 */
	protected AbstractEvaluator(int ranking, int handSize, String name, int main)
	{
		rank = ranking;
		sizeOfHand = handSize;
		evalName = name;
		mainCards = main;
	}
	
	/**
	 * Returns a name for this evaluator.
	 * 
	 * @return name of this evaluator
	 */
	public String getName()
	{
		return evalName;
	}
	
	/**
	 * Returns the number of main cards needed to satisfy this evaluator.
	 * 
	 * @return number of main cards needed to satisfy this evaluator
	 */
	public int numMainCards()
	{
		return mainCards;
	}
	
	/**
	 * Returns the ranking for this evaluator, where a lower number is
	 * considered "better".
	 * 
	 * @return ranking for this evaluator
	 */
	public int getRanking()
	{
		return rank;
	}
	
	/**
	 * Returns the total number of cards in a hand satisfying this evaluator
	 * (main cards plus side cards). This value is generally determined by the
	 * type of game being played, and may be larger than the value returned by
	 * cardsRequired. (main cards plus side cards).
	 * 
	 * @return number of cards in a hand
	 */
	public int totalCards()
	{
		return sizeOfHand;
	}
	
	/**
	 * Determines whether there exists a subset of the given cards that
	 * satisfies the criteria for this evaluator. The length of the given array
	 * must be greater than or equal to the value returned by numMainCards().
	 * The caller must ensure that the given array is sorted with highest-ranked
	 * card first according to Card.compareTo(). The array is not modified by
	 * this operation.
	 * 
	 * @param allCards
	 *            - list of cards to evaluate
	 * 
	 * @return true if some subset of the given cards satisfy this evaluator
	 */
	public boolean canSubsetSatisfy(Card[] allCards)
	{
		if (allCards == null)
			return false;

		ArrayList<Card[]> allSubsets = SubsetFinder.findSubsets(allCards, numMainCards());
		for (Card[] set : allSubsets)
		{
			if (satisfiedBy(set))
				return true;
		}
		return false;
	}
	
	/**
	 * Returns a hand whose main cards consist of the indicated subset of the
	 * given cards. If the indicated subset does not satisfy the criteria for
	 * this evaluator, this method returns null. The subset is described as an
	 * ordered array of indices to be selected from the given Card array. The
	 * number of main cards in the hand will be the value of numMainCards() and
	 * the total number of cards in the hand will be the value of totalCards().
	 * If the length of the given array of cards is less than totalCards(), this
	 * method returns null. The given array must be sorted with highest-ranked
	 * card first according to Card.compareTo(). The array is not modified by
	 * this operation.
	 * 
	 * @param allCards
	 *            - list of cards from which to select the main cards for the
	 *            hand
	 * @param subset
	 *            - list of indices of cards to be selected, in ascending order
	 * 
	 * @return hand whose main cards consist of the indicated subset, or null if
	 *         the indicated subset does not satisfy this evaluator
	 */
	public Hand createHand(Card[] allCards, int[] subset)
	{
		// Less cards than needed for Evaluator
		if (allCards.length < totalCards())
			return null;

		// Subset retrieved from allCards
		Card[] main = Util.getCardSubset(allCards, subset);

		// If subset does not satisfy this evaluator
		if (!satisfiedBy(main))
			return null;

		// Sub cards
		Card[] sub = Util.getCardNonSubset(allCards, subset);

		// Retrieve an initial hand to compare
		ArrayList<Card[]> allSubsets = SubsetFinder.findSubsets(sub, totalCards() - main.length);
		Hand hand = new Hand(main, allSubsets.get(0), getName(), getRanking());

		// Find the best hand (Best set of sub cards to match main)
		for (Card[] set : allSubsets)
		{
			// If hand is a worse hand than h, replace it with h
			Hand h = new Hand(main, set, getName(), getRanking());
			if (h.compareTo(hand) < 0)
				hand = h;
		}

		return hand;
	}
	
	/**
	 * Returns the best possible hand satisfying this evaluator's criteria that
	 * can be formed from the given list of cards. "Best" is defined to be first
	 * according to the compareTo() method of Hand. Returns null if there is no
	 * such hand. The number of main cards in the hand will be the value of
	 * numMainCards(). If the length of the given array of cards is less than
	 * totalCards(), this method returns null. The given array must be sorted
	 * with highest-ranked card first according to Card.compareTo(). The array
	 * is not modified by this operation.
	 * 
	 * @param allCards
	 *            - list of cards from which to create the hand
	 * 
	 * @return best possible hand satisfying this evaluator that can be formed
	 *         from the given cards
	 */
	public Hand createBestHand(Card[] allCards)
	{
		if (allCards.length < totalCards())
			return null;

		// All main and side subsets
		ArrayList<Card[]> mains = SubsetFinder.findSubsets(allCards, numMainCards());
		ArrayList<Card[]> sides = SubsetFinder.findSubsets(allCards, totalCards() - numMainCards());

		// Find first main subset (if there is one)
		Card[] fMain = findFirstMain(mains);
		// Ensure first side subset is disjoint
		Card[] fSide = findFirstSide(sides, fMain);

		if (fMain == null)
			return null;

		// Default hand
		Hand hand = new Hand(fMain, fSide, getName(), getRanking());

		// Find best main and Side subset
		bestMain(hand, mains, hand.getSideCards());
		bestSide(hand, hand.getMainCards(), sides);

		return hand;
	}

	/**
	 * Retrieve first available main subset
	 * 
	 * @param mains
	 *            - all main subsets
	 * 
	 * @return first main subset, or null if there is not one
	 */
	private Card[] findFirstMain(ArrayList<Card[]> mains)
	{
		for (int i = 0; i < mains.size(); i++)
			if (satisfiedBy(mains.get(i)))
				return mains.get(i);

		return null;
	}

	/**
	 * Finds first available side subset (checks disjointedness with main)
	 * 
	 * @param sides
	 *            - list of subsets
	 * @param main
	 *            - current main subset
	 *            
	 * @return first available subset
	 */
	private Card[] findFirstSide(ArrayList<Card[]> sides, Card[] main)
	{
		for (int i = 0; i < sides.size(); i++)
			if (areDisjoint(sides.get(i), main))
				return sides.get(i);

		return null;
	}

	/**
	 * Find the best possible main subset
	 * 
	 * @param currentBestHand
	 *            - best hand as of now (in terms of main subsets)
	 * @param mains
	 *            - set of all main subsets
	 * @param sides
	 *            - the currently assigned side subset
	 */
	private void bestMain(Hand currentBestHand, ArrayList<Card[]> mains, Card[] side)
	{
		for (Card[] mSet : mains)
		{
			Hand h = new Hand(mSet, side, getName(), getRanking());
			if (h.compareTo(currentBestHand) < 0)
				currentBestHand = h;
		}
	}

	/**
	 * Find the best possible side subset
	 * 
	 * @param currentBestHand
	 *            - best hand as of now (in terms of side subsets)
	 * @param mains
	 *            - the currently assigned main subset
	 * @param sides
	 *            - set of all side subsets
	 */
	private void bestSide(Hand currentBestHand, Card[] main, ArrayList<Card[]> sides)
	{
		for (Card[] sSet : sides)
		{
			Hand h = new Hand(main, sSet, getName(), getRanking());
			if (h.compareTo(currentBestHand) < 0)
				currentBestHand = h;
		}
	}

	/**
	 * Helper method to ensure two sets are disjoin
	 * 
	 * @param c1
	 *            - first set
	 * @param c2
	 *            - second set
	 * @return true if the sets are disjoint
	 */
	private boolean areDisjoint(Card[] c1, Card[] c2)
	{
		for (Card card_1 : c1)
			for (Card card_2 : c2)
				if (card_1.equals(card_2))
					return false;

		return true;
	}
}
