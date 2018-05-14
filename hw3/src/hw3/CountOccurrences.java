package hw3;

/**
 * Scoring category that is based on counting occurrences of a particular target
 * value (specified in the constructor). This category is satisfied by any hand.
 * The score is the sum of just the die values that match the target value.
 * 
 * @author drewu
 */
public class CountOccurrences extends SUPER {

	/**
	 * Keeps track of the specified value in our constructor
	 */
	private int targetValue;

	/**
	 * Constructs a CountOccurrences category with the given display name and
	 * target value.
	 * 
	 * @param displayName
	 *            name for this category
	 * @param whichValue
	 *            target value that must be matched for a die to count toward
	 *            the score
	 */
	public CountOccurrences(String displayName, int whichValue) {

		super(displayName);
		targetValue = whichValue;

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
	@Override
	public int getPotentialScore(Hand dice) {

		// As long as the category has been satisfied
		if (isSatisfiedBy(dice)) {

			// Local variable declaration
			int[] checker = dice.getAll();
			int potentialScore = 0;

			// Count the number of occurrences of the specified value
			// have been found in this hand
			for (int i = 0; i < checker.length; i++) {

				// For each occurrence, add onto our total score
				if (checker[i] == targetValue) {

					potentialScore += targetValue;

				}

			}

			return potentialScore;

		} else {

			return 0;

		}

	}

}
