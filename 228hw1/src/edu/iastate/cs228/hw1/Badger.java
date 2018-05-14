package edu.iastate.cs228.hw1;

/**
 *  
 * @author drewu
 *
 */

/**
 * A badger eats a rabbit and competes against a fox.
 */
public class Badger extends Animal {
	/**
	 * Constructor
	 * 
	 * @param w:
	 *            world
	 * @param r:
	 *            row position
	 * @param c:
	 *            column position
	 * @param a:
	 *            age
	 */
	public Badger(World w, int r, int c, int a) {
		
		row = r;
		column = c;
		world = w;
		age = a;
	}

	/**
	 * A badger occupies the square.
	 */
	public State who() {

		return State.BADGER;

	}

	/**
	 * A badger dies of old age or hunger, from isolation and attack by a group
	 * of foxes.
	 * 
	 * @param wNew
	 *            world of the next cycle
	 * @return Living life form occupying the square in the next cycle.
	 */
	public Living next(World wNew) {

		// Array holds values for the population in the local neighborhood
		int[] Census = new int[NUM_LIFE_FORMS];
		census(Census);
	
		// Conditional statements determine what type of Living
		// Object will be returned
		if(age == BADGER_MAX_AGE) {
			
			return new Empty(wNew, row, column);
			
		} else if(Census[BADGER] == 1 && Census[FOX] > 1) {
			
			return new Fox(wNew, row, column, 0);
			
		} else if(Census[BADGER] + Census[FOX] > Census[RABBIT]) {
			
			return new Empty(wNew, row, column);
			
		} else {
			
			age++;
			return new Badger(wNew, row, column, age);
			
		}
		
	
	}
}
