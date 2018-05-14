package edu.iastate.cs228.hw1;

/**
 *  
 * @author drewu
 *
 */

/*
 * A rabbit eats grass and lives no more than three years.
 */
public class Rabbit extends Animal {	
	/**
	 * Creates a Rabbit object.
	 * @param w: world  
	 * @param r: row position 
	 * @param c: column position
	 * @param a: age 
	 */
	public Rabbit (World w, int r, int c, int a) {
		
		row = r;
		column = c;
		world = w;
		age = a;
		
	}
		
	// Rabbit occupies the square.
	public State who() {
	
		return State.RABBIT; 
		
	}
	
	/**
	 * A rabbit dies of old age or hunger, or it is eaten if there are as many 
	 * foxes and badgers in the neighborhood.  
	 * @param wNew     world of the next cycle 
	 * @return Living  new life form occupying the same square
	 */
	public Living next(World wNew) {
		
		// Array holds values of the population in the
		// local neighborhood
		int[] Census = new int[NUM_LIFE_FORMS];
		census(Census);
	
		// Conditional statements determine the Living Object
		// which will be returned
		if(age == RABBIT_MAX_AGE) {

			return new Empty(wNew, row, column);
			
		} else if(Census[GRASS] == 0) {

			return new Empty(wNew, row, column);
			
		} else if(Census[FOX] + Census[BADGER] >= Census[RABBIT] && Census[FOX] > Census[BADGER]) {

			return new Fox(wNew, row, column, 0);
			
		} else if(Census[BADGER] > Census[RABBIT]) {

			return new Badger(wNew, row, column, 0);
			
		} else {
			
			age++;
			return new Rabbit(wNew, row, column, age);
			
		}
		
		
		
		
	}
}
