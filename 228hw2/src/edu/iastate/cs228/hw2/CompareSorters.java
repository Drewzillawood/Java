package edu.iastate.cs228.hw2;

import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner; 

/**
 *  
 * @author drewu
 *
 */

public class CompareSorters {
	/**
	 * Repeatedly take integer sequences either randomly generated or read from files. 
	 * Perform the four sorting algorithms over each sequence of integers, comparing 
	 * points by x-coordinate or by polar angle with respect to the lowest point.  
	 * 
	 * @param args
	 **/

	public static void main(String[] args) {		

		// Conducts multiple sorting rounds. In each round, performs the following: 
		// 
		//    a) If asked to sort random points, calls generateRandomPoints() to initialize an array 
		//       of random points. 
		//    b) Reassigns to elements in the array sorters[] (declared below) the references to the 
		//       four newly created objects of SelectionSort, InsertionSort, MergeSort and QuickSort. 
		//    c) Based on the input point order, carries out the four sorting algorithms in one for 
		//       loop that iterates over the array sorters[], to sort the randomly generated points
		//       or points from an input file.  
		//    d) Meanwhile, prints out the table of runtime statistics.
		// 
		// A sample scenario is given in Section 2 of the project description. 
		// 	
		AbstractSorter[] sorters = new AbstractSorter[4]; 
		Scanner s = new Scanner(System.in);
		Random r = new Random();
		int trials = 0;
		
		// Within a sorting round, have each sorter object call the sort and draw() methods
		// in the AbstractSorter class.  You can visualize the result of each sort. (Windows 
		// have to be closed manually before rerun.)  Also, print out the statistics table 
		// (cf. Section 2). 
		
		System.out.println("Welcome to Sorting Points in the Plane.\nPlease select one of the following options: ");
		System.out.println("keys:  1 (random integers) 2(file input) 3(exit)");
		
		
		while(true) {
		
			if(trials >= 1) { 
				
				System.out.println("keys:  1 (random integers) 2(file input) 3(exit)");
				
			}
			
			int input = s.nextInt();
			trials++;
			
			if(input == 3) {
				
				s.close();
				break;
			
			}
		
			if(input == 1) {
				
				System.out.println("order: 1 (by x-coordinate) 2(by polar angle)");
				int order = s.nextInt();
				System.out.print("Enter number of random points: ");
				Point[] randGen = new Point[s.nextInt()];
				randGen = generateRandomPoints(randGen.length, r);
				Point[] randGen2 = randGen.clone();
				Point[] randGen3 = randGen.clone();
				Point[] randGen4 = randGen.clone();
				sorters[0] = new SelectionSorter(randGen);
				sorters[1] = new InsertionSorter(randGen2);
				sorters[2] = new MergeSorter(randGen3);
				sorters[3] = new QuickSorter(randGen4);
			
				System.out.println("Trial: " + trials);
				System.out.println("algorithm            size         time (ns)");
				System.out.println("-------------------------------------------");
				for(int i = 0; i < sorters.length; i++) {
					
					sorters[i].sort(order);
					System.out.println(sorters[i].stats());
					sorters[i].draw();
				
					try{
					
						sorters[i].writePointsToFile();
					
					} catch(FileNotFoundException e) {
					
					
					
					}
				
				}
				System.out.println("--------------------------------------------");
				
			} else if(input == 2) {
			
				System.out.println("order: 1 (by x-coordinate) 2(by polar angle)");
				int order = s.nextInt();
				System.out.print("Enter file name: ");
				String file = s.next();
			
				try {
			
					sorters[0] = new SelectionSorter(file);
					sorters[1] = new InsertionSorter(file);
					sorters[2] = new MergeSorter(file);
					sorters[3] = new QuickSorter(file);
			
				} catch (FileNotFoundException e) {
				
					System.out.println("404: File Not found");
					s.close();
					return;
				
				}
			
				System.out.println("Trial: " + trials);
				System.out.println("algorithm            size         time (ns)");
				System.out.println("-------------------------------------------");
				for(int i = 0; i < sorters.length; i++) {
					
					sorters[i].sort(order);
					System.out.println(sorters[i].stats());
					sorters[i].draw();
				
					try {
					
						sorters[i].writePointsToFile();
					
					} catch(FileNotFoundException e) {
					
					
					
					}
				
					
				}
				System.out.println("--------------------------------------------");
			
			} 
		
		}
		
	}
	
	/**
	 * This method generates a given number of random points to initialize randomPoints[].
	 * The coordinates of these points are pseudo-random numbers within the range 
	 * [-50,50] × [-50,50]. Please refer to Section 3 on how such points can be generated.
	 * 
	 * Ought to be private. Made public for testing. 
	 * 
	 * @param numPts  	number of points
	 * @param rand      Random object to allow seeding of the random number generator
	 * @throws IllegalArgumentException if numPts < 1
	 */
	public static Point[] generateRandomPoints(int numPts, Random rand) throws IllegalArgumentException { 
		
		if(numPts < 1) {
			
			throw new IllegalArgumentException();
			
		}
		
		Point[] points = new Point[numPts];
		
			for(int i = 0; i < numPts; i++) {
			
				points[i] = new Point(rand.nextInt(101) - 50, rand.nextInt(101)- 50);
			
			}
			
		return points; 
		
	}
}
