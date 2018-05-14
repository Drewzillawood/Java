package lab5;

import balloon2.Balloon;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class BalloonTests {

	private Balloon b;
	private static final double EPSILON = 10e-07;
	
	@Before
	public void setup(){
		
		b = new Balloon(10);
		
	}
	
	@Test
	public void initialCaseCheck(){
		
		assertEquals(0, b.getRadius());
		assertEquals(false, b.isPopped());
		
	}
	
	@Test
	public void testBlow(){
		
		String msg = "Blow was to bring the radius to <5>";
		b.blow(5);
		assertEquals(msg, 5.0, b.getRadius(), EPSILON);
		
	}
	
	@Test
	public void testDeflate(){
		
		String msg = "Balloon was to be deflated to radius 0";
		b.deflate();
		assertEquals(msg, 0.0, b.getRadius(), EPSILON);
		
	}
	
	@Test
	public void testIsPopped(){
		
		String msg = "Balloon was to be inflated beyond parameters and pop";
		b.blow(15);
		assertEquals(msg, true, b.isPopped());
		
	}
	
	@Test
	public void testMorePopped(){
		
		b.blow(7);
		assertEquals(false, b.isPopped());
		b.blow(4);
		assertEquals(true, b.isPopped());
		
	}
	
	@Test
	public void testMulti(){
		
		String msg = "Balloon is popped";
		b.blow(5);
		b.pop();
		assertEquals(true, b.isPopped());
		fail(msg);
		
	}
	
	@Test
	public void testPopped(){
		
		String msg = "Balloon was to be intentionally popped";
		b.pop();
		assertEquals(msg ,true, b.isPopped());
		
	}
	
	@Test
	public void testGetRadius(){
		
		int increase = 5;
		String msg = "Balloon radius should be <" + increase +  "> ";
		b.blow(increase);
		assertEquals(msg, 5, b.getRadius());
		
	}
	
	
	
}
