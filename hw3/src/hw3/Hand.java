package hw3;

import java.util.Arrays;
import java.util.Random;

/**
 * This class represents values of a group of dice for a dice game such as
 * Yahtzee in which multiple rolls per turn are allowed. The number of faces on
 * the dice, the number of dice in the Hand, and the maximum number of rolls are
 * configurable via the constructor. At any time some of the dice may be
 * <em>available</em> to be rolled, and the other dice are <em>fixed</em>. Calls
 * to the <code>roll()</code> method will select new, random values for the
 * available dice only. After the maximum number of rolls, all dice are
 * automatically fixed; before that, the client can select which dice to "keep"
 * (change from available to fixed) and which dice to "free" (change from fixed
 * to available).
 * <p>
 * Note that valid die values range from 1 through the given
 * <code>maxValue</code>.
 * 
 * @author drewu
 */
public class Hand {
	
	/**
	 * Integer array to keep track of all dice valuesavailable to be rolled
	 */
	private int[] free;

	/**
	 * Integer array to keep track of all dice values which are fixed
	 */
	private int[] fixed;

	/**
	 * Integer value representing the maximum dice value
	 */
	private int max;
	
	/**
	 * Integer value representing the highest number of rolls allowed in a round
	 */
	private int maxRoll;

	/**
	 * Number of rolls taken for this round
	 */
	private int rolls;

	/**
	 * Number of dice to be used for this hand
	 */
	private int numberOfDice;

	/**
	 * Constructs a new Hand in which each die initially has the (invalid) value
	 * zero.
	 * 
	 * @param numDice
	 *            number of dice in this group
	 * @param maxValue
	 *            largest possible die value, where values range from 1 through
	 *            <code>maxValue</code>
	 * @param maxRolls
	 *            maximum number of total rolls
	 */
	public Hand(int numDice, int maxValue, int maxRolls) {
		
		// Instance variable initialization
		numberOfDice = numDice;
		free = new int[numDice];
		fixed = new int[0];
		max = maxValue;
		maxRoll = maxRolls;
		rolls = 0;
		
		// Each die value is to be defaulted to 0
		for (int i = 0; i < numDice; i++) {

			free[i] = 0;

		}

	}

	/**
	 * Constructs a new Hand in which each die initially has the value given by
	 * the <code>initialValues</code> array. If the length of the array is
	 * greater than the number of dice, the extra values are ignored. If the
	 * length of the array is smaller than the number of dice, remaining dice
	 * will be initialized to the (invalid) value 0.
	 * <p>
	 * This version of the constructor is primarily intended for testing.
	 * 
	 * @param numDice
	 *            number of dice in this group
	 * @param maxValue
	 *            largest possible die value, where values range from 1 through
	 *            <code>maxValue</code>
	 * @param maxRolls
	 *            maximum number of total rolls
	 * @param initialValues
	 *            initial values for the dice
	 */
	public Hand(int numDice, int maxValue, int maxRolls, int[] initialValues) {

		// Instance variable initialization
		numberOfDice = numDice;
		free = new int[numDice];
		fixed = new int[0];
		max = maxValue;
		maxRoll = maxRolls;
		rolls = 0;
		
		// Assigning designated values
		for (int i = 0; i < numDice; i++) {

			free[i] = initialValues[i];

		}
		
		// With non-zero values, we will need to sort the array in ascending order
		Arrays.sort(free);

	}

	/**
	 * Returns the number of dice in this group.
	 * 
	 * @return number of dice in this group
	 */
	public int getNumDice() {
		
		// With no array holding onto all values, combine length of fixed and free arrays
		return (free.length + fixed.length);

	}

	/**
	 * Returns the maximum die value in this group. Valid values start at 1.
	 * 
	 * @return maximum die value
	 */
	public int getMaxValue() {

		return max;

	}

	/**
	 * Rolls all available dice; that is, each available die value in this group
	 * is replaced by a randomly generated value produced by the given random
	 * number generator.
	 * 
	 * @param rand
	 *            random number generator to be used for rolling dice
	 */
	public void roll(Random rand) {
		
		// Can only roll if there are rolls remaining
		if (rolls < maxRoll) {
			
			// Assigning random value to each die
			for (int i = 0; i < free.length; i++) {

				free[i] = rand.nextInt(max) + 1;

			}
			
			// Sort the dice when the roll is complete
			Arrays.sort(free);

			rolls++;
			
			// Keep all dice if maximum rolls have been reached
			if (rolls >= maxRoll) {

				keepAll();

			}

		}

	}

	/**
	 * Selects a die value to be moved from the available dice to the fixed
	 * dice. Has no effect if the given value is not among the values in the
	 * available dice. Has no effect if the number of rolls has reached the
	 * maximum.
	 * 
	 * @param value
	 *            die value to be moved from available to fixed
	 */
	public void keep(int value) {
		
		// Keep is to only function until the hand has been completed
		if (rolls <= maxRoll) {
			
			// Tracker variable
			int hold = -1;

			for (int i = 0; i < free.length; i++) {
				
				// Once desired value is found, hold onto the index
				if (free[i] == value) {

					hold = i;

				}

			}
			
			// As long as the index is not the default value
			if (hold != -1) {
				
				// Clone the free array values
				int[] temp = free;
				
				// Initialize free to minus one of its length
				free = new int[(free.length) - 1];

				// Assign the new free array with every value but the value
				// which is to be kept
				free = removeElement(temp, hold);
				
				// Clone the fixed array values
				temp = fixed;
				
				// Reinitialize the fixed array with plus one to its length
				fixed = new int[(fixed.length) + 1];

				// Copy over all of the values from the fixed array beforehand
				for (int i = 0; i < temp.length; i++) {

					fixed[i] = temp[i];

				}

				// Add the new value
				fixed[(fixed.length) - 1] = value;

				// Re-sort the values in our re-sized fixed array
				Arrays.sort(fixed);

			}

		}

	}

