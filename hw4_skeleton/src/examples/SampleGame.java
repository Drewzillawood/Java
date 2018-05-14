package examples;
import java.util.ArrayList;
import java.util.List;

import api.AbstractGame;
import api.Position;

/**
 * Brain-dead subclass of AbstractGame for experimentation...
 */
public class SampleGame extends AbstractGame
{
  public SampleGame()
  {
    super(6, 8, new SampleGenerator());
  }

  @Override
  protected List<Position> determinePositionsToCollapse()
  {
    // empty list
    List<Position> positions = new ArrayList<>();
    return positions;
  }

  @Override
  protected int determineScore()
  {
    return 0;
  }
}
