package edu.iastate.cs228.hw3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.Scanner;

/**
 * 
 * @author drewu
 *
 */

/**
 * IMPORTANT: In the case of any minor discrepancy between the comments before a
 * method and its description in the file proj3.pdf, use the version from the
 * file.
 *
 */

public class DoublySortedList {
	
	private int size; // number of different kinds of fruits
	
	private Node headN; // dummy node as the head of the sorted linked list by
						// fruit name
	private Node headB; // dummy node as the head of the sorted linked list by
						// bin number
	
	/**
	 * Default constructor constructs an empty list. Initialize size. Set the
	 * fields nextN and previousN of headN to the node itself. Similarly, set
	 * the fields nextB and previousB of headB.
	 */
	public DoublySortedList() {
		
		// Construct DSL with Head nodes for both lists
		headN = new Node();
		headB = new Node();
		
		preAssign(headN, headB);
		
		size = 0;
		
	}

	/**
	 * Constructor over an inventory file consists of lines in the following
	 * format
	 * 
	 * <fruit> <quantity> <bin>
	 * 
	 * Throws an exception if the file is not found.
	 * 
	 * You are asked to carry out the following operations:
	 * 
	 * 1. Scan line by line to construct one Node object for each fruit. 2.
	 * Create the two doubly-linked lists, by name and by bin number,
	 * respectively, on the fly as the scan proceeds. 3. Perform insertion sort
	 * on the two lists. Use the provided BinComparator and NameComparator
	 * classes to generate comparator objects for the sort.
	 * 
	 * @inventoryFile name of the input file
	 */
	public DoublySortedList(String inventoryFile) throws FileNotFoundException {
		
		File file = new File(inventoryFile);
		Scanner s = new Scanner(file);
		
		// Construct DSL with head nodes for both lists
		headN = new Node();
		headB = new Node();
		
		preAssign(headN, headB);
		
		size = 0;
		
		// Continue through file until there is no more fruit
		while(s.hasNextLine()) {
			
			// Construct a node from each fruit
			Node temp = new Node(s.next(), s.nextInt(), s.nextInt(), headN, headN.nextN, headB, headB.nextB);	
			
			// Insert the node into each list and increment the size
			insertN(temp, headN, headN.nextN);
			insertB(temp, headB, headB.nextB);
			size++;
			
		}
		
		// Sort both lists with respect to their specifications
		NameComparator nComp = new NameComparator();
		this.insertionSort(true,  nComp);
		
		BinComparator  bComp = new BinComparator();
		this.insertionSort(false, bComp);
		
		s.close();
		
	}

	/**
	 * Called by split() and also used for testing.
	 * 
	 * Precondition: The doubly sorted list has already been created.
	 * 
	 * @param size
	 * @param headN
	 * @param headB
	 */
	public DoublySortedList(int size, Node headN, Node headB) {
		
		this.size = size;
		this.headN = headN;
		this.headB = headB;
		
	}

	/**
	 * 
	 * @return
	 * 		size of the DSL
	 */
	public int size() {
		
		return size;
		
	}

