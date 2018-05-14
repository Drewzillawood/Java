package edu.iastate.cs228.hw2;

/**
 *  
 * @author drewu
 *
 */

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class PolarAngleComparatorTest {
	
	private Point p1;
	private Point p2;
	private Point pr;
	private PolarAngleComparator comp;
	
	@Before
	public void setup() {
		
		
		p1 = new Point(3,3);
		p2 = new Point(2,0);
		pr = new Point(-2,-2);
		
		comp = new PolarAngleComparator(pr);
		
	}
	
	@Test
	public void testCompareDistance() {
		
		int result = comp.compareDistance(p1, p1);
		assertEquals(0, result);
		result = comp.compareDistance(p2, p1);
		assertEquals(-1, result);
		result = comp.compareDistance(p1, p2);
		assertEquals(1, result);
		
	}
	
	@Test 
	public void testComparePolarAngle() {
		
		int result = comp.comparePolarAngle(p1, p1);
//		assertEquals(0, result);
		result = comp.comparePolarAngle(p1, p2);
//		assertEquals(1, result);
		p1 = pr;
		p2 = new Point(0, 2);
		result = comp.comparePolarAngle(p1, p2);
//		assertEquals(-1, result);
		p1 = new Point(2, 0);
		result = comp.comparePolarAngle(p1, p2);
		assertEquals(-1, result);
		
	}
	
	@Test
	public void testCompare() {
		
		int result = comp.compare(p1, p1);
//		assertEquals(0, result);
		result = comp.compare(p2, p1);
//		assertEquals(-1 , result);
		p2 = new Point(2, 2);
		result = comp.compare(p2, p1);
//		assertEquals(-1, result);
		p1 = new Point(0, 0);
		result = comp.compare(p1, p2);
		assertEquals(-1, result);
		p1 = new Point(7,0);
		p2 = new Point(3,3);
		result = comp.compare(p1, p2);
		assertEquals(-1, result);
		
	}

}
