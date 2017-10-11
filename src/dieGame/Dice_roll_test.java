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
			switch (sum) {
			case 2:
				c[0]++;
				break;
			case 3:
				c[1]++;
				break;
			case 4:
				c[2]++;
				break;
			case 5:
				c[3]++;
				break;
			case 6:
				c[4]++;
				break;
			case 7:
				c[5]++;
				break;
			case 8:
				c[6]++;
				break;
			case 9:
				c[7]++;
				break;
			case 10:
				c[8]++;
				break;
			case 11:
				c[9]++;
				break;
			case 12:
				c[10]++;
			}

		}

		for (int i = 0; i < 11; i++)
			System.out.println("The number of times the sum of the two dice rolled was " + (i + 2) + ": " + c[i]);
	}

}
