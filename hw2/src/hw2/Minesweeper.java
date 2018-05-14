package hw2;

import java.util.ArrayList;
import java.util.Random;

import api.Cell;
import api.CellObserver;
import api.Mark;
import api.Status;

/**
 * This class encapsulates the state of a minesweeper game.
 * @author drewu
 */
public class Minesweeper
{
  
  /**
   * This variable will store the number of mines in a given game of Minesweeper
   */
  private int mines;
  
  /**
   * This variable will store the number of flags which have been marked
   */
  private int flags;
  
  /**
   * Keeps track of the number of clicks the player has registered
   */
  private int clicks;
  
  /**
   * Holds the number of rows in a given game of Minesweeper
   */
  private int numRows;
  
  /**
   * Holds the number of columns in a given game of Minesweeper
   */
  private int numCols;
  
  /**
   * Two dimensional array to store all of our mines and their locations
   */
  private Cell[][] checker;
  
  /**
   * History of all revealed cells in the order they were revealed
   */
  private ArrayList<Cell> history;
  
  /**
   * Boolean value to determine whether or not the game is over
   */
  private boolean gameOver;
  
  /**
   * Boolean value to determine if the game has been won
   */
  private boolean gameWon;
	
  /**
   * Constructs an instance of the game using the given array
   * of strings to initialize the mine locations. 
   * The jth character of the ith string corresponds to row i, column j
   * of the Cell array.  A <code>GameUtil.MINE_CHAR</code> character 
   * means the corresponding cell will be set as a mine. All 
   * strings in the given array must have the same length.  Initially
   * all cells have status HIDDEN and the counts are correct. 
   * @param descriptor
   *   array of strings representing mine positions
   */
  public Minesweeper(String[] descriptor)
  {
	
	mines = 0;
	history = new ArrayList<Cell>();
	
	// Converting given string array into our necessary grid
    checker = GridUtil.createFromStringArray(descriptor);
    
    // Initializing cells, statuses, marks, mines, and counts
    for(int i = 0; i < checker.length; i++){
		  
		  for(int j = 0; j < checker[0].length; j++){
			  
			  checker[i][j].setCount(0);
			  
			  // If a specified cell is not already a mine, leave it as such
			  if(!checker[i][j].isMine()){
				  
				  checker[i][j].setIsMine(false);
				  
			  } else {
				  
				  // We would know the cell is a mine, and can increment the mine count
				  mines++;
				  
			  }
			  
			  checker[i][j].setMark(Mark.NONE);
			  checker[i][j].setStatus(Status.HIDDEN);
		
		  }
		  
	}
    
    // Set counts throughout the grid
    GridUtil.initCounts(checker);
    
    // Initializing instance variables for future reference
    numRows = checker.length;
    numCols = checker[0].length;
    gameOver = false;
    gameWon = false;
    clicks = 0;
    
  }
  
  /**
   * Constructs an instance of the game of the specified size and
   * specified initial number of mines, using the given <code>Random</code> object
   * to select the mine locations.  The selection is performed in such a way that
   * each cell is equally likely to be selected as one of the mines.
   * Initially all cells have status HIDDEN and the counts are correct.
   * @param rows
   *   number of rows in the grid
   * @param columns
   *   number of columns in the grid
   * @param numberOfMines
   *   number of mines in the grid
   * @param givenRandom
   *   random number generator to use for placing mines and <code>randomMove</code>
   */
  public Minesweeper(int rows, int columns, int numberOfMines, Random givenRandom)
  {
	  
	  // Initializing instance variables
	  mines = numberOfMines;
	  flags = 0;
	  clicks = 0;
	  numRows = rows;
	  numCols = columns;
	  history = new ArrayList<Cell>();
	  
	  // Initializing our grid
	  checker = new Cell[rows][columns];
	  
	  // Filling our grid with default cells of hidden, count zero, no mark, and not a mine
	  for(int i = 0; i < rows; ++i){
		  
		  for(int j = 0; j < columns; ++j){
			  
			  checker[i][j] = new Cell(i, j);
			  checker[i][j].setCount(0);
			  checker[i][j].setIsMine(false);
			  checker[i][j].setMark(Mark.NONE);
			  checker[i][j].setStatus(Status.HIDDEN);
		
		  }
		  
	  }
	  
	  // If number of mines is greater than number of cells,
	  // simply fill all cells
	  if(mines > rows*columns){
		  
		  mines = rows*columns;
		  
	  }
	  int count = 0;
	  // Dispersing mines throughout our grid randomly
	  for(int i = 0; i < mines; i++){
		  
		  int row = givenRandom.nextInt(rows);
		  int col = givenRandom.nextInt(columns);
		  
		  
		  // If cell is already a mine, do not count it toward number
		  // of mines
		  if(checker[row][col].isMine()){
			  
			  i--;
			  
		  }
		  
		  checker[row][col].setIsMine(true);
		  count++;
	  }
	  
	  System.out.println("Count: " + count);
	  

	  GridUtil.initCounts(checker);
	  gameOver = false;
	  gameWon = false;
	  clicks = 0;
	  
  }
  
