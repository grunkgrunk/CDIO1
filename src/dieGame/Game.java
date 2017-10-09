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
	
	public void runSimulate() {
		while (true) {
			currentPlayer = determinePlayer(); 
			boolean gameover = run();
			if (gameover) {
				System.out.printf("Player " + (turn+1) + " wins!");
				return;
			}
		}
	}
	
	public void runNormal() {
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
	
	private void nextTurn() {
		turn += 1;
		turn = turn % maxPlayers;
	}
}
