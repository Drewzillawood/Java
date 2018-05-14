package edu.iastate.cs228.hw4;

import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 * 
 * @author drewu
 *
 */

public class CompareHullAlgorithms {
	/**
	 * Repeatedly take points either randomly generated or read from files.
	 * Perform Graham's scan and Jarvis' march over the input set of points,
	 * comparing their performances.
	 * 
	 * @param args
	 **/
	public static void main(String[] args) {

		// Conducts multiple rounds of convex hull construction. Within each
		// round, performs the following:
		//
		// 1) If the input are random points, calls generateRandomPoints() to
		// initialize an array
		// pts[] of random points. Use pts[] to create two objects of GrahamScan
		// and JarvisMarch,
		// respectively.
		//
		// 2) If the input is from a file, construct two objects of the classes
		// GrahamScan and
		// JarvisMarch, respectively, using the file.
		//
		// 3) Have each object call constructHull() to build the convex hull of
		// the input points.
		//
		// 4) Meanwhile, prints out the table of runtime statistics.
		//
		// A sample scenario is given in Section 4 of the project description.
		//
		ConvexHull[] algorithms = new ConvexHull[2];
		Scanner s = new Scanner(System.in);
		Random r = new Random();
		int trials = 0;
		
		System.out.println("Welcome to Compare Hull Algorithms.\nPlease Select one of the following options: ");
		System.out.println("keys: 1 (random generation) 2 (file input) 3 (exit)");
		
		while(true) {
			
			if(trials >= 1) {
				
				System.out.println("keys: 1 (random generation) 2 (file input) 3 (exit)");
				
			}
			
			int input = s.nextInt();
			trials++;
			
			if(input == 3) {
				
				s.close();
				break;
				
			}
			
			if(input == 1) {
				
				System.out.print("Enter number of random points: ");
				Point[] randGen = new Point[s.nextInt()];
				randGen = generateRandomPoints(randGen.length, r);
				Point[] randGen2 = randGen.clone();
				
				algorithms[0] = new GrahamScan(randGen);
				algorithms[0].constructHull();
				algorithms[1] = new JarvisMarch(randGen2);
				algorithms[1].constructHull();
				algorithms[0].writeHullToFile();
				algorithms[0].draw();
				algorithms[1].draw();
				
				System.out.println("Trial: " + trials);
				System.out.print(String.format("%1$-16s %2$-16s %3$s \n------------------------------------------\n", "algorithm", "size", "time (ns)"));
				System.out.print(algorithms[0].stats());
				System.out.print(algorithms[1].stats());
				System.out.print("------------------------------------------\n");
				
			} else if(input == 2) {
				
				System.out.print("Enter File name: ");
				String file = s.next();
			
				try {
						
					algorithms[0] = new GrahamScan(file);
					algorithms[1] = new JarvisMarch(file);
					
				} catch (InputMismatchException | FileNotFoundException e) {
					
					System.out.println("404: File Not Found");
					s.close();
					return;
					
				}
				
				algorithms[0].constructHull();
				algorithms[1].constructHull();
				algorithms[0].writeHullToFile();
				algorithms[0].draw();
				algorithms[1].draw();
				
				System.out.println("Trial: " + trials);
				System.out.println("Points from a file");
				System.out.print(String.format("%1$-16s %2$-16s %3$s \n------------------------------------------\n", "algorithm", "size", "time (ns)"));
				System.out.print(algorithms[0].stats());
				System.out.print(algorithms[1].stats());
				System.out.print("------------------------------------------\n");	
				
			}
			
			
			
		}
		
		// Within a hull construction round, have each algorithm call the
		// constructHull() and draw()
		// methods in the ConvexHull class. You can visually check the result.
		// (Windows
		// have to be closed manually before rerun.) Also, print out the
		// statistics table
		// (see Section 4).

	}

	/**
	 * This method generates a given number of random points. The coordinates of
	 * these points are pseudo-random numbers within the range [-50,50] ×
	 * [-50,50].
	 * 
	 * Reuse your implementation of this method in the CompareSorter class from
	 * Project 2.
	 * 
	 * @param numPts
	 *            number of points
	 * @param rand
	 *            Random object to allow seeding of the random number generator
	 * @throws IllegalArgumentException
	 *             if numPts < 1
	 */
	private static Point[] generateRandomPoints(int numPts, Random rand) throws IllegalArgumentException {

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
