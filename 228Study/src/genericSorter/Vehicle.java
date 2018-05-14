package genericSorter;

/**
 * A Vehicle class encapsulates a year and make. The "natural" ordering of
 * vehicles is by make.
 */
class Vehicle implements Comparable<Vehicle> {
	private String make;
	private int year;

	public Vehicle(String m, int y) {
		make = m;
		year = y;
	}

	public int getYear() {
		return year;
	}

	public String getMake() {
		return make;
	}

	@Override
	public String toString() {
		return year + " " + make;
	}

	@Override
	public int compareTo(Vehicle rhs) {
		// orders by make using the natural ordering of String
		return make.compareTo(rhs.make);
	}
}
