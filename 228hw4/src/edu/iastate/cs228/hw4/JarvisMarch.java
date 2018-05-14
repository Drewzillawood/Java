package edu.iastate.cs228.hw4;

import java.io.FileNotFoundException;
import java.util.InputMismatchException;

/**
 * 
 * @author drewu
 *
 */

public class JarvisMarch extends ConvexHull {
	
	// last element in pointsNoDuplicate(), i.e., highest of all points (and the
	// rightmost one in case of a tie)
	private Point highestPoint;

	// left chain of the convex hull counterclockwise from lowestPoint to
	// highestPoint
	private PureStack<Point> leftChain;

	// right chain of the convex hull counterclockwise from highestPoint to
	// lowestPoint
	private PureStack<Point> rightChain;

	/**
	 * Call corresponding constructor of the super class. Initialize the
	 * variable algorithm (from the class ConvexHull). Set highestPoint.
	 * Initialize the two stacks leftChain and rightChain.
	 * 
	 * @throws IllegalArgumentException
	 *             when pts.length == 0
	 */
	@SuppressWarnings({"rawtypes","unchecked"})
	public JarvisMarch(Point[] pts) throws IllegalArgumentException {
		
		super(pts);
		algorithm = "Jarvis' March";
		leftChain  = new ArrayBasedStack();
		rightChain = new ArrayBasedStack();
		highestPoint = pointsNoDuplicate[pointsNoDuplicate.length-1];
		
	}

	/**
	 * Call corresponding constructor of the superclass. Initialize the variable
	 * algorithm. Set highestPoint. Initialize leftChain and rightChain.
	 * 
	 * @param inputFileName
	 * @throws FileNotFoundException
	 * @throws InputMismatchException
	 *             when the input file contains an odd number of integers
	 */
	@SuppressWarnings({"rawtypes","unchecked"})
	public JarvisMarch(String inputFileName) throws FileNotFoundException, InputMismatchException {
		
		super(inputFileName);
		algorithm = "Jarvis' March";
		leftChain  = new ArrayBasedStack();
		rightChain = new ArrayBasedStack();
		highestPoint = pointsNoDuplicate[pointsNoDuplicate.length-1];

	}

	// ------------
	// Jarvis' march
	// ------------

	/**
	 * Calls createRightChain() and createLeftChain(). Merge the two chains
	 * stored on the stacks rightChain and leftChain into the array
	 * hullVertices[].
	 * 
	 * Two degenerate cases below must be handled:
	 * 
	 * 1) The array pointsNoDuplicates[] contains just one point, in which case
	 * the convex hull is the point itself.
	 * 
	 * 2) The array contains only two points, in which case the hull is the line
	 * segment connecting them.
	 */
	public void constructHull() {
		
		time = System.nanoTime();
		
		if(pointsNoDuplicate.length < 3) {
		
			if(pointsNoDuplicate.length < 2) {
			
				leftChain.push(pointsNoDuplicate[0]);
			
			} else if(pointsNoDuplicate.length < 3) {
			
				leftChain.push(pointsNoDuplicate[0]);
				leftChain.push(pointsNoDuplicate[1]);
			
			}
			
			int i = 0;
			hullVertices = new Point[leftChain.size()];
			while(!leftChain.isEmpty()) {
				
				hullVertices[i] = leftChain.pop();
				i++;
				
			}
			
		} else {
			

			createRightChain();
			createLeftChain();
			
			int i = 0;
			hullVertices = new Point[leftChain.size() + rightChain.size()];
			while(!leftChain.isEmpty()) {
				
				hullVertices[i] = leftChain.pop();
				i++;
				
			}
			
			while(!rightChain.isEmpty()) { 
				
				hullVertices[i] = rightChain.pop();
				i++;
				
			}
			
			Point[] fliparoo = new Point[hullVertices.length];
			
			for(int p = 0; p < hullVertices.length; p++) {
				
				fliparoo[p] = hullVertices[p];
				
			}
			
			for(int k = 0, j = fliparoo.length-1; k < fliparoo.length; k++, j--) {
				
				hullVertices[k] = fliparoo[j];
				
			}
			
			
		}
		
		time = System.nanoTime() - time;
		
	}

	/**
	 * Construct the right chain of the convex hull. Starts at lowestPoint and
	 * wrap around the points counterclockwise. For every new vertex v of the
	 * convex hull, call nextVertex() to determine the next vertex, which has
	 * the smallest polar angle with respect to v. Stop when the highest point
	 * is reached.
	 * 
	 * Use the stack rightChain to carry out the operation.
	 * 
	 * Ought to be private, but is made public for testing convenience.
	 */
	public void createRightChain() {
		
		Point init = lowestPoint;
		while(init.compareTo((highestPoint)) != 0) {
			
			rightChain.push(init);
			init = nextVertex(init);
			
		}
		
	}

	/**
	 * Construct the left chain of the convex hull. Starts at highestPoint and
	 * continues the counterclockwise wrapping. Stop when lowestPoint is
	 * reached.
	 * 
	 * Use the stack leftChain to carry out the operation.
	 * 
	 * Ought to be private, but is made public for testing convenience.
	 */
	public void createLeftChain() {
		
		Point init = highestPoint;
		while(init.compareTo((lowestPoint)) != 0) {
			
			leftChain.push(init);
			init = nextVertex(init);
			
		}
		
	}

	/**
	 * Return the next vertex, which is less than all other points by polar
	 * angle with respect to the current vertex v. When there is a tie, pick the
	 * point furthest from v. Comparison is done using a PolarAngleComparator
	 * object created by the constructor call PolarAngleCompartor(v, false).
	 * 
	 * Ought to be private. Made public for testing.
	 * 
	 * @param v
	 *            current vertex
	 * @return
	 */
	public Point nextVertex(Point v) {
		
		PolarAngleComparator comp = new PolarAngleComparator(v, false);
		Point temp = v;
		
		for(int i = 0; i < pointsNoDuplicate.length-1; i++) {
			
			if(comp.compare(pointsNoDuplicate[i], temp) < 0) {
				
				temp = pointsNoDuplicate[i];
				
			}
			
		}
		
		if(comp.compare(pointsNoDuplicate[pointsNoDuplicate.length-1], temp) < 0) {
			
			temp = pointsNoDuplicate[pointsNoDuplicate.length-1];
			
		}
		
		return temp;
		
	}
}
