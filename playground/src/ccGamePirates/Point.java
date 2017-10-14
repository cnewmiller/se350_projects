package ccGamePirates;


/**
 * Personally defined Point class to represent a 2d point. This allowed me to define my own hashCode and equals functions.
 * Not strictly necessary, but I did it anyway.
 * 
 * @author Clay
 *
 */
public class Point  {
	private int x, y;
	public int getX() {return x;}
	public int getY() {return y;}
	public void setX(int X) {this.x = X;}
	public void setY(int Y) {this.y = Y;}
	
	public Point() {
		x = 0;
		y = 0;
	}
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public int hashCode() {
		return x+y*OceanExplorer.dimension;
	}
	
	@Override
	public boolean equals(Object o) {
		if (null == o) {
			return false;
		}
		if (this == o) {
			return true;
		}
		if (o.getClass() == this.getClass()) {
			Point p = (Point) o;
			return (p.x == this.x && p.y == this.y);
		}
		
		return false;
	}

	
	@Override
	public String toString() {
		return String.format("(%d, %d)", this.x, this.y);
	}
}
