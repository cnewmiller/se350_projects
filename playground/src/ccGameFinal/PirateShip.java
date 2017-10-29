package ccGameFinal;

import java.util.Observable;
import java.util.Observer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


//change to factory
/**
 * 
 * PirateShip class that represents computer controlled ships that track the player ship.
 * Observable to notify OceanMap of its moves, Observer to react to the player ColumbusShip moves.
 * Default location is bottom right corner, opposit default player location.
 * 
 * The private boolean turn variable allows it to only move on every other player movement.
 * 
 * @author Clay
 *
 */
public class PirateShip extends Observable implements Observer, PointHistory {

	String imageLocation = "ccGamePirates/pirateShip_1.png";
	private Point p;
	private Point prevPoint;
	private ImageView shipImage;
	private SearchStrategy searchAgent;
	private boolean[][] grid;
	private boolean turn = false;
	private GridChecker checker;
	
	
	public ImageView getShipImage() {return this.shipImage;}
	
	
	public PirateShip(OceanMap map) {
		this.grid = map.getMap();
		this.checker = new SimpleChecker();
		searchAgent = new AStarSearch(this.grid);
		p = new Point();
		prevPoint = new Point();
		this.shipImage = new ImageView(new Image(imageLocation, OceanExplorer.scale, OceanExplorer.scale, true, true));
		this.setShipLocation(OceanExplorer.dimension-1, OceanExplorer.dimension-1);
	}
	
	public PirateShip(OceanMap map, Point p) {
		this.grid = map.getMap();
		this.checker = new SimpleChecker();
		searchAgent = new AStarSearch(this.grid);
		this.p = p;
		prevPoint = null;
		this.shipImage = new ImageView(new Image(imageLocation, OceanExplorer.scale, OceanExplorer.scale, true, true));
		this.setShipLocation(this.p.getX(), this.p.getY());
	}
	
	public Point getShipLocation() {
		return this.p;
	}
	
	private void updatePrev() {
		if (prevPoint == null) {
			prevPoint = new Point();
		}
		prevPoint.setX(p.getX());
		prevPoint.setY(p.getY());
	}
	
	public void setShipLocation(int x, int y) {
		updatePrev();
		p.setX(x);
		p.setY(y);
		
		shipImage.setX(p.getX()*OceanExplorer.scale);
		shipImage.setY(p.getY()*OceanExplorer.scale);
	}
	
	public void goEast() {
		if (checker.checkRight(p, grid)) {
			updatePrev();
			p.setX(p.getX()+1);
		}
		shipImage.setX(p.getX()*OceanExplorer.scale);
		shipImage.setY(p.getY()*OceanExplorer.scale);
	}
	public void goWest() {
		if (checker.checkLeft(p, grid)) {
			updatePrev();
			p.setX(p.getX()-1);
		}
		shipImage.setX(p.getX()*OceanExplorer.scale);
		shipImage.setY(p.getY()*OceanExplorer.scale);
	}
	public void goNorth() {
		if (checker.checkUp(p, grid)) {
			updatePrev();
			p.setY(p.getY()-1);
		}
		shipImage.setX(p.getX()*OceanExplorer.scale);
		shipImage.setY(p.getY()*OceanExplorer.scale);
	}
	public void goSouth() {
		if (checker.checkDown(p, grid)) {
			updatePrev();
			p.setY(p.getY()+1);
		}
		shipImage.setX(p.getX()*OceanExplorer.scale);
		shipImage.setY(p.getY()*OceanExplorer.scale);
	}

	@Override
	public Point getPrevPoint() {
		return prevPoint;
	}
	
	
	@Override
	public void update(Observable o, Object arg) {
		
		if (arg instanceof Point && turn) {
			turn = false;
			Point target = (Point) arg;
			
			SearchStrategy.Directions choice = searchAgent.getBestChoice(this.p, target);
			if (choice == null) return;
			switch (choice) {
			case UP:
				goNorth();
				break;
			case DOWN:
				goSouth();
				break;
			case LEFT:
				goWest();
				break;
			case RIGHT:
				goEast();
				break;
			default:
				break;
			}
			this.setChanged();
			notifyObservers(this.p);
		}
		else {
			turn = true;
		}
	}
	
}
