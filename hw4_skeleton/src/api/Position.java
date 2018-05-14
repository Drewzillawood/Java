package api;

/**
 * Data container for a row and column.
 */
public class Position
{
  /**
   * The row.
   */
  private int row;
  
  /**
   * The column.
   */
  private int col;
  
  /**
   * Constructs a Position with the given row and column.
   * @param givenRow
   * @param givenCol
   */
  public Position(int givenRow, int givenCol)
  {
    row = givenRow;
    col = givenCol;
  }
  
  /**
   * Constructs a copy of the given Position.
   * @param existing
   *   a given Position object
   */
  public Position(Position existing)
  {
    row = existing.row;
    col = existing.col;
  }
  
  /**
   * Returns the row for this position.
   * @return
   *   row for this position
   */
  public int row()
  {
    return row;
  }
  
  /**
   * Returns the column for this position.
   * @return
   *   column
   *    for this position
   */
  public int col()
  {
    return col;
  }
  
  /**
   * Sets the row for this position.
   * @param newRow
   *   new row value
   */
  public void setRow(int newRow)
  {
    row = newRow;
  }
  
  /**
   * Sets the column for this position.
   * @param newCol
   *   new column value
   */
  public void setCol(int newCol)
  {
    col = newCol;
  }
  
  @Override
  public boolean equals(Object obj)
  {
    if (obj == null || !(obj instanceof api.Position))
    {
      return false;
    }
    
    Position other = (Position) obj;
    return row == other.row && col == other.col;
  }
  
  @Override
  public String toString()
  {
    return "(" + row + ", " + col + ")";
  }

  @Override
  public int hashCode()
  {
    int result = 31 + row;
    result = 31 * result + row;
    return result;
  }
}
