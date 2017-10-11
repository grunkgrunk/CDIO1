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

	double x = 10;
	double y = 10;

	double width = 600;
	double height = 600;

	DieRenderer renderer;
	GraphicsContext gc;
	Game game;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		game = new Game("Red", "Blue");

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
				renderer.setParticleHues(100);
				// If a player rolls twice it wont be recognized.

				if (gameover) {
					renderer.goWild();
				} else {
					// set the energy according to player score
					renderer.setParticleEnergy((double) game.getCurrentScore() / 60 + 0.01);
					renderer.setShowDice(game.getCurrentRoll1(), game.getCurrentRoll2());
				}
			}
		});

		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long now) {

				renderer.render((double) now);
			}
		};

		root.getChildren().add(canvas);
		primaryStage.setScene(new Scene(root));
		primaryStage.show();

		timer.start();

	}

}
