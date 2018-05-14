package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import api.Shape;
import hw4.BasicGenerator;

public class TestBasicGenerator {
	BasicGenerator gen = new BasicGenerator();
	@Test
	public void testGetNext(){
		int numIterations = 1000;
		int iShape=0, jShape=0, lShape=0, oShape=0, szShape=0, tShape=0,magic=0;
		for( int i = 0; i < numIterations; i++ ){
			Shape shape = gen.getNext(0);
			Class<? extends Shape > c = shape.getClass();
			int col = shape.getCells()[0].getCol();
			int row = shape.getCells()[0].getRow();
			boolean isMagic = shape.getCells()[0].getBlock().isMagic();
			if( isMagic ){
				magic++;
			}
			switch(c.getSimpleName()){
			case "IShape":
				iShape++;
				assertEquals( -2, row );
				assertEquals( 0, col );
				break;
			case "JShape":
				jShape++;
				assertEquals( -1, row );
				assertEquals( -1, col );
				break;
			case "LShape":
				lShape++;
				assertEquals( -1, row );
				assertEquals( 1, col );
				break;
			case "OShape":
				oShape++;
				assertEquals( -1, row );
				assertEquals( 0, col );
				break;
			case "SZShape":
				szShape++;
				assertEquals(-1, row );
				assertEquals( 0, col );
				break;
			case "TShape":
				tShape++;
				assertEquals( -1, row );
				assertEquals( 0, col );
				break;
			default:
				fail("Invalid shape: " + c.getSimpleName() );
			};
		}
		assertEquals( numIterations/6, iShape, numIterations * 0.05 );
		assertEquals( numIterations/6, jShape, numIterations * 0.05 );
		assertEquals( numIterations/6, lShape, numIterations * 0.05 );
		assertEquals( numIterations/6, oShape, numIterations * 0.05 );
		assertEquals( numIterations/6, szShape, numIterations * 0.05 );
		assertEquals( numIterations/6, tShape, numIterations * 0.05 );
		assertEquals( numIterations/5, magic, numIterations * 0.05 );
	}
	@Test
	public void testGetNext2(){
		int numIterations = 1000;
		int iShape=0, jShape=0, lShape=0, oShape=0, szShape=0, tShape=0,magic=0;
		for( int i = 0; i < numIterations; i++ ){
			Shape shape = gen.getNext(10);
			Class<? extends Shape > c = shape.getClass();
			int col = shape.getCells()[0].getCol();
			int row = shape.getCells()[0].getRow();
			boolean isMagic = shape.getCells()[0].getBlock().isMagic();
			if( isMagic ){
				magic++;
			}
			switch(c.getSimpleName()){
			case "IShape":
				iShape++;
				assertEquals( -2, row );
				assertEquals( 5, col );
				break;
			case "JShape":
				jShape++;
				assertEquals( -1, row );
				assertEquals( 4, col );
				break;
			case "LShape":
				lShape++;
				assertEquals( -1, row );
				assertEquals( 6, col );
				break;
			case "OShape":
				oShape++;
				assertEquals( -1, row );
				assertEquals( 5, col );
				break;
			case "SZShape":
				szShape++;
				assertEquals( -1, row );
				assertEquals( 5, col );
				break;
			case "TShape":
				tShape++;
				assertEquals( -1, row );
				assertEquals( 5, col );
				break;
			default:
				fail("Invalid shape: " + c.getSimpleName() );
			};
		}
		assertEquals( numIterations/6, iShape, numIterations * 0.05 );
		assertEquals( numIterations/6, jShape, numIterations * 0.05 );
		assertEquals( numIterations/6, lShape, numIterations * 0.05 );
		assertEquals( numIterations/6, oShape, numIterations * 0.05 );
		assertEquals( numIterations/6, szShape, numIterations * 0.05 );
		assertEquals( numIterations/6, tShape, numIterations * 0.05 );
		assertEquals( numIterations/5, magic, numIterations * 0.05 );
	}
}