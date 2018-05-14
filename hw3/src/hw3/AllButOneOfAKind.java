package hw3;

/**
 * Scoring category for (N-1) of a kind. A hand with N dice satisfies this
 * category only if at least N - 1 of the values are the same. For a hand that
 * satisfies this category, the score the sum of all die values; otherwise the
 * score is zero.
 */
public class AllButOneOfAKind extends SUPER {
	
	/**
	 * Constructs an AllButOneOfAKind category with the given display name and
	 * score.
	 * 
	 * @param displayName
	 *            name of this category
	 */
	public AllButOneOfAKind(String displayName) {

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
		
		// Local Variable Declaration
		int[] hand = dice.getAll();
		int checker = 0;
		int counter = 1;

		// Compare each value in the array, to every value in the array, including itself
		for (int i = 0; i < dice.getNumDice(); i++) {

			for (int j = 0; j < dice.getNumDice(); j++) {

				if (hand[i] == hand[j]) {

					counter++;

				}

			}

			// Account for count of itself, and if we found more counts of a variable
			// than before, assign our new highest count
			if (checker < counter - 1) {

				checker = counter - 1;

			}

			counter = 1;

		}

		// As long as the count found is at least AllButOneOfAKind, then 
		// the category has been satisfied
		if (checker >= (dice.getNumDice() - 1)) {

			return true;

		} else {

			return false;

		}

	}

}
