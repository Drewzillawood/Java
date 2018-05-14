package hw4;

import java.awt.Color;

import api.Block;
import api.Cell;
import api.Position;

/**
 * Step between AbstractShape and SZShape
 * 
 * @author drewu
 *
 */
public abstract class sOrZShape extends AbstractShape
{	
	/**
	 * Keeps track of what type of shape this will be
	 */
	private boolean greenShape;
	
	/**
	 * ZShape constructor
	 * 
	 * @param p
	 *            Position to be centered around
	 * @param magic
	 *            Whether or not the shape will be magic
	 */
	public sOrZShape(Position p, boolean magic, boolean greenShape)
	{
		super(p, getPositions(p, true), Color.GREEN, magic);
		this.greenShape = greenShape;
	}
	
	/**
	 * Transform will be overridden to allow reflection
	 * about the y-axis
	 */
	@Override
	public void transform()
	{
		greenShape = !greenShape;
		Cell[] cells = getCellAddresses();
		Position[] p = getPositions(getCenter(), greenShape);
		Color toBe = greenShape ? Color.GREEN : Color.RED;
		for(int i = 0; i < cells.length; i++)
			cells[i] = new Cell(new Block(toBe, cells[i].getBlock().isMagic()), new Position(p[i].row(), p[i].col()));
	}
	
	/**
	 * Get all positions to initialize this shape
	 * 
	 * @param p
	 *            Centered position
	 *            
	 * @return List of positions
	 */
	private static Position[] getPositions(Position p, boolean isGreen)
	{
		int i = (isGreen) ? 0 : 1;
		Position[] pos = new Position[]
		{
			new Position(p.row(),   p.col()+i),
			new Position(p.row()+1, p.col()+i),
			new Position(p.row()+1, p.col()+1-i),
			new Position(p.row()+2, p.col()+1-i)
		};
		return pos;
	}
}
