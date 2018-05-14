package edu.iastate.cs228.hw1;

/**
 *  
 * @author drewu
 *
 */

/**
 * Grass remains if more than rabbits in the neighborhood;
 * otherwise, it is eaten. 
 *
 */
public class Grass extends Living {
	
	// Constructor for a Grass Object
	public Grass (World w, int r, int c) {
		
		world = w;
		row = r;
		column = c;
		
	}
	
	// State Definition for Grass
	public State who() {
		
		return State.GRASS;
		
	}
	
	/**
	 * Grass can be eaten out by too many rabbits in the neighborhood. Rabbits may also 
	 * multiply fast enough to take over Grass. 
	 */
	public Living next(World wNew) {
		
		// Array for holding our population values
		int[] Census = new int[NUM_LIFE_FORMS];
		census(Census);
		
		// Conditional statements, determine type of Living
		// Object which will be returned
		if(Census[RABBIT] >= 3 * Census[GRASS]) {
			
			return new Empty(wNew, row, column);
			
		} else if(Census[RABBIT] >= 3) {
			
			return new Rabbit(wNew, row, column, 0);
			
		} else {
			
			return new Grass(wNew, row, column);
			
		}
		
	}
}


