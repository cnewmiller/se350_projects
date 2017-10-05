package ccGamePirates;

import java.util.Observable;
import java.util.Observer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PirateShip extends Observable implements Observer, PointHistory {

	String imageLocation = "ccGamePirates/pirateShip.png";
	private Point p;
	private Point prevPoint;
	private ImageView shipImage;
	public ImageView getShipImage() {return this.shipImage;}
	private AStarSearch searchAgent;
	
	
//	private OceanMap map;
	private boolean[][] grid;
	
	public PirateShip(OceanMap map) {
		this.grid = map.getMap();
		searchAgent = new AStarSearch(this.grid);
		p = new Point();
		prevPoint = new Point();
		this.shipImage = new ImageView(new Image(imageLocation, OceanExplorer.scale, OceanExplorer.scale, true, true));
		this.setShipLocation(OceanExplorer.dimension-1, OceanExplorer.dimension-1);
	}
	public PirateShip(OceanMap map, Point p) {
		this.grid = map.getMap();
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
		if (checkRight(p.getX(), p.getY())) {
			updatePrev();
			p.setX(p.getX()+1);
		}
		shipImage.setX(p.getX()*OceanExplorer.scale);
		shipImage.setY(p.getY()*OceanExplorer.scale);
	}
	public void goWest() {
		if (checkLeft(p.getX(), p.getY())) {
			updatePrev();
			p.setX(p.getX()-1);
		}
		shipImage.setX(p.getX()*OceanExplorer.scale);
		shipImage.setY(p.getY()*OceanExplorer.scale);
	}
	public void goNorth() {
		if (checkUp(p.getX(), p.getY())) {
			updatePrev();
			p.setY(p.getY()-1);
		}
		shipImage.setX(p.getX()*OceanExplorer.scale);
		shipImage.setY(p.getY()*OceanExplorer.scale);
	}
	public void goSouth() {
		if (checkDown(p.getX(), p.getY())) {
			updatePrev();
			p.setY(p.getY()+1);
		}
		shipImage.setX(p.getX()*OceanExplorer.scale);
		shipImage.setY(p.getY()*OceanExplorer.scale);
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
	
	
	@Override
	public void update(Observable o, Object arg) {
		
		if (arg instanceof Point) {
			Point target = (Point) arg;
			
			
			AStarSearch.Directions choice = searchAgent.getBestChoice(this.p, target);
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
	}
	
}
