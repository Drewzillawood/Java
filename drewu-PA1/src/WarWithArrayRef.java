import java.util.ArrayList;


public class WarWithArrayRef extends WarRef
{
	ArrayList<String> array;

	public WarWithArrayRef(String[] s_, int k_)
	{
		super(s_, k_);

		array = new ArrayList<String>();
		for(String input : s)
		{
			array.add(input);
		}
	}

	public boolean inputContains(String candidate, int i)
	{
		return array.contains(candidate.substring(i, i+k));
	}
}