package examples;
import java.awt.Color;

import api.Block;
import api.Cell;
import api.Position;
import api.Shape;

/**
 * Partial implementation of the Shape interface for experimentation.
 */
public class SampleShape implements Shape
{
  private Position position;
  private Cell[] cells;
  
  public SampleShape(Position givenPosition, boolean magic)
  {
    position = givenPosition;
    cells = new Cell[2];
    cells[0] = new Cell(new Block(Color.RED, magic), givenPosition );
    
    // another cell just below the first one
    Position position1 = new Position(givenPosition.row() + 1, givenPosition.col());
    cells[1] = new Cell(new Block(Color.RED, false), position1);
  }
  
  @Override
  public Cell[] getCells()
  {
    Cell[] copy = new Cell[cells.length];
    copy[0] = new Cell(cells[0]);
    copy[1] = new Cell(cells[1]);
    return copy;
  }

  @Override
  public void shiftDown()
  {
      position.setRow(position.row() + 1);
      cells[0].setRow(cells[0].getRow() + 1);
      cells[1].setRow(cells[1].getRow() + 1);
  }

  @Override
  public void shiftLeft()
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void shiftRight()
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void transform()
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void cycle()
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public Shape clone()
  {
    try
    {
      // call the Object clone() method to create a shallow copy
      SampleShape s = (SampleShape) super.clone();

      // then make it into a deep copy
      s.position = new Position(position);
      s.cells = new Cell[2];
      s.cells[0] = new Cell(cells[0]);
      s.cells[1] = new Cell(cells[1]);
      return s;
    }
    catch (CloneNotSupportedException e)
    {
      // can't happen
      return null;
    }    
  }
}
