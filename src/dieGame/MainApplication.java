package dieGame;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MainApplication extends Application {

	private double x = 10;
	private double y = 10;

	private double width = 600;
	private double height = 600;
	
	private int score = 0;
	
	private boolean prevGameover = false;

	DieRenderer renderer;
	GraphicsContext gc;
	Game game;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		 
		game = new Game();

		primaryStage.setTitle("Dice game");
		Group root = new Group();
		Canvas canvas = new Canvas(width, height);
		gc = canvas.getGraphicsContext2D();

		renderer = new DieRenderer(canvas);

		// Changes color whenever it is another player's turn (Not completely)
		// Displays the winning person with a cool effect.
		canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				int turn = game.getTurn();
				boolean gameover = game.runOnce();

				if (turn == 0) {
					renderer.setClearColor(Color.rgb(244, 67, 54, 0.1));

				} else {
					renderer.setClearColor(Color.rgb(33, 150, 243, 0.1));

				}
				// renderer.setClearColor(Color.rgb(0,0,0, 0.1));
				//renderer.setParticleHues(100);

				if (gameover && prevGameover) {
					renderer.goWild();
				} else {

					// save the score so we can draw it 
					score = game.getCurrentScore();
					renderer.setScore(score);
					// set the energy according to player score
					double maxEnergy = 1.1;
					double energy = (double) score / 60 + 0.01;
					if (energy > maxEnergy) {
						energy = maxEnergy;
					}
					
					renderer.setParticleEnergy(energy);
					renderer.setShowDice(game.getCurrentRoll1(), game.getCurrentRoll2());
				}
				
				prevGameover = gameover;
			}
		});

		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				renderer.render((double) now);
				
				//gc.fillText(Integer.toString(score), width/2, 100);
				//gc.fillText("100", 100, 100);
			}
		};

		root.getChildren().add(canvas);
		primaryStage.setScene(new Scene(root));
		primaryStage.show();

		timer.start();

	}

}
