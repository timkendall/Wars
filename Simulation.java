import java.util.LinkedList;

public class Simulation
{
	// Fields
	private LinkedList<Integer> battlesPerGame;
	private LinkedList<Integer> warsPerGame;
	private LinkedList<Integer> doubleWarsPerGame;

	// Constructor
	public Simulation()
	{
		this.battlesPerGame = new LinkedList<Integer>();
		this.warsPerGame = new LinkedList<Integer>();
		this.doubleWarsPerGame = new LinkedList<Integer>();
	}

	// Methods
	public void playGames(int _n)
	{
		int countGames = 0;
		while(countGames < _n)
		{
			// Setup new game;
			Game newGame = new Game();
			// Play game
			newGame.play();

			// Grab game stats
			this.battlesPerGame.add(newGame.getBattles());
			this.warsPerGame.add(newGame.getWars());
			this.doubleWarsPerGame.add(newGame.getDoubleWars());
			// Keep track of games played
			countGames++;
		}
	}

	public void stats()
	{
		// Compute Stats
		double totalBattles = 0.0; 
		int minBattles = 0;
		int maxBattles = 0;
		for(Integer x : this.battlesPerGame)
		{
			totalBattles = totalBattles + x;
			if (x > maxBattles)
			{
				maxBattles = x;
				minBattles = maxBattles; // iffy...
			}
			if (x < minBattles)
				minBattles = x;
		}

		double totalWars = 0.0; 
		int minWars = 0;
		int maxWars = 0;
		for(Integer x : this.warsPerGame)
		{
			totalWars = totalWars + x;
			if (x > maxWars)
			{
				maxWars = x;
				minWars = maxWars;
			}
			if (x < minWars)
				minWars = x;
		}

		double totalDoubleWars = 0.0; 
		for(Integer x : this.doubleWarsPerGame)
			totalDoubleWars = totalDoubleWars + x;

		double avgBattlesGame = totalBattles / this.battlesPerGame.size();
		double avgWarsGame = totalWars / this.warsPerGame.size();
		double avgDoubleWarsGame = totalDoubleWars / this.doubleWarsPerGame.size();

		// Print Stats
		System.out.println("Average battles per game: " + avgBattlesGame);
		System.out.println("Average wars per game: " + avgWarsGame);
		System.out.println("Average double wars per game: " + avgDoubleWarsGame);
		System.out.println("Min battles in a game: " + minBattles);
		System.out.println("Max battles in a game: "+ maxBattles);
		System.out.println("Min wars in a game: " + minWars);
		System.out.println("Max wars in a game: " + maxWars);
	}

	// Take number of games "n" as command line param
	public static void main(String[] args) 
	{
		Simulation sim = new Simulation();
		sim.playGames(Integer.parseInt(args[0])); // ex. java MyProgram 43
		sim.stats();
	}
}