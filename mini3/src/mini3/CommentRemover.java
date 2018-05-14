package mini3;

import api.Transformation;

public class CommentRemover implements Transformation{
	
	public String apply(String s){
	
		for(int i = 0; i < s.length(); i++){
			
			if(s.charAt(i) == '/'){
				
				return s.substring(0, i);
				
			}
			
		}
		
		return s;
		
	}

}
