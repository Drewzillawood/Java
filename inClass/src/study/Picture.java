package study;

import java.util.ArrayList;

public class Picture {

	private ArrayList<Shape> list;
	
	public Picture() {
		
		list = new ArrayList<Shape>();
		
	}
	
	public void add(Shape s) {
		
		list.add(s);
		
	}
	
	public double findTotalArea() {
		
		int sum = 0;
		
		for(int i = 0; i < list.size(); i++) {
			
			sum += list.get(i).getArea();
			
		}
		
		return sum;
		
	}

}
