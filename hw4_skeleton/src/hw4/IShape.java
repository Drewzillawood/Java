package hw4;

import java.awt.Color;

import api.Position;

/**
 * IShape implementation
 * 
 * @author drewu
 *
 */
public class IShape extends AbstractShape
{
	/**
	 * IShape constructor
	 * 
	 * @param p
	 *            Position to be centered around
	 * @param magic
	 *            Whether or not the shape will be magic
	 */
	public IShape(Position p, boolean magic)
	{
		super(p, getPositions(p), Color.CYAN, magic);
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
			new Position(p.row()  , p.col()),
			new Position(p.row()+1, p.col()),
			new Position(p.row()+2, p.col())
		};
		return pos;
	}
}
