package hw3;

/**
 * Scoring category for a "small straight". A hand with N dice satisfies this
 * category only if it includes N - 1 distinct consecutive values. For a hand
 * that satisfies this category, the score is a fixed value specified in the
 * constructor; otherwise, the score is zero.
 * 
 * @author drewu
 */
public class SmallStraight extends AllOfAKind {

	/**
	 * Constructs a SmallStraight category with the given display name and
	 * score.
	 * 
	 * @param displayName
	 *            name of this category
	 * @param points
	 *            points awarded for a dice group that satisfies this category
	 */
	public SmallStraight(String displayName, int points) {

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

		// Local Variable declaration
		int[] hand = dice.getAll();
		int counter = 1;
		
		// Start from the second array value
		for(int i = 1; i < hand.length; i++) {
			
			// If the value before it is not exactly (n-1)
			if(hand[i] != hand[i-1]+1) {
				
				// If the value before it is the same
				if(hand[i] == hand[i-1]) {
					
					// Decrement the count
					counter--;
					
				} else {
					
					// Reset the Count
					counter = 1;
					
				}
				
			} else {
				
				// Iterate our count
				counter++;
				
				// As long as the count is at least length-2, the we have a small straight
				if(counter >= hand.length-2) {
					
					return true;
					
				}
				
			}
			
		}
		
		return false; 
		
	}
	
}
