package april_1st;

public class test {

	public static void main(String[] args) {
		
		Creature c;
		
		/**
		 *  An Ostrich is a type of creature
		 */
		c = new Ostrich();
		c.makeNoise(); // woo woo
		
		/**
		 *  A Tiger is also a type of creature!
		 */
		c = new Tiger();
		c.makeNoise(); // Grrrrr
		
		// This is all called - Polymorphism
				// A variable declared with type T can refer to an object that is a 
		 		// sub-type of T
		
		/* Example 
		 * 
		 *  'T' is an interface and the object's class implements the interface!
		 * 
		 */

	}

}


