package lab9;

import java.io.File;

public class checkPointTwo {

	public static void main(String[] args) {
		
		int[] test = {3, 4, 5, 1, 2, 3, 2};
		System.out.println(divideAndConquerMax(test));
		
		

	}
	
	public static int divideAndConquerMax(int[] arr){
		
		return(divideAndConquerMaxRec(arr, 0, arr.length-1));
		
	}
	
	public static int divideAndConquerMaxRec(int[] arr, int start, int end){
		
		if(start == end){
			
			return(arr[start]);
			
		} else {
			
			int mid = (start + end) / 2;
			int leftMax = divideAndConquerMaxRec(arr, start, mid);
			int rightMax = divideAndConquerMaxRec(arr, mid+1, end);
			return Math.max(leftMax, rightMax);
			
		}
		
	}
	
	public static long countBytes(File f){
		
		if(f.isFile()){
			
			return f.length();
			
		} else {
			
			File[] files = f.listFiles();
		      for (int i = 0; i < files.length;)
		      {
		        countBytes(files[i]);
		        return(files[i].length());
		      }
		    
			
		}
		
		return 0;
		
	}

}
