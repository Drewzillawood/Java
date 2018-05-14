package lab9;

public class checkpoint1
{

	 public static void main(String[] args)
	 {
	   System.out.println(getPyramidCount(4));
	 }
	 
	 public static int getPyramidCount(int n)
	 {
		 if(n == 1)
		 {
			 return 1;
		 }
		 else 
		 {
			 return getPyramidCount(n-1) + n*n;
		 }
	 }
	 
}
