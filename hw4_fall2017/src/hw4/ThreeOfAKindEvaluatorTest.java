package hw4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import api.Card;

public class ThreeOfAKindEvaluatorTest
{

	private ThreeOfAKindEvaluator	t;
	private int						rank, sizeOfHand;

	@Before
	public void setup()
	{
		rank = 3;
		sizeOfHand = 4;
		t = new ThreeOfAKindEvaluator(rank, sizeOfHand);
	}

	@Test
	public void testOnePairEvaluatorInit()
	{
		assertEquals("Three of a Kind", t.getName());
		assertEquals(rank, t.getRanking());
		assertEquals(3, t.numMainCards());
		assertEquals(sizeOfHand, t.totalCards());
	}
	
	@Test
	public void satisfiedBy_1()
	{
		// Simple test, array w/ 1 pair
		Card[] c = Card.createArray("2d, 2h, 2s");
		assertTrue(t.satisfiedBy(c));
	}
	
	@Test
	public void satisfiedBy_2()
	{
		// Evem numbered array
		Card[] c = Card.createArray("2d, 2h, 2s, 2c");
		assertFalse(t.satisfiedBy(c));
	}
	
	@Test
	public void canSubsetSatisfy_1()
	{
		// One subset within which can satisfy
		Card[] c = Card.createArray("2d, 2h, 2s, 3d");
		assertTrue(t.canSubsetSatisfy(c));
	}
	
	@Test
	public void canSubsetSatisfy_2()
	{
		// One subset w/ odd numbered size
		Card[] c = Card.createArray("2d, 2h, 2s, 3h, 3s");
		assertTrue(t.canSubsetSatisfy(c));
	}
	
	@Test
	public void canSubsetSatisfy_3()
	{
		// No subsets which can satisfy
		Card[] c = Card.createArray("4d, 2h, 3h");
		assertFalse(t.canSubsetSatisfy(c));
	}
	
	@Test
	public void canSubsetSatisfy_4()
	{
		// Empty set
		Card[] c = {};
		assertFalse(t.canSubsetSatisfy(c));
	}
	
	@Test
	public void canSubsetSatisfy_5()
	{
		// Null set
		Card[] c = null;
		assertFalse(t.canSubsetSatisfy(c));
	}
	
	@Test
	public void createHandTest_1()
	{
		Card[] c = Card.createArray("6s, Jd, Ah, 10h, 6h, Js, 6c, Kh, Qh");
		Arrays.sort(c); // now [Ah, Kh, Qh, Js, Jd, 10h, 6s, 6h, 6c]
		int[] subset = { 6, 7, 8 };
		assertEquals("Three of a Kind (3) [6s 6h 6c : Ah]", t.createHand(c, subset).toString()); 
		// Three of a Kind (3) [6s 6c Ah : Kh]
	}
	
	@Test
	public void createHandTest_2()
	{
		// No side sets
		t = new ThreeOfAKindEvaluator(3, 3);
		Card[] c = Card.createArray("Ah, Ad, As");
		int[] subset = { 0, 1, 2 };
		assertEquals("Three of a Kind (3) [Ah Ad As]", t.createHand(c, subset).toString());
 	}
	
	@Test
	public void createHandTest_3()
	{
		// No main sets
		Card[] c = Card.createArray("Ah, Kd, 3h, 4s");
		Arrays.sort(c);
		int[] subset = { 0, 1, 2 };
		assertNull(t.createHand(c, subset));
	}
	
	@Test
	public void createBestHand_1()
	{
		Card[] c = Card.createArray("6s, Jd, Ah, 10h, 6h, Js, 6c, Kh, Qh, Jh");
		Arrays.sort(c); // now [Ah, Kh, Qh, Js, Jh, Jd, 10h, 6s, 6h, 6c]
		assertEquals("Three of a Kind (3) [Js Jh Jd : Ah]", t.createBestHand(c).toString());
	}
	
	@Test
	public void createBestHand_2()
	{
		// Ensures main and side set are disjoint
		Card[] c = Card.createArray("6s, Jd, Ah, 10h, 6h, Js, 6c, Kh, Qh, Ac, Ad");
		Arrays.sort(c); // now [Ah, Ad, Ac, Kh, Qh, Js, Jd, 10h, 6s, 6h, 6c]
		assertEquals("Three of a Kind (3) [Ah Ad Ac : Kh]", t.createBestHand(c).toString());
	}
}