  /**
   * Returns the number of clicks for revealing a cell that have been made.
   * Note that this number may be smaller than the number of revealed
   * cells.  An operation to toggle the mark of a cell does not count
   * as a click.
   * @return
   *   number of clicks
   */
  public int getClicks()
  {
   
    return clicks;
    
  }
  
  /**
   * Returns the total number of mines in the grid.
   * @return
   *   number of mines
   *   
   */
  public int getNumMines()
  {

    return mines;
    
  }
  
  /**
   * Returns the total number of cells with mark value FLAG.
   * @return
   *   number of flagged cells
   */
  public int getNumFlags()
  {
    
    return flags;
    
  }
  
  /**
   * Returns the number of rows in the grid.
   * @return
   *   number of rows in the grid
   */
  public int getRows()
  {
    
    return numRows;
    
  }
  
  /**
   * Returns the number of columns in the grid.
   * @return 
   *   number of columns in the grid
   */
  public int getColumns()
  {
    
    return numCols;
    
  }
  
  /**
   * Returns the cell at the specified position.
   * <p>
   * NOTE: The caller of this method should normally not modify
   * the returned cell.
   * 
   * @param row
   *   given position row
   * @param col
   *   given position column
   * @return
   *   cell at the given position
   */
  public Cell getCell(int row, int col)
  {
    
    return checker[row][col];
    
  }
  
  /**
   * Returns this game's grid as an array of strings, according
   * to the conventions described in 
   * <code>GridUtil.convertToStringArray</code>.
   * @param revealAll
   *   true if hidden cell values should be shown
   * @return
   *   array of strings representing this game's grid
   */
  
  public String[] getGridAsStringArray(boolean revealAll)
  {
    
    return GridUtil.convertToStringArray(checker, revealAll);
    
  }
  
  /**
   * Returns a reference to the list of all revealed cells, in 
   * the order they were revealed.
   * <p>
   * NOTE: The caller of this method should normally not modify
   * the returned list or the cells it contains.
   * @return
   *   list of all revealed cells
   */
  public ArrayList<Cell> getHistory()
  {
    
    return history;
    
  }
  
  
  /**
   * Returns true if the game is over, false otherwise.
   * The game is over if the player attempts to reveal a mine,
   * or if all non-mine cells are revealed. 
   * @return
   *   true if the game is over, false otherwise
   */
  public boolean isOver()
  {
   
    return gameOver;
    
  }
  
  /**
   * Toggle the mark value on the cell at the given 
   * position.  The values should cycle through
   * <code>Mark.NONE</code>, <code>Mark.FLAG</code>, and
   * <code>Mark.QUESTION_MARK</code>, in that order.
   * @param row
   *   given position row
   * @param col
   *   given position column
   */
  public void toggleMark(int row, int col)
  {
	  Cell c = checker[row][col];
	  
	  if(c.getMark().equals(Mark.NONE) && !gameOver){
		  
		  // Turn the mark to a flag
		  c.setMark(Mark.FLAG);
		  
		  // Increment our flag count
		  flags++;
		  
	  } else if(c.getMark().equals(Mark.FLAG) && !gameOver){
		  
		  // Switch mark from flag to question mark
		  c.setMark(Mark.QUESTION_MARK);
		  
		  // Decrement our flag count
		  flags--;
		  
	  } else if(c.getMark().equals(Mark.QUESTION_MARK) && !gameOver){
		  
		  // Turn the mark back into NONE
		  c.setMark(Mark.NONE);
		 
		  
	  }
	  		  
  }
  