	/**
	 * Add one type of fruits in given quantity (n).
	 * 
	 * 1. Search for the fruit. 2. If already stored in some node, simply
	 * increase the quantity by n 3. Otherwise, create a new node to store the
	 * fruit at the first available bin. add it to both linked lists by calling
	 * the helper methods insertN() and insertB().
	 * 
	 * The case n == 0 should result in no operation. The case n < 0 results in
	 * an exception thrown.
	 * 
	 * @param fruit
	 *            name of the fruit to be added
	 * @param n
	 *            quantity of the fruit
	 * @throws IllegalArgumentException
	 *             if n < 0
	 */
	public void add(String fruit, int n) throws IllegalArgumentException {
		
		// Boolean value to find fruit
		boolean exists = false;
		
		// Conditional checks for valid entry
		if(n < 0) {
			
			throw new IllegalArgumentException();		
			
		} else if(n == 0) {
			
			return;
			
		} else {
			
			// Starting point for each list
			Node N = headN;
			Node B = headB;
			
			// Iterate through the list to find the fruit, if it exists, 
			// add the quantity specified to the node
			for(int i = 0; i < size; i++) {
				
				if(N.nextN.fruit.equals(fruit)) {
					
					exists = true;
					N.nextN.quantity += n;
					break;
					
				}
				N = N.nextN;
				
			}
			
			// Otherwise if the fruit is not present
			if(!exists) {
				
				// Start from beginning of each list
				N = headN;
				B = headB;
				int faBin = 1;
				
				// Iterate through B list until we find
				// the first unoccupied bin
				for(int i = 0; i < size; i++) {
					
					B = B.nextB;
					
					if(B.bin == faBin) {
						
						faBin++;
						
					}
					
				}
				
				// Nodes for final node declaration
				Node nPrev = headN;
				Node nNext = headN;
				Node bPrev = headB;
				Node bNext = headB;
					
				// Restart at beginning of list
				N = headN;
				B = headB;
				
				// Identify location to insert within N list
				for(int i = 0; i < size; i++) {
					
					N = N.nextN;
					if(fruit.compareTo(N.fruit) < 0) {
						
						nNext = N;
						nPrev = N.previousN;
						break;
						
					} else if(N.nextN.equals(headN)) {
						
						nNext = headN;
						nPrev = headN.previousN;
						
					}
					
				}
				
				// Identify location to insert within B list
				for(int i = 0; i < size; i++) {
					
					B = B.nextB;
					if(faBin < B.bin) {
						
						bNext = B;
						bPrev = B.previousB;
						break;
						
					} else if(B.nextB.equals(headB)) {
						
						bNext = headB;
						bPrev = headB.previousB;
						
					}
					
				}
				
				// Construct and insert node into both lists
				Node insert = new Node(fruit, n, faBin, nNext, nPrev, bNext, bPrev);
				insertN(insert, nPrev, nNext);
				insertB(insert, bPrev, bNext);
				size++;
				
			}
			
			
			
		}
		
	}

	/**
	 * The fruit list is not sorted. For efficiency, you need to sort by name
	 * using quickSort. Maintain two arrays fruitName[] and fruitQuant[].
	 * 
	 * After sorting, add the fruits to the doubly-sorted list (see project
	 * description) using the algorithm described in Section 3.2 of the project
	 * description.
	 * 
	 * Ignore a fruit if its quantity in fruitFile is zero.
	 * 
	 * @param fruitFile
	 *            list of fruits with quantities. one type of fruit followed by
	 *            its quantity per line.
	 * @throws FileNotFoundException
	 * @throws IllegalArgumentException
	 *             if the quantity specified for some fruit in fruitFile is
	 *             negative.
	 */
	public void restock(String fruitFile) throws FileNotFoundException, IllegalArgumentException {

		// Retrieve file for scanning
		File file = new File(fruitFile);
		Scanner s = new Scanner(file);
		
		// Establish file length to be used
		// as array lengths
		int fLength = 0;
		while(s.hasNextLine()) {
			
			fLength++;
			s.nextLine();
			
		}
		
		// Start from beginning of file once more
		s.close();
		s = new Scanner(file);
		
		String fruitName[] = new String[fLength];
		Integer fruitQuant[] = new Integer[fLength];
		
		// Fill our array with the values from file
		int i = 0;
		while(s.hasNextLine()) {
			
			fruitName[i] = s.next();
			fruitQuant[i] = s.nextInt();
			i++;
			
		}
		
		// Sort each array
		quickSort(fruitName, fruitQuant, fLength);
		
		// Establish iterator for DSL
		Node N = headN.nextN;
		Node B = headB.nextB;
		
		int faBin = 0;
		i = 0;
		
		// Step through DSL and sorted Arrays in O(n) time
		while(i < fruitName.length) {
			
			if(N.equals(headN)) {
				
				faBin = findFirstBin(B, faBin);
				
				if(!B.equals(headB)) {
					
					if(faBin >= B.bin) {
					
						B = B.nextB;
					
					}
				
				}
				
				Node M = new Node(fruitName[i], fruitQuant[i], faBin, N, N.previousN, B, B.previousB);
				insertN(M, N.previousN, N);
				insertB(M, B.previousB, B);
				size++;
				i++;
			
			// If the current Node matches the current array element, 
			// simply increment the quantity
			} else if(fruitName[i].equals(N.fruit)) {
				
				if(fruitQuant[i] < 0) {
					
					s.close();
					throw new IllegalArgumentException();
					
				} else {
				
					N.quantity += fruitQuant[i];
					N = N.nextN;
					i++;
				
				}
					
			// Otherwise find the first bin, and correct location to
			// insert within N list
			} else if(fruitName[i].compareTo(N.fruit) < 0) {
				
				if(fruitQuant[i] < 0) {
					
					s.close();
					throw new IllegalArgumentException();
					
				} else if(fruitName[i].compareTo(N.fruit) < 0) {
					
					faBin = findFirstBin(B, faBin);
					
					if(!B.equals(headB)) {
						
						if(faBin >= B.bin) {
						
							B = B.nextB;
						
						}
					
					}
		
					Node M = new Node(fruitName[i], fruitQuant[i], faBin, N, N.previousN, B, B.previousB);
					insertN(M, N.previousN, N);
					insertB(M, B.previousB, B);
					size++;
					i++;
					
				} else {
					
					N = N.nextN;
					
				}
				
			} else {
				
				N = N.nextN;
				
			}
			
			
			
		}
	
		s.close();
		
	}

