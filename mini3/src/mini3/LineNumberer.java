package mini3;

import api.Transformation;

public class LineNumberer implements Transformation{
	
	private int number = 1;
	
	public String apply(String s){
		
		String newString = "";
		
		newString = String.format("%-5d", number) + s; 
		
		number++;
		
		return newString;
		
	}

}
