package dieGame;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class DieRenderer {

	private Particle[] particles;

	private GraphicsContext gc;

	private Color clearColor;

	private double width;
	private double height;

	private int die1 = 0;
	private int die2 = 0;
	
	private String scoreText = "";
	private Font font = Font.font("Verdana", 50);

	private boolean isWild = false;

	// Sometimes bug when quitting

	// Coordinates for all the sides of dice.
	private static int[][][] dieSides = { { { 1, 1 }, }, { { 2, 0 }, { 0, 2 } }, { { 2, 0 }, { 1, 1 }, { 0, 2 } },
			{ { 0, 0 }, { 2, 0 },
					{ 0, 2 }, { 2, 2 } },
			{ { 0, 0 }, { 2, 0 }, { 1, 1 }, { 0, 2 }, { 2, 2 } }, { { 0, 0 }, { 2, 0 },

					{ 0, 1 }, { 2, 1 },

					{ 0, 2 }, { 2, 2 } } };

	private int numParticles = 10000;

	public DieRenderer(Canvas canvas) {
		
		
		
		gc = canvas.getGraphicsContext2D();
		width = canvas.getWidth();
		height = canvas.getHeight();

		
		
		clearColor = Color.rgb(0, 0, 0, 0.1);
		
		

		particles = new Particle[numParticles];
		for (int i = 0; i < numParticles; i++) {
			Particle p = new Particle(Math.random() * width, Math.random() * height);
			particles[i] = p;
		}

		canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				for (Particle p : particles) {
					// set the goal to nothing
					p.setGoal(-1, -1);
					p.explode(30);

				}
			}
		});

	}

	public void setShowDice(int d1, int d2) {
		die1 = d1;
		die2 = d2;
		distributeParticles();
	}

	public void setClearColor(Color c) {
		clearColor = c;
	}

	public void setParticleHues(double h) {
		for (Particle p : particles) {
			p.setHue(h + (Math.random() - 0.5) * 100);
		}
	}

	public void setParticleEnergy(double e) {
		for (Particle p : particles) {
			p.setEnergy(e);
		}
	}

	private void distributeParticles() {
		// int prEye = (numParticles/4) / eyeSum;
		int iterations = numParticles / 2;

		double betweenEyes = 50;

		double offx = 60;
		double offy = height / 2 - betweenEyes * 3 / 2 + 200;

		int[][] toDisplay1 = dieSides[die1 - 1];
		int[][] toDisplay2 = dieSides[die2 - 1];

		for (int i = 0; i < iterations; i++) {

			Particle p = getRandomParticle();

			// Alternate between die1 and die2
			int[][] toDisplay = toDisplay1;
			if (i % 2 == 1) {
				toDisplay = toDisplay2;
			}

			// random eye
			int ptIdx = (int) (Math.random() * toDisplay.length);
			int[] point = toDisplay[ptIdx];

			if (i % 2 == 1) {
				p.setGoal(point[0] * betweenEyes + offx, point[1] * betweenEyes + offy);
			} else {
				p.setGoal(point[0] * betweenEyes + width - betweenEyes * 3, point[1] * betweenEyes + offy);
			}
		}
	}

	public void goWild() {

		for (Particle p : particles) {
			p.explode(50);
			// set the goal to the middle of the screen
			p.setGoal(width / 2, height / 2);

		}

		isWild = true;
	}

	public void render(double now) {
		// clear with transparency to get a trailing effect
		gc.setFill(clearColor);
		gc.fillRect(0, 0, width, height);

		if (!scoreText.equals("") && !isWild) {
			gc.setTextAlign(TextAlignment.CENTER);
			gc.setFont(font);
			gc.setFill(Color.rgb(0,0,0,0.1));
			gc.fillText(scoreText, width/2, 100);

		}
		
		for (Particle p : particles) {

			if (isWild) {
				double diffx = width / 2 - p.getX();
				double diffy = height / 2 - p.getX();

				double mag = Math.sqrt(diffx * diffx + diffy * diffy);

				p.explode(1 / mag);
				
				// Draw lines between particles
				if (Math.random() > 0.999) {
					Particle p1 = getRandomParticle();
					Particle p2 = getRandomParticle();
					gc.setLineWidth(0.5);
					gc.setStroke(Color.hsb(p1.getHue(), 1, 1));
					gc.strokeLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
				}
			}

			p.update();
			double x = p.getX();
			double y = p.getY();

			// Loop around the screen.
			if (x < 0) {
				p.setX(width);
			}

			if (x > width) {
				p.setX(0);
			}

			if (y < 0) {
				p.setY(height);
			}

			if (y > height) {
				p.setY(0);
			}

			// double r = Math.sqrt(p.getVx()*p.getVx() + p.getVy()*p.getVy()) * 2;
			double r = Math.random() * 2;
			gc.setFill(Color.hsb(p.getHue(), 1, 1));
			gc.fillOval(x, y, r, r);
		}
		

	}

	private Particle getRandomParticle() {
		int pIdx = (int) (Math.random() * numParticles);
		return particles[pIdx];
	}

	public void setScore(int score) {
		this.scoreText = Integer.toString(score);
	}

}
