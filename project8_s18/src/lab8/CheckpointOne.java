package lab8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CheckpointOne
{

	public static void main(String[] args) throws FileNotFoundException
	{
//		File f = new File("../project7_s18/src/lab7/Deck.java");
//		lineNumberer(f);
		
		File g = new File("story.txt");
		numberOfWords(g);
	}

	public static void lineNumberer(File f) throws FileNotFoundException
	{
		Scanner s = new Scanner(f);
		for(int i = 1; s.hasNextLine(); i++)
			System.out.println(i + " " + s.nextLine());
		s.close();
	}

	public static void numberOfWords(File f) throws FileNotFoundException
	{
		Scanner s = new Scanner(f);
		while(s.hasNextLine())
		{
			String temp = s.nextLine();
			Scanner t = new Scanner(temp);
			int count = 0;
			while(t.hasNext())
			{
				t.next();
				count++;
			}
			t.close();
			System.out.println(count);
		}
		s.close();
	}

}
