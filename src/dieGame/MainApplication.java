package dieGame;

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

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
        primaryStage.setTitle("Dice game");
        Group root = new Group();
        Canvas canvas = new Canvas(400, 400);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, 
        		new EventHandler<MouseEvent>() {
	            @Override
	            public void handle(MouseEvent t) {            
	                if (t.getClickCount() >1) {
	                		gc.setFill(Color.WHITE);
	                		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
	                    //reset(canvas, Color.BLUE);
	                }
	            }
	        });
        
        
        
        drawDieResults(gc, "3", "6");
        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
		
	}
	
	private void drawDieResults(GraphicsContext gc, String d1, String d2) {
		gc.fillText(d1, 10, 10);
		gc.fillText(d2, 50, 10);
		
	}

}
