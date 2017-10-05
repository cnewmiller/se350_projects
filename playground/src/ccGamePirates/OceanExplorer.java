package ccGamePirates;


import java.io.File;
import java.util.Random;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

//player controller, input handler
public class OceanExplorer extends Application { //implement obseravable?
	
	public static final int dimension = 10;
	public static final int scale = 60; //goof with this
	AnchorPane root;
	ColumbusShip ship;
	PirateShip pirate;
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
				case ENTER:
					reset();
				default:
					break;
				
				}
				ship.getShipImage().setX(ship.getShipLocation().getX()*OceanExplorer.scale);
				ship.getShipImage().setY(ship.getShipLocation().getY()*OceanExplorer.scale);
			}
			
		});
	}
	
	private void reset() {
		Random r = new Random();
		
		root = new AnchorPane();
//		root.getChildren().add(display);
		Scene scene = new Scene(root, dimension*scale, dimension*scale + 50);
		this.drawMap();
		this.ship = new ColumbusShip(oceanMap);
		Point p = new Point(r.nextInt(dimension), r.nextInt(dimension));
		while(oceanMap.getMap()[p.getX()][p.getY()]) {
			p = new Point(r.nextInt(dimension), r.nextInt(dimension));
		}
		
		this.pirate = new PirateShip(oceanMap, p);
		oceanMap.getMap()[p.getX()][p.getY()] = true;
//		this.pirate.setShipLocation(r.nextInt(dimension), r.nextInt(dimension));
		
		ship.addObserver(pirate);
		pirate.addObserver(oceanMap);
		
		root.getChildren().add(pirate.getShipImage());
		
//		this.pirate = new PirateShip(oceanMap);
		
		p = new Point(r.nextInt(dimension), r.nextInt(dimension));
		while(oceanMap.getMap()[p.getX()][p.getY()]) {
			p = new Point(r.nextInt(dimension), r.nextInt(dimension));
		}
		this.pirate = new PirateShip(oceanMap, p);
		oceanMap.getMap()[p.getX()][p.getY()] = true;
		
		ship.addObserver(pirate);
		ship.addObserver(oceanMap);
		pirate.addObserver(oceanMap);
		
		
		root.getChildren().add(ship.getShipImage());
		root.getChildren().add(pirate.getShipImage());
		
		window.setTitle("Christopher Columbus! Oh Noes Pirates Too!");
		window.setScene(scene);
		window.show();
		
		startSailing(scene);
	}
	
	private void drawMap() {
		oceanMap = new OceanMap(10);
		boolean[][] map = oceanMap.getMap();
		for (int i = 0 ; i<OceanExplorer.dimension ; i++) {
			for (int j = 0 ; j<OceanExplorer.dimension ; j++) {				
				Rectangle rect = new Rectangle(i*scale, j*scale, scale, scale);
				
				
				rect.setStroke(Color.BLACK);
				if (!map[i][j]) {
//					rect.setFill(Color.PALETURQUOISE);
					ImageView tile = new ImageView(new Image("ccGamePirates/ocean.jpg", OceanExplorer.scale, OceanExplorer.scale, true, true));
					tile.setX(i*OceanExplorer.scale);
					tile.setY(j*OceanExplorer.scale);
					root.getChildren().add(tile);
					rect.setOpacity(0.0);
				}
				else {
					rect.setFill(Color.BURLYWOOD);
				}
				
				root.getChildren().add(rect);
				
			}
		}
		
		Button reset = new Button("Press me to reset the game!");
		reset.setLayoutY(scale*dimension);
//		reset.setScaleY(1);
//		reset.setScaleX(1);
//		reset.setLayoutx(scale*dimension);
		reset.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				//do reset action here
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
		
		String musicFile = "src/ccGamePirates/PirateSoundtrack.mp3";     // For example

		AudioClip sound = new AudioClip(new File(musicFile).toURI().toString());
//		MediaPlayer mediaPlayer = new MediaPlayer(sound);
		sound.play();
		
		
		this.reset();
	}

}