  /**
   * Processes a selection by the player to reveal the cell at the 
   * given position.  In general revealing a mine
   * should end the game, and revealing a cell with
   * count zero should initiate a call to 
   * <code>GridUtil.clearRegion</code>.  However, a special
   * case is made for the first selection: if the player
   * selects a mine as the first move, the cell is first converted
   * to a non-mine and the count is adjusted, and then the 
   * selection is processed normally.  This method does
   * nothing if the game is over.
   * @param row
   *   given position row
   * @param col
   *   given position column
   */
  public void play(int row, int col)
  {
	  
	  // Cell which was selected by our click
	  Cell c = checker[row][col];
	  
	  
	  // If we clicked on a mine...
	  if(c.isMine() && !gameOver && !c.getMark().equals(Mark.FLAG)){
		  
		  // But it's our first click
		  if(clicks == 0){
			  
			  // Get rid of the mine, and adjust our grid accordingly
			  c.setIsMine(false);
			  GridUtil.initCounts(checker);	
			  c.setStatus(Status.REVEALED);
			  mines--;
			  
			  // If the mine being cleared results in a count of zero, 
			  // call clear region
			  if(c.getCount() == 0){
				  
				  GridUtil.clearRegion(checker, row, col, history);
				  
			  }
			  
			  // If getting rid of the mine gets rid of all mines (there was only one to begin with)
			  // or results in the only possible click (all cells are mines) the game should be won
			  if(mines == 0 || mines == (checker.length*checker[0].length)-1){
				  
				  GridUtil.flagAllMines(checker);
				  gameOver = true;
				  gameWon = true;
				  
			  }
			  
		  } else {
			  
			  // Otherwise the game is over, reveal all mines
			  GridUtil.revealAllMines(checker);
			  gameOver = true;
			  
		  }
	
	  // If our click reveals a cell with count zero, clear the region of all other 
	  // Zeroes 
	  } else if(c.getCount() == 0 && !gameOver && !c.getMark().equals(Mark.FLAG)){
		  
		  GridUtil.clearRegion(checker, row, col, history);
	  
	  // If the cell is not a flag, and has a count greater than zero, reveal the cell
	  } else if(c.getCount() > 0 && !gameOver && !c.getMark().equals(Mark.FLAG)){
		  
		  c.setStatus(Status.REVEALED);
		  
	  // If the game is over, or the cell is a flag, do nothing
	  } else if((gameOver) || c.getMark().equals(Mark.FLAG)){
		  
		  // Do Nothing
		  
	  } 
	  
	  // If all non-mine cells have been revealed, then the game is over, 
	  // and the game is won.
	  if(GridUtil.areAllCellsRevealed(checker)){
		  
		  GridUtil.flagAllMines(checker);
		  gameOver = true;
		  gameWon = true;
		  
	  }
	  
	  history.add(c);
	  clicks++;
	  
  }
  
  /**
   * Returns whether or not the game has been won.
   * @return
   *   true if the game is won, false otherwise
   */
  public boolean isWon()
  {
  
    return gameWon;
    
  }
  
  /**
   * Iterates over the history of revealed cells, in reverse order, to find a
   * neighboring cell that is still hidden and has count greater than zero, 
   * and then reveals the first such cell found.  If the history is empty or no such cell
   * exists, this method does nothing. (The latter can only occur if all remaining hidden
   * non-mine cells have count zero.)
   */
  public void hint()
  {
	  
	// Iterate through every cell in our history ArrayList
    for(int i = history.size()-1; i >= 0; i--){
    	
    	Cell c = history.get(i);
    	
    	int row = c.getRow();
    	int col = c.getCol();
    	
    	Cell d = GridUtil.findOneHiddenNeighbor(checker, row, col);
    	
    	// Conditional to be sure we're capturing a valid cell
    	// A valid cell being within the grid, and not a mine
    	if(d != null && !gameOver){
    			
    			// If a valid cell is found, reveal it
    			d.setStatus(Status.REVEALED);
    			
    			history.add(d);
    			clicks++;
    			return;
    		
    	} else {
    		
    		// Conditional which wins the game if no hints are left, and all non-mine
    		// cells have been revealed
    		if(GridUtil.areAllCellsRevealed(checker)){
    			
    			  GridUtil.flagAllMines(checker);
    			  gameOver = true;
    			  gameWon = true;
    			  
    		}
    		
    		// Do Nothing - for an instance where no hint is possible
    		
    	}
    	
    }
    
  }

  
  
  /**
   * Calls setObserver with the given <code>CellObserver</code> on every cell 
   * of the grid.
   * @param observer
   *   reference to a <code>CellObserver</code>
   */
  public void setObserver(CellObserver observer)
  {
	  
	// Iterate through every cell in our grid
    for(int i = 0; i < checker.length; i++){
    	
    	for(int j = 0; j < checker[0].length; j++){
    		
    		// Set the observer to the cell
    		checker[i][j].setObserver(observer);
    		
    	}
    	
    }
  }
}
