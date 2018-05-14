package edu.iastate.cs228.hw2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *  
 * @author drewu
 *
 *
 */



/**
 * 
 * This abstract class is extended by SelectionSort, InsertionSort, MergeSort, and QuickSort.
 * It stores the input (later the sorted) sequence and records the employed sorting algorithm, 
 * the comparison method, and the time spent on sorting. 
 *
 */


public abstract class AbstractSorter {
	
	protected Point[] points;    // Array of points operated on by a sorting algorithm. 
	                             // The number of points is given by points.length.
	
	protected String algorithm = null; // "selection sort", "insertion sort", "mergesort", or
	                                   // "quicksort". Initialized by a subclass. 
									   // constructor.
	protected boolean sortByAngle;     // true if the last sorting was done by polar angle and  
									   // false if by x-coordinate 
	
	protected String outputFileName;   // "select.txt", "insert.txt", "merge.txt", or "quick.txt"
	
	protected long sortingTime; 	   // execution time in nanoseconds. 
	 
	protected Comparator<Point> pointComparator;  // comparator which compares polar angle if 
												  // sortByAngle == true and x-coordinate if 
												  // sortByAngle == false 
	
	private Point lowestPoint; 	    // lowest point in the array, or in case of a tie, the
									// leftmost of the lowest points. This point is used 
									// as the reference point for polar angle based comparison.

	
	// Add other protected or private instance variables you may need. 
	
	protected AbstractSorter() {
		// No implementation needed. Provides a default super constructor to subclasses. 
		// Removable after implementing SelectionSorter, InsertionSorter, MergeSorter, and QuickSorter.
	}
	
	
	/**
	 * This constructor accepts an array of points as input. Copy the points into the array points[]. 
	 * Sets the instance variable lowestPoint.
	 * 
	 * @param  pts  input array of points 
	 * @throws IllegalArgumentException if pts == null or pts.length == 0.
	 */
	protected AbstractSorter(Point[] pts) throws IllegalArgumentException {
		
		if(pts == null || pts.length == 0) {
			
			throw new IllegalArgumentException();
			
		}
		
		points = pts;
		lowestPoint = pts[0];
		
		for(int i = 0; i < pts.length; i++) {
			
			if(lowestPoint.getY() > pts[i].getY()) {
				
				lowestPoint = pts[i];
				
			} else if(lowestPoint.getY() == pts[i].getY() && lowestPoint.getX() > pts[i].getX()) {
				
				lowestPoint = pts[i];
				
			}
			
		}
		
		
		
	}

	
	/**
	 * This constructor reads points from a file. Sets the instance variables lowestPoint and 
	 * outputFileName.
	 * 
	 * @param  inputFileName
	 * @throws FileNotFoundException 
	 * @throws InputMismatchException   when the input file contains an odd number of integers
	 */
	protected AbstractSorter(String inputFileName) throws FileNotFoundException, InputMismatchException {
		
		File file = new File(inputFileName);
		Scanner s = new Scanner(file);

		int i = 0;
		
		while(s.hasNextInt()) {
			
			s.nextInt();
			i++;
			
		}
		
		if(i % 2 == 1) {
			
			s.close();
			throw new InputMismatchException();
			
		}
		
		points = new Point[i/2];
		s.close();
		s = new Scanner(file);
		
		for(i = 0; i < points.length; i++) {
			
			points[i] = new Point(s.nextInt(), s.nextInt());
			
		}
		
		lowestPoint = points[0];
		
		for( i = 0; i < points.length; i++) {
			
			if(lowestPoint.getY() > points[i].getY()) {
				
				lowestPoint = points[i];
				
			} else if(lowestPoint.getY() == points[i].getY() && lowestPoint.getX() > points[i].getX()) {
				
				lowestPoint = points[i];
				
			}
			
		}
		
		s.close();
		
	}
	

	/**
	 * Sorts the elements in points[]. 
	 * 
	 *     a) in the non-decreasing order of x-coordinate if order == 1
	 *     b) in the non-decreasing order of polar angle w.r.t. lowestPoint if order == 2 
	 *        (lowestPoint will be at index 0 after sorting)
	 * 
	 * Sets the instance variable sortByAngle based on the value of order. Calls the method 
	 * setComparator() to set the variable pointComparator and use it in sorting.    
	 * Records the sorting time (in nanoseconds) using the System.nanoTime() method. 
	 * (Assign the time spent to the variable sortingTime.)  
	 * 
	 * @param order  1   by x-coordinate 
	 * 			     2   by polar angle w.r.t lowestPoint 
	 *
	 * @throws IllegalArgumentException if order is less than 1 or greater than 2
	 */
	public abstract void sort(int order) throws IllegalArgumentException; 
	
	
	/**
	 * Outputs performance statistics in the format: 
	 * 
	 * <sorting algorithm> <size>  <time>
	 * 
	 * For instance, 
	 * 
	 * selection sort   1000	  9200867
	 * 
	 * Use the spacing in the sample run in Section 2 of the project description. 
	 */
	public String stats() {
		
		String ret = "";
		
			ret += algorithm + "  " + points.length + "         " + sortingTime;
		
		return ret;
		
	}
	
	
	/**
	 * Write points[] to a string.  When printed, the points will appear in order of increasing
	 * index with every point occupying a separate line.  The x and y coordinates of the point are 
	 * displayed on the same line with exactly one blank space in between. 
	 */
	@Override
	public String toString() {
		
		String ret = "";

		for(int i = 0; i < points.length; i++) {
			
			ret += "(" + points[i].getX() + ", " + points[i].getY() + ")\n";
			
		}
		
		return ret; 

	}

