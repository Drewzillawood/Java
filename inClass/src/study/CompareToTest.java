package study;
/**
 * Try out the String compareTo method
 */
public class CompareToTest
{

  /**
   * @param args
   */
  public static void main(String[] args)
  {   
    System.out.println("aardvark".compareTo("zebra"));
    System.out.println("zebra".compareTo("aardvark"));
    System.out.println("Zebra".compareTo("aardvark"));

    System.out.println("aardvark".compareTo("aardvark"));
    System.out.println("bear".compareTo("aardvark"));
    System.out.println("aardvark".compareTo("bear"));

  }

}