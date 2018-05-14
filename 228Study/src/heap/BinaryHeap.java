package heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

// Binary heap implementation with
// additional static methods
// for sorting an array using the heapsort
// algorithm. The methods percolateUp and
// percolateDown are static in order to
// reuse them in the heapsort methods.
// See main method at bottom for a simple
// demo.

public class BinaryHeap<E extends Comparable<? super E>>
{
	private static final int DEFAULT_SIZE = 10;
	private E[] data;
	private int size;
	private Comparator<? super E> comp;
		
	// Creates a heap with a backing array of
	// default initial size.
	public BinaryHeap()
	{
		this(DEFAULT_SIZE);
	}

	// Creates an empty heap with a backing
	// array of the given initial
	// capacity.
	@SuppressWarnings("unchecked")
	public BinaryHeap(int initialCapacity)
	{
		// Unchecked warning unavoidable. Note
		// that we have to create an array of
		// Comparable, not Object, in order
		// to cast to E[]
		data = (E[])new Comparable[initialCapacity];
		size = 0;
		
		// create and cache the comparator to
		// reuse in the static percolateUp/Down
		// methods
		Comparator<E> comp = new Comparator<E>()
		{
			@Override
			public int compare(E lhs, E rhs)
			{
				return lhs.compareTo(rhs);
			}
		};
			
		this.comp = comp;
	}
		
	// Returns the minimal element with
	// respect to the natural ordering of the
	// elements.
	public E peek()
	{
		if (size == 0)
			throw new NoSuchElementException();
		return data[0];
	}
		
	// Adds an element to this heap.
	public boolean add(E item)
	{
		checkCapacity();
		data[size] = item;
		percolateUp(data, comp, size);
		++size;
		return true;
	}
		
	// Removes and returns the minimal
	// element with respect to the natural
	// ordering of the elements.
	public E remove()
	{
		if (size == 0)
			throw new NoSuchElementException();
			
		E ret = data[0];
		data[0] = data[size - 1];
		data[size - 1] = null;
		--size;
		percolateDown(data, comp, size, 0);
		return ret;
	}
		
	// Sorts the array according to the given
	// Comparator.
	public static <T> void
	heapSort(T[] data, final Comparator<? super T> comp)
	{
		Comparator<T> reverseComp = 
		new Comparator<T>()
		{
			@Override
			public int compare(T lhs, T rhs)
			{
				return -comp.compare(lhs, rhs);
			}
		};
			
		heapSortImpl(data, reverseComp,	data.length);
	}
		
	// Sorts the array according to the
	// natural ordering of the element type.
	public static <T extends Comparable<? super T>>
	void heapSort(T[] data)
	{
		Comparator<T> reverseComp =
		new Comparator<T>()
		{
			@Override
			public int compare(T lhs, T rhs)
			{
				return -lhs.compareTo(rhs);
			}
		};
			
		heapSortImpl(data, reverseComp, data.length);
	}
		
	// Sorts the given array using the
	// heapsort algorithm.
	private static <T> void 
	heapSortImpl(T[] data, Comparator<? super T> comp, final int size)
	{
		// comp is reversed, so this creates a
		// max-heap
		heapify(data, comp, size);
		int last = size - 1;
		while (last > 0)
		{
			// remove max, as in remove() method
			// last is new size of heap
			T max = data[0];
			data[0] = data[last];
			percolateDown(data, comp, last, 0);
			// store at end of array
			data[last] = max;
			--last;
		}
	}
			
	// Imposes a binary heap ordering on the
	// first size elements of the given array
	private static <T> void
	heapify(T[] data, Comparator<? super T> comp, int size)
	{
		// Loop invariant: for each index i >
		// current, the subtree rooted at
		// data[i] is heap-ordered.
		int current = size / 2 - 1; // index of last internal node 
		while (current >= 0)
		{
			// children of current are at indices
			// > current, so by the
			// loop invariant, both subtrees of
			// current are heap-ordered
			percolateDown(data, comp, size, current);
						
			// postcondition of percolateDown
			// ensures that subtree at current
			// is now heap ordered
			--current;
						
			// loop invariant still holds for
			// i > current
		}
	}
			
	// Moves element at current up to correct
	// position in heap.
	//
	// Preconditions: Subtrees at both
	// children of current are heap-ordered,
	// AND if subtree at current is removed,
	// the resulting tree is heap-ordered.
	// Postcondition: The entire tree is
	// heap-ordered.
	private static <T> void 
	percolateUp(T[] data, Comparator<? super T> comp, int current)
	{
		int parent = (current - 1) / 2;
		while (current > 0 && 
				comp.compare(data[current], data[parent]) < 0)
		{
			swap(data, current, parent);
			current = parent;
			parent = (current - 1) / 2;
		}
	}
							
	// Moves element at current down to
	// correct position in heap.
	//
	// Precondition: Subtrees at both
	// children of current are heap-ordered.
	// Postcondition: Subtree at current is
	// heap-ordered. No other
	// elements of tree are modified.
	private static <T> void
	percolateDown(T[] data, Comparator<? super T> comp,
					  int size, int current)
	{
		int child = 2 * current + 1; // left
							// child, if any
							
		while (child < size)
		{
			if (child + 1 < size)
			{
				// there's a right child too, pick
				// the smallest child
				if (comp.compare(data[child], data[child + 1]) > 0)
				{
					child = child + 1;
				}
			}
							
			if (comp.compare(data[current], data[child]) > 0)
			{
				swap(data, current, child);
			}
							
			current = child;
			child = 2 * current + 1;
		}
	}
							
	// Exchanges two elements of an array.
	private static void swap(Object[] data, int i, int j)
	{
		Object temp = data[i];
		data[i] = data[j];
		data[j] = temp;
	}
							
	// Ensures that the backing array has
	// space to store at least one additional
	// element.
	private void checkCapacity()
	{
		if (size == data.length)
		{
			// create a copy of the data array
			// with double the capacity
			data = Arrays.copyOf(data, data.length * 2);
		}
	}
		
	// Entry point for brief demo.
	public static void main(String[] args)
	{
		Integer[] arr = new Integer[]{4, 4, 12, 8, 10, 2, 7,
										18, 8, 1};
		Integer[] arr2 = Arrays.copyOf(arr, arr.length);
			
		// heap sort the array
		BinaryHeap.heapSort(arr);
		System.out.println(Arrays.toString(arr));
		
		// put everything in a heap and remove
		// one element at a time
		BinaryHeap<Integer> h = new BinaryHeap<Integer>();
		for (int i = 0; i < arr2.length; ++i)
		{		
			h.add(arr2[i]);
		}
			
		for (int i = 0; i < arr2.length; ++i)
		{
			arr2[i] = h.remove();
		}
			
		System.out.println(Arrays.toString(arr2));
	}
}
