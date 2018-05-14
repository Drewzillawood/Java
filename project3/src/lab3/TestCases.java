package lab3;

public class TestCases {

	public static void main(String[] args) {
		
		int i = 0, x = 0;
		
		for(i=0;i<1000000;i++){
			
			x++;
			if(x > 6){
				
				x = 0;
				
			}
			
		}
		
		System.out.println("The one millionth number in the pattern is: " + x);
		
	}

}
