package dieGame;

import java.util.Random;

// Particle class 
// Responsible for updating it's state
// The most important thing is that 
// velocity is added to position. 
// This creates acceleration and is what makes 
// the particles look life-like.

public class Particle {
	// We could have a vector class so we didn't have to declare this many variables.
	// This would also make it easier to normalize a vector and find the magnitude of it etc.
	private double x = 0;
	private double y = 0;
	private double vx = 0;
	private double vy = 0;
	private double r = 0.5;
	private double hue = Math.random() * 255;
	private double energy = 0.1;
	private boolean frictionOn = true;
	private static Random rnd = new Random();
	private double goalX = -1;
	private double goalY = -1;

	public Particle(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public void update() {
		if (goalX != -1 || goalY != -1) {
			double offsetX = Math.cos(Math.random() * Math.PI * 2) * 20;
			double offsetY = Math.sin(Math.random() * Math.PI * 2) * 20;

			double diffX = goalX + offsetX - x;
			double diffY = goalY + offsetY - y;

			double mag = Math.sqrt(diffX * diffX + diffY * diffY);

			// diffX /= 10;
			// diffY /= 10;
			vx += diffX / mag * 0.6;
			vy += diffY / mag * 0.6;
		}

		// Something is wrong with this. All particles seem to change
		// color in a pattern.
		hue += Math.random() * 10;
		hue %= 240;
		//hue += x/100;
		
		double rx = rnd.nextDouble() - 0.5;
		double ry = rnd.nextDouble() - 0.5;
		double mag = Math.sqrt(rx * rx + ry * ry);
		rx /= mag;
		ry /= mag;
		//
		vx += rx * energy;
		vy += ry * energy;

		if (frictionOn) {
			double fric = 0.9;
			vx *= fric;
			vy *= fric;
		}

		x += vx;
		y += vy;
	}

	public void explode(double strength) {
		double rx = rnd.nextDouble() - 0.5;
		double ry = rnd.nextDouble() - 0.5;
		double mag = Math.sqrt(rx * rx + ry * ry);
		rx /= mag;
		ry /= mag;
		vx += rx * strength;
		vy += ry * strength;
	}

	public void setFriction(boolean state) {
		frictionOn = state;
	}

	public double getHue() {
		return hue;
	}

	public void setHue(double h) {
		hue = h;
	}

	public void setGoal(double x, double y) {
		goalX = x;
		goalY = y;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getVx() {
		return vx;
	}

	public void setVx(double vx) {
		this.vx = vx;
	}

	public double getVy() {
		return vy;
	}

	public void setVy(double vy) {
		this.vy = vy;
	}

	public double getR() {
		return r;
	}

	public void setR(double r) {
		this.r = r;
	}

	public void setEnergy(double e) {
		energy = e;

	}
}
