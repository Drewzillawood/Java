package tests;

import java.util.ArrayList;

public class First
{

	public static void main(String[] args)
	{
		multiMatch(35);
		System.out.println(findDivisible().toString());
	}
	
	private static ArrayList<Integer> findDivisible()
	{
		ArrayList<Integer> a = new ArrayList<Integer>();
		ArrayList<Integer> counts = new ArrayList<Integer>();
		
		int i = 0;
		for(int j = 0; j < 15; i++)
		{
			int count = 0;
			if(i % 5 == 0)
			{
				count++;
			}
			if(Integer.toString(i).contains("5"))
			{
				count += 2;
			}
			if(i % 7 == 0)
			{
				count += 4;
			}
			if(Integer.toString(i).contains("7"))
			{
				count += 8;
			}
			
			if(!a.contains(i) && !counts.contains(count))
			{
				a.add(i);
				counts.add(count);
				j++;
			}
			
		}
		return a;
	}

	private static void multiMatch(int toBeDivided)
	{
		if (isMatch(toBeDivided, 5, "cats") && isMatch(toBeDivided, 7, "boots"))
		{
			System.out.println("cats boots");
		}
		else if(isMatch(toBeDivided, 5, "cats"))
		{
			System.out.println("cats");
		}
		else if(isMatch(toBeDivided, 7, "boots"))
		{
			System.out.println("boots");
		}
		else
		{
			System.out.println(toBeDivided);
		}
	}

	private static boolean isMatch(int num, int divisibleBy, String output)
	{
		if (num % divisibleBy == 0)
		{
			return true;
		}

		String number = Integer.toString(num);
		if (number.contains(Integer.toString(divisibleBy)))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

}
