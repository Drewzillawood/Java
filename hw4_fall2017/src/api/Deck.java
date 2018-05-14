package api;

import java.util.Random;


/**
 * Class representing a deck of playing
 * cards from which cards can be selected at random.
 */
public class Deck
{
  /**
   * The cards comprising this deck.
   */
  private Card[] cards;
  
  /**
   * Number of cards currently in this deck;
   */
  private int size;
  
  /**
   * The random number generator to use for selecting cards.
   */
  private Random rand;
  
  /**
   * The maximum rank for cards in this deck (defaults to 13).
   */
  private int maxRank;
  
  /**
   * Constructs a new deck with a default random number generator
   * and a maximum card rank of 13.
   */
  public Deck()
  {
    rand = new Random();
    init();
  }

  /**
   * Constructs a new deck with the given random number generator
   * and a maximum card rank of 13.
   */
  public Deck(Random givenGenerator)
  {
    rand = givenGenerator;
    init();
  }
  
  /**
   * Constructs a new deck with the given random number generator.
   */
  public Deck(Random givenGenerator, int maxRank)
  {
    rand = givenGenerator;
    this.maxRank = maxRank;
    init();
  }
  
  

  /**
   * Removes and returns a card selected at random from the 
   * remaining cards in this deck.
   * @return
   *   one randomly selected card
   */
  public Card select()
  {
    return select(1)[0];
  }
  
  /**
   * Removes and returns k cards randomly selected from the remaining cards
   * in this deck.
   * @return 
   *    array of selected cards
   */
  public Card[] select(int k)
  {
    if (k > size)
    {
      String msg = "Deck contains only " + size + " cards, " + k + " requested."; 
      throw new IllegalStateException(msg);
    }

    Card[] result = new Card[k];
    int i = 0;
    int newSize = size - k;
    while (size > newSize)
    {
      int index = rand.nextInt(size);
      result[i++] = cards[index];
      cards[index] = cards[size - 1];
      size -= 1;
    }
    return result;
  }
  
  /**
   * Initializes a new deck.
   */
  private void init()
  {
    Suit[] suitValues = Suit.values();
    size = maxRank * suitValues.length;
    cards = new Card[size];

    int index = 0;
    for (int rank = 1; rank <= maxRank; ++rank)
    {
      for (Suit suit : suitValues)
      {
        cards[index] = new Card(rank, suit);
        index += 1;       
      }
    }
  }
}