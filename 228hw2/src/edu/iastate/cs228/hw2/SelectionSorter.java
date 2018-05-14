package edu.iastate.cs228.hw2;

import java.io.FileNotFoundException;
import java.util.InputMismatchException; 


/**
 *  
 * @author drewu
 *
 */

/**
 * 
 * This class implements selection sort.   
 *
 */

public class SelectionSorter extends AbstractSorter
{
	// Other private instance variables if you need ... 
	
	/**
	 * The two constructors below invoke their corresponding superclass constructors. They
	 * also set the instance variables algorithm and outputFileName in the superclass.
	 */

	/**
	 * Constructor takes an array of points.
	 *  
	 * @param pts  
	 */
	public SelectionSorter(Point[] pts) {
		
		super(pts);
		algorithm = "Selection Sort     ";
		outputFileName = "select.txt";
		
	}	

	
	/**
	 * Constructor reads points from a file. 
	 * 
	 * @param inputFileName  name of the input file
	 */
	public SelectionSorter(String inputFileName) throws FileNotFoundException, InputMismatchException {
		
		super(inputFileName);
		algorithm = "Selection Sort     ";
		outputFileName = "select.txt";
		
	}
	
	
	/** 
	 * Apply selection sort on the array points[] of the parent class AbstractSorter.  
	 *
	 * @param order  1   by x-coordinate 
	 * 			     2   by polar angle 
	 *
	 */
	@Override 
	public void sort(int order) {
		
		setComparator(order);
		int min = 0;
		
		long startTime = System.nanoTime();
		
		for(int i = 0; i < points.length; i++) {
			
			min = i;
			
			for(int j = i; j < points.length; j++) {
				
				if(pointComparator.compare(points[j], points[min]) == -1) {
				
					min = j;
				
				}
			
			}
			
			swap(i,min);
			
		}
		
		sortingTime = System.nanoTime() - startTime;
		
	}	
}
