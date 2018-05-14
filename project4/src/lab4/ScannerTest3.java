package lab4;

import java.util.Scanner;

public class ScannerTest3
{
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);

		System.out.print("Enter a number: ");
		if(in.hasNextInt())
		{
			int i = in.nextInt();
			System.out.println("Ok, you entered the number " + i);
		}
		else
		{
			String text = in.next();
			System.out.println("Hmm, " + text + " isn't a number");
		}

		in.close();
	}
}