package lab2;

/**
 * 
 *  Our Atom class, which will perform some basic mathematical operations
 *  on given numbers of protons, neutrons, and electrons
 *  
 */
public class Atom {
	
	/**
	 *  Instance variable declarations
	 */
	private int numProtons, numNeutrons, numElectrons;
	
	/**
	 *  Constructor for our atom
	 * @param givenProtons
	 * 		number of protons given
	 * @param givenNeutrons
	 * 		number of neutrons given
	 * @param givenElectrons
	 * 		number of electrons given
	 */
	public Atom (int givenProtons, int givenNeutrons, int givenElectrons){
			
		numProtons = givenProtons;
		numNeutrons = givenNeutrons;
		numElectrons = givenElectrons;
			
    }
	
	/**
	 *  Method to return the atomic mass of our atom
	 * @return
	 * 		returns the sum of neutrons and protons
	 */
	public int getAtomicMass(){
		
		return (numProtons + numNeutrons);
		
	}
	
	/**
	 *  Method to return the charge of our atom
	 * @return
	 * 		returns the difference of protons to electrons
	 */
	public int getAtomicCharge(){
		
		return (numProtons - numElectrons);
		
	}
	
	/**
	 *  Mutator method which will decay our atom
	 */
	public void decay(){
		
		// Error check for when statement given is invalid for decay
		if(numNeutrons <=1 || numProtons <= 1){
			
			String error = "Error";
			System.out.println(error);
			System.exit(0);
		}
		
		numNeutrons = numNeutrons - 2;
		numProtons = numProtons - 2;
		
	}
	
	
	
}
