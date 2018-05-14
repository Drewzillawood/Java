package edu.iastate.cs228.hw2;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *  
 * @author drewu
 *
 */

public class PointTest {
	
	private Point point;
	private Point point2;
	
	@Before
	public void setup() { 
		
		point = new Point();
		point2 = new Point(1,2);
		
	}
	
	@Test 
	public void testDefault() {
		
		assertEquals(0, point.getX());
		assertEquals(0, point.getY());
		
	}
	
	@Test 
	public void testConstructor() {
		
		assertEquals(1, point2.getX());
		assertEquals(2, point2.getY());
		
	}
	
	@Test
	public void testEquals() {
		
		assertTrue(!point.equals(point2));
		assertTrue(point.equals(point));
		
	}
	
	@Test
	public void testCompareTo() {
		
		assertEquals(-1, point.compareTo(point2));
		assertEquals(0, point.compareTo(point));
		assertEquals(1, point2.compareTo(point));
		
	}
	
	@Test
	public void testToString() {
		
		assertEquals("(0, 0)\n", point.toString());
		
	}

}
