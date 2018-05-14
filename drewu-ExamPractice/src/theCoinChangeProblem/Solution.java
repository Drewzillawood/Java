package theCoinChangeProblem;

import java.util.Scanner;

public class Solution
{
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int m = in.nextInt();
		long[] c = new long[m];
		for (int c_i = 0; c_i < m; c_i++)
		{
			c[c_i] = in.nextLong();
		}
		in.close();
		// Print the number of ways of making change for 'n' units using coins
		// having the values given by 'c'
		System.out.println(getWays(n, c));
	}
	
	static long getWays(long n, long[] c)
	{
		long[][] memo = new long[c.length+1][(int)n+1];
		
		for(int row = 0; row < c.length+1; row++)
			for(int col = 0; col < n+1; col++)
				getWaysHelper(row, col, c, memo);
		
		return memo[c.length][(int)n];
	}
	
	static void getWaysHelper(int row, int n, long[]c, long[][] memo)
	{	
		int memoRow = Math.max(row-1, 0);
		
		if(n == 0)
		{
			memo[row][n] = 1;
		}
		else if(row > 0)
		{
			if(c[row-1] > n)
				memo[row][n] = memo[row-1][n];
			else
				memo[row][n] = memo[memoRow][n] + memo[row][(int)(n-c[row-1])];
		}
	}

	static long wrongGetWays(long n, long[] c)
	{
		// Complete this function
		long[][] memo = new long[c.length][(int)(n + 1)];

		for (int row = 0; row < c.length; row++)
		{
			for (int col = 1; col < n + 1; col++)
			{
				if (row == 0)
				{
					memo[row][col] = (col - row < 0) ? 0 : 1;
				}
				else
				{
					int memoRow = Math.max(row - 1, 0);
					int memoCol = Math.max(col - (int) c[row], 0);

					if (col < c[row])
						memo[row][col] = memo[memoRow][col];
					else
						memo[row][col] = Math.max(c[row] + memo[memoRow][memoCol], memo[memoRow][col]);
				}
			}
		}
		return memo[c.length-1][(int)n];
	}

}
