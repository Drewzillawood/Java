
package hw4;

import java.awt.Color;

import api.Block;
import api.Cell;
import api.Position;
import api.Shape;

/**
 * Abstract superclass for implementations of the Shape interface.
 */
public abstract class AbstractShape implements Shape
{
	/**
	 * Instance variable for this Shapes cells
	 */
	private Cell[]		cells;

	/**
	 * Instance variable to hold center of rotation
	 */
	private Position	center;

	/**
	 * Position of the magic block if it exists
	 */
	private int			i;

	/**
	 * Abstract constructor for shapes
	 * 
	 * @param p
	 *            Position of the new shape
	 * @param magic
	 *            Whether or not this shape will have a magic cell
	 */
	protected AbstractShape(Position center, Position[] p, Color c, boolean magic)
	{
		this.center = new Position(center);
		Block b = new Block(c);
		cells = new Cell[p.length];
		cells[0] = new Cell(new Block(c, magic), p[0]);
		for(int i = 1; i < cells.length; i++)
			cells[i] = new Cell(b, p[i]);
	}

	/**
	 * Return a clone of our cells
	 */
	public Cell[] getCells()
	{
		Cell[] copy = new Cell[cells.length];
		for(int i = 0; i < cells.length; i++)
			copy[i] = new Cell(cells[i]);
		return copy;
	}

	/**
	 * Shifts shape down one row
	 */
	public void shiftDown()
	{
		center = new Position(center.row() + 1, center.col());
		for(Cell c : cells)
			c.setRow(c.getRow() + 1);
	}

	/**
	 * Shifts shape left one column
	 */
	public void shiftLeft()
	{
		center = new Position(center.row(), center.col() - 1);
		for(Cell c : cells)
			c.setCol(c.getCol() - 1);
	}

	/**
	 * Shifts shape right one column
	 */
	public void shiftRight()
	{
		center = new Position(center.row(), center.col() + 1);
		for(Cell c : cells)
			c.setCol(c.getCol() + 1);
	}

	/**
	 * Cycle the magic block through natural ordering if it exists
	 */
	public void cycle()
	{
		if(cells[i].getBlock().isMagic())
		{
			int o = (i + 1) % cells.length;
			Cell t = cells[i];
			Color c = t.getBlock().getColorHint();
			cells[i] = new Cell(new Block(c), new Position(t.getRow(), t.getCol()));
			cells[o] = new Cell(new Block(c, true), new Position(cells[o].getRow(), cells[o].getCol()));
			i = o;
		}
	}

	/**
	 * Abstract transformation
	 */
	public void transform()
	{
		for(int j = 0; j < cells.length; j++)
		{
			Block b = cells[j].getBlock();
			int r = cells[j].getRow() - center.row();
			int c = cells[j].getCol() - center.col();

			int r_rotation = center.row() - c;
			int c_rotation = center.col() + r;

			cells[j] = new Cell(b, new Position(r_rotation, c_rotation));
		}
	}

	/**
	 * Provide a deep copy of the Shape
	 */
	public Shape clone()
	{
		try
		{
			AbstractShape s = (AbstractShape)super.clone();

			s.cells = new Cell[cells.length];
			s.center = new Position(center);
			for(int i = 0; i < cells.length; i++)
				s.cells[i] = new Cell(cells[i]);
			return s;
		}
		catch(CloneNotSupportedException e)
		{
			// can't happen
			return null;
		}
	}

	/**
	 * Returns the center of rotation for this shape
	 * 
	 * @return center
	 */
	protected Position getCenter()
	{
		return center;
	}

	/**
	 * Changes made in this method SHOULD modify the array
	 */
	protected Cell[] getCellAddresses()
	{
		return cells;
	}
}
