package edu.iastate.cs228.hw1;

/**
 *  
 * @author drewu
 *
 */

/**
 * Empty squares are competed by various forms of life.
 */
public class Empty extends Living {
	
	// Constructor for an Empty Object
	public Empty(World w, int r, int c) {

		world = w;
		row = r;
		column = c;

	}

	// State definition
	public State who() {

		return State.EMPTY;

	}

	/**
	 * An empty square will be occupied by a neighboring Badger, Fox, Rabbit, or
	 * Grass, or remain empty.
	 * 
	 * @param wNew
	 *            world of the next life cycle.
	 * @return Living life form in the next cycle.
	 */
	public Living next(World wNew) {
		
		// Array for holding population values of local neighborhood
		int[] Census = new int[NUM_LIFE_FORMS];
		census(Census);
		
		// Conditional statements determining the cell to be returned
		if(Census[RABBIT] > 1) {
			
			return new Rabbit(wNew, row, column, 0);
			
		} else if(Census[FOX] > 1) {
			
			return new Fox(wNew, row, column, 0);
			
		} else if(Census[BADGER] > 1) {
			
			return new Badger(wNew, row, column, 0);
			
		} else if(Census[GRASS] >= 1) {
			
			return new Grass(wNew, row, column);
			
		} else {
			
			return new Empty(wNew, row, column);
			
		}
		
	}
	
}
