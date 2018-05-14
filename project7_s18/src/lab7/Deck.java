package lab7;

import java.util.Random;

import lab7.Card.Suit;

/**
 * Class representing a standard 52-card deck of playing cards from which cards
 * can be selected at random.
 */
public class Deck
{
	/**
	 * The cards comprising this deck.
	 */
	private Card[]	cards;

	/**
	 * The random number generator to use for selecting cards.
	 */
	private Random	r;

	/**
	 * Constructs a new deck with a default random number generator.
	 */
	public Deck()
	{
		r = new Random();
		init();
	}

	/**
	 * Constructs a new deck with the given random number generator.
	 */
	public Deck(Random givenGenerator)
	{
		r = givenGenerator;
		init();
	}

	/**
	 * Returns a new array containing k elements selected at random from this
	 * deck.
	 */
	public Card[] select(int k)
	{
		Card[] hand = new Card[k];

		for(int i = 0, j = cards.length; i < k && j > 0; i++)
		{
			int select = r.nextInt(j);
			hand[i] = cards[select];
			swap(select, j - 1);
			j--;
		}

		return hand;
	}

	/**
	 * Handy little swap method
	 * 
	 * @param hand
	 *            current hand
	 * @param select
	 *            first card to swap
	 * @param end
	 *            second card to swap
	 */
	private void swap(int select, int end)
	{
		Card temp     = cards[select];
		cards[select] = cards[end];
		cards[end]    = temp;
	}

	/**
	 * Initializes a new deck of 52 cards.
	 */
	private void init()
	{
		cards = new Card[52];
		int index = 0;
		for(int rank = 1; rank <= 13; ++rank)
		{
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