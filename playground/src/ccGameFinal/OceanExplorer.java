package ccGameFinal;


import java.io.File;
import java.util.Random;

import ccGameFinal.UtilityClasses.BreadthLimitedSearch;
import ccGameFinal.UtilityClasses.CollisionChecker;
import ccGameFinal.UtilityClasses.ColumbusCollectTreasure;
import ccGameFinal.UtilityClasses.PirateFactory;
import ccGameFinal.UtilityClasses.Point;
import ccGameFinal.UtilityClasses.PortCollectScore;
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
import javafx.stage.StageStyle;
import javafx.scene.paint.Color;
import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

/**
 * Main class for this game. Initializes the window, the map, and both ships, and sets all key handlers. Begins playing audio after the stage is set.
 * 
 * All ships and islands are spawned at random open points on the map. Unfortunately, being closed off by islands IS possible. It takes a couple seconds to set up the entire game, as optimization was not a priority during development.
 * 
 * Game keys: arrow keys, Enter to reset, Esc to quit
 * 
 * Game ends when the player is "caught" by either the pirates or a sea monster.
 * 
 * Music has been removed to prevent weird file system conflicts and to keep the filesize smaller. Grading is no fun.
 * 
 * Game parameters:
 * 		dimension: number of squares wide/long the game grid is
 * 		scale: size of each cell in pixels
 * 		numOfIslands: the variable number of islands that are spawned onto the grid each time the game sets up
 * 		numOfPirates: the variable number of pirate ships that are spawned onto the grid each time the game sets up
 * 		searchRange: the distance the pirates will chase you from
 * 
 * @author Clay
 *
 */
public class OceanExplorer extends Application {
	
	private static OceanExplorer instance = null;
	public static OceanExplorer getInstance() {
		return instance;
	}
	
