package edu.iastate.cs228.hw4;

import org.junit.Test;
import static org.junit.Assert.*;

public class PolarAngleComparatorTest {

	@Test
	public void testCompareDistanceSamePoint() {
		
		Point p1 = new Point(3,3);
		Point p2 = new Point(3,3);
		Point rp = new Point();
		Boolean flag = true;
		
		PolarAngleComparator comp = new PolarAngleComparator(rp, flag);
		
		assertEquals(0,comp.compare(p1, p2));
		
	}
	
	@Test
	public void testAngleCompGreaterThanZero() {
		
		Point p1 = new Point(4,0);
		Point p2 = new Point(4,4);
		Point rp = new Point();
		Boolean flag = true;
		
		PolarAngleComparator comp = new PolarAngleComparator(rp, flag);
		
		assertEquals(-1,comp.compare(p1, p2));
		
	}
	
	@Test
	public void testAngleCompP1CloserAndFlagTrue() {
		
		Point p1 = new Point(3,3);
		Point p2 = new Point(4,4);
		Point rp = new Point();
		Boolean flag = true;
		
		PolarAngleComparator comp = new PolarAngleComparator(rp, flag);
		
		assertEquals(-1,comp.compare(p1, p2));
		
	}
	
	@Test
	public void testAngleCompP1CloserAndFlagFalse() {
		
		Point p1 = new Point(3,3);
		Point p2 = new Point(4,4);
		Point rp = new Point();
		Boolean flag = false;
		
		PolarAngleComparator comp = new PolarAngleComparator(rp, flag);
		
		assertNotEquals(-1,comp.compare(p1, p2));
		
	}
	
	@Test
	public void testAngleCompP1FurtherAndFlagFalse() {
		
		Point p1 = new Point(5,5);
		Point p2 = new Point(4,4);
		Point rp = new Point();
		Boolean flag = false;
		
		PolarAngleComparator comp = new PolarAngleComparator(rp, flag);
		
		assertEquals(-1,comp.compare(p1, p2));
		
	}
	
	@Test
	public void testAngleCompP1FurtherAndFlagTrue() {
		
		Point p1 = new Point(5,5);
		Point p2 = new Point(4,4);
		Point rp = new Point();
		Boolean flag = true;
		
		PolarAngleComparator comp = new PolarAngleComparator(rp, flag);
		
		assertNotEquals(-1,comp.compare(p1, p2));
		
	}
	
	@Test
	public void testAngleCompLessThanZero() {
		
		Point p1 = new Point(3,3);
		Point p2 = new Point(3,0);
		Point rp = new Point();
		Boolean flag = true;
		
		PolarAngleComparator comp = new PolarAngleComparator(rp, flag);
		
		assertEquals(1,comp.compare(p1, p2));
		
	}
	
	
	
}
