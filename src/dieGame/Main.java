package dieGame;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		// The game in terminal.
		// Get the player names. 
		Scanner sc = new Scanner(System.in);
		System.out.print("Player 1: What is your name? ");
		String name1 = sc.nextLine();
		System.out.print("Player 2: What is your name? ");
		String name2 = sc.nextLine();
		
		Game game = new Game(name1, name2);
		game.runNormal();
		
		sc.close();
	}
}