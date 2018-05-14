package study;

public class Point implements Comparable<Point> {

	private int x;
	private int y;

	public Point(int givenX, int givenY) {

		x = givenX;
		y = givenY;

	}

	public int getX() {

		return x;

	}

	public int getY() {

		return y;

	}

	public int compareTo(Point pnt) {

		double dist;

		dist = getX() * getX() + getY() * getY();
		dist = Math.sqrt(dist);

		if (dist < distance(pnt)) {

			return -1;

		} else if (dist > distance(pnt)) {

			return 1;

		} else {

			return 0;

		}

	}

	private double distance(Point pnt) {

		double dist;

		dist = (pnt.getX() * pnt.getX()) + (pnt.getY() * pnt.getY());
		dist = Math.sqrt(dist);

		return dist;

	}

}
