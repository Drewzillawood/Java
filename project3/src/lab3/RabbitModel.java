package lab3;

/**
 * 
 * 		A RabbitMode is used to simulate the growth
 * 		of a population of rabbits
 * @author Drew
 *
 */
public class RabbitModel {
	
	// TODO - Add instance variables as needed
	private int pop;
	
	
	/**
	 * Constructs a new RabbitModel
	 */
	public RabbitModel(){
		
		pop = 500;
		
	}
	
	/**
	 * Returns the current number of rabbits
	 * @return
	 * 		current rabbit population
	 */
	public int getPopulation(){
		
		return pop ;
		
	}
	
	/**
	 *  Updates the population to simulate the passing of the year
	 */
	public void simulateYear(){
		
		pop = pop/2;
		
	}
	
	/**
	 * Sets or resets the state of the model
	 * to the initial conditions
	 */
	public void reset(){
		
		pop = 500;
		
	}
	
}
