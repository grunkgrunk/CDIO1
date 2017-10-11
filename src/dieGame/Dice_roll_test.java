package dieGame;

public class Dice_roll_test {

	public static void main(String[] args) {
		Die die = new Die();
		int[] eyecount = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }; //Array der indeholder antallet af gange de forskellige summe forkommer.
		int numbrolls = 1000;
		for (int i = 1; i <= numbrolls; i++) { //For loop der looper det antal gange vi gerne vil kaste med terningerne.
			int r1 = die.roll();
			int r2 = die.roll();
			int sum = r1 + r2;
			
			// 2 - 12
			// 0 - c.length-1
			eyecount[sum-2] += 1;
			
		}

		for (int i = 0; i < 11; i++) { //For loop der printer antallet af gange de forskellige summe forkommer.
			System.out.println("The number of times the sum of the two dice rolled was " + (i + 2) + ": " + eyecount[i]);
		}
	}

}
