package edu.iastate.cs228.hw1;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import java.io.FileNotFoundException;



public class WorldTest {
	
	private World world;
	private Fox fox;
	
	@Before
	public void setup() throws FileNotFoundException {
		
		String string = "public1.txt";
		world = new World(string);
		fox = new Fox(world, 1, 1, 0);
		
	}
	
	@Test
	public void testInitial() {
		
		// Testing if width of grid is correct
		assertEquals(3, world.grid.length);
		
		// Ensuring file was read properly, and an object was created
		assertNotNull(world);
		
		// Checking a choice out of our object array, comparing to an 
		// identical expected object, in this instance a Fox age and 
		// its status
		assertSame(fox.who(), world.grid[1][1].who());
		assertSame(fox.myAge(), ((Animal)world.grid[1][1]).myAge());
		
	}
	
	@Test 
	public void testToString() {
		
		world = new World(1);
		world.grid[0][0] = new Empty(world, 0, 0);
		
		String string = world.toString();
		assertEquals("E  " + "\n", string);
		
	}
	
	@Test
	public void testToStringLarge() throws FileNotFoundException {
		
		world = new World("public1.txt");
		
		String string = world.toString();
		assertEquals("G  B0 F0 \nF0 F0 R0 \nF0 E  G  \n", string);
		
	}
	
	

}
