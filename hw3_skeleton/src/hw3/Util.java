package hw3;

import java.util.ArrayList;
import java.util.Scanner;

import api.Jewel;

/**
 * Utility class that isolates the fundamental array algorithms needed
 * for implementation of the Bejeweled game.  Also includes some useful
 * methods for converting strings into arrays of Jewel objects.
 */
public class Util
{
  /**
   * Private constructor disables instantiation.
   */
  private Util()
  {    
  }
  
  /**
   * Finds all runs of three or more Jewels of matching type in the 
   * given array. The return value is a list of the indices of all
   * elements that are part of some run.  The given array is not modified.
   * Caller should ensure that there are no null elements in the given
   * array.
   * @param arr
   *   array of Jewels
   * @return
   *   indices of all Jewels that are part of a run
   */
  public static ArrayList<Integer> findRuns(Jewel[] arr)
  {
    ArrayList<Integer> runs = new ArrayList<Integer>();
    for(int i = 0; i < arr.length; i++)
    {
    	int count = 0;
    	for(int j = (i - 2) > -1 ? i - 2 : 0; j < ((i + 3 > arr.length) ? arr.length : i + 3); j++)
    	{
    		if(arr[i] == null || arr[j] == null)
    		{
    			continue;
    		}
    		if(arr[i].getType() == arr[j].getType())
    		{
    			count++;
    			if(count > 2)
    	    	{
    	    	    runs.add(i);
    	    	    break;
    	    	}
    		}
    		else
    		{
    			count = 0;
    		}
    	}
    }
    return runs;
  }
  
  /**
   * Shifts all non-null elements in the array to the right without
   * changing the order.  This operation modifies the given array.
   * The return value is a list of the indices of elements that 
   * were actually shifted by this operation, ordered left to right
   * (this means the index of the element in the modified array, 
   * not the original). 
   * @param arr
   *   array of Jewel objects, possibly containing null cells
   * @return
   *   list of the new indices of moved elements
   */
  public static ArrayList<Integer> shiftNonNullElements(Jewel[] arr)
  {
	ArrayList<Integer> shifted = new ArrayList<Integer>();
    for(int i = arr.length - 1, j = arr.length - 1; i >= 0 && j >= 0; i--)
    {
    	if(arr[i] == null)
    	{
    		j = i;
    		while(arr[j] == null && j > 0)
    		{
    			j--;
    		}
    		shifted.add(i - j);
    		swap(i, j, arr);
    	}
    }
    return shifted;
  }
  
  /**
   * Swap helper method
   * @param a
   * 	first element index
   * @param b
   * 	second element index
   * @param arr
   * 	provided array
   */
  private static void swap(int a, int b, Jewel[] arr)
  {
	  Jewel temp = arr[a];
	  arr[a] = arr[b];
	  arr[b] = temp;
  }
  
  /**
   * Creates an array of Jewel objects from a string of 
   * numbers separated by whitespace.
   * @param values
   *   string containing values for the Jewel types
   * @return
   *   array of Jewel objects with types determined by the given string
   */
  public static Jewel[] createFromString(String values)
  {
    ArrayList<Jewel> temp = new ArrayList<Jewel>();
    Scanner scanner = new Scanner(values);
    while (scanner.hasNextInt())
    {
      int value = scanner.nextInt();
      temp.add(new Jewel(value));
    }
    Jewel[] ret = temp.toArray(new Jewel[0]);
    scanner.close();
    return ret;
  }
  
  /**
   * Returns a grid initialized from an array of strings.  Each string
   * consists of numbers, separated by whitespace, for the Jewel types for 
   * the corresponding row of the grid.  Throws IllegalArgumentException
   * if the strings in the array do not all contain the same number of
   * values.
   * @param descriptor
   *   array of strings containing numbers
   * @return
   *   a 2D array of Jewel objects whose types are determined by the
   *   given strings
   */
  public static Jewel[][] createFromStringArray(String[] descriptor)
  {
    int height = descriptor.length;
 
    // creates an uninitialized array of Jewel[]
    Jewel[][] grid = new Jewel[height][];
    
    // make rows from the strings of the given array
    for (int row = 0; row < height; row += 1)
    {
      grid[row] = createFromString(descriptor[row]);
    }
    
    // sanity check that all rows are the same length
    int width = grid[0].length;
    for (int row = 1; row < height; row += 1)
    {
      if (grid[row].length != width)
      {
        throw new IllegalArgumentException("Rows of descriptor are not the same length. ");
      }
    }

    return grid;
  }
  
  /**
   * Returns a String representation of the given 2D array, with rows
   * delimited by newlines.
   */
  public static String convertToString(Jewel[][] grid)
  {
    StringBuilder sb = new StringBuilder();
    for (int row = 0; row < grid.length; ++row)
    {
      for (int col = 0; col < grid[0].length; ++col)
      {
        Jewel jewel = grid[row][col];
        String s = String.format("%2s",
            (jewel == null ? "*" : "" + jewel.getType()));
        sb.append(s);
      }
      sb.append("\n");
    }
    return sb.toString();
  }
}
