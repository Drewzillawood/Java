package mini1;

import java.util.Scanner;

public class Test {

	public static void main(String[] args){
		
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Enter a number: ");
		String first = scanner.next();
		
		System.out.println("Your input was: " + first);
		scanner.close();
		
	}
	
	public boolean foo(int x, int y){
		
		boolean result = false;
		
			Math.min(x, y);
			
		return result;
	
	}
	
}
