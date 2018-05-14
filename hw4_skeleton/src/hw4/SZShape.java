package hw4;

import api.Position;

/**
 * SZShape Implementation
 * 
 * @author drewu
 *
 */
public class SZShape extends sOrZShape
{	
	/**
	 * SZShape constructor
	 * 
	 * @param p
	 *            Position to be centered around
	 * @param magic
	 *            Whether or not the shape will be magic
	 */
	public SZShape(Position p, boolean magic)
	{
		super(p, magic, true);
	}
}
