package edu.iastate.cs228.hw1;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;


public class EmptyTest {
	
	private World world;
	private World nWorld;
	
	@Before
	public void setup() {
		
		world = new World(4);
		nWorld = new World(4);
		
		for(int i = 0; i < world.getWidth(); i++) {
			
			for(int j = 0; j < world.getWidth(); j++) {
				
				world.grid[i][j] = new Empty(world, i, j);
				nWorld.grid[i][j] = new Empty(nWorld, i, j);
				
			}
			
		}
		
	}
	
	@Test
	public void testRabbitPresence() {
		
		world.grid[1][0] = new Rabbit(world, 1, 0, 0);
		world.grid[1][1] = new Rabbit(world, 1, 1, 0);
		
		nWorld.grid[0][0] = world.grid[0][0].next(nWorld);
		assertEquals(State.RABBIT, nWorld.grid[0][0].who());
		
	}
	
	@Test
	public void testFoxPresence() {
		
		world.grid[1][0] = new Fox(world, 1, 0, 0);
		world.grid[1][1] = new Fox(world, 1, 1, 0);
		
		nWorld.grid[0][0] = world.grid[0][0].next(nWorld);
		assertEquals(State.FOX, nWorld.grid[0][0].who());
		
	}
	
	@Test
	public void testBadgerPresence() {
		
		world.grid[1][0] = new Badger(world, 1, 0, 0);
		world.grid[1][1] = new Badger(world, 1, 1, 0);
		
		nWorld.grid[0][0] = world.grid[0][0].next(nWorld);
		assertEquals(State.BADGER, nWorld.grid[0][0].who());
		
	}
	
	@Test
	public void testLawn() {
		
		world.grid[1][0] = new Grass(world, 1, 0);
		world.grid[1][1] = new Grass(world, 1, 1);
		
		nWorld.grid[0][0] = world.grid[0][0].next(nWorld);
		assertEquals(State.GRASS, nWorld.grid[0][0].who());
		
	}

}
