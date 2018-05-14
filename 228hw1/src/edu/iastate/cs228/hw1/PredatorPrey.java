package edu.iastate.cs228.hw1;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *  
 * @author drewu
 *
 */

/**
 * 
 * The PredatorPrey class performs the predator-prey simulation over a grid
 * world with squares occupied by badgers, foxes, rabbits, grass, or none.
 *
 */
public class PredatorPrey {
	/**
	 * Update the new world from the old world in one cycle.
	 * 
	 * @param wOld
	 *            old world
	 * @param wNew
	 *            new world
	 */
	public static void updateWorld(World wOld, World wNew) {
		
		// For every life form (i.e., a Living object) in the grid wOld,
		// generate
		// a Living object in the grid wNew at the corresponding location such
		// that
		// the former life form changes into the latter life form.
		//
		// Employ the method next() of the Living class.
		
		// Double for loop iterates through every cell of our
		// grid
		for(int i = 0; i < wOld.grid.length; i++) {
			
			for(int j = 0; j < wOld.grid.length; j++) {
				
				// Update our new world with data from the old world at each cell
				wNew.grid[i][j] = wOld.grid[i][j].next(wNew);
				
			}
			
		}	
		
	}

	/**
	 * Repeatedly generates worlds either randomly or from reading files. Over
	 * each world, carries out an input number of cycles of evolution.
	 * 
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {

		// Generate predator-prey simulations repeatedly like shown in the
		// sample run in the project description.
		//
		// 1. Enter 1 to generate a random world, 2 to read a world from an
		// input
		// file, and 3 to end the simulation. (An input file always ends with
		// the suffix .txt.)
		//
		// 2. Print out standard messages as given in the project description.
		//
		// 3. For convenience, you may define two worlds even and odd as below.
		// In an even numbered cycle (starting at zero), generate the world
		// odd from the world even; in an odd numbered cycle, generate even
		// from odd.
		// 4. Print out initial and final worlds only. No intermediate worlds
		// should
		// appear in the standard output. (When debugging your program, you can
		// print intermediate worlds.)
		//
		// 5. You may save some randomly generated worlds as your own test
		// cases.
		//
		// 6. It is not necessary to handle file input & output exceptions for
		// this
		// project. Assume data in an input file to be correctly formated.	
		
		// Scanner for reading user input
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please select an option below: \n 1: Generate a Random world. \n 2: Read a world from an input file. \n 3: End this sumulation. \n");
		
		// While loop will continue to await user input
		while(scanner.hasNextInt()) {
			
			int in = scanner.nextInt();
			
			// Random world generation has been selected
			if(in == 1) {
				
				System.out.println("What size shall the world be: ");
				
				// Read in user input for size of World
				in = scanner.nextInt();
				
				// Initialize two world, so that one 
				// can be updated from the other
				World odd = new World(in);
				World even = new World(in);
				even.randomInit();
				odd.randomInit();
				
				// Display the world, request number of cycles
				System.out.println("Here is the initial World:\n" + odd.toString());
				System.out.println("How many cycles shall be iterated: ");
				in = scanner.nextInt();
				
				// Loop through the number of cycles and 
				// switch the old and new worlds each cycle as 
				// to be sure no interference between new
				// and old worlds
				for(int i = 0; i < in; i++) {
					
					if((i%2 == 0)) {
						
						updateWorld(odd, even);
						
					} else {
						
						updateWorld(even, odd);
						
					}
					
				}
				
				// Display only the final world, even, for even number of cycles
				if((in%2) == 0) {
					
					System.out.println("Here is the final World: \n" + even.toString());
					
				// And odd for odd number of cycles
				} else {
					
					System.out.println("Here is the final World: \n" + odd.toString());
					
				}
				
				break;
				
			// File input generation has been selected
			} else if(in == 2) {
				
				System.out.println("Please enter the file name: ");
				
				// Read user input through scanner
				String init = scanner.next();
				World odd = new World(init);
				World even = new World(init);
				
				// Display initial world and request number of cycles 
				System.out.println("Here is our initial World: \n " + odd.toString());
				System.out.println("How many cycles are to be iterated: ");
				in = scanner.nextInt();
				
				// Loop through the number of cycles and 
				// switch the old and new worlds each cycle as 
				// to be sure no interference between new
				// and old worlds
				for(int i = 0; i < in; i++) {
					
					if((i%2 == 0)) {
						
						updateWorld(odd, even);
						
					} else {
						
						updateWorld(even, odd);
						
					}
					
				}
				
				// Display only the final world, even, for even number of cycles
				if((in%2) == 0) {
					
					System.out.println("Here is the final World: \n" + odd.toString());
					
				// And odd for odd number of cycles
				} else {
					
					System.out.println("Here is the final World: \n" + even.toString());
					
				}
				
				scanner.close();
				break;
				
				
			// End simulation has been selected
			} else if(in == 3) {
				
				break;
							
			}
			
		}
		
		scanner.close();

		


		
		
	}
}
