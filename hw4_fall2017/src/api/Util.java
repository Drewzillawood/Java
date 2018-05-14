package api;

import java.util.Arrays;

/**
 * Utility class with methods for extracting subset from a 
 * Card array.
 */
public class Util
{
  /**
   * Returns an array consisting of the cards from the given Card array
   * at the given indices. The relative ordering of 
   * the cards is the same as in the given Card array.
   * @param allCards
   * @param subset
   * @return
   */
  public static Card[] getCardSubset(Card[] allCards, int[] subset)
  {
    // put indices in order
    subset = Arrays.copyOf(subset, subset.length);
    Arrays.sort(subset);

    Card[] result = new Card[subset.length];
    int index = 0;
    for (int i = 0; i < subset.length; ++i)
    {
      result[index] = allCards[subset[i]];
      index += 1;
    }
    return result;    
  }
  
  /**
   * Returns an array consisting of the cards from the given Card array
   * that are not at the given indices. The relative ordering of 
   * the cards is the same as in the given Card array.
   * @param allCards
   * @param subset
   * @return
   */
  public static Card[] getCardNonSubset(Card[] allCards, int[] subset)
  {
    Card[] result = new Card[allCards.length - subset.length];
    int resultIndex = 0;
    int subsetIndex = 0;
    
    // Note, we're counting on the fact that the ints in the subset
    // are in increasing order, so to check whether a given index i
    // is in the subset, we don't have to iterate over the whole
    // subset, just check the current subsetIndex.
    subset = Arrays.copyOf(subset, subset.length);
    Arrays.sort(subset);
    
    for (int i = 0; i < allCards.length; ++i)
    {
      if (subsetIndex < subset.length && i == subset[subsetIndex])
      {
        // i is in the subset, so skip that card
        subsetIndex += 1;
      }
      else
      {
        // i is not in the subset, so keep that card
        result[resultIndex] = allCards[i];
        resultIndex += 1;
      }
    }
    return result;
    
  }
}