	/**
	 * Remove a fruit from the inventory.
	 * 
	 * 1. Search for the fruit on the N-list. 2. If no existence, make no
	 * change. 3. Otherwise, call the private method remove() on the node that
	 * stores the fruit to remove it.
	 * 
	 * @param fruit
	 */
	public void remove(String fruit) {
		
		// Start at beginning of N list
		Node N = headN;
		
		// Continue through list until we reach the end
		while(!N.nextN.equals(headN)) {
			
			N = N.nextN;
			
			// If we find the specified node, remove it
			if(N.fruit.equals(fruit)) {
				
				remove(N);
				break;
				
			}
			
		}
		
	}

	/**
	 * Remove all fruits stored in the bin. Essentially, remove the node. The
	 * steps are as follows:
	 * 
	 * 1. Search for the node with the bin in the B-list. 2. No change if it is
	 * not found. 3. Otherwise, call remove() on the found node.
	 * 
	 * @param bin
	 * @throws IllegalArgumentException
	 *             if bin < 1
	 */
	public void remove(int bin) throws IllegalArgumentException {

		if(bin < 1) {
			
			throw new IllegalArgumentException();
			
		}
		
		Node B = headB;
		
		// Continue through to the end of list
		while(!B.nextB.equals(headB)) {
			
			B = B.nextB;
			
			// Once we find the specified node, remove it
			if(B.bin == bin) {
				
				remove(B);
				break;
				
			}
			
		}
		
	}

	/**
	 * Sell n units of a fruit.
	 * 
	 * Search the N-list for the fruit. Return in the case no fruit is found.
	 * Otherwise, a Node node is located. Perform the following:
	 * 
	 * 1. if n >= node.quantity, call remove(node). 2. else, decrease
	 * node.quantity by n.
	 * 
	 * @param fruit
	 * @param n
	 * @throws IllegalArgumentException
	 *             if n < 0
	 */
	public void sell(String fruit, int n) throws IllegalArgumentException {

		if(n < 0) {
			
			throw new IllegalArgumentException();
			
		}
		
		// Starting point 
		Node item = headN;
		
		// Continue through until the end of the list
		while(!item.nextN.equals(headN)) {
			
			// Iterate to next node
			item = item.nextN;
			
			// If we find the fruit
			if(item.fruit.equals(fruit)) {
				
				// If it's sold out, remove it
				if(n >= item.quantity) {
					
					remove(item);
					break;
					
				// Decrement the quantity
				} else {
					
					item.quantity -= n;
					break;
					
				}
				
			}
			
		}
		
	}

