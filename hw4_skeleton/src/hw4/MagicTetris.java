
package hw4;

import java.util.ArrayList;
import java.util.List;

import api.AbstractGame;
import api.Block;
import api.Position;

/**
 * MagicTetris implementation.
 */
public class MagicTetris extends AbstractGame
{
	/**
	 * Grid used to represent the game, and to find collapsable positions
	 */
	private Block[][]	grid_2;

	/**
	 * Time for some magic
	 */
	private boolean		magicTime;

	/**
	 * Keeps track of the score
	 */
	private int			score;

	/**
	 * Constructs a game with the given width (columns) and height (rows). This
	 * game will use a new instance of BasicGenerator to generate new shapes.
	 * 
	 * @param width
	 *            width of the game grid (number of columns)
	 * @param height
	 *            height of the game grid (number of rows)
	 */
	public MagicTetris(int width, int height)
	{
		super(width, height, new BasicGenerator());
		grid_2 = new Block[height][width];
		for(int i = 0; i < height; i++)
			for(int j = 0; j < width; j++)
				grid_2[i][j] = getBlock(i, j);
		score = 0;
	}

	/**
	 * Returns a list of locations for all cells that form part of a collapsible
	 * set. This list may contain duplicates.
	 */
	@Override
	public List<Position> determinePositionsToCollapse()
	{
		fillGrid();
		List<Position> positions = new ArrayList<>();

		if(magicTime)
		{
			boolean[] collapse = new boolean[grid_2[0].length];
			for(int i = 0; i < grid_2.length; i++)
				for(int j = 0; j < grid_2[0].length; j++)
					if(grid_2[i][j] != null)
						collapse[j] = true;
					else if(collapse[j])
						positions.add(new Position(i, j));
			magicTime = false;
		}
		else
		{
			for(int i = 0; i < grid_2.length; i++)
			{
				if(checkComplete(grid_2[i]))
				{
					int magicCount = checkForMagic(grid_2[i]);
					if(magicCount > 2)
						magicTime = true;
					for(int j = 0; j < grid_2[0].length; j++)
						positions.add(new Position(i, j));
					score += Math.pow(2, magicCount);
				}
			}
		}
		return positions;
	}

	/**
	 * Returns the current score.
	 */
	@Override
	public int determineScore()
	{
		return score;
	}

	/**
	 * Check if a completed row is magic
	 * 
	 * @param blocks
	 * 
	 * @return true if its magic, y'knooooow
	 */
	private int checkForMagic(Block[] blocks)
	{
		int magicCount = 0;
		for(Block b : blocks)
			if(b.isMagic())
				magicCount++;
		return magicCount;
	}

	/**
	 * Check that a row is completely full
	 * 
	 * @param blocks
	 * 
	 * @return true if the row is completely full
	 */
	private boolean checkComplete(Block[] blocks)
	{
		int count = 0;
		for(Block b : blocks)
			if(b != null)
				count++;
		return count == grid_2[0].length;
	}

	/**
	 * Retrieve specifies row of blocks
	 * @return
	 */
	private void fillGrid()
	{
		for(int i = 0; i < grid_2.length; i++)
			for(int j = 0; j < grid_2[0].length; j++)
					grid_2[i][j] = super.getBlock(i, j);
	}

}