	/**
	 * Selects a die value to be moved from the fixed dice to the available
	 * dice, so it will be re-rolled in the next call to <code>roll()</code>.
	 * Has no effect if the given value is not among the values in the fixed
	 * dice. Has no effect if the number of rolls has reached the maximum.
	 * 
	 * @param value
	 *            die value to be moved
	 */
	public void free(int value) {

		// Free is to function only while the maximum number of rolls has not been met
		if (rolls < maxRoll) {

			// Tracker Variable
			int hold = -1;

			for (int i = 0; i < fixed.length; i++) {
				
				// Once desired value is found, hold onto the index
				if (fixed[i] == value) {

					hold = i;

				}

			}

			// As long as the index is not the default value
			if (hold != -1) {

				// Clone the fixed array values
				int[] temp = fixed;

				// Initialize fixed to minus one of its length
				fixed = new int[(fixed.length) - 1];
				
				// Assign the new fixed array with every value but the value
				// which is to be freed
				fixed = removeElement(temp, hold);

				// Clone the free array values
				temp = free;

				// Reinitialize the free array with plus one to its length
				free = new int[(free.length) + 1];

				// Copy over all of the values from the freed array beforehand
				for (int i = 0; i < temp.length; i++) {

					free[i] = temp[i];

				}

				// Add the new value
				free[(free.length) - 1] = value;

				// Re-sort the values in our re-sized free array
				Arrays.sort(free);

			}

		}

	}

	/**
	 * Helper method to remove an element from an array
	 * 
	 * @param arr
	 * @param element
	 * @return
	 */
	private int[] removeElement(int[] arr, int element) {
		
		// Local Variable declarations
		int arrIt = 0;
		int[] newArr = new int[(arr.length) - 1];

		
		for (int i = 0; i < arr.length; i++) {

			// Copy over every element except the one specified
			if (i != element) {

				newArr[arrIt] = arr[i];
				arrIt++;

			} else {

				// Do nothing, ignoring the specified element

			}

		}

		return newArr;

	}

	/**
	 * Causes all die values be moved from the available dice to the fixed dice.
	 * Has no effect if the number of rolls has reached the maximum.
	 */
	public void keepAll() {

		// Functions only until the maximum number of rolls has been reached
		if (rolls <= maxRoll) {
			
			// Continue to keep dice until there are none remaining to be kept
			while (free.length != 0) {

				keep(free[0]);

			}

		}

	}

	/**
	 * Causes all die values be moved from the fixed dice to the available dice.
	 * Has no effect if the number of rolls has reached the maximum.
	 */
	public void freeAll() {

		// Functions only until the maximum number of rolls has been reached
		if (rolls < maxRoll) {

			// Continue to free dice until there are none remaining to be freed
			while (fixed.length != 0) {

				free(fixed[0]);

			}

		}

	}

	/**
	 * Determines whether there are any dice available to be rolled in this
	 * group.
	 * 
	 * @return true if there are no available dice, false otherwise
	 */
	public boolean isComplete() {

		// If all of our dice are fixed, 
		if (fixed.length == numberOfDice) {

			// The Hand is complete
			return true;

		// Otherwise
		} else {
			
			// The hand is not complete
			return false;

		}

	}

	/**
	 * Returns the values of the dice that are currently fixed (not available to
	 * be rerolled) in ascending order.
	 * 
	 * @return values of the dice that are currently fixed
	 */
	public int[] getFixedDice() {

		return fixed;

	}

	/**
	 * Returns the values of the dice that are currently available to be
	 * rerolled by a subsequent call to <code>roll()</code>, in ascending order.
	 * 
	 * @return dice that are available to be rerolled
	 */
	public int[] getAvailableDice() {

		return free;

	}

	/**
	 * Returns all die values in this group, in ascending order.
	 * 
	 * @return all die values in this group
	 */
	public int[] getAll() {

		return combine(free, fixed);

	}

	/**
	 * Helper method to retrieve the values of both the fixed and freed dice values
	 * 
	 * @param freed
	 * @param fix
	 * @return
	 */
	private int[] combine(int[] freed, int[] fix) {

		// New array, needs to be size of both free and fixed
		int[] newArr = new int[(free.length) + (fixed.length)];
		int j = 0;

		// Assign freed values
		for (int i = 0; i < free.length; i++, j++) {

			newArr[j] = free[i];

		}

		// Assigned fixed values
		for (int i = 0; i < fixed.length; i++, j++) {

			newArr[j] = fixed[i];

		}

		// Sort the combination
		Arrays.sort(newArr);

		return newArr;

	}

	/**
	 * Returns a string representation of the die values in this hand, in the
	 * form <em>available</em>(<em>fixed</em>). (For example, if there are two
	 * fixed dice with values 2 and 4, and there are 3 available dice with
	 * values 1, 1, and 6, then the method returns the string
	 * 
	 * <pre>
	 * 1 1 6 (2 4)
	 * </pre>
	 * 
	 * If all dice are available, the parentheses should be empty.
	 * 
	 * @return string representation of the available and completed dice, with
	 *         the completed values in parentheses
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		int[] availableDice = getAvailableDice();
		for (int value : availableDice) {
			sb.append(value + " ");
		}

		sb.append("(");
		int[] fixedDice = getFixedDice();
		if (fixedDice.length > 0) {
			// use an index so we can add the first one without a leading space
			sb.append(fixedDice[0]);
			for (int i = 1; i < fixedDice.length; ++i) {
				sb.append(" " + fixedDice[i]);
			}
		}
		sb.append(")");
		return sb.toString();
	}
}
