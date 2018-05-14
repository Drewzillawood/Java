package hw3;

public class HandTest {

	public static void main(String[] args) {
	
		int[] test = {1, 2, 3, 4};
		Hand hand = new Hand(4,6,3,test);
		hand.keep(2);
		hand.keep(3);
		hand.keep(4);
		
		System.out.println(hand);
			
	}

}
