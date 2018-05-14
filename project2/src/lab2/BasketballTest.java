package lab2;

/**
 * 
 *  Creating a class to test out our basketball class
 *
 */
public class BasketballTest {
	
	// Main method, where all the magic happens
	public static void main(String[] args) {
		
		Basketball b;
		b = new Basketball(4.0);
		System.out.println(b.getDiameter());
		System.out.println(b.isDribbleable());
		
		Basketball b2 = new Basketball(6.0);
		
		b.inflate();
		
		System.out.println(b.isDribbleable());
		System.out.println(b2.isDribbleable());
		
		
		
	}

}
