package ccGameFinal;


import java.io.File;
import java.util.Random;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.paint.Color;


/**
 * Main class for this game. Initializes the window, the map, and both ships, and sets all key handlers. Begins playing audio after the stage is set.
 * 
 * All ships and islands are spawned at random open points on the map. Unfortunately, being closed off by islands IS possible. 
 * 
 * Game keys: arrow keys, Enter to reset, Esc to quit
 * 
 * Game parameters:
 * 		dimension: number of squares wide/long the game grid is
 * 		scale: size of each cell in pixels
 * 		numOfIslands: the variable number of islands that are spawned onto the grid each time the game sets up
 * 		numOfPirates: the variable number of pirate ships that are spawned onto the grid each time the game sets up
 * 		
 * 
 * @author Clay
 *
 */
public class OceanExplorer extends Application {
	
	public static final int dimension = 10;
	public static final int scale = 60;
	public static final int numOfIslands = 10;
	public static final int numOfPirates = 2;
	AnchorPane root;
	ColumbusShip ship;
	OceanMap oceanMap;
	Stage window;
	
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
				case ESCAPE:
					System.exit(0);
					break;
				case ENTER:
					reset();
					break;
				default:
					break;
				
				}
				ship.getShipImage().setX(ship.getShipLocation().getX()*OceanExplorer.scale);
				ship.getShipImage().setY(ship.getShipLocation().getY()*OceanExplorer.scale);
			}
			
		});
	}
	
	/**
	 * Sets up the game board. This is what the reset button calls. 
	 */
	private void reset() {
		Random r = new Random();
		
		root = new AnchorPane();
		Scene scene = new Scene(root, dimension*scale, dimension*scale + 40);
		this.drawMap();
		this.ship = new ColumbusShip(oceanMap);
		Point p = oceanMap.getOpenSquare(r);
		this.ship.setShipLocation(p.getX(), p.getY());
		oceanMap.getMap()[p.getX()][p.getY()] = true;
		ship.addObserver(oceanMap);
		root.getChildren().add(ship.getShipImage());

		
		for (int i = 0; i< numOfPirates ; i++) {
			p = oceanMap.getOpenSquare(r);
			PirateShip pirate = new PirateShip(oceanMap, p);
			oceanMap.getMap()[p.getX()][p.getY()] = true;
			ship.addObserver(pirate);
			pirate.addObserver(oceanMap);
			root.getChildren().add(pirate.getShipImage());
		}
		
		window.setTitle("Christopher Columbus! Oh No, And Pirates Too!");
		window.setScene(scene);
		window.show();
		
		startSailing(scene);
	}
	
	private void drawMap() {
		oceanMap = new OceanMap(numOfIslands);
		boolean[][] map = oceanMap.getMap();
		for (int i = 0 ; i<OceanExplorer.dimension ; i++) {
			for (int j = 0 ; j<OceanExplorer.dimension ; j++) {				
				Rectangle rect = new Rectangle(i*scale, j*scale, scale, scale);
				
				ImageView tile;
				rect.setStroke(Color.BLACK);
				if (!map[i][j]) {
					tile = new ImageView(new Image("ccGamePirates/ocean.jpg", OceanExplorer.scale, OceanExplorer.scale, false, true));
				}
				else {
					tile = new ImageView(new Image("ccGamePirates/island.jpg", OceanExplorer.scale, OceanExplorer.scale, false, true));
				}
				tile.setX(i*OceanExplorer.scale);
				tile.setY(j*OceanExplorer.scale);
				root.getChildren().add(tile);
				rect.setOpacity(0.0);
				root.getChildren().add(rect);
				
			}
		}
		
		Button reset = new Button("Press me to reset the game!");
		reset.setLayoutY(scale*dimension);
		reset.setLayoutX(scale*dimension/3);
		
		reset.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				reset();
			}
			
		});
		root.getChildren().add(reset);
		
	}
	
	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage oceanStage) throws Exception {
		window = oceanStage;
		
		//music from here: https://archive.org/details/jamendo-044842
		//creative commons license
		String musicFile = "src/ccGamePirates/PirateSoundtrack.mp3";

		AudioClip sound = new AudioClip(new File(musicFile).toURI().toString());		
		
		this.reset();
		sound.play();
	}

}
