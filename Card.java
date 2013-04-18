public class Card 
{
	// Fields
	private int rank; // [2-10] ranks [11-14]
	private String suit; // [hearts, spades, clubs, diamonds] // Change to ENUM
	private boolean isFaceCard;

	// Custom constructors
	public Card(int _rank, String _suit) 
	{
		this.rank = _rank;
		this.suit = _suit;

		if (_rank > 10)
			this.isFaceCard = true;
		else
			this.isFaceCard = false;
	}

	// Accessors
	// Can we return these Wrapper Classes saftely (i.e information hiding)?
	public Integer getRank() 
	{
		return this.rank;
	}

	public boolean getIsFaceCard() 
	{
		return this.isFaceCard;
	}

	public String getSuit() 
	{
		return this.suit;
	}

}