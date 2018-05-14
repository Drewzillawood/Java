package mini3;

import java.util.Scanner;

import api.Selector;

public class NonCommentLineSelector implements Selector {

	public boolean select(String s) {
		
		Scanner scanner = new Scanner(s);
		
		if(scanner.hasNext()){
		
			if(scanner.next().contains("//")){
			
				scanner.close();
				return false;
			
			} else {
			
				scanner.close();
				return true;
			
			}
		
		}
		
		scanner.close();
		return true;
		
	}
	
}
