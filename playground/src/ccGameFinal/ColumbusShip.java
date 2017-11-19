package ccGameFinal;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import ccGameFinal.Interfaces.Collidable;
import ccGameFinal.Interfaces.CollisionFunction;
import ccGameFinal.Interfaces.GridChecker;
import ccGameFinal.Interfaces.PointHistory;
import ccGameFinal.UtilityClasses.Point;
import ccGameFinal.UtilityClasses.SimpleChecker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 * 
 * Player class. Contains no pathfinding abilities, and broadcasts its moves as an Observable.
 * I wanted to make a single base ship class, but since Observable is a superclass, Java "helps" prevent diamond inheritance patterns and made that difficult.
 * Default position is 0,0
 * Maintains a record of where it was previously, so the OceanMap can clear its previous square.
 * 
 * @author Clay
 *
 */
public class ColumbusShip extends Observable implements PointHistory, Collidable{
	public static Map<Class<?>, CollisionFunction> collisions = new HashMap<>();
	
	String imageLocation = "ccGameFinal/images/ship.png";
	private Point p;
	private Point prevPoint = null;
	private ImageView shipImage;
	private Label treasureLabel;
	public Label getTreasureLabel() {return this.treasureLabel;}
	public ImageView getShipImage() {return this.shipImage;}
	private GridChecker checker;
	private Treasure booty = null;
	public void addCollisionClass(Class<?> otherClass, CollisionFunction f) {
		collisions.put(otherClass, f);
	}
	public void removeCollisionClass(Class<?> otherClass) {
		collisions.remove(otherClass);
	}
	
	
	public Treasure getBooty() {
		return booty;
	}
	public void setBooty(Treasure t) {
		this.booty = t;
	}
	
	private boolean[][] grid;
	
	public ColumbusShip() {
		this.grid = OceanMap.getInstance().getMap();
		checker = new SimpleChecker();
		p = new Point();
		prevPoint = new Point();
		this.shipImage = new ImageView(new Image(imageLocation, OceanExplorer.scale, OceanExplorer.scale, true, true));
		this.treasureLabel = new Label("No treasure yet!");
		treasureLabel.setLayoutY(OceanExplorer.scale*OceanExplorer.dimension+40);
		treasureLabel.setWrapText(true);
		treasureLabel.setMaxWidth(OceanExplorer.scale*OceanExplorer.dimension);
	}
	
	private void updatePrev() {
		prevPoint.setX(p.getX());
		prevPoint.setY(p.getY());
	}
	
	public Point getPoint() {
		return this.p;
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
		this.setChanged();
		this.notifyObservers(this);
	}
	public void goWest() {
		updatePrev();
		if (checker.checkLeft(p, grid)) {
			p.setX(p.getX()-1);
		}
		this.setChanged();
		this.notifyObservers(this);
	}
	public void goNorth() {
		updatePrev();
		if (checker.checkUp(p, grid)) {
			p.setY(p.getY()-1);
		}
		this.setChanged();
		this.notifyObservers(this);
		}
	public void goSouth() {
		updatePrev();
		if (checker.checkDown(p, grid)) {
			p.setY(p.getY()+1);
		}
		this.setChanged();
		this.notifyObservers(this);
	}

	@Override
	public Point getPrevPoint() {
		return prevPoint;
	}

	

	@Override
	public void collideWith(Collidable c) {
		
		if (collisions.containsKey(c.getClass())) {
			collisions.get(c.getClass()).doCollision(this, c);
		}
		
		
	}
	
}
