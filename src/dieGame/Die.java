package dieGame;

public class Die {
	private int faceValue;
	private int faces = 6;
	
	public Die() {
		roll();
	}
	// roll the die and return the value (1-6)
	// Maybe not random as we want it to 
	public int roll() {
		double d1=(float)Math.random();     // 0-1
		double d2=d1*(faces);                     // 0-5
		faceValue = (int)Math.ceil(d2);              // 0-5 integer
		return faceValue;
	}
	
	public int getFaceValue() {
		return faceValue;
	}
}
