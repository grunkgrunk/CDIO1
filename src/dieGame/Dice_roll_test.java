package dieGame;

public class Dice_roll_test {

	public static void main(String[] args) {
		Die die = new Die();
		int[] c = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		int numbrolls = 1000;
		for (int i = 1; i <= numbrolls; i++) {
			int r1 = die.roll();
			int r2 = die.roll();
			int sum = r1 + r2;
			
			// 2 - 12
			// 0 - c.length-1
			c[sum-2] += 1;
			
		}

		for (int i = 0; i < 11; i++)
			System.out.println("The number of times the sum of the two dice rolled was " + (i + 2) + ": " + c[i]);
	}

}
