import java.util.LinkedList;

public class Game
{
	// Fields
	private Deck deck;
	private Player player1;
	private Player player2;
	private LinkedList<Card> cardsDown;
	private int winner;

	private int battles;
	private int wars;
	private int doubleWars;

	// Constructor
	public Game()
	{
		// Grab a deck
		this.deck = new Deck();

		// Setup players
		this.player1 = new Player(1, this.deck);
		this.player2 = new Player(2, this.deck);

		// Setup a "collection" pile
		this.cardsDown = new LinkedList<Card>();

		this.battles = 0;
		this.wars = 0;
		this.doubleWars = 0;
	}

	// Methods
	public void play()
	{
		// [DEBUG] System.out.println("PLAYING AGAIN");

		// Battle
		while(this.player1.hasWon() == false && this.player2.hasWon() == false)
		{
			// [DEBUG] System.out.println("Still playing...");

			// If gone through entire deck reshuffle
			if (this.battles % 52 == 0 && this.battles > 0 && this.battles > 500)
			{
				// [DEBUG] System.out.println("Calling shuffle!");
				this.player1.shuffle();
				this.player2.shuffle();
			}

			Card player1Card = player1.flip();
			Card player2Card = player2.flip();
			cardsDown.add(player1Card);
			cardsDown.add(player2Card);

			if (player1Card.getRank() > player2Card.getRank())
			{
				// collect() also checks for win
				if(this.player1.collect(cardsDown))
				{
					this.winner = 1; 
					// [DEBUG] System.out.println("Winner - break");
					break;
				}

				this.battles++;
			}
			else if (player2Card.getRank() > player1Card.getRank())
			{
				if(this.player2.collect(cardsDown))
				{
					this.winner = 2;
					// [DEBUG] System.out.println("Winner - break");
					break;
				}

				this.battles++;
			}	
			// War
			else if (player1Card.getRank() == player2Card.getRank())
			{
				// If player's last card
				// [DEBUG] System.out.println("Last card before war");
				if (this.player1.countCardsLeft() == 0)
				{
					this.player1.collect(player1Card);
					// Remove that card from collection pile (second to last)
					cardsDown.remove((cardsDown.size() - 1) - 1);
				}
					
				else if (this.player2.countCardsLeft() == 0)
				{
					this.player2.collect(player2Card);
					// Remove that card from collection pile (last)
					cardsDown.remove((cardsDown.size() - 1));
				}

				this.wars++;
				this.war(); // Recursive

				if(this.gameOver())
					break;
			}

			// Clear cardsDown
			this.cardsDown.clear();
		}

	}

	public void war()
	{	
		// [DEBUG] System.out.println("In war() recursion...");

		if(this.gameOver())
			return;
		// Each players 3 cards
		LinkedList<Card> player1WarCards = this.player1.war();
		LinkedList<Card> player2WarCards = this.player2.war();

		// Add each players 3 cards to collection stack
		cardsDown.addAll(player1WarCards);
		cardsDown.addAll(player2WarCards);

		// Flip another card and compare
		Card player1WarCard = player1.flip();
		Card player2WarCard = player2.flip();
		// Add to collection pile
		cardsDown.add(player1WarCard);
		cardsDown.add(player2WarCard);
				
		if (player1WarCard.getRank() > player2WarCard.getRank())
		{
			// collect() also checks for win
			if(this.player1.collect(cardsDown) == true)
				this.winner = 1;
			return;
		}
		else if (player2WarCard.getRank() > player1WarCard.getRank())
		{
			if(this.player2.collect(cardsDown) == true)
				this.winner = 2;
			return;
		}	
		else if(player1WarCard.getRank() == player2WarCard.getRank())
		{	
			// [DEBUG] System.out.println("Last card during war");
			// If player has no cards left and still a tie - give player that card back and play again
			if (this.player1.countCardsLeft() == 0)
			{
				this.player1.collect(player1WarCard);
				// Remove that card from collection pile (second to last)
				cardsDown.remove((cardsDown.size() - 1) - 1);
			}
				
			else if (this.player2.countCardsLeft() == 0)
			{
				this.player2.collect(player2WarCard);
				// Remove that card from collection pile (last)
				cardsDown.remove((cardsDown.size() - 1));
			}

			if(this.gameOver())
				return;	
			this.doubleWars++;

			// Recursive
			this.war();
		}

	}

	public boolean gameOver()
	{
		if(this.player1.countCardsLeft() == 52 || this.player2.countCardsLeft() == 52)
			return true;
		else
			return false;
	}

	public Integer getBattles()
	{
		return new Integer(this.battles);
	}

	public Integer getWars()
	{
		return new Integer(this.wars);
	}

	public Integer getDoubleWars()
	{
		return new Integer(this.doubleWars);
	}
}