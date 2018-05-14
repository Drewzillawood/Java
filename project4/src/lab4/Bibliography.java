package lab4;

import java.util.Scanner;

public class Bibliography
{

	public static void main(String[] args)
	{

		String s1 = "Dijkstra, Edsger#Go To Statement Considered Harmful#Communications of the ACM#1968#11";
		String s2 = "Levoy, Marc#Display of Surfaces from Volume Data#IEEE Computer Graphics and Applications#1988#8";
		String s3 = "Dean, Jeffrey; Ghemawat, Sanjay#MapReduce: Simplified Data Processing on Large Clusters#Communications of the ACM#2008#51";

		System.out.println(myHelperMethod(s1));
		System.out.println(myHelperMethod(s2));
		System.out.println(myHelperMethod(s3));

	}

	private static BibItem myHelperMethod(String s)
	{

		Scanner parser = new Scanner(s);
		parser.useDelimiter("#");

		String author = parser.next();
		String title = parser.next();
		String journal = parser.next();
		int year = parser.nextInt();
		int volume = parser.nextInt();

		BibItem temp = new BibItem(author, title, journal, year, volume);
		parser.close();
		return temp;

	}

}
