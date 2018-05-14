package hw3;

import java.util.ArrayList;

import api.GridPosition;
import api.Jewel;

/**
 * Game class
 * 
 * @author drewu
 *
 */
public class Game
{

	/**
	 * 2D Array of Jewel objects
	 */
	private Jewel[][]		myJewels;

	/**
	 * Current JewelFactory instance
	 */
	private JewelFactory	jewelFactory;

	/**
	 * Score
	 */
	private int				score;

	/**
	 * Constructs a game with the given number of columns and rows that will use
	 * the given JewelFactory instance to create new jewels. The grid is
	 * initialized according to the factory's createGrid method.
	 * 
	 * @param width
	 *            number of columns
	 * @param height
	 *            number of rows
	 * @param generator
	 *            generator for new jewels
	 */
	public Game(int width, int height, JewelFactory generator)
	{
		myJewels = generator.createGrid(width, height);
		jewelFactory = generator;
		score = 0;
	}

	/**
	 * Constructs a game whose size and initial configuration are determined by
	 * the given string array and that will use the given JewelFactory instance
	 * to create new jewels. Each string in the given array consists of
	 * whitespace-separated integers corresponding to the initial jewel type for
	 * each cell.
	 * 
	 * @param descriptor
	 *            array of strings
	 * @param generator
	 *            generator for new jewels
	 */
	public Game(String[] descriptor, JewelFactory generator)
	{
		myJewels = Util.createFromStringArray(descriptor);
		jewelFactory = generator;
		score = 0;
	}

	/**
	 * Returns the Jewel at the given row and column.
	 * 
	 * @param row
	 *            given row in the grid
	 * @param col
	 *            given column in the grid
	 * @return Jewel at the given row and column
	 */
	public Jewel getJewel(int row, int col)
	{
		return myJewels[row][col];
	}

	/**
	 * Sets the Jewel at the given row and column.
	 * 
	 * @param row
	 *            given row in the grid
	 * @param col
	 *            given column in the grid
	 * @param jewel
	 *            value to be set
	 */
	public void setJewel(int row, int col, Jewel jewel)
	{
		myJewels[row][col] = jewel;
	}

	/**
	 * Return the width of the grid for this game (number of columsn).
	 * 
	 * @return width of the grid
	 */
	public int getWidth()
	{
		return myJewels[0].length;
	}

	/**
	 * Returns the height of the grid for this game (number of rows).
	 * 
	 * @return number of rows in the grid for this game
	 */
	public int getHeight()
	{
		return myJewels.length;
	}

	/**
	 * Returns the current score for this game
	 * 
	 * @return number of rows in the grid for this game
	 */
	public int getScore()
	{
		return score;
	}

	/**
	 * Returns a copy of the given row of the grid
	 * 
	 * @param row
	 * @return a new array containing the Jewels from the given row
	 */
	public Jewel[] getRow(int row)
	{
		return myJewels[row];
	}

	/**
	 * Copies the given array values into the specified row of the grid.
	 * 
	 * @param row
	 *            given row
	 * @param arr
	 *            array of Jewel containing the values for the row
	 * @return
	 */
	public void setRow(int row, Jewel[] arr)
	{
		for(int i = 0; i < getWidth(); i++)
		{
			myJewels[i][row] = arr[i];
		}
	}

	/**
	 * Returns a copy of the given column of the grid
	 * 
	 * @param col
	 *            given col
	 * @return a new array containing the Jewels from the given col
	 */
	public Jewel[] getCol(int col)
	{
		Jewel[] thisCol = new Jewel[getHeight()];
		for(int i = 0; i < thisCol.length; i++)
		{
			thisCol[i] = myJewels[i][col];
		}
		return thisCol;
	}

	/**
	 * Copies the given array values into the specified column of the grid.
	 * given array.
	 * 
	 * @param col
	 *            given col
	 * @param arr
	 *            array of Jewel containing the values for the column
	 */
	public void setCol(int col, Jewel[] arr)
	{
		for(int i = 0; i < getHeight(); i++)
		{
			myJewels[i][col] = arr[i];
		}
	}

	/**
	 * Exchanges the Jewels described by the given GridPositions, if possible.
	 * 
	 * The caller is responsible for ensuring that the given positions are
	 * horizontally or vertically adjacent. The exchange is allowed only if one
	 * of the affected cells forms part of a run after the jewels are swapped.
	 * If so, the jewels for the two cells are exchanged and the method returns
	 * true; otherwise, the method returns false. No other aspects of the game
	 * state are modified. (Note that only the row and column of the given
	 * GridPositions are used; the Jewel attribute is ignored.)
	 * 
	 * @param c0
	 *            grid position
	 * @param c1
	 *            grid position adjacent to c0
	 * @return true if the two given cells are exchanged, false otherwise
	 */
	public boolean select(GridPosition c0, GridPosition c1)
	{
		if(c0.equals(null) || c1.equals(null))
		{
			return false;
		}
		else
		{
			swap(c0, c1);
			if(findVerticalRuns(c0.col()).size() > 0 || findHorizontalRuns(c0.row()).size() > 0
					|| findVerticalRuns(c1.col()).size() > 0 || findHorizontalRuns(c1.row()).size() > 0)
			{
				return true;
			}
			else
			{
				swap(c0, c1);
				return false;
			}
		}
	}

