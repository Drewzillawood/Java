package lab7;

import java.util.Arrays;
import java.util.Random;

/**
 * Putting together simple checkpoint 1
 * 
 * @author drewu
 *
 */
public class CheckPoint_1
{
	/**
	 * Execute test meothds
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		int[] a = {2, 3, -1, -4, 5, 7};
		System.out.println(Arrays.toString(getPositiveNumbers(a)));
		System.out.println(Arrays.toString(randomExperiment(10, 1000)));
		
		Random r = new Random(137);
		Deck d = new Deck(r);
		Card[] hand = d.select(5);
		System.out.println(Card.toString(hand));
	}

	/**
	 * Retrieve positive numbers from int array
	 * 
	 * @param numbers
	 *            int array of numbers
	 * 
	 * @return positives
	 */
	public static int[] getPositiveNumbers(int[] numbers)
	{
		int size = 0;
		for(int i : numbers)
			if(i >= 0)
				size++;
		
		int[] positives = new int[size];
		for(int i = 0, j = 0; i < numbers.length; i++)
			if(numbers[i] >= 0)
			{
				positives[j] = numbers[i];
				j++;
			}
		
		return positives;
	}

	/**
	 * Generate some random numbers
	 * 
	 * @param max
	 *            upper bound
	 * @param iters
	 *            how many numbers to generate
	 * 
	 * @return how many of each number was generated
	 */
	public static int[] randomExperiment(int max, int iters)
	{
		Random r = new Random();
		int[] counts = new int[max];
		
		for(int i = 0; i < iters; i++)
			counts[r.nextInt(max)]++;
		
		return counts;
	}

}
