package hw3;

import hw3.api.Category;

public class CatTest {

	public static void main(String[] args) {
		
//		 int[] test = {3, 3, 3, 5, 6};
//		 Hand dice = new Hand(5, 6, 3, test);
//		 Category cat = new CountOccurrences("Count threes", 3);
//		 System.out.println(cat.isSatisfiedBy(dice)); // should be true
//		 System.out.println(cat.getPotentialScore(dice)); // should be 9
//		 System.out.println(cat.getScore()); // should be zero
//		 cat.fill(dice);
//		 System.out.println(cat.getScore()); // should be nine
		 
		 int[] test = {8,8,8,8,8,8,8,8};
		 Hand dice = new Hand(8, 8, 3, test);
		 Category cat = new AllButTwoOfAKind("Kind");
		 System.out.println(cat.isSatisfiedBy(dice));
		 System.out.println(cat.getPotentialScore(dice));
		 System.out.println(cat.getScore());
		 dice.keepAll();
		 cat.fill(dice);
		 System.out.println(cat.getScore());

	}

}