	public static final int dimension = 14;
	public static final int scale = 50;
	public static final int numOfIslands = 10;
	public static final int numOfPirates = 2;
	public static final int searchRange = 5;
	public static Random r = new Random();
	AnchorPane root;
	ColumbusShip ship;
	PortRoyale port;
	OceanMap oceanMap;
	CollisionChecker checker;
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
				ship.getShipImage().setX(ship.getPoint().getX()*OceanExplorer.scale);
				ship.getShipImage().setY(ship.getPoint().getY()*OceanExplorer.scale);
			}
			
		});
	}
	
	/**
	 * Sets up the game board. This is what the reset button calls. 
	 */
	private void reset() {
		
		
		root = new AnchorPane();
		Scene scene = new Scene(root, dimension*scale, dimension*scale + 80);
		this.drawMap();
		checker = CollisionChecker.getInstance();
		checker.reset();
		port = new PortRoyale();
		root.getChildren().add(port.image);
		checker.addChild(port);
		this.ship = new ColumbusShip();

		
		Point p = oceanMap.getOpenSquare();
		this.ship.setShipLocation(p.getX(), p.getY());
		oceanMap.getMap()[p.getX()][p.getY()] = true;
		root.getChildren().add(ship.getShipImage());
		root.getChildren().add(ship.getTreasureLabel());

		PirateFactory pf = new PirateFactory();
		
		pf.setStrategy(new BreadthLimitedSearch(OceanMap.getInstance().getMap(), searchRange));
		pf.setCreationLocation(oceanMap.getOpenSquare());
		
		
		for (int i = 0; i< numOfPirates ; i++) {
			p = oceanMap.getOpenSquare();
			pf.setCreationLocation(p);
			//gets a random image
			pf.setImageLocation("ccGameFinal/images/pirateShip_"+Integer.toString(r.nextInt(PirateFactory.numOfPiratePictures))+".png");
			PirateShip pirate = pf.getPirate();
//			checker.addChild(pirate);
			ship.addObserver(pirate);
			pirate.addObserver(oceanMap);
			root.getChildren().add(pirate.getShipImage());
		}
		
		//sea monsters
		p = oceanMap.getOpenSquare();
		SeaMonster monster = new SeaMonster(p);
		oceanMap.getMap()[p.getX()][p.getY()] = true;
		ship.addObserver(monster);
		monster.addObserver(oceanMap);
		root.getChildren().add(monster.getMonsterImage());
		
		p = oceanMap.getOpenSquare();
		monster = new SeaMonster(p);
		oceanMap.getMap()[p.getX()][p.getY()] = true;
		ship.addObserver(monster);
		monster.addObserver(oceanMap);
		root.getChildren().add(monster.getMonsterImage());
		
		
		
		ship.addObserver(oceanMap);
		
		Treasure initialTreasure = new Treasure(oceanMap.getOpenSquare());
		checker.addChild(ship);
		checker.addChild(initialTreasure);
		
		root.getChildren().add(initialTreasure.getImage());
		
		ship.addObserver(checker);
		
		
		window.setTitle("Christopher Columbus 2: Preindustrial Boogaloo");
		window.setScene(scene);
		window.show();
		
		startSailing(scene);
	}
	
	private void drawMap() {
		OceanMap.setIslands(numOfIslands);
		oceanMap = OceanMap.getInstance();
		boolean[][] map = oceanMap.getMap();
		for (int i = 0 ; i<OceanExplorer.dimension ; i++) {
			for (int j = 0 ; j<OceanExplorer.dimension ; j++) {				
				Rectangle rect = new Rectangle(i*scale, j*scale, scale, scale);
				
				ImageView tile;
				rect.setStroke(Color.BLACK);
				if (!map[i][j]) {
					tile = new ImageView(new Image("ccGameFinal/images/ocean.jpg", OceanExplorer.scale, OceanExplorer.scale, false, true));
				}
				else {
					tile = new ImageView(new Image("ccGameFinal/images/island.jpg", OceanExplorer.scale, OceanExplorer.scale, false, true));
				}
				tile.setX(i*OceanExplorer.scale);
				tile.setY(j*OceanExplorer.scale);
				root.getChildren().add(tile);
				rect.setOpacity(0.0);
				root.getChildren().add(rect);
			}
		}
		
		Button reset = new Button("Click me or press 'Enter' to reset the game!");
		reset.setLayoutY(scale*dimension);
		reset.setLayoutX(scale*dimension/4);
		
		reset.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				reset();
			}
			
		});
		root.getChildren().add(reset);
		
	}
	
	/**
	 * Ends the game by showing a display of how many treasures you've brought back to the port successfully
	 */
	public void endGame() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Uh oh!");
		alert.setHeaderText("You've been caught! Your final score is " + port.totalScore);
		alert.setContentText("Would you like to restart or quit?");
		alert.initStyle(StageStyle.UTILITY);
		
		ButtonType buttonRestart = new ButtonType("Restart");
		ButtonType buttonQuit = new ButtonType("Quit");

		alert.getButtonTypes().setAll(buttonRestart, buttonQuit);
		Optional<ButtonType> result = alert.showAndWait();
		
		if (result.get() == buttonRestart){
			this.reset();
		}
		else {
			System.exit(1);
		}
	}
	
	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage oceanStage) throws Exception {
		window = oceanStage;
		Treasure.initializeDefaultList();
		
		ColumbusShip.collisions.put(Treasure.class, new ColumbusCollectTreasure());		
		PortRoyale.collisions.put(ColumbusShip.class, new PortCollectScore());
		
		
		//set singleton
		OceanExplorer.instance = this;
		
		//music from here: https://archive.org/details/jamendo-044842
		//creative commons license
		//String musicFile = "src/ccGameFinal/PirateSoundtrack.mp3";

		//@SuppressWarnings("unused")
		//AudioClip sound = new AudioClip(new File(musicFile).toURI().toString());		
		
		this.reset();
		//sound.play();
	}

}
