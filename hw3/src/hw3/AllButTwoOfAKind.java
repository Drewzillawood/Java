package hw3;

/**
 * Scoring category for (N-2) of a kind. A hand with N dice satisfies this
 * category only if at least N - 2 of the values are the same. For a hand that
 * satisfies this category, the score the sum of all die values; otherwise the
 * score is zero.
 */
public class AllButTwoOfAKind extends SUPER {

	/**
	 * Constructs an AllButTwoOfAKind with the given display name and score.
	 * 
	 * @param displayName
	 *            name of this category
	 */
	public AllButTwoOfAKind(String displayName) {

		super(displayName);

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

		// Retrieve values from the current hand
		int[] hand = dice.getAll();
		int checker = 0;
		int counter = 1;

		// Iterate through every value in the array, compare it to every value
		// in the array including itself
		for (int i = 0; i < dice.getNumDice(); i++) {

			for (int j = 0; j < dice.getNumDice(); j++) {

				// If values are identical, iterate our counter
				if (hand[i] == hand[j]) {

					counter++;

				}

			}
			
			// Accounting for counting of itself, assign our higher counter value
			if (checker < counter - 1) {

				checker = counter - 1;

			}

			// Reset the counter for the next value in the array
			counter = 1;

		}

		// If we have found at least the number of occurrences specified
		if (checker >= (dice.getNumDice() - 2)) {

			// Then we have ALLButTwoOfAKind
			return true;

		} else {

			// Otherwise, the condition is not satisfied
			return false;

		}

	}

}
