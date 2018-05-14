package study;

import java.util.ArrayList;

public class PointTest {

	public static void main(String[] args) {
		
		Point point = new Point(1,1);
		Point point2 = new Point(2,2);
		Point point3 = new Point(-1,-1);
		
		ArrayList<Point> holder = new ArrayList<Point>();
		holder.add(point3);
		holder.add(point2);
		holder.add(point);
		
		System.out.println("[ (" + holder.get(0).getX() + ", " + holder.get(0).getY() + ") " +
							 "(" + holder.get(1).getX() + ", " + holder.get(1).getY() + ") " +
							 "(" + holder.get(2).getX() + ", " + holder.get(2).getY() + ") ]");
		
		System.out.println(point2.compareTo(point3));

	}

}
