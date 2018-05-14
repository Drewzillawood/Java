package hw4;

import java.awt.Color;

import api.Position;

/**
 * TShape implementation
 * 
 * @author drewu
 *
 */
public class TShape extends AbstractShape
{
	/**
	 * OShape constructor
	 * 
	 * @param p
	 *            Position to be centered around
	 * @param magic
	 *            Whether or not the shape will be magic
	 */
	public TShape(Position p, boolean magic)
	{
		super(p, getPositions(p), Color.MAGENTA, magic);
	}
	
	/**
	 * Get all positions to initialize this shape
	 * 
	 * @param p
	 *            Centered position
	 * @return List of positions
	 */
	private static Position[] getPositions(Position p)
	{
		Position[] pos = new Position[]
		{
			new Position(p.row()-1, p.col()),
			new Position(p.row(),   p.col()-1),
			new Position(p.row(),   p.col()),
			new Position(p.row(),   p.col()+1)
		};
		return pos;
	}
}
