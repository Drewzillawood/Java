package edu.iastate.cs228.hw1;

import static org.junit.Assert.assertArrayEquals;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

public class LivingTest {
	
	private World world;
	private int[] checker;
	private int[] population;
	
	@Before
	public void setup() throws FileNotFoundException {
		
		world = new World("public1.txt");
	
		checker = new int[5];
		checker[0] = 1;
		checker[1] = 1;
		checker[2] = 4;
		checker[3] = 2;
		checker[4] = 1;
		population = new int[5];
	
		
	}
	
	@Test
	public void testCensus() {

		
		
		world.grid[1][1].census(population);
		assertArrayEquals(checker, population);
		
	}

}
