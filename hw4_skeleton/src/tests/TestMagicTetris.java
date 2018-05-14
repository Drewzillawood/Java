package tests;

import hw4.MagicTetris;

import api.Block;
import api.Position;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import java.awt.Color;
import java.util.List;
import java.util.ArrayList;

public class TestMagicTetris {
	private MagicTetris game;
	private Block block = new Block( Color.BLUE );
	private Block magicBlock = new Block( Color.BLUE, true );
	@Before
	public void setup(){
		game = new MagicTetris(10, 10);
	}
	@Test
	public void testDeterminePositionToCollapse(){
		ArrayList<Position> expected = new ArrayList<>();
		for( int col = 0; col < 10; col++ ){
			game.setBlock(5, col, block);
			expected.add( new Position(5, col ) );
		}
		List<Position> result = game.determinePositionsToCollapse();
		assertListEquals( result, expected );
	}
	
	@Test
	public void testDeterminePositionToCollapse2(){
		ArrayList<Position> expected = new ArrayList<>();
		for( int col = 0; col < 10; col++ ){
			for( int row = 5; row < 9; row++ ){
				game.setBlock(row, col, block);
				expected.add( new Position( row, col ) );
			}
		}
		game.setBlock( 4, 3, block);
		game.setBlock(3, 3, block);
		List<Position> result = game.determinePositionsToCollapse();
		assertListEquals( result, expected );
	}
	@Test
	public void testDeterminePositionToCollapseGravity(){
		for( int col = 0; col < 10; col++ ){
			game.setBlock(9,  col, magicBlock);
		}
		game.determinePositionsToCollapse();
		ArrayList<Position> expected = new ArrayList<Position>();
		List<Position> result = game.determinePositionsToCollapse();
		assertListEquals( result, expected );
	}
	@Test
	public void testDeterminePositionToCollapseGravity2(){
		for( int col = 0; col < 3; col++ ){
			game.setBlock(9,  col, magicBlock);
		}
		for( int col = 3; col < 10; col++ ){
			game.setBlock(9, col, block);
		}
		game.collapsePositions(game.determinePositionsToCollapse());
		game.setBlock(0, 8, block);
		ArrayList<Position> expected = new ArrayList<Position>();
		expected.add(new Position(1,8));
		expected.add(new Position(2,8));
		expected.add(new Position(3,8));
		expected.add(new Position(4,8));
		expected.add(new Position(5,8));
		expected.add(new Position(6,8));
		expected.add(new Position(7,8));
		expected.add(new Position(8,8));
		expected.add(new Position(9,8));
		List<Position> result = game.determinePositionsToCollapse();
		assertListEquals( result, expected );
	}
	@Test
	public void testDetermineScore(){
		for( int col = 0; col < 10; col++ ){
			game.setBlock(9,  col, block);
		}
		game.determinePositionsToCollapse();
		assertEquals( 1, game.getScore() );
	}
	@Test
	public void testDetermineScore2(){
		for( int col = 0; col < 9; col++ ){
			game.setBlock( 9,  col, block );
		}
		game.setBlock( 9, 9, magicBlock);
		game.determinePositionsToCollapse();
		assertEquals(2, game.getScore());
	}
	@Test
	public void testDetermineScore3(){
		for( int col = 0; col < 7; col++ ){
			game.setBlock(9, col, block );
		}
		game.setBlock(9, 7, magicBlock);
		game.setBlock(9, 8, magicBlock);
		game.setBlock(9, 9, magicBlock);
		game.determinePositionsToCollapse();
		assertEquals(8, game.getScore());
	}
	@Test
	public void exampleGameTests(){
		ArrayList<Position> expected = new ArrayList<Position>();
		MagicTetris game = new MagicTetris(4, 6);
	    List<Position> result = game.determinePositionsToCollapse();
	    assertListEquals( expected, result );
	    
	    // fill some cells to try out, see method below
	    setUpExample(game, false);
	    
	    // print it out, should be:
	    //    - - - -
	    //    - x - x
	    //    - - - -
	    //    x - x -
	    //    x x x x
	    //    x x x -
	    
	    // try our method
	    result = game.determinePositionsToCollapse();
	    expected.clear();// expected [(4, 0), (4, 1), (4, 2), (4, 3)]
	    expected.add( new Position(4,0) );
	    expected.add( new Position(4,1) );
	    expected.add( new Position(4,2) );
	    expected.add( new Position(4,3) );
	    assertListEquals( expected, result );
	    // try collapsing, should get:
	    //    - - - -
	    //    - - - -
	    //    - x - x
	    //    - - - -
	    //    x - x -
	    //    x x x -
	    game.collapsePositions(result);
	    
	    // since we aren't in gravity mode, next call should return empty list
	    result = game.determinePositionsToCollapse();
	    expected.clear(); // expected []
	    assertListEquals( expected, result );
	    
	    // try out gravity mode, put three magic blocks in completed row
	    //    - - - -
	    //    - x - x
	    //    - - - -
	    //    x - x -
	    //    * x * *
	    //    x x x -
	    game = new MagicTetris(4, 6);
	    setUpExample(game, true);
	    
	    // this should put us in "gravity" mode
	    result = game.determinePositionsToCollapse();
	    expected.clear(); // [(4, 0), (4, 1), (4, 2), (4, 3)]
	    expected.add( new Position(4,0) );
	    expected.add( new Position(4,1) );
	    expected.add( new Position(4,2) );
	    expected.add( new Position(4,3) );
	    assertListEquals( expected, result );
	    
	    // Now in gravity mode, this call finds empty positions below nonempty ones
	    // (notice we haven't collapsed anything!)
	    result = game.determinePositionsToCollapse();
	    expected.clear();  // [(2, 1), (3, 1), (2, 3), (3, 3), (5, 3)]
	    expected.add( new Position(2,1) );
	    expected.add( new Position(3,1) );
	    expected.add( new Position(2,3) );
	    expected.add( new Position(3,3) );
	    expected.add( new Position(5,3) );
	    assertListEquals( expected, result );

	    // Now should be out of gravity mode, we just get row 4 again
	    result = game.determinePositionsToCollapse();
	    expected.clear();  // [(4, 0), (4, 1), (4, 2), (4, 3)]
	    expected.add( new Position(4,0) );
	    expected.add( new Position(4,1) );
	    expected.add( new Position(4,2) );
	    expected.add( new Position(4,3) );
	    assertListEquals( expected, result );
	}
	@Test
	public void exampleGameTests2(){
	    // try the same thing, with collapsing
		ArrayList<Position> expected = new ArrayList<Position>();
		MagicTetris game = new MagicTetris(4, 6);
	    List<Position> result = game.determinePositionsToCollapse();
	    setUpExample(game, true);
	    result = game.determinePositionsToCollapse();
	    game.collapsePositions(result);
	    result = game.determinePositionsToCollapse();
	    expected.clear();  // [(3, 1), (4, 1), (3, 3), (4, 3), (5, 3)]
	    expected.add( new Position(3,1) );
	    expected.add( new Position(4,1) );
	    expected.add( new Position(3,3) );
	    expected.add( new Position(4,3) );
	    expected.add( new Position(5,3) );
	    assertListEquals( expected, result );
	    
	    game.collapsePositions(result);
	   
	    // Now should be out of gravity mode, get bottom row
	    result = game.determinePositionsToCollapse();
	    expected.clear();  // [(5, 0), (5, 1), (5, 2), (5, 3)]
	   	expected.add( new Position(5,0) );
	   	expected.add( new Position(5,1) );
	   	expected.add( new Position(5,2) );
	   	expected.add( new Position(5,3) );
	   	assertListEquals( expected, result );
	}
	@Test
	public void exampleGameTests3(){
	    // check score
		MagicTetris game = new MagicTetris(4, 6);
	    List<Position> result = game.determinePositionsToCollapse();
	    setUpExample(game, true);
	    result = game.determinePositionsToCollapse();
	    game.collapsePositions(result);
	    
	    // should be 8 (3 magic blocks, so we get 2 to power 3)
	    assertEquals(8,game.getScore()); 
	        
	    // after collapsing, in gravity mode, the score doesn't change
	    result = game.determinePositionsToCollapse();    
	    assertEquals(8,game.getScore()); // still expected 8
	    game.collapsePositions(result);
	    
	    // we get one point for the bottom row, since it has no magic blocks
	    result = game.determinePositionsToCollapse();    
	    assertEquals(9,game.getScore()); // expected 9
	}
	
