import java.util.LinkedList;
import java.util.Random;

public class Deck
{
	// Fields
	private LinkedList<Card> cards;

	// Constructor
	public Deck()
	{
		this.cards = new LinkedList<Card>();

		// Generate cards
		for (int i = 2; i <= 14; i++)
		{
			String suit = "hearts";
			this.cards.add(new Card(i, suit));
		}

		for (int i = 2; i <= 14; i++)
		{
			String suit = "spades";
			this.cards.add(new Card(i, suit));
		}

		for (int i = 2; i <= 14; i++)
		{
			String suit = "clubs";
			this.cards.add(new Card(i, suit));
		}

		for (int i = 2; i <= 14; i++)
		{
			String suit = "diamonds";
			this.cards.add(new Card(i, suit));
		}
	}

	// Methods
	public Card deal()
	{
		// Choose random cards from LinkedList 
		Random random = new Random();
		int index = random.nextInt(this.cards.size());
		return this.cards.remove(index); // Retrieve and remove
	}
}