import java.io.FileNotFoundException;
import java.util.ArrayList;

import mini2.PolylineConverter;
import mini2.StringList;
import mini2.ValidLineSelector;
import plotter.Plotter;
import plotter.Polyline;

/**
 * This is a re-do of lab 8 checkpoint 2 using the StringList 
 * operations.
 */
public class PolylineExample
{
  public static void main(String[] args) throws FileNotFoundException
  {
    // filter out the blank lines and comments, and then
    // convert each line to a Polyline object
    ArrayList<Polyline> arr = new StringList("test.txt")
        .filter(new ValidLineSelector())
        .mapToList(new PolylineConverter());
    
    // plot all the polylines
    Plotter plotter = new Plotter();
    for (Polyline p : arr)
    {
      plotter.plot(p);
    }
  }

}
