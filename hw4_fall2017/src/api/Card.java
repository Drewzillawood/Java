package api;

/**
 * Class representing a playing card.  Each card has a 
 * positive integer rank and a suit defined by the enumeration
 * type <code>Suit</code>.
 */
public class Card implements Comparable<Card>
{
 
  /**
   * Suit for this card.
   */
  private final Suit suit;
 
  /**
   * Rank for this card.
   */
  private final int rank;

  
 /**
  * Constructs a card with the given rank and suit. Ranks are 
  * integers greater than zero.  A rank of 1 is special and
  * is considered higher than any other rank.
  * @param givenRank
  *   rank for this card
  * @param givenSuit
  *   suit for this card
  */
  public Card(int givenRank, Suit givenSuit)
  {
    rank = givenRank;
    suit = givenSuit;
  }

  /**
   * Returns the rank for this card.
   * @return
   *   rank for this card
   */
  public int getRank()
  {
    return rank;
  }
  
  /**
   * Returns the suit for this card.
   * @return
   *   suit for this card
   */
  public Suit getSuit()
  {
    return suit;
  }
  
  /**
   * Returns a compact String representation of this card.
   * Ranks of 1, 11, 12, and 13 are represented using the
   * characters A, J, Q, and K respectively, and suits
   * are represent as characters 'c', 'd', 'h', 's'.
   * For example the ten of diamonds is "10d" and the 
   * queen of spades is "Qs". Ranks higher than 13 are 
   * represented numerically, e.g., "42h".
   */
  @Override
  public String toString()
  {
    String rankString;
    switch (rank)
    {
      case 1: rankString = "A"; break;
      case 11: rankString = "J"; break;
      case 12: rankString = "Q"; break;
      case 13: rankString = "K"; break;
      default: rankString = "" + rank;
    }
    switch (suit)
    {
      case CLUBS: return rankString + "c";
      case DIAMONDS: return rankString + "d";
      case HEARTS: return rankString + "h";
      case SPADES: return rankString + "s";
      default:
        // can't happen
        return null;
    }
  }


  /**
   * Orders cards by rank from highest to lowest, and within
   * rank, by suit from highest (SPADES) to lowest (CLUBS).
   */
  @Override
  public int compareTo(Card rhs)
  {
    int comp = compareToIgnoreSuit(rhs);
    if (comp == 0)
    {
      // same rank, compare suits, reversed so "high" suit comes first
      return -this.suit.compareTo(rhs.suit);
    }
    else
    {
      return comp;
    }
  }
  
  /**
   * Orders cards by rank from highest to lowest, ignoring suit.
   * @param rhs
   * @return
   */
  public int compareToIgnoreSuit(Card rhs)
  {
    if (this.rank == rhs.rank)
    {
      return 0;
    }
    else
    {
      // ranks are unequal, check for aces
      if (this.rank == 1)
      {
        // higher rank comes first, return a negative value
        return -1;
      }
      else if (rhs.rank == 1) 
      {
        return 1;
      }
      else
      {
        // reverse numerical ordering to get A, K, Q, J, 10, 9, ...
        return -(this.rank - rhs.rank);
      }
    }     
  }
  
  @Override
  public boolean equals(Object obj)
  {
    if (obj == null || obj.getClass() != this.getClass())
    {
      return false;
    }
    Card other = (Card) obj;
    return this.rank == other.rank && this.suit == other.suit;
  }
  
  /**
   * Creates an array of cards from a variable-length list of integers
   * representing their ranks.  All cards will have suit CLUBS.
   * @param cards
   *   variable-length list of integer values
   * @return
   *   array of cards with the given ranks and suit CLUBS
   */
  public static Card[] createArray(Integer...cards)
  {
    Suit suit = Suit.CLUBS; // default
    int size = cards.length;
    Card[] result = new Card[size];
    for (int i = 0; i < cards.length; ++i)
    {
      result[i] = new Card(cards[i], suit);
    }
    return result;
  }
  
  /**
   * Creates an array of cards from a string.  The 
   * string is a comma-separated list of rank and 
   * suit representations as described in <code>toString<code>.
   * If no suit is given, the corresponding card will be
   * clubs. If the string is not of the correct form,
   * the method returns null.
   * @param cardString
   *   comma-separated string of card representation
   * @return
   *   array of cards, or null if the string cannot be parsed
   */
  public static Card[] createArray(String cardString)
  {
    Suit suit = Suit.CLUBS; // default
    String[] cards = cardString.split(",");
    Card[] result = new Card[cards.length];
    for (int i = 0; i < cards.length; ++i)
    {
      String rankString = cards[i].trim().toLowerCase();
      char last = rankString.charAt(rankString.length() - 1);
      if (!Character.isDigit(last))
      {
        // strip off last character and attempt to interpret as a suit
        rankString = rankString.substring(0, rankString.length() - 1);
        switch (last)
        {
          case 'c':
            suit = Suit.CLUBS;
            break;
          case 'd': 
            suit = Suit.DIAMONDS;
            break;
          case 'h':
            suit = Suit.HEARTS;
            break;
          case 's':
            suit = Suit.SPADES;
            break;
          default:
            return null;
        }
      }
      int rank = -1;
      if (rankString.equals("a"))
      {
        rank = 1;
      }
      else if (rankString.equals("k"))
      {
        rank = 13;
      }
      else if (rankString.equals("q"))
      {
        rank = 12;
      }
      else if (rankString.equals("j"))
      {
        rank = 11;
      }
      else
      {
        try
        {
          rank = Integer.parseInt(rankString);
        }
        catch (NumberFormatException e)
        {
          return null;
        }
      }
      if (rank > 0)
      {
        result[i] = new Card(rank, suit);
      }
      else
      {
        return null;
      }
    }
    return result;
  }
}