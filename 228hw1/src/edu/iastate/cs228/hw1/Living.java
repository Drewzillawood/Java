package edu.iastate.cs228.hw1;

/**
 *  
 * @author drewu
 *
 */

/**
 * 
 * Living refers to the life form occupying a square in a world grid. It is a 
 * superclass of Empty, Grass, and Animal, the latter of which is in turn a superclass
 * of Badger, Fox, and Rabbit. Living has two abstract methods awaiting implementation. 
 *
 */
public abstract class Living  {
	
	protected World world; // the world in which the life form resides
	protected int row;     // location of the square on which 
	protected int column;  // the life form resides
	
	// constants to be used as indices. 
	protected static final int BADGER = 0; 
	protected static final int EMPTY = 1; 
	protected static final int FOX = 2; 
	protected static final int GRASS = 3; 
	protected static final int RABBIT = 4; 
	
	public static final int NUM_LIFE_FORMS = 5; 
	
	// life expectancies 
	public static final int BADGER_MAX_AGE = 4; 
	public static final int FOX_MAX_AGE = 6; 
	public static final int RABBIT_MAX_AGE = 3; 
	
	
	/**
	 * Censuses all life forms in the 3 X 3 neighborhood in a world. 
	 * @param population  counts of all life forms
	 */
	protected void census(int population[ ]) {		
	
		// Establish borders for situation where Living object
		// is positioned against a border
		int upper = Math.max(0,  row-1);
		int lower = Math.min(world.getWidth() - 1,  row+1);
		int left = Math.max(0, column-1);
		int right = Math.min(world.getWidth() - 1, column+1);
		
		population[BADGER] = 0;
		population[RABBIT] = 0;
		population[EMPTY] = 0;
		population[FOX] = 0;
		population[GRASS] = 0;
		
		// Double for loop which iterates through our 3x3 or 
		// adjusted neighborhood
		for(int i = upper; i <= lower; i++) {
			
			for(int j = left; j <= right; j++) {
				
				// Conditional statements identify State of Living object
				// and increment population[] at the proper indices
				
				if(world.grid[i][j].who().equals(State.BADGER)) {
					
					population[BADGER]++;
					
				} else if(world.grid[i][j].who().equals(State.FOX)) {
					
					population[FOX]++;
					
				} else if(world.grid[i][j].who().equals(State.RABBIT)) {
					
					population[RABBIT]++;
					
				} else if(world.grid[i][j].who().equals(State.GRASS)) {
					
					population[GRASS]++;
					
				} else if(world.grid[i][j].who().equals(State.EMPTY)) {
					
					population[EMPTY]++;
					
				}
 				
			}
			
		}
		
		
		// TODO 
		// 
		// Count the numbers of Badgers, Empties, Foxes, Grasses, and Rabbits  
		// in the 3 by 3 neighborhood centered at this Living object.  Store the 
		// counts in the array population[] at indices 0, 1, 2, 3, 4, respectively. 
		
	}

	/**
	 * Gets the identity of the life form on the square.
	 * @return State
	 */
	public abstract State who();
	// To be implemented in each class of Badger, Empty, Fox, Grass, and Rabbit. 
	// 
	// There are five states given in State.java. Include the prefix State in   
	// the return value, e.g., return State.Fox instead of Fox.  
	
	/**
	 * Determines the life form on the square in the next cycle.
	 * @param  wNew  world of the next cycle
	 * @return Living 
	 */
	public abstract Living next(World wNew); 
	// To be implemented in the classes Badger, Empty, Fox, Grass, and Rabbit. 
	// 
	// For each class (life form), carry out the following: 
	// 
	// 1. Obtains counts of life forms in the 3X3 neighborhood of the class object. 

	// 2. Applies the survival rules for the life form to determine the life form  
	//    (on the same square) in the next cycle.  These rules are given in the  
	//    project description. 
	// 
	// 3. Generate this new life form at the same location in the world wNew.      

}
