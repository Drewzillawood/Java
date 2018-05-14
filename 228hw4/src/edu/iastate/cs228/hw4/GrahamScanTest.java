package edu.iastate.cs228.hw4;

import org.junit.Test;

public class GrahamScanTest
{

	@SuppressWarnings("unused")
	@Test
	public void test()
	{

		Point[] test = new Point[3];
		test[0] = new Point(2, 2);
		test[1] = new Point(3, 1);
		test[2] = new Point(3, 3);

		GrahamScan gs = new GrahamScan(test);

	}

}
