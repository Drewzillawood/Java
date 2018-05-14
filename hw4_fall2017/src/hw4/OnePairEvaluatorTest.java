package hw4;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import api.Card;

public class OnePairEvaluatorTest
{
	private OnePairEvaluator	o;
	private int					rank, sizeOfHand;

	@Before
	public void setup()
	{
		rank = 3;
		sizeOfHand = 4;
		o = new OnePairEvaluator(rank, sizeOfHand);
	}

	@Test
	public void testOnePairEvaluatorInit()
	{
		assertEquals("One Pair", o.getName());
		assertEquals(rank, o.getRanking());
		assertEquals(2, o.numMainCards());
		assertEquals(sizeOfHand, o.totalCards());
	}

	@Test
	public void satisfiedBy_1()
	{
		// Simple test, array w/ 1 pair
		Card[] c = Card.createArray("2d, 2h");
		assertTrue(o.satisfiedBy(c));
	}

	@Test
	public void satisfiedBy_2()
	{
		// Odd numbered array
		Card[] c = Card.createArray("2d, 2h, 2s");
		assertFalse(o.satisfiedBy(c));
	}

	@Test
	public void canSubsetSatisfy_1()
	{
		// One subset within which can satisfy
		Card[] c = Card.createArray("2d, 2h, 3h, 3d");
		assertTrue(o.canSubsetSatisfy(c));
	}

	@Test
	public void canSubsetSatisfy_2()
	{
		// One subset w/ odd numbered size
		Card[] c = Card.createArray("2d, 2h, 3h");
		assertTrue(o.canSubsetSatisfy(c));
	}

	@Test
	public void canSubsetSatisfy_3()
	{
		// No subsets which can satisfy
		Card[] c = Card.createArray("4d, 2h, 3h");
		assertFalse(o.canSubsetSatisfy(c));
	}

	@Test
	public void canSubsetSatisfy_4()
	{
		// Empty set
		Card[] c = {};
		assertFalse(o.canSubsetSatisfy(c));
	}

	@Test
	public void canSubsetSatisfy_5()
	{
		// Null set
		Card[] c = null;
		assertFalse(o.canSubsetSatisfy(c));
	}

	@Test
	public void createHandTest_1()
	{
		Card[] c = Card.createArray("6s, Jd, Ah, 10h, 6h, Js, 6c, Kh, Qh");
		Arrays.sort(c); // now [Ah, Kh, Qh, Js, Jd, 10h, 6s, 6h, 6c]
		int[] subset = { 6, 8 };
		assertEquals("One Pair (3) [6s 6c : Ah Kh]", o.createHand(c, subset).toString()); // One
																							// Pair
																							// (3)
																							// [6s
																							// 6c
																							// :
																							// Ah
																							// Kh]
	}

	@Test
	public void createHandTest_2()
	{
		// No side sets
		o = new OnePairEvaluator(3, 2);
		Card[] c = Card.createArray("Ah, Ad");
		Arrays.sort(c);
		int[] subset = { 0, 1 };
		assertEquals("One Pair (3) [Ah Ad]", o.createHand(c, subset).toString());
	}

	@Test
	public void createHandTest_3()
	{
		// No main sets
		Card[] c = Card.createArray("Ah, Kd, 3h, 4s");
		Arrays.sort(c);
		int[] subset = { 0, 1 };
		assertNull(o.createHand(c, subset));
	}

	@Test
	public void createBestHand_1()
	{
		Card[] c = Card.createArray("6s, Jd, Ah, 10h, 6h, Js, 6c, Kh, Qh");
		Arrays.sort(c); // now [Ah, Kh, Qh, Js, Jd, 10h, 6s, 6h, 6c]
		assertEquals("One Pair (3) [Js Jd : Ah Kh]", o.createBestHand(c).toString());
	}

	@Test
	public void createBestHand_2()
	{
		// Ensures main and side set are disjoint
		Card[] c = Card.createArray("6s, Jd, Ah, 10h, 6h, Js, 6c, Kh, Qh, Ac");
		Arrays.sort(c); // now [Ah, Ac, Kh, Qh, Js, Jd, 10h, 6s, 6h, 6c]
		assertEquals("One Pair (3) [Ah Ac : Kh Qh]", o.createBestHand(c).toString());
	}

	@Test
	public void createBestHand_3()
	{
		Card[] c = Card.createArray("Ah, Ac, Ad, As");
		Arrays.sort(c); // now [As, Ah, Ad, Ac]
		assertEquals("One Pair (3) [As Ah : Ad Ac]", o.createBestHand(c).toString());
	}
}
