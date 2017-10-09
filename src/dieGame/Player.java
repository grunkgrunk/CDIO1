package dieGame;

public class Player {
	private int score = 0;
	private static int winPoints = 40;
	
	private Die die = new Die();
	
	// init these values out of range of die
	private int prevRoll1 = -1;
	private int prevRoll2 = -1;
	private int roll1 = -1;
	private int roll2 = -1;
	
	private String name;
	private boolean hasWon = false;
	
	public Player(String name) {
		this.name = name;
	}
	
	public void takeTurn() {
		boolean extraTurn = false;
		roll1 = die.roll();
		roll2 = die.roll();
		
		
		
		if (roll1 == roll2 && roll1 != 1) {
			
			// win conditions:
			if (score >= winPoints) {
				// the player wins
				hasWon = true;
				return;
			}
			
			if (prevRoll1 == 6 && prevRoll2 == 6 && roll1 == 6) {
				// wins
				hasWon = true;
				return;
			}
			// extra turn
			extraTurn = true;
		} else if (roll1 == 1 && roll2 == 1) {
			score = 0;
		}
		
		int sum = roll1 + roll2;
		score += sum;
		
		prevRoll1 = roll1;
		prevRoll2 = roll2;
		
		// recursion to take a turn again
		if (extraTurn) {
			takeTurn();
		}
	}
	
	public boolean getHasWon() {
		return hasWon;
	}
	
	// Get a string of the state of the player
	public String toString() {
		String out = String.format("%s rolls %d and %d. Score: %d", name, roll1, roll2, score);
		return out;
	}
	
	public String getName() {
		return name;
	}
	
	
}
