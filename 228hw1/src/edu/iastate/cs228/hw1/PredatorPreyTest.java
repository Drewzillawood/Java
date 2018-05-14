package edu.iastate.cs228.hw1;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import java.io.FileNotFoundException;

public class PredatorPreyTest {
	
	private World world;
	private World worldMirror;
	private World finalWorld;
	
	@Before
	public void setup() throws FileNotFoundException {
		
		world = new World("public1.txt");
		worldMirror = new World("public1.txt");
		
		finalWorld = new World(3);
		
		finalWorld.grid[0][0] = new Grass(finalWorld, 0, 0);
		finalWorld.grid[0][1] = new Fox(finalWorld, 0, 1, 0);
		finalWorld.grid[0][2] = new Empty(finalWorld, 0, 2);
		finalWorld.grid[1][0] = new Empty(finalWorld, 1, 0);
		finalWorld.grid[1][1] = new Empty(finalWorld, 1, 1);
		finalWorld.grid[1][2] = new Fox(finalWorld, 1, 2, 0);
		finalWorld.grid[2][0] = new Empty(finalWorld, 2, 0);
		finalWorld.grid[2][1] = new Fox(finalWorld, 2, 1, 0);
		finalWorld.grid[2][2] = new Grass(finalWorld, 2, 2);
		
	}
	
	@Test
	public void testEndResult() {
		
		PredatorPrey.updateWorld(world, worldMirror);
		
		assertEquals(worldMirror, finalWorld);
		
		
	}

}
