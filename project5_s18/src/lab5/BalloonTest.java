package lab5;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import balloon.Balloon;

public class BalloonTest
{
	private Balloon b;
	
	@Before
	public void setup()
	{
		b = new Balloon(5);
	}
	
	@Test
	public void testBlow()
	{
		b.blow(3);
		assertEquals(3, b.getRadius());
	}
	
	@Test
	public void testDoubleBlow()
	{
		b.blow(2);
		b.blow(2);
		assertEquals(4, b.getRadius());
	}
	
	@Test
	public void testPopAndBlow()
	{
		b.pop();
		b.blow(2);
		assertEquals(0, b.getRadius());
	}
	
	@Test
	public void testIsPopped()
	{
		b.pop();
		assertTrue(b.isPopped());
	}
	
	@Test
	public void testBeyondMaxRadius()
	{
		b.blow(6);
		assertEquals(0, b.getRadius());
	}
	
	@Test
	public void testPoppedBeyondMaxRadius()
	{
		b.blow(6);
		assertTrue(b.isPopped());
	}
	
	@Test
	public void deflateAndNotPopped()
	{
		b.blow(2);
		b.deflate();
		assertFalse(b.isPopped());
	}
	
	@Test
	public void blowDeflateBlow()
	{
		b.blow(2);
		b.deflate();
		b.blow(2);
		assertEquals(2, b.getRadius());
	}
	
	@Test
	public void doublePop()
	{
		b.pop();
		b.pop();
		assertTrue(b.isPopped());
	}
	
}
