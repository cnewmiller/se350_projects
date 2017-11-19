package ccGameFinal;

import java.util.Observable;
import java.util.Observer;

import ccGameFinal.Interfaces.GridChecker;
import ccGameFinal.Interfaces.PointHistory;
import ccGameFinal.UtilityClasses.AStarSearch;
import ccGameFinal.UtilityClasses.AdjacencyChecker;
import ccGameFinal.UtilityClasses.Point;
import ccGameFinal.UtilityClasses.SearchStrategy;
import ccGameFinal.UtilityClasses.SimpleChecker;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 * 
 * PirateShip class that represents computer controlled ships that track the player ship.
 * Observable to notify OceanMap of its moves, Observer to react to the player ColumbusShip moves.
 * Default location is bottom right corner, opposit default player location.
 * 
 * The private boolean turn variable allows it to only move on every other player movement.
 * 
 * Pirates are not Collidable: they cannot collect treasure or interact with the port.
 * 
 * @author Clay
 *
 */
public class PirateShip extends Observable implements Observer, PointHistory {

	String imageLocation = "ccGameFinal/images/pirateShip_1.png";
	private Point p;
	private Point prevPoint;
	private ImageView shipImage;
	private SearchStrategy searchAgent;
	private boolean[][] grid;
	private boolean turn = false;
	private GridChecker checker;
	
	public ImageView getShipImage() {return this.shipImage;}
	public void setImage(String location) {
		imageLocation = location;
		this.shipImage = new ImageView(new Image(imageLocation, OceanExplorer.scale, OceanExplorer.scale, true, true));
		shipImage.setX(p.getX()*OceanExplorer.scale);
		shipImage.setY(p.getY()*OceanExplorer.scale);
	}
	
	
	public PirateShip() {
		
		this.grid = OceanMap.getInstance().getMap();
		this.checker = new SimpleChecker();
		searchAgent = new AStarSearch(this.grid);
		p = new Point();
		prevPoint = new Point();
		this.setImage(imageLocation);
		this.setShipLocation(OceanExplorer.dimension-1, OceanExplorer.dimension-1);
	}
	
	public PirateShip(Point p, SearchStrategy agent) {
		this.grid = OceanMap.getInstance().getMap();
		this.checker = new SimpleChecker();
		searchAgent = agent;
		this.p = p;
		prevPoint = null;
		this.setImage(imageLocation);
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
		updatePrev();
		if (checker.checkRight(p, grid)) {
			p.setX(p.getX()+1);
		}
		shipImage.setX(p.getX()*OceanExplorer.scale);
		shipImage.setY(p.getY()*OceanExplorer.scale);
	}
	public void goWest() {
		updatePrev();
		if (checker.checkLeft(p, grid)) {
			p.setX(p.getX()-1);
		}
		shipImage.setX(p.getX()*OceanExplorer.scale);
		shipImage.setY(p.getY()*OceanExplorer.scale);
	}
	public void goNorth() {
		updatePrev();
		if (checker.checkUp(p, grid)) {
			p.setY(p.getY()-1);
		}
		shipImage.setX(p.getX()*OceanExplorer.scale);
		shipImage.setY(p.getY()*OceanExplorer.scale);
	}
	public void goSouth() {
		updatePrev();
		if (checker.checkDown(p, grid)) {
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
		
		
				
		if (o instanceof PointHistory && turn) {
			if (o instanceof PointHistory) {
				if (AdjacencyChecker.isNextToAnywhere(this.p, ( (PointHistory)o).getPoint())) {
					OceanExplorer.getInstance().endGame();
				}
			}
			turn = false;
			PointHistory other = (PointHistory) o;
			Point target = other.getPoint();	
			
			SearchStrategy.Directions choice = searchAgent.getBestChoice(this.p, target);
			if (choice == null) {
				this.setChanged();
				notifyObservers(this);
				return;
			}
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
			notifyObservers(this);
		}
		else {
			
			if (o instanceof ColumbusShip) {
				if (AdjacencyChecker.isNextToAnywhere(this.p, ( (PointHistory)o).getPoint())) {
					OceanExplorer.getInstance().endGame();
				}
			}
			turn = true;
		}
	}

	@Override
	public Point getPoint() {
		return this.p;
	}
	
	
}
