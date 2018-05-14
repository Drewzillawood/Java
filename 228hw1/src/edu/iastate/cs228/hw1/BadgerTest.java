package edu.iastate.cs228.hw1;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;


public class BadgerTest {
	
	private World world;
	private World nWorld;
	@SuppressWarnings("unused")
	private Badger badger;
	@SuppressWarnings("unused")
	private Grass grass;
	
	@Before
	public void setup() throws FileNotFoundException {
		
		world = new World("public1.txt");
		nWorld = new World("public1.txt");
		
	}
	
	@Test
	public void testInitial() {
		
		assertEquals(State.BADGER, world.grid[0][1].who());
		
	}
	
	@Test
	public void testManyFox() {
		
		nWorld.grid[0][1] = world.grid[0][1].next(nWorld);
		
		assertEquals(State.FOX, nWorld.grid[0][1].who());
		
	}
	
	@Test
	public void testOldAge() {
		
		world.grid[0][1] = new Badger(world, 0 , 1, 4);
		
		nWorld.grid[0][1] = world.grid[0][1].next(nWorld);
		
		assertEquals(State.EMPTY, nWorld.grid[0][1].who());
		
	}
	
	@Test
	public void testTooManyFoxAndBadger() {
		
		world.grid[0][0] = new Badger(world, 0, 0, 0);
		world.grid[1][0] = new Badger(world, 1, 0, 0);
		
		nWorld.grid[0][0] = world.grid[0][0].next(nWorld);
		
		assertEquals(State.EMPTY, nWorld.grid[0][0].who());
		
	}
	
	@Test
	public void liveOn() {
		
		world.grid[1][1] = new Badger(world, 1, 1, 0);
		world.grid[2][1] = new Rabbit(world, 0, 0, 0);
		world.grid[2][2] = new Badger(world, 2, 2, 0);
		
		nWorld.grid[2][2] = world.grid[2][2].next(nWorld);

		assertEquals(State.BADGER, world.grid[2][2].who());
		assertEquals(1, ((Animal)world.grid[2][2]).myAge());
		
	}
	
	

}
