package hw3;

/**
 * Scoring category for a "yahtzee". A hand with N dice satisfies this category
 * only if all N values are the same. For a hand that satisfies this category,
 * the score is a fixed value specified in the constructor; otherwise, the score
 * is zero.
 * 
 * @author drewu
 */
public class AllOfAKind extends SUPER {
	
	/**
	 * Holds onto the specified point value for this category
	 */
	private int pointsValue;
	
	/**
	 * Constructs an AllOfAKind with the given display name and score.
	 * 
	 * @param displayName
	 *            name of this category
	 * @param points
	 *            points awarded for a hand that satisfies this category
	 */
	public AllOfAKind(String displayName, int points) {

		super(displayName);
		pointsValue = points;

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

		// Local variable declaration
		int[] hand = dice.getAll();
		
		// If any value is not equal to the first value
		for(int i = 0; i < dice.getNumDice(); i++) {
			
			// We do not have a AllOfAKind
			if(hand[0] != hand[i]) {
				
				return false;
				
			}
			
		}
		
		// Otherwise return true
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
	@Override
	public int getPotentialScore(Hand dice) {

		// As long as the category has been satisfied
		if (isSatisfiedBy(dice)) {

			// Return specified score
			return pointsValue;
			
		} else {
			
			return 0;
			
		}

	}
}
