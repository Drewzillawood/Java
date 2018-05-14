package edu.iastate.cs228.hw2;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import java.io.FileNotFoundException;

/**
 *  
 * @author drewu
 *
 */

public class InsertionSortTest {
	
	private Point[] test;
	private InsertionSorter s;
	
	@Before
	public void setup() throws FileNotFoundException {
		
		test = new Point[5];
		test[0] = new Point(7,0);
		test[1] = new Point(-1,0);
		test[2] = new Point(3,3);
		test[3] = new Point(1, 0);
		test[4] = new Point(-2,-2);
		
//		s = new InsertionSorter(test);
		s = new InsertionSorter("test1.txt");
		
	}
	
	@Test
	public void testSortXCoord() {
		
		s.sort(1);
		String temp = "";
		for(int i = 0; i < test.length; i++) {
			
			temp += test[i].toString();
			
		}
		assertEquals(temp, "(-2, -2)\n(-1, 0)\n(1, 0)\n(3, 3)\n(7, 0)\n");
		
	}
//	
//	@Test
//	public void testSortPAngle() {
//		
//		s.sort(2);
//		String temp = "";
//		for(int i = 0; i < test.length; i++) {
//			
//			temp += test[i].toString();
//			
//		}
//		assertEquals(temp, "(-2, -2)\n(7, 0)\n(1, 0)\n(3, 3)\n(-1, 0)\n");
//		
//	}


}