	/**
	 * Process an order for multiple fruits as follows.
	 * 
	 * 1. Sort the ordered fruits and their quantities by fruit name using the
	 * private method quickSort(). 2. Traverse the N-list. Whenever a node with
	 * the next needed fruit is encountered, let m be the ordered quantity of
	 * this fruit, and do the following: a) if m < 0, throw an
	 * IllegalArgumentException; b) if m == 0, ignore. c) if 0 < m <
	 * node.quantity, decrease node.quantity by n. d) if m >= node.quanity, call
	 * remove(node).
	 * 
	 * @param fruitFile
	 */
	public void bulkSell(String fruitFile) throws FileNotFoundException, IllegalArgumentException {
		
		// Retrieve file for scanning
		File file = new File(fruitFile);
		Scanner s = new Scanner(file);
		
		// Establish file length for use 
		// as array length
		int fLength = 0;
		while(s.hasNextLine()) {
			
			fLength++;
			s.nextLine();
			
		}
		
		// Restart from beginning of file
		s.close();
		s = new Scanner(file);
		
		String fruitName[] = new String[fLength];
		Integer fruitQuant[] = new Integer[fLength];
		
		// Fill each of our arrays with values
		// given in file
		int i = 0;
		while(s.hasNextLine()) {
			
			fruitName[i] = s.next();
			fruitQuant[i] = s.nextInt();
			i++;
			
		}
		
		// Sort each array
		quickSort(fruitName, fruitQuant, fLength);
		
		Node N = headN.nextN;
		i = 0;
		
		// Traverse through DSL and Sorted arrays in
		// O(n) time
		while(!N.nextN.equals(headN)) {
			
			
			if(fruitName[i].equals(N.fruit)) {
				
				if(fruitQuant[i] < 0) {
					
					s.close();
					throw new IllegalArgumentException();
					
				} else if(fruitQuant[i] > 0 && fruitQuant[i] < N.quantity) {
					
					N.quantity -= fruitQuant[i];
					
				} else {
					
					remove(N);
					
				}
				
				N = N.nextN;
				
			} else {
				
				i++;
				
			}
			
			
		}
		
		s.close();
		
	}

	/**
	 * 
	 * @param fruit
	 * @return quantity of the fruit (zero if not on stock)
	 */
	public int inquire(String fruit) {
		
		Node iter = headN.nextN;
		
		// Continue through the entire list
		while(!iter.equals(headN)) {
			
			// Once we find the specified fruit, return its quantity
			if(iter.fruit.equals(fruit)) {
				
				return iter.quantity;
				
			}
			
			iter = iter.nextN;
			
		}
		
		// If never found, quantity is zero
		return 0;
		
	}

	/**
	 * Output a string that gets printed out as the inventory of fruits by
	 * names. The exact format is given in Section 5.1. Here is a sample:
	 *
	 * 
	 * fruit   quantity  bin 
	 * --------------------------- 
	 * apple   50        5 
	 * banana  20        9
	 * grape   100       8 
	 * pear    40        3
	 */
	public String printInventoryN() {

		return this.toString();
		
	}

	/**
	 * Output a string that gets printed out as the inventory of fruits by
	 * storage bin. Use the same formatting rules for printInventoryN(). Below
	 * is a sample:
	 * 
	 * bin fruit  quantity 
	 * ---------------------------- 
	 * 3   pear   40 
	 * 5   apple  50 
	 * 8   grape  100 
	 * 9   banana 20
	 * 
	 */
	public String printInventoryB() {
		
		String ret = String.format("%1$-16s %2$-16s %3$s \n------------------------------------------\n", "bin", "fruit", "quantity");

		Node iter = headB.nextB;
		
		for(int i = 0; i < size; i++) {
			
			ret += String.format("%1$-16d %2$-16s %3$d\n", iter.bin, iter.fruit, iter.quantity);
			iter = iter.nextB;
			
		}
		
		return ret;
		
	}

