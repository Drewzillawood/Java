package edu.iastate.cs228.hw3;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
//import java.io.FileNotFoundException;


public class DoublySortedListTest {

	private DoublySortedList DSL;
	
	@Before
	public void setup() {
		
		DSL = new DoublySortedList();
		
	}
	
	@Test
	public void testSize() {
		
		assertEquals(0, DSL.size());
		
	}
	

	@Test
	public void testAdd() {
		
		DSL.add("Orange", 50);
		
		
	}
	
}
