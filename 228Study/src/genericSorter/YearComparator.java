package genericSorter;

import java.util.Comparator;

/**
 * Comparator for vehicles that orders lexicographically by year and make.
 */
public class YearComparator implements Comparator<Vehicle> {
	@Override
	public int compare(Vehicle lhs, Vehicle rhs) {
		// if the years are different, order by year,
		// for years that are the same, order by make
		if (lhs.getYear() != rhs.getYear()) {
			return lhs.getYear() - rhs.getYear();
		} else {
			return lhs.getMake().compareTo(rhs.getMake());
		}
	}
}
