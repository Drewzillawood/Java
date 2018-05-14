package lab7;

import java.util.Random;

import lab7.Card.Suit;

/**
 * Class representing a standard 52-card deck of playing cards from which cards
 * can be selected at random.
 */
public class Deck {
	/**
	 * The cards comprising this deck.
	 */
	private Card[] cards;

	/**
	 * The random number generator to use for selecting cards.
	 */
	private Random rand;

	/**
	 * Constructs a new deck with a default random number generator.
	 */
	public Deck() {
		rand = new Random();
		init();
	}

	/**
	 * Constructs a new deck with the given random number generator.
	 */
	public Deck(Random givenGenerator) {
		rand = givenGenerator;
		init();
	}

	/**
	 * Returns a new array containing k elements selected at random from this
	 * deck.
	 */
	public Card[] select(int k) {
		
		Card hand[] = new Card[k];
		Card holder;
		int index = 52;
		int tempIndex = 0;
		
			for(int i = 0; i < k; i ++){
				
				holder = cards[rand.nextInt(index)];
				
				for(int j = 0; j < index; j++){
					
					if(holder.equals(cards[j]) && holder.getSuit().equals(cards[j].getSuit())){
						
						tempIndex = j;
						 
					}
					
				}
				
				cards[tempIndex] = cards[index-1];
				cards[index-1] = holder;
				index--;
				
			}
		
		for(int i = index, j = 0; i < 52; i++, j++){
			
			hand[j] = cards[i];
			
		}

		return hand;
		
	}

	/**
	 * Initializes a new deck of 52 cards.
	 */
	private void init() {
		cards = new Card[52];
		int index = 0;
		for (int rank = 1; rank <= 13; ++rank) {
			cards[index] = new Card(rank, Suit.CLUBS);
			index += 1;
			cards[index] = new Card(rank, Suit.DIAMONDS);
			index += 1;
			cards[index] = new Card(rank, Suit.HEARTS);
			index += 1;
			cards[index] = new Card(rank, Suit.SPADES);
			index += 1;
		}

	}
}