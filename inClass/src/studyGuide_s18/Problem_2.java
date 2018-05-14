package studyGuide_s18;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Problem_2
{

	public static void main(String[] args) throws FileNotFoundException
	{
		Scanner s = new Scanner(System.in);
		String fileName = s.nextLine();
		File f = new File(fileName);
		PrintWriter o = new PrintWriter(fileName.replace(".java", ".out"));
		s.close();
		s = new Scanner(f);
		
		while(s.hasNextLine())
		{
			String temp = s.nextLine();
			if(!temp.contains("//"))
				o.println(temp);
		}
		
		o.close();
		s.close();
		
	}

}
