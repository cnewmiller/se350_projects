package ccGame;


import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

//player controller, input handler
public class OceanExplorer extends Application { //implement obseravable?
	
	public static final int dimension = 10;
	public static final int scale = 60; //goof with this
	AnchorPane root;
	Ship ship;
	OceanMap oceanMap;
	
	private void startSailing(Scene scene) {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				
				switch(event.getCode()){
				case RIGHT:
					ship.goEast();
					break;
				case LEFT:
					ship.goWest();
					break;
				case UP:
					ship.goNorth();
					break;
				case DOWN:
					ship.goSouth();
					break;
				default:
					break;
				
				}
				ship.getShipImage().setX(ship.getShipLocation().x*OceanExplorer.scale);
				ship.getShipImage().setY(ship.getShipLocation().y*OceanExplorer.scale);
				
				
			}
			
		});
	}
	
	
	
	private void drawMap() {
		oceanMap = new OceanMap(10);
		boolean[][] map = oceanMap.getMap();
		for (int i = 0 ; i<OceanExplorer.dimension ; i++) {
			for (int j = 0 ; j<OceanExplorer.dimension ; j++) {				
				Rectangle rect = new Rectangle(i*scale, j*scale, scale, scale);
				rect.setStroke(Color.BLACK);
				if (!map[i][j]) {
					rect.setFill(Color.PALETURQUOISE);
				}
				else {
					rect.setFill(Color.BURLYWOOD);
				}
				
				root.getChildren().add(rect);
				
			}
		}
		
	}
	
	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage oceanStage) throws Exception {
		
		root = new AnchorPane();
//		root.getChildren().add(display);
		Scene scene = new Scene(root, dimension*scale, dimension*scale);
		this.drawMap();
		
		this.ship = new Ship(oceanMap);
		
		root.getChildren().add(ship.getShipImage());
		
		oceanStage.setTitle("Christopher Columbus!");
		oceanStage.setScene(scene);
		oceanStage.show();
		
		startSailing(scene);
	}

}