	@Override
	/**
	 * The returned string should be printed out according to the format in
	 * Section 5.1, exactly the same required for printInventoryN().
	 */
	public String toString() {

		String ret = String.format("%1$-16s %2$-16s %3$s \n------------------------------------------\n", "fruit", "quantity", "bin");
		
		Node iter = headN.nextN;
		
		for(int i = 0; i < size; i++) {
			
			ret += iter.toString();
			iter = iter.nextN;
			
		}

		return ret;
		
	}

	/**
	 * Relocate fruits to consecutive bins starting at bin 1. Need only operate
	 * on the B-list.
	 */
	public void compactStorage() {
		
		// Start at beginning of the list
		Node B = headB;
		int cur = 1;
		
		// Continue through the end of the list
		while(!B.nextB.equals(headB)) {
			
			// Simply assign each element with an incrementing value
			B = B.nextB;
			B.bin = cur;
			cur++;
			
		}
		
	}

	/**
	 * Remove all nodes storing fruits from the N-list and the B-list.
	 */
	public void clearStorage() {

		// Go through entire list and remove each node
		while(!headN.nextN.equals(headN) ) {
			
			remove(headN.nextN);
			
		}
		
	}

	/**
	 * Split the list into two doubly-sorted lists DST1 and DST2. Let N be the
	 * total number of fruit types. Then DST1 will contain the first N/2 types
	 * fruits in the alphabetical order. DST2 will contain the remaining fruit
	 * types. The algorithm works as follows.
	 * 
	 * 1. Traverse the N-list to find the median of the fruits by name. 2. Split
	 * at the median into two lists: DST1 and DST2. 3. Traverse the B-list. For
	 * every node encountered add it to the B-list of DST1 or DST2 by comparing
	 * node.fruit with the name of the median fruit.
	 * 
	 * DST1 and DST2 must reuse the nodes from this list. You cannot make a copy
	 * of each node from this list, and arrange these copy nodes into DST1 and
	 * DST2.
	 * 
	 * @return two doubly-sorted lists DST1 and DST2 as a pair.
	 */
	@SuppressWarnings({"rawtypes","unchecked"})
	public Pair<DoublySortedList> split() {
		
		// Construct two head nodes for each new list
		Node headDST1N = new Node();
		Node headDST1B = new Node();
		
		preAssign(headDST1N, headDST1B);
		
		Node headDST2N = new Node();
		Node headDST2B = new Node();
		
		preAssign(headDST2N, headDST2B);
		
		// Assign sizes to each respective list
		DoublySortedList DST1 = new DoublySortedList(size / 2, headDST1N, headDST1B);
		DoublySortedList DST2 = new DoublySortedList((size/2)+(size%2), headDST2N, headDST2B);
		
		// Tracer nodes to iterate through the lists
		Node N = new Node();
		Node M = new Node();
		Node B = new Node();
		
		// Designated node which will be the first node of list DSL2
		M = headN;
		for(int i = 0; i <= DST1.size(); i++) {
			
			M = M.nextN;
			
		}
		
		// Iterate through our initial list
		int i = 0;
		while(!headN.nextN.equals(headN)) {
			
			// Start at the first element in each list
			/* As we continue through this list, the initial 
			 * element is removed, thus we can continue to 
			 * start at the element which is head.next;
			 */
			N = headN.nextN;
			B = headB.nextB;
			
			// Insert node into first new DSL
			if(i < DST1.size()) {
				
				infuse(N,headDST1N);
				if(B.fruit.compareTo(M.fruit) < 0) {
					
					binfuse(B,headDST1B);
				
				} else {
					
					binfuse(B,headDST2B);
					
				}
				size--;
				
			// Insert node into second new DSL
			} else {
				
				infuse(N,headDST2N);
				if(B.fruit.compareTo(M.fruit) < 0) {
					
					binfuse(B,headDST1B);
					
				} else {
					
					binfuse(B,headDST2B);
					
				}
				size--;
				
			}
			i++;
			
		}
		Pair<DoublySortedList> pair = new Pair(DST1, DST2);

		return pair;
		
	}

