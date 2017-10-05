package ccGamePirates;

import java.util.Observable;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ColumbusShip extends Observable implements PointHistory{
	
	
	
	String imageLocation = "ccGame/ship.png";
	private Point p;
	private Point prevPoint = null;
	private ImageView shipImage;
	public ImageView getShipImage() {return this.shipImage;}
	
//	private OceanMap map;
	private boolean[][] grid;
	
	public ColumbusShip(OceanMap map) {
//		this.map = map;
		this.grid = map.getMap();
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
	
	public void goEast() {
		if (checkRight(p.getX(), p.getY())) {
			updatePrev();
			p.setX(p.getX()+1);
		}
		this.setChanged();
		this.notifyObservers(p);
	}
	public void goWest() {
		if (checkLeft(p.getX(), p.getY())) {
			updatePrev();
			p.setX(p.getX()-1);
		}
		this.setChanged();
		this.notifyObservers(p);
	}
	public void goNorth() {
		if (checkUp(p.getX(), p.getY())) {
			updatePrev();
			p.setY(p.getY()-1);
		}
		this.setChanged();
		this.notifyObservers(p);
	}
	public void goSouth() {
		if (checkDown(p.getX(), p.getY())) {
			updatePrev();
			p.setY(p.getY()+1);
		}
		this.setChanged();
		this.notifyObservers(p);
	}
	
	private boolean checkLeft(int xPos, int yPos) {
		
		if (xPos > 0) {
			if (!grid[xPos-1][yPos]) {
				return true;
			}
		}
		return false;
	}
	private boolean checkRight(int xPos, int yPos) {
		if (xPos < grid.length-1) {
			if (!grid[xPos+1][yPos]) {
				return true;
			}
		}
		return false;
	}
	private boolean checkUp(int xPos, int yPos) {
		if (yPos > 0) {
			if (!grid[xPos][yPos-1]) {
				return true;
			}
		}
		return false;
	}
	private boolean checkDown(int xPos, int yPos) {
		if (yPos < grid[0].length-1) {
			if (!grid[xPos][yPos+1]) {
				return true;
			}

		}
		return false;
	}
	@Override
	public Point getPrevPoint() {
		return prevPoint;
	}
	
	
}
