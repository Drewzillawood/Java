package tests;

import static org.junit.Assert.assertEquals;

import java.awt.Color;

import api.Cell;
import api.Position;
import hw4.TShape;

public class TestTShape extends TestShape {
	
	public TestTShape(){
		super(Color.MAGENTA);
	}
	
	@Override
	public void setup(){
		position = new Position(0,0);
		shape = new TShape(position,false);
	}
	
	@Override
	public void testConstruction2(){
		position = new Position( 10, 100 );
		shape = new TShape( position, false );
		Cell[] cells = shape.getCells();
		assertEquals( "Cell 0", new Cell( genericBlock, new Position(9,100) ), cells[0] );
		assertEquals( "Cell 1", new Cell( genericBlock, new Position(10,99) ), cells[1] );
		assertEquals( "Cell 2", new Cell( genericBlock, new Position(10,100) ), cells[2] );
		assertEquals( "Cell 3", new Cell( genericBlock, new Position(10,101) ), cells[3] );
	}
	
	@Override
	public void testConstructionMagic(){
		shape = new TShape(position,true);
		Cell[] cells = shape.getCells();
		assertEquals( "Cell 0", new Cell( magicBlock, new Position(-1,0) ), cells[0] );
		assertEquals( "Cell 1", new Cell( genericBlock, new Position(0,-1) ), cells[1] );
		assertEquals( "Cell 2", new Cell( genericBlock, new Position(0,0) ), cells[2] );
		assertEquals( "Cell 3", new Cell( genericBlock, new Position(0,1) ), cells[3] );
	}
	
	@Override
	public void testTransform(){
		shape.transform();
		Cell[] cells = shape.getCells();
		assertEquals( "Cell 0", new Cell( genericBlock, new Position(0,-1) ), cells[0] );
		assertEquals( "Cell 1", new Cell( genericBlock, new Position(1,0) ), cells[1] );
		assertEquals( "Cell 2", new Cell( genericBlock, new Position(0,0) ), cells[2] );
		assertEquals( "Cell 3", new Cell( genericBlock, new Position(-1,0) ), cells[3] );
	}
	
	@Override
	public void testTransform2() {
		shape.transform();
		shape.transform();
		Cell[] cells = shape.getCells();
		assertEquals( "Cell 0", new Cell( genericBlock, new Position(1,0) ), cells[0] );
		assertEquals( "Cell 1", new Cell( genericBlock, new Position(0,1) ), cells[1] );
		assertEquals( "Cell 2", new Cell( genericBlock, new Position(0,0) ), cells[2] );
		assertEquals( "Cell 3", new Cell( genericBlock, new Position(0,-1) ), cells[3] );
		
	}

	@Override
	public void testTransform3() {
		shape.transform();
		shape.transform();
		shape.transform();
		Cell[] cells = shape.getCells();
		assertEquals( "Cell 0", new Cell( genericBlock, new Position(0,1) ), cells[0] );
		assertEquals( "Cell 1", new Cell( genericBlock, new Position(-1,0) ), cells[1] );
		assertEquals( "Cell 2", new Cell( genericBlock, new Position(0,0) ), cells[2] );
		assertEquals( "Cell 3", new Cell( genericBlock, new Position(1,0) ), cells[3] );
	}

	@Override
	public void testShiftDown(){
		shape.shiftDown();
		Cell[] cells = shape.getCells();
		assertEquals( "Cell 0", new Cell( genericBlock, new Position(0,0) ), cells[0] );
		assertEquals( "Cell 1", new Cell( genericBlock, new Position(1,-1) ), cells[1] );
		assertEquals( "Cell 2", new Cell( genericBlock, new Position(1,0) ), cells[2] );
		assertEquals( "Cell 3", new Cell( genericBlock, new Position(1,1) ), cells[3] );
	}
	
	@Override
	public void testShiftLeft(){
		shape.shiftLeft();
		Cell[] cells = shape.getCells();
		assertEquals( "Cell 0", new Cell( genericBlock, new Position(-1,-1) ), cells[0] );
		assertEquals( "Cell 1", new Cell( genericBlock, new Position(0,-2) ), cells[1] );
		assertEquals( "Cell 2", new Cell( genericBlock, new Position(0,-1) ), cells[2] );
		assertEquals( "Cell 3", new Cell( genericBlock, new Position(0,0) ), cells[3] );
	}
	
	@Override
	public void testShiftRight(){
		shape.shiftRight();
		Cell[] cells = shape.getCells();
		assertEquals( "Cell 0", new Cell( genericBlock, new Position(-1,1) ), cells[0] );
		assertEquals( "Cell 1", new Cell( genericBlock, new Position(0,0) ), cells[1] );
		assertEquals( "Cell 2", new Cell( genericBlock, new Position(0,1) ), cells[2] );
		assertEquals( "Cell 3", new Cell( genericBlock, new Position(0,2) ), cells[3] );
	}
	
	/**
	 * Note: Ensure passing shiftDown before attempting this
	 */
	@Override
	public void testTransform4(){
		shape.shiftDown();
		shape.transform();
		Cell[] cells = shape.getCells();
		assertEquals( "Cell 0", new Cell( genericBlock, new Position(1,-1) ), cells[0] );
		assertEquals( "Cell 1", new Cell( genericBlock, new Position(2,0) ), cells[1] );
		assertEquals( "Cell 2", new Cell( genericBlock, new Position(1,0) ), cells[2] );
		assertEquals( "Cell 3", new Cell( genericBlock, new Position(0,0) ), cells[3] );
	}
	
	@Override
	public void testCycle(){
		shape = new TShape(position,true);
		shape.cycle();
		Cell[] cells = shape.getCells();
		assertEquals( "Cell 0", new Cell( genericBlock, new Position(-1,0) ), cells[0] );
		assertEquals( "Cell 1", new Cell( magicBlock, new Position(0,-1) ), cells[1] );
		assertEquals( "Cell 2", new Cell( genericBlock, new Position(0,0) ), cells[2] );
		assertEquals( "Cell 3", new Cell( genericBlock, new Position(0,1) ), cells[3] );
	}
	
	@Override
	public void testCycle2(){
		shape = new TShape(position,true);
		shape.cycle();
		shape.cycle();
		shape.cycle();
		Cell[] cells = shape.getCells();
		assertEquals( "Cell 0", new Cell( genericBlock, new Position(-1,0) ), cells[0] );
		assertEquals( "Cell 1", new Cell( genericBlock, new Position(0,-1) ), cells[1] );
		assertEquals( "Cell 2", new Cell( genericBlock, new Position(0,0) ), cells[2] );
		assertEquals( "Cell 3", new Cell( magicBlock, new Position(0,1) ), cells[3] );
	}

	@Override
	protected void assertEqualsOriginal(Cell[] cells) {
		// TODO Auto-generated method stub
		
	}
}