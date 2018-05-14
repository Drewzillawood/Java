package lab8;

import java.util.ArrayList;
import java.util.Arrays;

public class CheckPointTwo
{

	public static void main(String[] args)
	{
		ArrayList<String> a = new ArrayList<String>(Arrays.asList("Thing", "Thing"));
		removeDuplicates(a);
	}

	public static void removeDuplicates(ArrayList<String> words)
	{
		ArrayList<String> nonDupes = new ArrayList<String>();
		for(String s : words)
			if(!nonDupes.contains(s))
				nonDupes.add(s);

		System.out.println(nonDupes.toString());
	}

}
