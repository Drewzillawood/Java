package mini2;

import api.Selector;

/**
 * Selector whose <code>select</code> method returns false for strings whose first non-whitespace
 * text is "//", and true for all other strings.
 */
public class NonLineCommentSelector implements Selector
{

	@Override
	public boolean select(String s)
	{
		// TODO Auto-generated method stub
		return false;
	}
}