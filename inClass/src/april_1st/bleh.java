package april_1st;

import java.util.Arrays;

public class bleh {

	public static void main(String[] args) {

		int[] car = {4, 6, 2};
		
		int[] thing = new int[4];
		
		System.out.println(thing.length);
		
		thing = car;
		
		System.out.println(thing.length);
		
		System.out.println(Arrays.toString(car));
		System.out.println(Arrays.toString(thing));

	}

}
