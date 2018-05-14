package studyGuide_s18;

import java.io.File;
import java.util.ArrayList;

public class Problem_8
{

	public static void main(String[] args)
	{
		System.out.println(Beatrice(1));
		System.out.println(Beatrice(2));
		System.out.println(Beatrice(3));
		System.out.println(Beatrice(4));

		System.out.println();

		System.out.println(Manhattan(1, 1));
		System.out.println(Manhattan(1, 2));
		System.out.println(Manhattan(2, 2));
		System.out.println(Manhattan(2, 3));

		System.out.println();

		System.out.println(listJavaFiles("U://cs227/workspace/inClass/src/studyGuide_s18"));

	}

	public static int Beatrice(int n)
	{
		if(n < 0)
			return 0;
		if(n == 0)
			return 1;
		return Beatrice(n - 1) + Beatrice(n - 2) + Beatrice(n - 3);
	}

	public static int Manhattan(int r, int c)
	{
		if(r == 0)
			return 1;
		if(c == 0)
			return 1;
		return Manhattan(r - 1, c) + Manhattan(r, c - 1);
	}

	public static ArrayList<String> listJavaFiles(String fileName)
	{
		File f = new File(fileName);
		ArrayList<String> files = new ArrayList<String>();
		findJavaFiles(f, files);
		return files;
	}

	private static void findJavaFiles(File file, ArrayList<String> results)
	{
		if(!file.isDirectory())
		{
			if(file.getName().contains(".java"))
				results.add(file.getName());
		}
		else
		{
			File[] files = file.listFiles();
			for(File f : files)
				findJavaFiles(f, results);
		}
	}

}
