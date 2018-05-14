import java.util.HashSet;


public class WarWithHashRef extends WarRef
{
	HashSet<String> set;

	public WarWithHashRef(String[] s_, int k_)
	{
		super(s_, k_);

		set = new HashSet<String>();
		for(String input : s)
		{
			set.add(input);
		}
	}

	public boolean inputContains(String candidate, int i)
	{
		return set.contains(candidate.substring(i, i+k));
	}
}