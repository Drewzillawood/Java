package edu.iastate.cs228.hw4;

/**
 * 
 * @author drewu
 * 
 */

import org.junit.Test;
import static org.junit.Assert.*;

public class PointTest {

	@Test
	public void thisYLessThanQY() {
		
		Point p1 = new Point(4,2);
		Point p2 = new Point(3,3);
		
		assertEquals(-1, p1.compareTo(p2));
		
	}
	
	@Test
	public void thisYEqualQYButThisXLessThanQX() {
		
		Point p1 = new Point(2,3);
		Point p2 = new Point(3,3);
		
		assertEquals(-1, p1.compareTo(p2));
		
	}
	
	@Test
	public void equalPoints() {
		
		Point p1 = new Point(3,3);
		Point p2 = new Point(3,3);
		
//		assertEquals(true, p1.equals(p2));
		assertEquals(0, p1.compareTo(p2));
		
	}
	
	@Test
	public void thisYGreaterThanQY() {
		
		Point p1 = new Point(3,4);
		Point p2 = new Point(3,3);
		
		assertEquals(1, p1.compareTo(p2));
		
	}
	
}
