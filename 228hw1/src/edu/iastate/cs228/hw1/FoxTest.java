package edu.iastate.cs228.hw1;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class FoxTest {
	
	private World world;
	private World nWorld;
	
	@Before
	public void setup() {
		
		world = new World(5);
		nWorld = new World(5);
		
		
		for(int i = 0; i < world.getWidth(); i++) {
			
			for(int j = 0; j < world.getWidth(); j++) {
				
				world.grid[i][j] = new Fox(world, i, j, 0);
				nWorld.grid[i][j] = new Fox(nWorld, i, j, 0);
				
			}
			
		}
		
	}
	
	@Test 
	public void testGrandpaFox() {
		
		world.grid[0][0] = new Fox(world, 0, 0, 6);
		nWorld.grid[0][0] = world.grid[0][0].next(nWorld);
		
		assertEquals(State.EMPTY,nWorld.grid[0][0].who());
		
	}
	
	@Test
	public void testTooManyBadger() {
		
		for(int i = 0; i < world.getWidth(); i++) {
			
			for(int j = 0; j < world.getWidth(); j++) {
				
				world.grid[i][j] = new Badger(world, i, j, 0);
				nWorld.grid[i][j] = new Badger(nWorld, i, j, 0);
				
			}
			
		}
		
		world.grid[0][0] = new Fox(world, 0, 0, 0);
		nWorld.grid[0][0] = world.grid[0][0].next(nWorld);
		assertEquals(State.BADGER,nWorld.grid[0][0].who());
		
	}
	
	@Test
	public void testTooFewRabbit() {
		
		for(int i = 0; i < world.getWidth(); i++) {
			
			for(int j = 0; j < world.getWidth(); j++) {
				
				world.grid[i][j] = new Empty(world, i, j);
				nWorld.grid[i][j] = new Empty(nWorld, i, j);
				
			}
			
		}
		
		world.grid[0][0] = new Fox(world, 0, 0, 0);
		nWorld.grid[0][0] = world.grid[0][0].next(nWorld);
		assertEquals(State.EMPTY,nWorld.grid[0][0].who());
		
	}
	
	@Test
	public void testLiveOnFox() {
		
		for(int i = 0; i < world.getWidth(); i++) {
			
			for(int j = 0; j < world.getWidth(); j++) {
				
				world.grid[i][j] = new Empty(world, i, j);
				nWorld.grid[i][j] = new Empty(nWorld, i, j);
				
			}
			
		}
	
		world.grid[0][0] = new Fox(world, 0, 0, 0);
		world.grid[0][1] = new Rabbit(world, 0, 1, 0);
		nWorld.grid[0][0] = world.grid[0][0].next(nWorld);
		assertEquals(State.FOX,nWorld.grid[0][0].who());
		assertEquals(1,((Animal)nWorld.grid[0][0]).myAge());
		
	}
	
	
	

}
