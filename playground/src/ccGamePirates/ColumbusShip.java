package ccGamePirates;

import java.util.Observable;

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
public class ColumbusShip extends Observable implements PointHistory{
	
	String imageLocation = "ccGame/ship.png";
	private Point p;
	private Point prevPoint = null;
	private ImageView shipImage;
	public ImageView getShipImage() {return this.shipImage;}
	private GridChecker checker;
	
	private boolean[][] grid;
	
	public ColumbusShip(OceanMap map) {
		this.grid = map.getMap();
		checker = new SimpleChecker();
		p = new Point();
		prevPoint = new Point();
		this.shipImage = new ImageView(new Image(imageLocation, OceanExplorer.scale, OceanExplorer.scale, true, true));
	}
	
	private void updatePrev() {
		prevPoint.setX(p.getX());
		prevPoint.setY(p.getY());
	}
	
	public Point getShipLocation() {
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
		if (checker.checkRight(p, grid)) {
			updatePrev();
			p.setX(p.getX()+1);
		}
		this.setChanged();
		this.notifyObservers(p);
	}
	public void goWest() {
		if (checker.checkLeft(p, grid)) {
			updatePrev();
			p.setX(p.getX()-1);
		}
		this.setChanged();
		this.notifyObservers(p);
	}
	public void goNorth() {
		if (checker.checkUp(p, grid)) {
			updatePrev();
			p.setY(p.getY()-1);
		}
		this.setChanged();
		this.notifyObservers(p);
	}
	public void goSouth() {
		if (checker.checkDown(p, grid)) {
			updatePrev();
			p.setY(p.getY()+1);
		}
		this.setChanged();
		this.notifyObservers(p);
	}

	@Override
	public Point getPrevPoint() {
		return prevPoint;
	}
	
	
}
