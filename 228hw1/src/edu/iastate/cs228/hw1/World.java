package edu.iastate.cs228.hw1;

/**
 *  
 * @author drewu
 *
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Random;

/**
 * 
 * The world is represented as a square grid of size width X width.
 *
 */
public class World {
	
	private int width; // grid size: width X width

	public Living[][] grid;

	/**
	 * Default constructor reads from a file
	 */
	public World(String inputFileName) throws FileNotFoundException {
		
		// Assumption: The input file is in correct format.
		//
		// You may create the grid world in the following steps:
		//
		// 1) Reads the first line to determine the width of the grid.
		//
		// 2) Creates a grid object.
		//
		// 3) Fills in the grid according to the input file.
		//
		// Be sure to close the input file when you are done.

		width = 0;
		
		// Declare file with input file name
		File file = new File(inputFileName);
		Scanner scanner = new Scanner(file);

		// Determine width by counting through the lines
		while (scanner.hasNextLine()) {
			
			width++;
			scanner.nextLine();

		}
		
		// Declare our grid with Width*Width dimensions
		grid = new Living[width][width];

		// Close our file so that we can
		// start from the beginning
		scanner.close();
		
		// Re-open file for initializing our world
		scanner = new Scanner(file);
		
		
		// Iterate through every string
		for (int i = 0; i < width; i++) {

			for (int j = 0; j < width; j++) {

				String temp = scanner.next();

				// Conditional statements determine Living State through identifying
				// the first char of each string
				if (temp.charAt(0) == 'F') {

					grid[i][j] = new Fox(this, i, j, Character.getNumericValue(temp.charAt(1)));      //
																									  //
				} else if (temp.charAt(0) == 'B') {													  //
																									  //  If state is of Class Animal, 
					grid[i][j] = new Badger(this, i, j, Character.getNumericValue(temp.charAt(1)));   //  an age is retrieved by converting
																									  //  the second char of the string to Int
				} else if (temp.charAt(0) == 'R') {													  //
																									  //
					grid[i][j] = new Rabbit(this, i, j, Character.getNumericValue(temp.charAt(1)));   //

				} else if (temp.charAt(0) == 'G') {

					grid[i][j] = new Grass(this, i, j);

				} else {

					grid[i][j] = new Empty(this, i, j);

				}

			}

		}
		scanner.close();
	}

	/**
	 * Constructor that builds a w X w grid without initializing it.
	 * 
	 * @param width
	 *            the grid
	 */
	public World(int w) {
		
		// Initialize our instance variables
		width = w;
		grid = new Living[w][w];
		
	}
	
	/**
	 * Getter method for grid width
	 * 
	 * @return width
	 */
	public int getWidth() {

		return width;

	}

	/**
	 * Initialize the world by randomly assigning to every square of the grid
	 * one of BADGER, FOX, RABBIT, GRASS, or EMPTY.
	 * 
	 * Every animal starts at age 0.
	 */
	public void randomInit() {
		
		// Random number generator
		Random generator = new Random();
		
		// Cycle through our entire grid
		for(int i = 0; i < grid.length; i++) {
			
			for(int j = 0; j < grid.length; j++) {
				
				// Random number generator given max value, NUM_LIFE_FORMS
				// which is defined in living
				int temp = generator.nextInt(Living.NUM_LIFE_FORMS);
				
				// Conditional statements assigned to cell based
				// on result from random
				if(temp == Living.BADGER) {
					
					grid[i][j] = new Badger(this, i, j, 0);
					
				} else if(temp == Living.FOX) {
					
					grid[i][j] = new Fox(this, i, j, 0);
					
				} else if(temp == Living.RABBIT) {
					
					grid[i][j] = new Rabbit(this, i, j, 0);
					
				} else if(temp == Living.GRASS) {
					
					grid[i][j] = new Grass(this, i, j);
					
				} else if(temp == Living.EMPTY) {
					
					grid[i][j] = new Empty(this, i, j);
					
				}
				
			}
			
			
			
		}

		
		
	}

	/**
	 * Output the world grid. For each square, output the first letter of the
	 * living form occupying the square. If the living form is an animal, then
	 * output the age of the animal followed by a blank space; otherwise, output
	 * two blanks.
	 */
	public String toString() {
		
		// Blank string to begin with
		String ret = "";
		
		// Cycle through each cell in the grid
		for(int i = 0; i < width; i++) {
			
			for(int j = 0; j < width; j++) {
				
				// Conditional statements determine letter and number of spaces
				if(grid[i][j].who().equals(State.BADGER)) {
					
					ret += "B" + Integer.toString(((Badger)grid[i][j]).myAge()) + " ";
					
				} else if(grid[i][j].who().equals(State.FOX)) {
					
					ret += "F" + Integer.toString(((Fox)grid[i][j]).myAge()) + " ";
					
				} else if(grid[i][j].who().equals(State.RABBIT)) {
					
					ret += "R" + Integer.toString(((Rabbit)grid[i][j]).myAge()) + " ";
					
				} else if(grid[i][j].who().equals(State.GRASS)) {
					
					ret += "G" + "  ";
					
				} else if(grid[i][j].who().equals(State.EMPTY)) {
					
					ret += "E" + "  ";
					
				}
				
			}
			
			ret += "\n";
			
		}
		
		return ret;
	}

	/**
	 * Write the world grid to an output file. Also useful for saving a randomly
	 * generated world for debugging purpose.
	 * 
	 * @throws FileNotFoundException
	 */
	public void write(String outputFileName) throws FileNotFoundException {
		
		File outFile = new File(outputFileName);
		PrintWriter out = new PrintWriter(outFile);
		
		out.print(this.toString());
		
		out.close();
		
	}
}
