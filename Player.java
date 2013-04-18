import java.util.LinkedList;
import java.util.Random;

public class Player
{
	// Fields
	private int playerNumber;
	private LinkedList<Card> ownedCards;

	// Constructor
	public Player(int _playerNumber, Deck _cards)
	{
		this.ownedCards = new LinkedList<Card>();
		this.playerNumber = _playerNumber;
		// Deal out 26 cards to player
		for(int i = 0; i < 26; ++i)
		{
			this.ownedCards.add(_cards.deal()); 
		}
	}

	// Methods
	public int getPlayerNumber()
	{
		return this.playerNumber;
	}

	public Card flip()
	{
		// Remove card at front list
		if(this.ownedCards.size() == 0)
			System.out.println("This player is out of cards!");
		return this.ownedCards.removeFirst();
	}

	public void collect(Card _card)
	{
		// Add card to player's deck (this method should only get called when player is in war with no cards left)
		this.ownedCards.add(_card);
	}

	public boolean collect(LinkedList<Card> _cards)
	{
		// Add cards to player's deck
		this.ownedCards.addAll(_cards);

		if(this.hasWon() == true)
			return true;
		else
			return false;
	}

	
	public LinkedList<Card> war()
	{
		// Draw 3
		LinkedList<Card> cards = new LinkedList<Card>();
		for(int i = 0; i < 4; ++i)
		{
			// Make sure one card left over for flip
			if(this.ownedCards.size() < 2)
				break;
			cards.add(this.ownedCards.removeFirst());
		}
		
		return cards;
	}

	public int countCardsLeft()
	{
		return this.ownedCards.size();
	}

	public void shuffle()
	{
		// Shuffle player 1's cards
		Random random = new Random();
		LinkedList<Card> newDeck = new LinkedList<Card>();
		int origSize = this.ownedCards.size();
		for(int i = 0; i < origSize; i++)
		{
			// Remove random card from current deck
			Card card = this.ownedCards.remove(random.nextInt(this.ownedCards.size()));
			newDeck.add(card);
		}

		// Replace cards back into deck (new order)
		this.ownedCards.addAll(newDeck);
	}

	public boolean hasWon()
	{
		if(this.ownedCards.size() == 52)
			return true;
		else
			return false;
	}
}