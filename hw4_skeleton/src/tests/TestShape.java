package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import api.Block;
import api.Cell;
import api.Position;
import api.Shape;
import hw4.AbstractShape;

public abstract class TestShape {
	protected AbstractShape shape;
	protected Block genericBlock;
	protected Block magicBlock;
	protected Position position;
	
	protected TestShape(Color c){
		genericBlock = new Block( c );
		magicBlock = new Block( c, true );
	}

	@Before
	public abstract void setup();
	
	@Test
	public final void testConstruction(){
		Cell[] cells = shape.getCells();
		assertEqualsOriginal( cells );
	}
	
	@Test
	public abstract void testConstruction2();
	@Test
	public abstract void testConstructionMagic();
	@Test
	public abstract void testTransform();
	@Test
	public abstract void testTransform2();
	@Test
	public abstract void testTransform3();
	@Test
	public abstract void testShiftDown();
	@Test
	public abstract void testShiftLeft();
	@Test
	public abstract void testShiftRight();
	/**
	 * Note: Ensure passing shiftDown before attempting this
	 */
	@Test
	public abstract void testTransform4();
	@Test
	public abstract void testCycle();
	@Test
	public abstract void testCycle2();

	
	@Test
	public final void testGetCells(){
		Cell[] cells = shape.getCells();
		shape.shiftDown();
		assertEqualsOriginal( cells );
	}
	@Test
	public final void testGetCells2(){
		shape.getCells()[0].setBlock(magicBlock);;
		assertEqualsOriginal( shape.getCells() );
	}
	
	@Test
	public final void testGetCells3(){
		shape.getCells()[0] = new Cell( magicBlock, new Position(10, 10) );
		assertEqualsOriginal( shape.getCells() );
	}
	
	/**
	 * Note: pass all other tests before attempting clone
	 */
	@Test
	public final void testClone(){
		Shape starting = shape.clone();
		assertNotNull( starting );
		shape.shiftDown();
		Cell[] startingCells = starting.getCells();
		Cell[] endingCells = shape.getCells();
		for( int i = 0; i < startingCells.length; i++ ){
			assertNotEquals( "Cell " + i, startingCells[i], endingCells[i] );
		}
	}
	
	@Test
	public final void testClone2(){
		shape.shiftDown();
		Shape starting = shape.clone();
		assertNotNull( starting );
		Cell[] startingCells = starting.getCells();
		Cell[] endingCells = shape.getCells();
		for( int i = 0; i < startingCells.length; i++ ){
			assertEquals( "Cell " + i, startingCells[i], endingCells[i] );
		}
	}
	
	@Test
	public final void testClone3(){
		shape.transform();
		Shape starting = shape.clone();
		assertNotNull( starting );
		Cell[] startingCells = starting.getCells();
		Cell[] endingCells = shape.getCells();
		for( int i = 0; i < startingCells.length; i++ ){
			assertEquals( "Cell " + i, startingCells[i], endingCells[i] );
		}
	}
	
	protected abstract void assertEqualsOriginal( Cell[] cells );
}