package lab7;

public class DeckTest2
{

  public static void main(String[] args)
  {
    // try a straight
    Card[] cards = new Card[5];
    cards[0] = new Card(2, Card.Suit.SPADES);
    cards[1] = new Card(3, Card.Suit.HEARTS);
    cards[2] = new Card(5, Card.Suit.CLUBS);
    cards[3] = new Card(4, Card.Suit.DIAMONDS);
    cards[4] = new Card(1, Card.Suit.SPADES);
    
    // print out the hand
    System.out.println(Card.toString(cards));
    PokerHand hand = new PokerHand(cards);

    System.out.println(hand.isStraight());  // expected true
    System.out.println(hand.isFullHouse()); // expected false
    
    // method call above should sort by rank (but not by suit) 
    System.out.println(Card.toString(cards));
    System.out.println();
    
    // try a full house
    cards = new Card[5];
    cards[0] = new Card(2, Card.Suit.SPADES);
    cards[1] = new Card(7, Card.Suit.HEARTS);
    cards[2] = new Card(7, Card.Suit.CLUBS);
    cards[3] = new Card(2, Card.Suit.SPADES);
    cards[4] = new Card(7, Card.Suit.DIAMONDS);
    hand = new PokerHand(cards);
    System.out.println(Card.toString(cards));
    
    System.out.println(hand.isStraight());  // expected false
    System.out.println(hand.isFullHouse()); // expected true

    // should now be sorted by rank
    System.out.println(Card.toString(cards));

  }

}