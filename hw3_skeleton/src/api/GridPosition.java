package api;

/**
 * Class that represents a grid position with a Jewel.
 */
public class GridPosition
{
  /**
   * Row for this position.
   */
  private final int row;
  
  /**
   * Column for this position.
   */
  private final int col;
  
  /**
   * Jewel at this position.
   */
  private final Jewel jewel;
  
  /**
   * Constructs a GridPosition to represent the given row, column, and jewel.
   * 
   * @param row
   *   row for this cell
   * @param col
   *   column for this cell
   * @param jewel
   *   the jewel in this cell
   */
  public GridPosition(int row, int col, Jewel jewel)
  {
    this.row = row;
    this.col = col;
    this.jewel = jewel;
  }
  
  /**
   * Returns the Jewel in this cell.
   * @return
   *   the jewel in this cell
   */
  public Jewel getJewel()
  {
    return jewel;
  }  

  /**
   * Returns the row of this cell
   * @return
   *   row of this cell
   */
  public int row()
  {
    return row;
  }
  
  /**
   * Returns the column of this cell
   * @return
   *   column of this cell
   */
  public int col()
  {
    return col;
  }
  
  /**
   * Determines whether this position has the same row and column
   * as the given position.
   * @param other
   *   the position to compare with this one
   * @return
   *   true if the given position has the same row and column
   *   as this one
   */
  public boolean samePosition(GridPosition other)
  {
    return row == other.row && col == other.col;
  }

  /**
   * Determines whether this position is equal to the given object.
   * @return
   *   true if the given object is a GridPosition with the same
   *   row, column, and Jewel type as this one
   */
  public boolean equals(Object obj)
  {
    if (obj == null || obj.getClass() != this.getClass())
    {
      return false;
    }
    GridPosition other = (GridPosition) obj;
    return row == other.row && 
           col == other.col &&
           jewel.equals(other.jewel);
  }
  
  /**
   * Returns a String representation of this position in the form:
   * <pre>
   * [(row, column) jewel]
   * </pre>
   */
  @Override
  public String toString()
  {
    return String.format("[(%d,%d) %d]", row, col, jewel.getType());
  }
}
