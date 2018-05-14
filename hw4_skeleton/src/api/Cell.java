package api;


/**
 * Container for a block and a position.
 */
public class Cell
{
  /**
   * The block represented by this Cell.
   */
  private Block block;
  
  /**
   * The position of this Cell.
   */
  private Position position;
  
  /**
   * Constructs a Cell from the given block and position. 
   * @param b
   * @param position
   */
  public Cell(Block b, Position position)
  {
    this.block = b;
    this.position = new Position(position);
  }
  
  /**
   * Copy constructor creates a deep copy of the given Cell.
   * @param existing 
   *   the given Cell
   */
  public Cell(Cell existing)
  {
    // Blocks are immutable, no need to copy
    this.block = existing.block;
    this.position = new Position(existing.position);
  }
    
  /**
   * Returns the column for this cell's position.
   * @return 
   *   the column for this cell
   */
  public int getCol()
  {
    return position.col();
  }
  
  /**
   * Returns the row for this cell's position.
   * @return 
   *   the row for this cell
   */
  public int getRow()
  {
    return position.row();
  }
  
  /**
   * Sets the column for this cell's position.
   * @param col 
   *   the new column
   */
  public void setCol(int col)
  {
    position.setCol(col);
  }
  
  /**
   * Sets the row for this cell's position.
   * @param row 
   *   the new row
   */
  public void setRow(int row)
  {
    position.setRow(row);
  }
  
  /**
   * Returns the block associated with this Cell.
   * @return 
   *   the block associated with this Cell
   */
  public Block getBlock()
  {
    return block;
  }
  
  /**
   * Sets the block associated with this Cell.
   * @param b 
   *   the new block
   */
  public void setBlock(Block b)
  {
    block = b;
  }
  
  @Override
  public String toString()
  {
    String b = "";
    if (block == null)
    {
      b = "null block";
    }
    else if (block.isMagic())
    {
      b = "*";
    }
    String p = position == null ? "null" : position.toString();
    return p + b;
  }
  
  @Override
  public boolean equals(Object obj)
  {
    if (obj == null || obj.getClass() != this.getClass())
    {
      return false;
    }
    Cell other = (Cell) obj;
    return position.equals(other.position) && block.equals(other.block);
  }
}
