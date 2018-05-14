import java.io.FileNotFoundException;

import mini2.NonLineCommentSelector;
import mini2.NonJavadocSelector;
import mini2.SlocCounter;
import mini2.StringList;

/**
 * Counts lines of code in a .java file, not including comments
 * and not including lines consisting of a single opening or closing brace.
 */
public class SlocCounterExample
{

  public static void main(String[] args) throws FileNotFoundException
  {
    // remove line comments, then remove all the javadoc
    StringList list = new StringList("src/mini2/StringList.java")
        .filter(new NonLineCommentSelector())
        .filter(new NonJavadocSelector());   

    // have a look at it
    list.toOutputStream(System.out);
    
    // count source lines of code
    int count = 
        list.reduce(new SlocCounter(), 0);
    
    System.out.println("SLOC count: " + count);
  }

}
