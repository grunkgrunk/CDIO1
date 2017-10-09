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
		float d1=(float)Math.random();     // 0-1
		float d2=d1*(faces-1);                     // 0-5
		int d3 = (int)d2 + 1;              // 0-5 integer
		faceValue = d3;
		return d3;          // 1-6
	}
	
	public int getFaceValue() {
		return faceValue;
	}
}
