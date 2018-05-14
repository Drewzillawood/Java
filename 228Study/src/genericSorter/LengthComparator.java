package genericSorter;

import java.util.Comparator;

/**
 * Comparator that will order strings by length.
 */
class LengthComparator implements Comparator<String> {
	@Override
	public int compare(String lhs, String rhs) {
		// returns a negative number if lhs is shorter, positive number if lhs
		// is longer
		return lhs.length() - rhs.length();
	}
}
