package hw3;

/**
 * Scoring category for a generalized full house. A hand with N dice satisfies
 * this category only in the following cases: If N is even, there are two
 * different values, each occurring exactly N/2 times. If N is odd, there are
 * two different values, one of them occurring N/2 times and the other occurring
 * N/2 + 1 times. For a hand that satisfies this category, the score is a fixed
 * value specified in the constructor; otherwise, the score is zero.
 */
public class FullHouse extends AllOfAKind {
	
	/**
	 * Constructs a FullHouse category with the given display name and score.
	 * 
	 * @param displayName
	 *            name of this category
	 * @param points
	 *            points awarded for a hand that satisfies this category
	 */
	public FullHouse(String displayName, int points) {
		
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
		int nO2 = hand.length/2;
		int nO2P1 = (hand.length/2) + 1;
		int marker = 0;
		int first[];
		int second[];
		
		// If hand is an even number of dice
		if(hand.length % 2 == 0) {
			
			// Declare two arrays, each half the size of the hand
			first = new int[nO2];
			second = new int[nO2];
			
			// Iterate through the first and second half of the hand values
			for(int i = 0, j = nO2-1; i < nO2-1; i++) {
				
				// If any values are not identical to the initial values of the 
				// respective first and second halves of the hand, then we do not
				// have a full house
				if(hand[i] != hand[0] || hand[j] != hand[nO2-1]) {
					
					return false;
					
				}
				
			}
			
			// Otherwise it is indeed a full house
			return true;
		
		// If hand is an odd number of dice
		} else if(hand.length % 2 == 1) {
			
			// Find the point where values are no longer similar
			for(int i = 0; i < hand.length; i++) { 
				
				// Once values are no longer similar, hold onto the index
				if(hand[i] != hand[0]) { 
					
					marker = i;
					break;
					
				}
				
			}
			
			// Create two arrays, one which holds values before the marker
			first = new int[marker];
			
			// The second holds all values after the marker, inclusive
			second = new int[(hand.length)-marker];
			
			// If neither of the array lengths do not equate to length/2 or length/2 + 1 then there is no full house
			if((first.length != nO2 && first.length != nO2P1) || (second.length != nO2 && second.length != nO2P1)) {
				
				return false;
				
			} else {
				
				// Otherwise we have a full house
				return true;
				
			}
			
		} 
		
		return true;

	}

}