	/**
	 * This method is called after sorting for visually check whether the result is correct.  You  
	 * just need to generate a list of points and a list of segments, depending on the value of 
	 * sortByAngle, as detailed in Section 4.1. Then create a Plot object to call the method myFrame().  
	 */
	public void draw() {
		
		int numSegs = 0;  // number of segments to draw
		
		numSegs = segTotal(points, sortByAngle);
	
		// Based on Section 4.1, generate the line segments to draw for display of the sorting result.
		// Assign their number to numSegs, and store them in segments[] in the order. 
		Segment[] segments = new Segment[numSegs]; 
		
		segments = segList(segments, sortByAngle);
		
		// The following statement creates a window to display the sorting result.
		Plot.myFrame(points, segments, getClass().getName());
		
	}
	
	/**
	 * 	Private method determines size of array for segments required 
	 *  to be plotted.
	 *  
	 * @param p
	 * @param order
	 * @return
	 */
	private int segTotal(Point[] p, Boolean order) {
		
		int segs = 0;
		
		if(!order) {
			
			for(int i = 0; i < points.length-1; i++) {
				
				if(points[i].equals(points[i+1])) {
					
					// Don't Count it!
					
				} else {
					
					segs++;
					
				}
				
			}
			
		} else {
			
			for(int i = 0; i < points.length-1; i++) {
				
				if(points[i].equals(points[i+1])) {
					
					segs--;
					
				} else {
					
					segs++;
					
				}
				
			}
			
			segs += points.length - 2;
			
		}
		
		return segs;
		
	}
	
	/**
	 *  Private method for filling the segments array in draw
	 *  with the proper segments
	 * @param init
	 * @param order
	 * @return
	 */
	private Segment[] segList(Segment[] init, Boolean order) {
		
		int i, j, k, l = 0;
		
		for(i = 0, j = 0; i < points.length-1; i++, j++) {
				
			if(points[i].equals(points[i+1])) {
					
				// Ignore duplicates
				j--;
					
			} else {
					
				init[j] = new Segment(points[i], points[i+1]);
				l++;	
				
			}
		}
		
		if(order) {
			
			for(k = l, j = points.length-1; k < init.length; k++, j--) { 
				
				init[k] = new Segment(points[j], lowestPoint);
				
			}
			
		}
		
		return init;
		
	}
		
	/**
	 * Generates a comparator on the fly that compares by polar angle if sortByAngle == true
	 * and by x-coordinate if sortByAngle == false. Set the protected variable pointComparator
	 * to it. Need to create an object of the PolarAngleComparator class and call the compareTo() 
	 * method in the Point class, respectively for the two possible values of sortByAngle.  
	 * 
	 * @param order
	 */
	protected void setComparator(int order) {
		
		if(order == 2) {
			
			sortByAngle = true;
			pointComparator = new PolarAngleComparator(lowestPoint);
				
		} else if(order == 1){
				
			sortByAngle = false;
			pointComparator =  new Comparator<Point>() {

				@Override
				public int compare(Point p, Point q) {
					
					return p.compareTo(q);
					
				}
				
			};
				
		}
		
	}

	
	/**
	 * Swap the two elements indexed at i and j respectively in the array points[]. 
	 * 
	 * @param i
	 * @param j
	 */
	protected void swap(int i, int j) {
	
		Point temp = points[i];
		points[i] = points[j];
		points[j] = temp;
		
	}	
	
	 /**
	 * This method, called after sorting, writes point data into a file by outputFileName.<br>
	 * The format of data in the file is the same as printed out from toString().<br>
	 * The file can help you verify the full correctness of a sorting result and debug the underlying algorithm.
	 * 
	 * @throws FileNotFoundException
	 */
	 public void writePointsToFile() throws FileNotFoundException {

	     File file = new File(outputFileName);
	     PrintWriter out = new PrintWriter(file);
	     
	     out.println(this.toString());

	     out.close();
	     
	 }
}
