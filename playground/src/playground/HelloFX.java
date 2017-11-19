package playground;

import javafx.application.*;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.*;


public class HelloFX extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Text display = new Text();
		
		display.setText("Hello world");
		display.setX(25);
		display.setY(40);
		display.setFont(new Font ("Arial", 20));
		
		AnchorPane root = new AnchorPane();
		root.getChildren().add(display);
		Scene scene = new Scene(root, 200, 200);
		
		primaryStage.setTitle("This is a fucking window!");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
