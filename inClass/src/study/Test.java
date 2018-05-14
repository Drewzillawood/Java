package study;

import java.util.Scanner;

public class Test
{

	public static void main(String[] args)
	{

		System.out.println(tryStuff("10 20 23skidoo 30 foo bar"));

	}

	public static int tryStuff(String text)
	{

		int total = 0;
		int i = 0;

		Scanner scanner = new Scanner(text);
		while(scanner.hasNext())
		{

			try
			{

				String s = scanner.next();
				i = Integer.parseInt(s);
				total += i;

			}
			catch(NumberFormatException nf)
			{

				System.out.println("Work");
				total -= i;

			}

		}
		scanner.close();

		return total;

	}

}
