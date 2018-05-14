package hw4;

import java.awt.Color;

import api.Position;

/**
 * LShape implementation
 * 
 * @author drewu
 *
 */
public class LShape extends AbstractShape
{
	/**
	 * LShape constructor
	 * 
	 * @param p
	 * 			Position to be centered around
	 * @param magic
	 * 			Whether or not the shape will be magic
	 */
	public LShape(Position p, boolean magic)
	{
		super(p, getPositions(p), Color.ORANGE, magic);
	}
	
	/**
	 * Get all positions to initialize this shape
	 * 
	 * @param p
	 *            Centered position
	 *            
	 * @return List of positions
	 */
	private static Position[] getPositions(Position p)
	{
		Position[] pos = new Position[]
		{
			new Position(p.row(),   p.col()),
			new Position(p.row()+1, p.col()-2),
			new Position(p.row()+1, p.col()-1),
			new Position(p.row()+1, p.col())
		};
		return pos;
	}
}