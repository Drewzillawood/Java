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
 * This class implements the version of the quicksort algorithm presented in the
 * lecture.
 *
 */

public class QuickSorter extends AbstractSorter {

	// Other private instance variables if you need ...
	private Point pivot;

	/**
	 * The two constructors below invoke their corresponding superclass
	 * constructors. They also set the instance variables algorithm and
	 * outputFileName in the superclass.
	 */

	/**
	 * Constructor accepts an input array of points.
	 * 
	 * @param pts
	 *            input array of integers
	 */
	public QuickSorter(Point[] pts) {

		super(pts);
		algorithm = "Quicksort          ";
		outputFileName = "quick.txt";

	}

	/**
	 * Constructor reads points from a file.
	 * 
	 * @param inputFileName
	 *            name of the input file
	 */
	public QuickSorter(String inputFileName) throws FileNotFoundException, InputMismatchException {

		super(inputFileName);
		algorithm = "Quicksort          ";
		outputFileName = "quick.txt";

	}

	/**
	 * Carry out quicksort on the array points[] of the AbstractSorter class.
	 * 
	 * @param order
	 *            1 by x-coordinate 2 by polar angle
	 *
	 */
	@Override
	public void sort(int order) {

		setComparator(order);
		long startTime = System.nanoTime();

		quickSortRec(0, points.length - 1);

		sortingTime = System.nanoTime() - startTime;

	}

	/**
	 * Operates on the subarray of points[] with indices between first and last.
	 * 
	 * @param first
	 *            starting index of the subarray
	 * @param last
	 *            ending index of the subarray
	 */
	private void quickSortRec(int first, int last) {

		if (first >= last) {

			return;

		}

		int part = partition(first, last);
		quickSortRec(first, part - 1);
		quickSortRec(part + 1, last);

	}

	/**
	 * Operates on the subarray of points[] with indices between first and last.
	 * 
	 * @param first
	 * @param last
	 * @return
	 */
	private int partition(int first, int last) {

		pivot = points[last];
		int i = first - 1;

		for (int j = first; j < last; j++) {

			if (pointComparator.compare(points[j], pivot) < 0) {

				i++;
				swap(i, j);

			}

		}

		swap(i + 1, last);

		return i + 1;

		// pivot = points[first];
		// int left = first;
		// int right = last;
		//
		// while(true) {
		//
		// while(pointComparator.compare(points[left], pivot) < 0) {
		//
		// left++;
		//
		// }
		//
		// while(pointComparator.compare(points[right], pivot) > 0) {
		//
		// right--;
		//
		// }
		//
		// if(left < right) {
		//
		// swap(left+1, right-1);
		//
		// } else {
		//
		// break;
		//
		// }
		//
		// }
		//
		// return right;

	}

}
