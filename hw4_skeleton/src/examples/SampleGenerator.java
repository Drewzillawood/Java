package examples;
import api.Generator;
import api.Position;
import api.Shape;

public class SampleGenerator implements Generator
{
  @Override
  public Shape getNext(int width)
  {
    return new SampleShape(new Position(-1, width / 2), false);
  }
}
