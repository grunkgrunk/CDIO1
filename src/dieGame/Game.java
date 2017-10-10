package dieGame;

import java.util.Scanner;

public class Game {
	Player player1;
	Player player2;
	Player currentPlayer;
	
	int turn = 0;
	int maxPlayers = 2;
	
	
	public Game(String name1, String name2) {
		player1 = new Player(name1);
		player2 = new Player(name2);
	}
	
	private void greetPlayers() {
		System.out.printf(
				"Welcome %s and %s to a game of dice! %nA game where everything is NOT determined by you! %n", 
				player1.getName(), player2.getName());
		
		
		System.out.println("----------------------------------");
		System.out.println();
	}
	
	// If we want computer vs computer. Good for testing. 
	public void runSimulate() {
		while (true) {
			currentPlayer = determinePlayer();
			System.out.println(currentPlayer);
			boolean gameover = run();
			if (gameover) {
				System.out.printf(currentPlayer.getName() + " wins!");
				// If a player won, we want to return out of the method. 
				return;
			}
		}
	}
	
	public void runNormal() {
		greetPlayers();
		// Make a scanner so we can make the game only progress when the user presses enter. 
		Scanner scanner = new Scanner(System.in);
		while (true) {
			currentPlayer = determinePlayer();  
			System.out.print(currentPlayer.getName() + "'s turn. Roll the dice?");
			scanner.nextLine();
			boolean gameover = run();
			System.out.println(currentPlayer);
			System.out.println("----------------------------------");
			System.out.println();
			
			
			if (gameover) {
				System.out.println(currentPlayer.getName() + " wins!");
				break;
			}
		}
		scanner.close();
	}
	
	// return a bool indicating if the game is over or not.
	private boolean run() {
		currentPlayer.takeTurn();
		if (currentPlayer.getHasWon()) {
			return true;
		}
		nextTurn();
		return false;
	}
	
	private Player determinePlayer() {
		Player pl = player1;
		if (turn == 1) {
			pl = player2;
		}
		return pl;
	}
	
	// Increment turn and make sure it stays in range 0 .. maxPlayers - 1
	private void nextTurn() {
		turn += 1;
		turn = turn % maxPlayers;
	}
}
