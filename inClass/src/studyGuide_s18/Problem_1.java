package studyGuide_s18;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Problem_1
{

	@SuppressWarnings("resource")
	public static void main(String[] args) throws FileNotFoundException
	{
		String[][] whatProblemsShouldweCover = {
				{"1", "m", "o", "s"},
				{"2"},
				{"6"},
				{"7"},
				{"8"}
		};
		String filename = "U://cs227/workspace/inClass/src/studyGuide_s18/test.txt";
		File file = new File(filename);
		Scanner scanner = new Scanner(file);
		
		// Gets the whole next line
		String s = scanner.nextLine();
		
		// Gives you the next token
		// i.e. "hello there" calling next gives "hello" 
		String t = scanner.next();
		
		// If we want to read a whole file to the end
		while(scanner.hasNextLine())
		{
			// This actually moves the scanner, without it you get infinite loop
			String temp = scanner.nextLine();
			// Need a new scanner to walk through our line
			Scanner line = new Scanner(temp);
			while(line.hasNext())
				line.next();
		}
		
		// IMPORTANT FOR FILES
		scanner.close();
		
		
		
		
		
		int[] arr = {2, 5, 4, 6};
		int[][] newArr = m(2, 2, arr);
		for(int[] a : newArr)
			System.out.println(Arrays.toString(a));
		//---------------------
		int[] arr_o = {1, 2, 3, 4};
		o(arr_o);
		System.out.println(Arrays.toString(arr_o));
		//---------------------
		int[] arr_s = {2,3,4,5,6};
		s(arr_s);
		System.out.println(Arrays.toString(arr_s));
	}

	/**
	 * Problem 1_m
	 * 
	 * @param w
	 * @param h
	 * @param arr
	 * @return
	 */
	public static int[][] m(int w, int h, int[] arr)
	{
		int[][] newArr = new int[h][w];
		int k = 0;
		for(int i = 0; i < h; i++)
			for(int j = 0; j < w; j++)
			{
				newArr[i][j] = arr[k];
				k++;
			}
		return newArr;
	}

	/**
	 * Problem 1_n
	 * 
	 * @param n
	 * @return
	 */
	public static int n(int n)
	{
		int n_1 = n + 1;
		while(!isPrime(n_1))
			n_1++;
		return n_1;
	}

	/** With helper **/
	private static boolean isPrime(int num)
	{
		if(num < 2)
			return false;
		if(num == 2)
			return true;
		if(num % 2 == 0)
			return false;
		for(int i = 3; i * i <= num; i += 2)
			if(num % i == 0)
				return false;
		return true;
	}

	/**
	 * Problem 1_o
	 * @param arr
	 * @return
	 */
	public static void o(int[] arr)
	{
		// {2, 2, 4, 2}
		// {2, 0, 4, 0}
		for(int i = 0; i < arr.length - 1; i++)
			for(int j = i + 1; j < arr.length; j++)
				if(arr[i] == arr[j])
					arr[j] = 0;
		
		// Now we need to shift the zeros to the end!
		for(int i = 0; i < arr.length - 1; i++)
			for(int j = i + 1; j < arr.length; j++)
				if(arr[i] == 0)
					swap(arr, i, j);
	}
	/** With helper **/
	private static void swap(int[] arr, int i, int j)
	{
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	/**
	 * Problem 1_s
	 * @param arr
	 */
	public static void s(int[] arr)
	{
		int swapper = arr.length / 2 + arr.length % 2;
		for(int i = 0; i < arr.length - swapper; i++)
			swap(arr, i, i + swapper);
	}

}
