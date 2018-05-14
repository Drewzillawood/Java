package edu.iastate.cs228.hw1;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class AnimalTest {
	
	private World world;
	private Fox fox;
	
	@Before
	public void setup() {
		
		world = new World(5);
		fox = new Fox(world, 2, 2, 2);
		
	}
	
	@Test
	public void testMyAge() {
		
		assertEquals(2, fox.myAge());
		
	}

}
