package examples;

import hw4.BasicGenerator;

public class TestGenerator
{
	public static void main(String[] args)
	{
		BasicGenerator b = new BasicGenerator();
		
		for(int i = 0; i < 15; i++)
			System.out.println(b.getNext(10).toString());
	}
}
