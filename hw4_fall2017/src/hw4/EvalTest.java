package hw4;

import java.util.Arrays;

import api.Card;
import api.Hand;
import api.IEvaluator;
import api.Suit;

public class EvalTest
{

	public static void main(String[] args)
	{

		IEvaluator eval = new OnePairEvaluator(3, 4);
		System.out.println(eval.getName());
		
		// Should be satisfied by 
		Card[] cards = {new Card(2, Suit.CLUBS), new Card(2, Suit.DIAMONDS)};
		System.out.println(Arrays.toString(cards));
		System.out.println(eval.satisfiedBy(cards)); // true
		
		// Should not be satisfied by
		cards = Card.createArray("Kc, Qd");
		System.out.println(Arrays.toString(cards));
		System.out.println(eval.satisfiedBy(cards)); // false
		
		// Won't satisfy either, has more htna required # of cards
		cards = Card.createArray("2c, 2d, 3h");
		System.out.println(Arrays.toString(cards));
		System.out.println(eval.satisfiedBy(cards)); // false
		
		// However, it contains a subset that does
		System.out.println(eval.canSubsetSatisfy(cards)); // true
	
		// Try a bigger array. We'll use Arrays.sort to get them
		// in order, as required by the IEvaluator API. This
		// illustrates the ordering of the Card compareTo() method
		cards = Card.createArray("6s, Jd, Ah, 10h, 6h, Js, 6c, Kh, Qh");
		Arrays.sort(cards); // now [Ah, Kh, Qh, Js, Jd, 10h, 6s, 6h, 6c]
		System.out.println(Arrays.toString(cards));
		System.out.println(eval.canSubsetSatisfy(cards)); // true
		

		// Define a subset consisting of indices 6 and 8
		// and have the evaluator create a Hand from those cards
		int[] subset = {6, 8};
		Hand hand = eval.createHand(cards, subset);
		System.out.println(hand); // One Pair (3) [6s 6c : Ah Kh]
		
		// Subset at indices 0 and 3 doesn't satisfy evaluator, so
		// createHand returns null
		int[] subset2 = {0, 3};
		hand = eval.createHand(cards, subset2);
		System.out.println(hand); // null
		
		// Finds the best hand from these cards (for this evaluator)
		// which will be the pair of jacks plus ace and king
		hand = eval.createBestHand(cards);
		System.out.println(hand); // One Pair (3) [Js Jd : Ah Kh]
		
	}

}
