public class WarWithBSTRef extends WarRef
{
	BinaryST bst;

	public WarWithBSTRef(String[] s_, int k_)
	{
		super(s_, k_);

		bst = new BinaryST();
		for(String input : s)
		{
			bst.add(input);
		}
	}

	public boolean inputContains(String candidate, int i)
	{
		return bst.search(candidate.substring(i, i+k));
	}
}