	private void assertListEquals( List<Position> result, List<Position> expected ){
		assertTrue( "Expected at least " + expected.size() + ", got " + result.size(), expected.size() <= result.size() );
		expected.forEach( (Position p)->assertTrue( "Did not find: " + p.toString(), result.contains(p)));
		result.forEach( (Position p)->assertTrue( "Did not expect: " + p.toString(), expected.contains(p)));
	}
	
	private void setUpExample(MagicTetris game, boolean addMagic){
	    Block b = new Block(Color.RED, false);
	    game.setBlock(5, 0, b);
	    game.setBlock(5, 1, b);
	    game.setBlock(5, 2, b);
	    
	    game.setBlock(4, 0, b);
	    game.setBlock(4, 1, b);
	    game.setBlock(4, 2, b);
	    game.setBlock(4, 3, b);
	    
	    game.setBlock(3, 0, b);
	    game.setBlock(4, 1, b);
	    game.setBlock(3, 2, b);
	    game.setBlock(4, 3, b);
	    
	    game.setBlock(1, 1, b);
	    game.setBlock(1, 3, b);    
	    
	    if (addMagic){
	    	Block b2 = new Block(Color.RED, true);  // magic
	    	game.setBlock(4, 0, b2);
	    	game.setBlock(4, 2, b2);
	    	game.setBlock(4, 3, b2);
	    }
	}
}