package study;

/**
 * Class representing a first and last name pair.  This class
 * implements the Comparable interface so that names are 
 * ordered by last name, and those with the same last name
 * are ordered by first name.
 */
public class FullName implements Comparable<FullName>
{
  private String first;
  private String last;
  
  public FullName(String givenFirst, String givenLast)
  {
    first = givenFirst;
    last = givenLast;
  }
  
  /**
   * Returns a string representation of this object. 
   */
  public String toString()
  {
    return first + " " + last;
  }

  /**
   * Returns the first name.
   */
  public String getFirst()
  {
    return first;
  }
  
  /**
   * Returns the last name.
   */
  public String getLast()
  {
    return last;
  }
  
//  public int compareTo(FullName other)
//  {
//    int lhs = first.length() + last.length();
//    int rhs = other.first.length() + other.last.length();
//    return lhs - rhs;
//  }
  
  /**
   * Compares this FullName to the given one.  This method is specified in the
   * Comparable interface.
   * 
   * @rhs 
   *   the FullName that this one is being compared to (the "right-hand-side"
   *   of the comparison)
   * @return
   *   a negative value if this FullName comes before rhs, a positive value if
   *   this one comes after rhs, and zero if they are the same
   */
  public int compareTo(FullName rhs)
  {
    if (!last.equals(rhs.last))
    {
      // last names are different, so order according to last name,
      // according to the regular the String class compareTo method
      return last.compareTo(rhs.last);      
    }
    else
    {
      // last names are the same, so order according to first name
      // (again just using the String class compareTo method)
      return first.compareTo(rhs.first);
    }
  }
}