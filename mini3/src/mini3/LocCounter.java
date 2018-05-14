package mini3;

import java.util.Scanner;

import api.IntCombiner;

public class LocCounter implements IntCombiner{
	
	public int combine(int num, String s){
		
		Scanner scanner = new Scanner(s);
		String checker = "";
		
		if(scanner.hasNext()){
			
			checker = scanner.next();
			
		} else {
			
			scanner.close();
			return num;
			
		}
		
		if(checker.contains("//") || checker.equals("}") || checker.equals("{")){
			
			scanner.close();
			return num;
			
		}
		
		scanner.close();
		num++;
		return num;
		
	}
	

}
