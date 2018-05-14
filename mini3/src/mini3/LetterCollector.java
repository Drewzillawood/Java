package mini3;

import api.Combiner;

public class LetterCollector implements Combiner {
	
	public String combine(String first, String second){
		
		String newString = "";
		newString += first;
		int flag = 0;
		
		for(int i = 0; i < second.length(); i++){
			
			for(int j = 0; j < first.length(); j++){
				
				if(newString.charAt(j) == second.charAt(i)){
					
					flag++;
					
				}
				
			}
			
			if(flag > 0){
				
				flag = 0;
				
			} else {
				
				if(newString.contains("" + second.charAt(i))){
					
					
					
				} else {
				
					newString += second.charAt(i);
					
				}
				
			}
			
		}
		
		return newString;
		
	}

}
