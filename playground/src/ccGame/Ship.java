package ccGame;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Ship {
	public static class Point{
		public int x, y;
		public Point() {
			x = 0;
			y = 0;
		}
	}
	
	
	String imageLocation;
	private Point p;
	private ImageView shipImage;
	public ImageView getShipImage() {return this.shipImage;}
	
	private OceanMap map;
	private boolean[][] grid;
	
	public Ship(OceanMap map) {
		this.map = map;
		this.grid = map.getMap();
		p = new Point();
		this.shipImage = new ImageView(new Image("ccGame/ship.png", OceanExplorer.scale, OceanExplorer.scale, true, true));
		
	}
	
	public Point getShipLocation() {
		return this.p;
	}
	
	public void goEast() {
		if (checkRight(p.x, p.y)) {
			p.x++;
		}
	}
	public void goWest() {
		if (checkLeft(p.x, p.y)) {
			p.x--;
		}
	}
	public void goNorth() {
		if (checkUp(p.x, p.y)) {
			p.y--;
		}
	}
	public void goSouth() {
		if (checkDown(p.x, p.y)) {
			p.y++;
		}
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
	
	
	
	
	
	
	
	
	
	
	
}
