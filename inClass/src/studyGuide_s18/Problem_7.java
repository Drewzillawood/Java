package studyGuide_s18;

public class Problem_7
{

	public static void main(String[] args)
	{
		mystery(10);

	}
	
	public static void mystery(int x)
	{
		if(x == 1)
			System.out.println("pooh");
		else if(x % 2 == 0)
		{	
			mystery(x / 2);
			System.out.println(x);
		}	
		else
			mystery(x - 1);
	}

}