	/**
	 * Perform insertion sort on the doubly linked list with head node using a
	 * comparator object, which is of either the NameComparator or the
	 * Bincomparator class.
	 * 
	 * Made a public method for testing by TA.
	 * 
	 * @param sortNList
	 *            sort the N-list if true and the B-list otherwise.
	 * @param comp
	 */
	public void insertionSort(boolean sortNList, Comparator<Node> comp) {

		if(sortNList) {
		
			Node N1 = headN;
			Node N2 = headN;
			
			while(!N1.nextN.equals(headN)) {
				
				N1 = N1.nextN;
				if(!N1.previousN.equals(headN)) {
					
					N2 = N1.previousN;
					
				}
				
				while(!N2.equals(headN)) {
					
					if(comp.compare(N1, N2) < 0) {
						
						swapN(N1,N2);
						
					}
					N2 = N2.previousN;
					
				}
				
			}
			
		} else {
			
			Node B1 = headB;
			Node B2 = headB;
			
			while(!B1.nextB.equals(headB)) {
				
				B1 = B1.nextB;
				if(!B1.previousB.equals(headB)) {
					
					B2 = B1.previousB;
					
				}
				
				while(!B2.equals(headB)) {
					
					if(comp.compare(B1, B2) < 0) {
						
						swapB(B1,B2);
						
					}
					B2 = B2.previousB;
					
				}
				
			}
			
		}
		
	}
	
	
	

	/**
	 * Sort an array of fruit names using quicksort. After sorting, quant[i] is
	 * the quantity of the fruit with name[i].
	 * 
	 * Made a public method for testing by TA.
	 * 
	 * @param size
	 *            number of fruit names
	 * @param fruit
	 *            array of fruit names
	 * @param quant
	 *            array of fruit quantities
	 */
	public void quickSort(String fruit[], Integer quant[], int size) {

		quickSortRec(fruit, quant, 0, size-1);
		
	}
	
	/**
	 * 	Recursive call for Quicksort
	 * @param fruit
	 * @param quant
	 * @param first
	 * @param last
	 */
	private void quickSortRec(String fruit[], Integer quant[], int first, int last) {
		
		if(first >= last) {
			
			return;
			
		}
		
		int part = partition(fruit, quant, first, last);
		quickSortRec(fruit, quant, first ,part-1);
		quickSortRec(fruit, quant, part+1 ,last);
		
	}

	// --------------
	// helper methods
	// --------------
	
	/**
	 *  Helper method for restock method, finds first available 
	 *  bin for a fruit if it is new and needing to be added
	 *  to the DSL
	 * @param b
	 * @param pfaBin
	 * @return
	 */
	private int findFirstBin(Node b, int pfaBin) {
		
		while(!b.equals(headB)) {
			
			pfaBin++;
			if(pfaBin < b.bin) {
				
				return pfaBin;
				
			} else if(pfaBin >= b.bin){
				
				b = b.nextB;
				
			}
			
			
		}

		pfaBin++;
		return pfaBin;
		
	}
	
	/**
	 *  Helper method for split, transfers node
	 *  from initial list to designated list through
	 *  proper head node;
	 * @param n1
	 * @param n2
	 */
	private void infuse(Node n1, Node n2) {
		
		n1.previousN.nextN = n1.nextN;
		n1.nextN.previousN = n1.previousN;
		
		n1.previousN = n2.previousN;
		n1.nextN = n2;
		
		n2.previousN.nextN = n1;
		n2.previousN = n1;
		
	}
	
