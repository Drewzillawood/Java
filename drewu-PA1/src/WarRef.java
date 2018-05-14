import java.util.ArrayList;


public abstract class WarRef
{
	String[] s;
	int k;

	public WarRef(String[] s_, int k_)
	{
		s = s_;
		k = k_;
	}

	public ArrayList<String> compute2k()
	{
		ArrayList<String> output = new ArrayList<String>();

		for(String si : s)
		{
			for(String sj : s)
			{
				String candidate = si + sj;

				boolean acceptCandidate = true;
				for(int i = 0; i <= k; i++)
				{
					if(!inputContains(candidate, i))
					{
						acceptCandidate = false;
						break;
					}
				}

				if(acceptCandidate)
				{
					output.add(candidate);
				}
			}
		}

		return output;
	}

	public abstract boolean inputContains(String candidate, int i);

	public static void main(String[] args)
	{
		String[] inputs = {"AA", "AC", "CC"};

		WarWithHash wwa = new WarWithHash(inputs, 2);

		System.out.println(wwa.compute2k());
	}
}
