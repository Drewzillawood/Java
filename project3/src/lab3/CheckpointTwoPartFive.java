package lab3;

import java.util.Random;

public class CheckpointTwoPartFive {
	
	public static void main(String[] args){
		
		Random rand = new Random();
//		System.out.println(rand.nextInt(6));
//		System.out.println(rand.nextInt(6));
//		System.out.println(rand.nextInt(6));
//		System.out.println(rand.nextInt(6));
		
		String firstName = "Drew";
		String lastName = "Underwood";
		
		String returnValue = firstName.charAt(0) + lastName + rand.nextInt(50);

		System.out.println(returnValue.toLowerCase());
		System.out.println(Integer.parseInt("15")+18);
		
	}

}
