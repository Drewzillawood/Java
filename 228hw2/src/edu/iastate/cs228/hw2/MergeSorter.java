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
 * This class implements the mergesort algorithm.   
 *
 */

public class MergeSorter extends AbstractSorter
{
	// Other private instance variables if you need ... 
	
	/**
	 * The two constructors below invoke their corresponding superclass constructors. They
	 * also set the instance variables algorithm and outputFileName in the superclass.
	 */

	/** 
	 * Constructor accepts an input array of points. 
	 * in the array. 
	 *  
	 * @param pts   input array of integers
	 */
	public MergeSorter(Point[] pts)  {
		
		super(pts);
		algorithm = "Mergesort          ";
		outputFileName = "merge.txt";
		
	}
	
	
	/**
	 * Constructor reads points from a file. 
	 * 
	 * @param inputFileName  name of the input file
	 */
	public MergeSorter(String inputFileName) throws FileNotFoundException, InputMismatchException {

		super(inputFileName);
		algorithm = "Mergesort          ";
		outputFileName = "merge.txt";
		
	}


	/**
	 * Perform mergesort on the array points[] of the parent class AbstractSorter. 
	 * 
	 * @param order  1   by x-coordinate 
	 * 			     2   by polar angle 
	 *
	 */
	@Override 
	public void sort(int order) {
		
		setComparator(order);
		long startTime = System.nanoTime();
		
		mergeSortRec(points);
		
		sortingTime = System.nanoTime() - startTime;
		
	}

	
	/**
	 * This is a recursive method that carries out mergesort on an array pts[] of points. One 
	 * way is to make copies of the two halves of pts[], recursively call mergeSort on them, 
	 * and merge the two sorted subarrays into pts[].   
	 * 
	 * @param pts	point array 
	 */
	private void mergeSortRec(Point[] pts) {
		
		int n = pts.length;
		if(n == 1) {
			
			return;
			
		}
		
		int m = n/2;
		
		Point[] leftHalf =  new Point[m];
		Point[] rightHalf = new Point[m + n%2];
		
		for(int i = 0; i < m; i++) {
			
			leftHalf[i] = pts[i];
			
		}
		
		for(int j = m, i = 0; j < n; j++, i++) {
			
			rightHalf[i] = pts[j];
			
		}
		
		mergeSortRec(leftHalf);
		mergeSortRec(rightHalf);
		Point[] temp = merge(leftHalf, rightHalf);
		
		for(int i = 0; i < temp.length; i++) {
			
			pts[i] = temp[i];
			
		}
		
	}

	private Point[] merge(Point[] A, Point B[]) {
		
		Point[] merged = new Point[A.length + B.length];
		
		int i = 0, j = 0;
		
		while(i < A.length && j < B.length) {
			
			if(pointComparator.compare(A[i], B[j]) == -1) {
				
				merged[i+j] = A[i];
				i++;
				
			} else {
				
				merged[i+j] = B[j];
				j++;
				
			}
			
		}
		
		if(i >= A.length) {
			
			while(j < B.length) {
				
				merged[i+j] = B[j];
				j++;
				
			}
			
		} else {
			
			while(i < A.length) {
				
				merged[i+j] = A[i];
				i++;
				
			}
			
		}
		
		return merged;
		
	}

}
