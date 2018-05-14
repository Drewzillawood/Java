package edu.iastate.cs228.hw1;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class GrassTest {

	private World world;
	private World nWorld;
	
	@Before
	public void setup() {
		
		world = new World(3);
		nWorld = new World(3);
		
		for(int i = 0; i < world.getWidth(); i++) {
			
			for(int j = 0; j < world.getWidth(); j++) {
				
				world.grid[i][j] = new Rabbit(world, i, j, 0);
				nWorld.grid[i][j] = new Rabbit(nWorld, i, j, 0);
				
			}
			
		}
		
	}
	
	@Test
	public void testMoreRabbitsThanGrass() {
		
		world.grid[0][1] = new Grass(world, 0, 1);
		world.grid[1][1] = new Grass(world, 1, 1);
		
		nWorld.grid[1][1] = world.grid[1][1].next(nWorld);
		
		assertEquals(nWorld.grid[1][1].who(), State.EMPTY);
		
	}
	
	@Test
	public void testStillTooManyRabbits() {
		
		world.grid[0][0] = new Grass(world, 0, 0);
		world.grid[1][0] = new Grass(world, 1, 0);
		nWorld.grid[1][0] = world.grid[1][0].next(nWorld);
		
		assertEquals(nWorld.grid[1][0].who(), State.RABBIT);
		
	}
	
	@Test
	public void testGrassLivesOnQuestionMark() {
		
		for(int i = 0; i < world.getWidth(); i++) {
			
			for(int j = 0; j < world.getWidth(); j++) {
				
				world.grid[i][j] = new Fox(world, i, j, 0);
				nWorld.grid[i][j] = new Fox(nWorld, i, j, 0);
				
			}
			
		}
		
		world.grid[0][0] = new Grass(world, 0, 0);
		nWorld.grid[0][0] = world.grid[0][0].next(nWorld);
		assertEquals(nWorld.grid[0][0].who(), State.GRASS);
		
	}
	
}
