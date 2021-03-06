package hw3;

/**
 * Scoring category for a "large straight". A hand with N dice satisfies this
 * category only if there are N distinct consecutive values. For a hand that
 * satisfies this category, the score is a fixed value specified in the
 * constructor; otherwise, the score is zero.
 * 
 * @author drewu
 */
public class LargeStraight extends AllOfAKind {
	/**
	 * Constructs a LargeStraight category with the given display name and
	 * score.
	 * 
	 * @param displayName
	 *            name of this category
	 * @param points
	 *            points awarded for a hand that satisfies this category
	 */
	public LargeStraight(String displayName, int points) {

		super(displayName, points);

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
	@Override
	public boolean isSatisfiedBy(Hand dice) {

		// Local Variable Declaration
		int[] hand = dice.getAll();

		// Iterate through every die value in our hand,
		// starting at the second array value
		for (int i = 1; i < hand.length; i++) {

			// If the value before the current value is not
			// exactly (n-1) 
			if (hand[i] != hand[i - 1] + 1) {

				// No large straight
				return false;

			}

		}

		// Otherwise we have a large straight
		return true;

	}

}
