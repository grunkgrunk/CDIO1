package dieGame;

public class Player {
	private int score = 0;
	private static final int winPoints = 40;

	private Die die1;
	private Die die2;

	// init these values out of range of die
	private int prevRoll1 = -1;
	private int prevRoll2 = -1;
	
	private int roll1 = -1;
	private int roll2 = -1;

	private String name;
	private boolean hasWon = false;

	boolean extraTurn = false;

	public Player(String name, Die die1, Die die2) {
		this.name = name;
		this.die1 = die1;
		this.die2 = die2;
	}

	public boolean takeTurn() {
		// boolean extraTurn = false;
		roll1 = die1.roll();
		roll2 = die2.roll();

		// all the game logic is here
		if (roll1 == roll2) {

			// We rolled two 1's.
			if (roll1 == 1) {
				score = 0;
			}

			// win conditions:
			if (score >= winPoints) {
				// the player wins
				hasWon = true;
				// return;
			}

			if (prevRoll1 == 6 && prevRoll2 == 6 && roll1 == 6) {
				// wins
				hasWon = true;
			}
			// grant an extra turn because we rolled two equal dice.
			if (!extraTurn) {
				extraTurn = true;
			}
		} else {
			// If we didn't roll a pair, so we wont get an extra turn.
			extraTurn = false;
		}

		// Dont add anything to our score, if it just got reset.
		if (!(roll1 == 1 && roll2 == 1)) {
			int sum = roll1 + roll2;
			score += sum;
		}

		prevRoll1 = roll1;
		prevRoll2 = roll2;

		return extraTurn;
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

	public int getRoll1() {
		return roll1;
	}

	public int getRoll2() {
		return roll2;
	}

	public int getScore() {
		return score;
	}

}
