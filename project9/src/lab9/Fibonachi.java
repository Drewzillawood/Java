package lab9;

public class Fibonachi {

	public static void main(String[] args) {
		
		System.out.println(checkpointOne(75));

	}
	
	/**
	   * Recursively computes the nth Fibonacci number.
	   */
	  public static long fib(long n)
	  {
	    if (n == 0 || n == 1)
	    {
	      // base cases: return n
	      return n;
	    }
	    else
	    {
	      // recursive case: sum of previous two values
	      return fib(n - 1) + fib(n - 2);
	    }
	  }
	  
	  public static int checkpointOne(int n){
		  
		  if(n == 1){
			  
			  return n;
			  
		  }
		  
		  return (n*n) + (checkpointOne(n-1));
		  
	  }

}