	/**
	 * 	Helper method for split, transfers node 
	 *  from initial list to designated list through
	 *  proper head node
	 * @param b1
	 * @param b2
	 */
	private void binfuse(Node b1,Node b2) {
		
		b1.previousB.nextB = b1.nextB;
		b1.nextB.previousB = b1.previousB;
		
		b1.previousB = b2.previousB;
		b1.nextB = b2;
		
		b2.previousB.nextB = b1;
		b2.previousB = b1;
		
	}
	
	/**
	 *  Got tired of rewriting this, so I made a helper method
	 * @param n1
	 * @param b1
	 */
	private void preAssign(Node n1, Node b1) {
		
		n1.nextN = n1;
		n1.previousN = n1;
		
		b1.nextB = b1;
		b1.previousB = b1;
		
	}
	
	/**
	 * Helper swap method for swapping two nodes in the
	 * N-list
	 * @param n1
	 * @param n2
	 */
	private void swapN(Node n1, Node n2) {
		
		if(n1.fruit.equals(n2.fruit)) {
			
			return;
			
		} else {
			
			n2.nextN = n1.nextN;
			n1.previousN = n2.previousN;
			
			n1.nextN = n2;
			n2.nextN.previousN = n2;
			
			n1.previousN.nextN = n1;
			n2.previousN = n1;
			
		}
		
	}
	
	/**
	 * Helper swap method for swapping two nodes in the 
	 * B-list
	 * @param b1
	 * @param b2
	 */
	private void swapB(Node b1, Node b2) {
		
		if(b1.fruit.equals(b2.fruit)) {
			
			return;
			
		} else {
			
			b2.nextB = b1.nextB;
			b1.previousB = b2.previousB;
			
			b1.nextB = b2;
			b2.nextB.previousB = b2;
			
			b1.previousB.nextB = b1;
			b2.previousB = b1;
			
		}
		
	}

	/**
	 * Add a node between two nodes prev and next in the N-list.
	 * 
	 * @param node
	 * @param prev
	 *            preceding node after addition
	 * @param next
	 *            succeeding node
	 */
	private void insertN(Node node, Node prev, Node next) {
		
		prev.nextN = node;
		next.previousN = node;
		
		node.nextN = next;
		node.previousN = prev;
		
	}

	/**
	 * Add a node between two nodes prev and next in the B-list.
	 * 
	 * @param node
	 * @param prev
	 *            preceding node
	 * @param next
	 *            succeeding node
	 */
	private void insertB(Node node, Node prev, Node next) {

		prev.nextB = node;
		next.previousB = node;
		
		node.nextB = next;
		node.previousB = prev;
		
	}

	/**
	 * Remove node from both linked lists.
	 * 
	 * @param node
	 */
	private void remove(Node node) {
		
		node.previousN.nextN = node.nextN;
		node.nextN.previousN = node.previousN;
		
		node.previousB.nextB = node.nextB;
		node.nextB.previousB = node.previousB;
		
		size--;
		
	}
	
	/**
	 * 
	 * @param fruit
	 *            array of fruit names
	 * @param quant
	 *            array of fruit quantities corresponding to fruit[]
	 * @param first
	 * @param last
	 * @return
	 */
	private int partition(String fruit[], Integer quant[], int first, int last) {

		String pivot = fruit[last];
		int i = first - 1;
		
		for(int j = first; j < last; j++) {
			
			if(fruit[j].compareTo(pivot) < 0) {
				
				i++;
				swap(fruit, quant, i, j);
				
			}
			
		}
		
		swap(fruit, quant, i+1, last);
		
		return i+1;
		
	}
	
	/**
	 * 	Takes two arrays and swaps the specified elements
	 * @param fruit
	 * @param quant
	 * @param i
	 * @param j
	 */
	private void swap(String fruit[], Integer quant[], int i, int j) {
		
		String temp = fruit[i];
		int t = quant[i];
		fruit[i] = fruit[j];
		quant[i] = quant[j];
		fruit[j] = temp;
		quant[j] = t;
		
	}

	


}
