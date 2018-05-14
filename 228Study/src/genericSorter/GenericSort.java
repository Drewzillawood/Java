package genericSorter;

import java.util.Arrays;
import java.util.Comparator;

/**
 * This class demonstrates the use of Comparable and Comparator interfaces by
 * developing a sequence of six versions of the selection sort algorithm, each
 * one containing minor changes from the previous one.
 * 
 * Refer to the javadoc for java.lang.Comparable and java.util.Comparator. You
 * may also want to refer to summary document generics_notes.pdf posted on
 * WebCT.
 * 
 * There is a short main method at the bottom to provide some usage examples.
 */
public class GenericSort {

	/**
	 * 1. Selection sort for integer arrays, as written in class.
	 */
	public static void selectionSort(int[] arr) {
		for (int i = 0; i < arr.length - 1; ++i) {
			int minIndex = i;
			for (int j = i + 1; j < arr.length; ++j) {
				if (arr[j] < arr[minIndex]) {
					minIndex = j;
				}
			}
			int temp = arr[i];
			arr[i] = arr[minIndex];
			arr[minIndex] = temp;
		}
	}

	/**
	 * 2. Same algorithm, but modified to sort Strings. Instead of using "<" to
	 * compare elements, we use the fact that String implements the Comparable
	 * interface, and therefore has a method compareTo(). Also, the temp
	 * variable is changed to type String. Those are the only two changes in the
	 * code.
	 */
	public static void selectionSort(String[] arr) {
		for (int i = 0; i < arr.length - 1; ++i) {
			int minIndex = i;
			for (int j = i + 1; j < arr.length; ++j) {
				// "<" changed to use of compareTo()
				if (arr[j].compareTo(arr[minIndex]) < 0) {
					minIndex = j;
				}
			}
			// int changed to String
			String temp = arr[i];
			arr[i] = arr[minIndex];
			arr[minIndex] = temp;
		}
	}

	/**
	 * 3. Same as above, but the caller can supply a custom Comparator in order
	 * to sort strings differently than the "natural" or built-in ordering
	 * provided in the String class.
	 */
	public static void selectionSort(String[] arr, Comparator<String> comp) {
		for (int i = 0; i < arr.length - 1; ++i) {
			int minIndex = i;
			for (int j = i + 1; j < arr.length; ++j) {
				// lns.compareTo(rhs) replaced by comp.compare(lhs, rhs)
				if (comp.compare(arr[j], arr[minIndex]) < 0) {
					minIndex = j;
				}
			}
			String temp = arr[i];
			arr[i] = arr[minIndex];
			arr[minIndex] = temp;
		}
	}

	/**
	 * 4. Generic version of selection sort. The type T is arbitrary, but the
	 * caller must supply a Comparator.
	 * 
	 */
	public static <T> void selectionSort(T[] arr, Comparator<? super T> comp) {
		for (int i = 0; i < arr.length - 1; ++i) {
			int minIndex = i;
			for (int j = i + 1; j < arr.length; ++j) {
				if (comp.compare(arr[j], arr[minIndex]) < 0) {
					minIndex = j;
				}
			}
			// String is changed to T
			T temp = arr[i];
			arr[i] = arr[minIndex];
			arr[minIndex] = temp;
		}
	}

	/**
	 * 5. Same as above, but imposes an upper bound on the type T. Since T is
	 * guaranteed to implement the Comparable interface, we can call the
	 * compareTo() method on objects of type T. (When dealing with type
	 * parameters, the keyword "extends" is used for both classes and
	 * interfaces.)
	 */
	public static <T extends Comparable<? super T>> void selectionSort(T[] arr) {
		for (int i = 0; i < arr.length - 1; ++i) {
			int minIndex = i;
			for (int j = i + 1; j < arr.length; ++j) {
				// comp.compare(lhs, rhs) changed to lhs.compareTo(rhs)
				if (arr[j].compareTo(arr[minIndex]) < 0) {
					minIndex = j;
				}
			}
			T temp = arr[i];
			arr[i] = arr[minIndex];
			arr[minIndex] = temp;
		}
	}

	/**
	 * 6. Alternate implementation of above which allows the re-use of version
	 * 4. The natural ordering of type T is adapted to create a Comparator for T
	 * with exactly the same ordering, and then version 4 is called with that
	 * Comparator.
	 */
	public static <T extends Comparable<? super T>> void selectionSortAlt(T[] arr) {
		// This creates an "anonymous inner class" implementing Comparable<T>
		Comparator<T> comp = new Comparator<T>() {
			@Override
			public int compare(T lhs, T rhs) {
				return lhs.compareTo(rhs);
			}
		};

		selectionSort(arr, comp);
	}

	/**
	 * Try out the methods above.
	 * 
	 * @param args
	 *            not used
	 */
	public static void main(String[] args) {
		String[] WORDS = { "wombat", "zebra", "aardvark", "cat" };

		// sort according to natural ordering
		String[] arr = Arrays.copyOf(WORDS, WORDS.length);
		selectionSort(arr);
		System.out.println(Arrays.toString(arr));

		// sort according to LengthComparator, defined below
		arr = Arrays.copyOf(WORDS, WORDS.length);
		selectionSort(arr, new LengthComparator());
		System.out.println(Arrays.toString(arr));

		// same as above, but creates the Comparator "on the fly" as
		// an anonymous inner class
		arr = Arrays.copyOf(WORDS, WORDS.length);
		Comparator<String> comp = new Comparator<String>() {
			@Override
			public int compare(String lhs, String rhs) {
				// returns a negative number if lhs is shorter, positive number
				// if lhs is longer
				return lhs.length() - rhs.length();
			}
		};
		selectionSort(arr, comp);
		System.out.println(Arrays.toString(arr));

		// Sort an array of Vehicle using the natural ordering
		Vehicle[] cars = { new Vehicle("Studebaker", 1943), new Vehicle("Chevy", 1958), new Vehicle("Ford", 1957),
				new Vehicle("Chevy", 1957), new Vehicle("Ford", 1943) };

		selectionSort(cars);
		System.out.println(Arrays.toString(cars));

		// Sort an array of Vehicle using the YearComparator defined below
		selectionSort(cars, new YearComparator());
		System.out.println(Arrays.toString(cars));
		
	}

}
