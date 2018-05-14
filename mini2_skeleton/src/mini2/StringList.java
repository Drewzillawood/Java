package mini2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import api.Combiner;
import api.Converter;
import api.Selector;
import api.Transformation;

/**
 * A StringList represents a specialized list of strings that supports 
 * a set of operations called <em>map</em>, <em>filter</em>, 
 * <em>reduce</em>, and <em>reduceToList</em>.  The argument to each 
 * of these methods is an interface type representing 
 * an operation to be performed on the list.
 */
public class StringList
{
  /**
   * The actual strings making up this StringList object.
   */
  private ArrayList<String> data;
  
  /**
   * Constructs an empty StringList.
   */
  public StringList()
  {
    data = new ArrayList<>();
  }
  
  /**
   * Constructs a StringList using the given array. 
   * @param strings
   *   an array of strings
   */
  public StringList(String[] strings)
  {
    data = new ArrayList<>();
    for (String d : strings)
    {
      data.add(d);
    }
  }
  
  /**
   * Constructs a StringList using the given list. 
   * @param strings
   *   an ArrayList of strings
   */
  public StringList(ArrayList<String> strings)
  {
    data = new ArrayList<>();
    for (String d : strings)
    {
      data.add(d);
    }
  }
  
  /**
   * Constructs a StringList from the given file, where each line
   * of the file becomes one item in the StringList.
   * @param filename
   * @throws FileNotFoundException
   */
  public StringList(String filename) throws FileNotFoundException
  {
    data = new ArrayList<>();
    Scanner in = new Scanner(new File(filename));
    while (in.hasNextLine())
    {
      data.add(in.nextLine());
    }
    in.close();
  }
  
  /**
   * Appends the given string to the end of this StringList.
   * @param s
   *   string to be appended
   */
  public void append(String s)
  {
    data.add(s);
  }
  
  /**
   * Returns the string at the given position in this StringList.
   * @param index
   *   position of string to return
   * @return
   *   string at given position
   */
  public String get(int index)
  {
    return data.get(index);
  }
  
  /**
   * Returns the number of elements in this StringList.
   * @return
   *   number of elements in this StringList
   */
  public int size()
  {
    return data.size();
  }
  
  /**
   * Returns a new StringList obtained by applying the given Transformation to 
   * every string in this StringList.  This StringList is not modified.
   * @param transform
   *   a Transformation object
   * @return
   *   a new StringList 
   */
  public StringList map(Transformation transform)
  {
    StringList result = new StringList();
    for (String d : data)
    {
      String newString = transform.apply(d);
      result.append(newString);
    }
    return result;
  }
  
  /**
   * Returns a new StringList consisting of just the strings that are 
   * chosen by the given Selector.
   * @param selector
   *   the Selector to use for selecting the strings in the result
   * @return
   *   a new StringList
   */
  public StringList filter(Selector selector)
  {
    // TODO
    return null;
  }
  
  /**
   * Combines all strings in this list into a single value of type T
   * using the given Combiner object and the given initial value.  If this 
   * StringList is empty, this method just returns the given initial value.
   * @param combiner
   *   the operation to be applied to successive strings
   * @param initialValue
   *   the initial value that is combined with the first element 
   * @return
   *   result
   */
  public <T> T reduce(Combiner<T> combiner, T initialValue)
  {
    T result = initialValue;
    for (String d : data)
    {
      result = combiner.combine(result, d);
    }
    return result;
  }
 
  /**
   * Returns a list of objects obtained by applying the given
   * conversion to each string in this StringList.
   * @param converter
   *   a Converter object for converting strings to type T
   */
  public <T> ArrayList<T> mapToList(Converter<T> converter)
  {
    // TODO
    return null;
  }
  
  /**
   * Returns a single string representing the contents of this StringList
   * based on the format of java.util.ArrayList.
   * @return
   *   string representation of this list
   */
  public String toString()
  {
    return data.toString();
  }

  /**
   * Writes the contents of this StringList to the given
   * output stream, one line per item.
   * @param out
   *   given output stream
   */
  public void toOutputStream(PrintStream out)
  {
    for (String s : data)
    {
      out.println(s);
    }
  }
}



