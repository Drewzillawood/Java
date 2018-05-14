package lab6;

public class StringUtil
{
  /**
   * Returns a string similar to the given string, but with 
   * pairs of vowels reversed.  For example, given "their", the
   * method returns "thier".  For more than two vowels in a row,
   * they are considered in pairs only; for example, given 
   * "aeio" the method should reverse "ea" and reverse "io", 
   * returning "eaoi".  For a word such as "beautiful", the 
   * method should return "baeutiful". Here is the pseudocode
   * for the algorithm:
   * 
   * <pre>
   * initialize result to an empty string
   * while there are more characters
   *   if current character is a vowel
   *      if next one is also a vowel
   *         append the two characters to result in reverse order
   *      else 
   *         append the character to result
   *   else
   *      append the character to result
   * return result
   * </pre>
   * 
   * @param s
   *   the string whose vowels are to be reversed
   * @return
   *   string based on given string with pairs of vowels reversed
   */
  public static String fixSpelling(String s)
  {
    int i = 0;
    String result = "";
    while (i < s.length())
    {
      char c1 = s.charAt(i);
      if (isVowel(c1))
      {
        char c2 = s.charAt(i + 1);
        if (isVowel(c2))
        {
          // c1 and c2 are both vowels, append in reverse order
          result += c2;
          result += c1;
          i++;
        }
        else
        {
          result += c1;
        }
      }
      else
      {
        result += c1;
      }
      
      i = i + 1;
    }
    
    return result;
  }
  

  /**
   * Determines whether the given character is a vowel.
   * @param ch
   *   the character to check for vowel-ness
   * @return
   *   true if the given character is a vowel, false otherwise
   */
  private static boolean isVowel(char ch)
  {
    return "aeiouAEIOU".indexOf(ch) >= 0;
  }
  
}