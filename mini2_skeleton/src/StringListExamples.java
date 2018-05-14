import java.io.FileNotFoundException;

import api.Combiner;
import api.Converter;
import api.Selector;
import api.Transformation;
import mini2.FirstLetterCombiner;
import mini2.LengthCombiner;
import mini2.LineNumberer;
import mini2.StringList;

// examples of map, filter, reduce
public class StringListExamples
{
  public static void main(String[] args) throws FileNotFoundException
  {    
    // Note we can try out classes like Reverser all by themselves without
    // creating a StringList
    // (see bottom of file for Reverser and other implementations)
    Transformation t = new Reverser();
    String reversed = t.apply("banana");
    System.out.println(reversed);

    Selector f = new StartingLetterSelector("b");
    System.out.println(f.select("banana")); // true
    System.out.println(f.select("apple"));  // false
    
    Combiner<Integer> c = new MaxLengthFinder();
    System.out.println(c.combine(5, "banana")); // should be 6
    System.out.println(c.combine(5, "pear"));   // should be 5
    
    // Next, try out a StringList
    String[] arr = {"So", "long", "and", "thanks", "for", "all", "the", "fish"};
    StringList sl = new StringList(arr);   
    
    // reverse all strings in the string list
    // - prints [os, gnol, dna, sknaht, rof, lla, eht, hsif]
    StringList result = sl.map(new Reverser());
    System.out.println(result); 
    
    // length of longest string
    // - prints 6
    System.out.println(sl.reduce(new MaxLengthFinder(), 0));  
    
    // longest string
    // - prints "thanks"
    System.out.println(sl.reduce(new LongestStringFinder(), ""));
    
    // strings that start with the letter f
    // - prints [for, fish]
    result = sl.filter(new StartingLetterSelector("f"));
    System.out.println(result); 
    
    // since map() and filter() return another StringList, we can 
    // "chain" operations together.
    // - here we find the reverse of the longest word starting with 'f', prints "hsif"
    System.out.println(sl
        .filter(new StartingLetterSelector("f"))
        .map(new Reverser())
        .reduce(new LongestStringFinder(), ""));
    
    System.out.println();
    
    // try out some of the examples in mini2 package, 
    // see PolylineExample.java and SlocCounterExample.java for more
    
    // FirstLetterCombiner
    Combiner<String> flc = new FirstLetterCombiner();
    String r = flc.combine("foo", "bar");
    System.out.println(r);
    
    String initials = new StringList("Edna von Humboldt van der Schooch".split(" "))
        .reduce(new FirstLetterCombiner(), "");
    System.out.println(initials);  // EvHvdS
    System.out.println();
    
    // LengthCombiner
    int totalChars = new StringList("story.txt").reduce(new LengthCombiner(), 0);
    System.out.println("Total characters in story, excluding newlines: " + totalChars);
    System.out.println();
    
    // LineNumberer
    // displays:
//     1    Flowers for Pachyderm, by Mark Strand
//     2    
//     3    As Franz Kafka awoke one morning from uneasy dreams, he found himself
//     4    transformed into a raging bull elephant. He charged around his room with
//     etc.    
    new StringList("story.txt").map(new LineNumberer()).toOutputStream(System.out);

    
  }
}


// Some examples. Note that these have "package-private" scope
// just to keep the example code compact, since this way we don't
// have to put each one in its own file.  The required
// classes for miniassignment 3 need to be declared public and 
// each needs to be in its own file.

class Reverser implements Transformation
{
  @Override
  public String apply(String s)
  {
    String result = "";
    for (int i = 0; i < s.length(); i += 1)
    {
      result = s.charAt(i) + result;
    }
    return result;
  } 
}

class StartingLetterSelector implements Selector
{
  private String start;
  public StartingLetterSelector(String givenStart)
  {
    start = givenStart.toLowerCase();
  }
  
  public boolean select(String s)
  {
    return s.toLowerCase().startsWith(start);
  }
}

class ContactConverter implements Converter<Contact>
{
  public Contact convert(String s)
  {
    // assume each string is of the form "name, phoneNumber"
    int i = s.indexOf(",");
    String name = s.substring(0, i);
    String phone = s.substring(i + 1).trim();
    return new Contact(name, phone);
  }
}

class LongestStringFinder implements Combiner<String>
{
  @Override
  public String combine(String s, String t)
  {
    if (t.length() > s.length())
    {
      return t;
    }
    else
    {
      return s;
    }
  } 
}

class MaxLengthFinder implements Combiner<Integer>
{
  public Integer combine(Integer i, String s)
  {
    return Math.max(i, s.length());
  }
}

// from exam 2 review sheet, problem 6
class Contact
{
  private String name;
  private String phoneNumber;
  public Contact(String givenName, String givenPhoneNumber)
  {
    name = givenName;
    phoneNumber = givenPhoneNumber;
  }
  // remaining details not shown
}
