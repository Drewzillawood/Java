package hw2;

import static hw2.CS227Baseball.*;

public class Test
{
	public static void main(String[] args)
	{
		CS227Baseball c = new CS227Baseball(2);
		for(int i = 0; i < 20; i++)
			c.pitch(POP_FLY);
		System.out.println(c);
	}
}
