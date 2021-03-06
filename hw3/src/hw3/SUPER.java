package hw3;

import java.util.stream.IntStream;

import hw3.api.Category;

/**
 * The SUPER class is a SuperClass which sits at the 
 * top of the inheritance Hierarchy for the Category interface. 
 * 
 * @author drewu
 */
public class SUPER implements Category {

	/**
	 * Keeps track of the name of the category
	 */
	private String catName;

	/**
	 * Keeps track of the current hand in use
	 */
	private Hand draw;

	/**
	 * Keeps track of the score for this category
	 */
	private int score;

	/**
	 * SuperClass constructor, default initialization for a category given their
	 * displayName
	 * 
	 * @param displayName
	 */
	public SUPER(String displayName) {

		catName = displayName;
		score = 0;

	}

	/**
	 * Determines whether this category is filled.
	 * 
	 * @return true if this category has a fixed hand and score, false otherwise
	 */
	public boolean isFilled() {

		// Check that the score for this category is not zero, and is non-null
		if (getScore() != 0 && getHand() != null) {

			// If so, then return true
			return true;

		}

		// Otherwise false
		return false;

	}

	/**
	 * Returns the score for this category, or zero if the category is not
	 * <em>filled</em>.
	 * 
	 * @return score for the category or zero if not filled
	 */
	public int getScore() {

		return score;

	}

	/**
	 * Returns the hand that was used to fill this category, or null if not
	 * <em>filled</em>.
	 * 
	 * @return hand filling this category, or null if not filled
	 */
	public Hand getHand() {

		return draw;

	}

	/**
	 * Returns the name for this category.
	 * 
	 * @return name for this category
	 */
	public String getDisplayName() {

		return catName;

	}

	/**
	 * Permanently sets the hand being used to fill this category. The score is
	 * set to the value of <code>getPotentialScore</code> for the given hand.
	 * Throws <code>IllegalStateException</code> if the category has already
	 * been filled or if the given hand is not <em>complete</em> (as defined by
	 * the <code>Hand.isComplete</code> method).
	 * 
	 * @param dice
	 *            hand to be used to satisfy this category
	 * @throws IllegalStateException
	 *             if the given hand is not <em>complete</em> (according to the
	 *             <code>isComplete()</code> method of Hand) or if this category
	 *             is already filled
	 */
	public void fill(Hand dice) throws IllegalStateException {

		// If the hand is filled, or the hand is not complete
		if (isFilled() || !dice.isComplete()) {

			// Throw exception
			throw new IllegalStateException();

		}

		// Otherwise, set the score to potential score
		score = getPotentialScore(dice);
		// Set the hand permanently for this category
		draw = dice;

	}

	/**
	 * Determines whether the given hand satisfies the defined criteria for this
	 * scoring category. The criteria are determined by the concrete type
	 * implementing the interface. This method does not modify the state of this
	 * category.
	 * 
	 * @param dice
	 *            hand to check
	 * @return true if the given hand satisfies the defined criteria for the
	 *         category, false otherwise
	 */
	public boolean isSatisfiedBy(Hand dice) {

		return true;

	}

	/**
	 * Returns the potential score that would result from using the given hand
	 * to fill this category. Always returns zero if the
	 * <code>isSatisfiedBy()</code> method returns false for the given hand.
	 * This method does not modify the state of this category.
	 * 
	 * @param dice
	 *            hand to check
	 * @return potential score for the given hand
	 */
	public int getPotentialScore(Hand dice) {

		// If the category is satisfied
		if (isSatisfiedBy(dice)) {

			// Initialize local variables
			int potentialScore = 0;
			int[] values = dice.getAll();

			// Sum up all the values of the array
			potentialScore = IntStream.of(values).sum();

			return potentialScore;

		} else {

			return 0;

		}

	}

}
