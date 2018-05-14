package edu.iastate.cs228.hw1;

/**
 *  
 * @author drewu
 *
 */

/**
 * A fox eats rabbits and competes against a badger. 
 */
public class Fox extends Animal 
{
	/**
	 * Constructor 
	 * @param w: world
	 * @param r: row position 
	 * @param c: column position
	 * @param a: age 
	 */
	public Fox (World w, int r, int c, int a) {
		
		row = r;
		column = c;
		world = w;
		age = a;
		
	}
		
	/**
	 * A fox occupies the square. 	 
	 */
	public State who() {
	
		return State.FOX; 
		
	}
	
	/**
	 * A fox dies of old age or hunger, or from attack by numerically superior badgers. 
	 * @param wNew     world of the next cycle
	 * @return Living  life form occupying the square in the next cycle. 
	 */
	public Living next(World wNew) {
		
		// Array holds values for the population in the local neighborhood
		int[] Census = new int[NUM_LIFE_FORMS];
		census(Census);
		
		// Conditional Statements determine the living object to be returned
		if(age == FOX_MAX_AGE) {
			
			return new Empty(wNew, row, column);
			
		} else if(Census[BADGER] > Census[FOX]) {
			
			return new Badger(wNew, row, column, 0);
			
		} else if(Census[BADGER] + Census[FOX] > Census[RABBIT]) {
			
			return new Empty(wNew, row, column);
			
		} else {
			
			age++;
			return new Fox(wNew, row, column, age);
			
		}
		
	}
}