	/**
	 * Private swap method
	 * 
	 * @param c0
	 * @param c1
	 */
	private void swap(GridPosition c0, GridPosition c1)
	{
		Jewel temp = myJewels[c0.row()][c0.col()];
		myJewels[c0.row()][c0.col()] = myJewels[c1.row()][c1.col()];
		myJewels[c1.row()][c1.col()] = temp;
		c0 = new GridPosition(c1.row(), c1.col(), c1.getJewel());
		c1 = new GridPosition(c0.row(), c0.col(), c0.getJewel());
	}

	/**
	 * Finds runs in the given row of the grid using the method Util.findRuns
	 * The return value is a list containing a GridPosition object for each cell
	 * that is part of a run, in left-to-right order. This method does not
	 * modify the grid or update the score.
	 * 
	 * @param row
	 *            the row in which to find runs
	 * @return list of GridPosition objects for cells that are part of a run
	 */
	public ArrayList<GridPosition> findHorizontalRuns(int row)
	{
		ArrayList<Integer> thisRowRuns = Util.findRuns(getRow(row));
		ArrayList<GridPosition> runPositions = new ArrayList<GridPosition>();
		for(int i = 0; i < thisRowRuns.size(); i++)
		{
			runPositions.add(new GridPosition(row, thisRowRuns.get(i), myJewels[row][thisRowRuns.get(i)]));
		}
		return runPositions;
	}

	/**
	 * Finds runs in the given column of the grid using the method Util.findRuns
	 * The return value is a list containing a GridPosition object for each cell
	 * that is part of a run, in top-to-bottom order. This method does not
	 * modify the grid or update the score.
	 * 
	 * @param col
	 *            the column in which to find runs
	 * @return list of GridPosition objects for cells that are part of a run
	 */
	public ArrayList<GridPosition> findVerticalRuns(int col)
	{
		ArrayList<Integer> thisColRuns = Util.findRuns(getCol(col));
		ArrayList<GridPosition> runPositions = new ArrayList<GridPosition>();
		for(int i = 0; i < thisColRuns.size(); i++)
		{
			runPositions.add(new GridPosition(thisColRuns.get(i), col, myJewels[thisColRuns.get(i)][col]));
		}
		return runPositions;
	}

	/**
	 * Finds all horizontal and vertical runs, and returns a list containing a
	 * GridPosition object for all cells that are part of a run. The list is in
	 * no particular order and may contain duplicates. All grid positions that
	 * are part of a run are set to null in the grid, and the score is updated.
	 * 
	 * @return list of GridPosition objects for all cells that are part of a run
	 */
	public ArrayList<GridPosition> findAndMarkAllRuns()
	{
		ArrayList<GridPosition> runs = new ArrayList<GridPosition>();
		for(int i = 0; i < myJewels[0].length; i++)
		{
			ArrayList<GridPosition> vRuns = findVerticalRuns(i);
			for(int j = 0; j < vRuns.size(); j++)
			{
				runs.add(vRuns.get(j));
				myJewels[vRuns.get(j).row()][vRuns.get(j).col()] = null;
			}
		}
		for(int i = 0; i < myJewels.length; i++)
		{
			ArrayList<GridPosition> hRuns = findHorizontalRuns(i);
			for(int j = 0; j < hRuns.size(); j++)
			{
				runs.add(hRuns.get(j));
				myJewels[hRuns.get(j).row()][hRuns.get(j).col()] = null;
			}
		}
		for(int i = 0; i < myJewels[0].length; i++)
		{
			shiftColumnDown(i);
		}
		return runs;
	}

	/**
	 * Shifts the Jewels in a given column downward using the method
	 * Util.shiftNonNullElements. After executing this method the null cells, if
	 * any, are at the top of the column. The order of the Jewels is not
	 * changed. The return value is a list containing a GridPosition object for
	 * each Jewel that was moved by this operation. The GridPosition's row and
	 * column should be the position of the moved Jewel when the operation
	 * completes.
	 * 
	 * @param col
	 *            the given column
	 * @return list of GridPosition objects for elements that are moved
	 */
	public ArrayList<GridPosition> shiftColumnDown(int col)
	{
		ArrayList<GridPosition> downShift = new ArrayList<GridPosition>();
		Jewel[] newCol = getCol(col);
		ArrayList<Integer> shifted = Util.shiftNonNullElements(newCol);
		setCol(col, newCol);
		for(int i = 0; i < shifted.size(); i++)
		{
			downShift.add(new GridPosition(col, shifted.get(i), myJewels[shifted.get(i)][col]));
		}
		return downShift;
	}

	/**
	 * Replaces each null cell in the grid with a new Jewel created by a call to
	 * this Game's JewelFactory. The return value is a list containing a
	 * GridPosition object for each newly assigned cell.
	 * 
	 * @return list of GridPosition objects for the filled cells
	 */
	public ArrayList<GridPosition> fillAll()
	{
		ArrayList<GridPosition> added = new ArrayList<GridPosition>();
		for(int i = 0; i < myJewels.length; i++)
		{
			for(int j = 0; j < myJewels[i].length; j++)
			{
				if(myJewels[i][j] == null)
				{
					myJewels[i][j] = jewelFactory.generate();
					added.add(new GridPosition(i, j, myJewels[i][j]));
				}
			}
		}
		return added;
	}

	/**
	 * Returns a String representation of the grid for this game, with rows
	 * delimited by newlines.
	 * 
	 * @return String representation of grid
	 */
	public String toString()
	{
		return Util.convertToString(myJewels);
	}
}
