package hw3;

import java.util.ArrayList;

import hw3.api.Category;

/**
 * Game state for a dice game such as Yahtzee. The game consists of a list of
 * <code>Category</code> objects, each of which is responsible for keeping track
 * of the dice used to satisfy it and of its own contribution to the total
 * score. Clients interact directly with the category objects, which are
 * obtained using method <code>getCategories()</code>. The total score for the
 * game may be obtained via the <code>getScore</code> method. This class also
 * keeps track of several game attributes: the number of dice being used in the
 * game, the maximum value (number of "sides") of the dice, and the number of
 * times the dice may be rerolled in each round.
 * 
 * For organizing the inheritance Hierarchy, I organized my classes in the following Order
 * SUPER is the class which contains the general layout for a category class
 * 
 * The following classes extend SUPER: AllButOneOfAKind, AllButTwoOfAKind, AllOfAKind, Chance, CountOccurrences
 * Further down the hierarchy, a few subclasses shared more common traits and AllOfAKind was used as the next step
 * down the hierarchy.
 * 
 * These subclasses extend the AllOfAKind class: FullHouse, LargeStraight, SmallStraight
 * 
 * @author drewu
 */
public class YahtzeeGame {

	/**
	 * Stores all categories currently in use
	 */
	private ArrayList<Category> categories;

	/**
	 * Stores the number of dice in play
	 */
	private int dice;

	/**
	 * Keeps track of the maximum dice roll value
	 */
	private int numSides;

	/**
	 * Number of rolls for this round
	 */
	private int nRolls;

	/**
	 * Constructs a new YahtzeeGame based on the given parameters. Initially the
	 * list of categories is empty.
	 * 
	 * @param numDice
	 *            number of dice used in this game
	 * @param maxDieValue
	 *            maximum face value (number of faces) for each die
	 * @param numRolls
	 *            number of times the dice can be rolled in each round
	 */
	public YahtzeeGame(int numDice, int maxDieValue, int numRolls) {

		dice = numDice;
		numSides = maxDieValue;
		nRolls = numRolls;
		categories = new ArrayList<Category>();

	}

	/**
	 * Adds a scoring category to this game.
	 * 
	 * @param category
	 *            scoring category to add
	 */
	public void addCategory(Category category) {
		
		categories.add(category);
		
	}

	/**
	 * Returns the list of categories in this game.
	 * 
	 * @return list of categories
	 */
	public ArrayList<Category> getCategories() {

		return categories;

	}

	/**
	 * Returns a new Hand corresponding to the number of dice, maximum die
	 * value, and number of rolls for this game. Initially all dice in the hand
	 * are available to be rolled.
	 * 
	 * @return new Hand based on this game's parameters
	 */
	public Hand createNewHand() {

		Hand hand = new Hand(dice, numSides, nRolls);
		return hand;

	}

	/**
	 * Returns the current total score for all categories.
	 * 
	 * @return total score for all categories
	 */
	public int getScore() {
		
		return totalScore(categories);

	}
	
	private int totalScore(ArrayList<Category> list) {
		
		int sum = 0;
		
		for(int i = 0; i < list.size(); i++) {
			
			sum += list.get(i).getScore();
			
		}
		
		return sum;
		
	}
	
	

}
