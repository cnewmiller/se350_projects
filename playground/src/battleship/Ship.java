package battleship;

public class Ship {
	
	public enum Orientations{Horizontal, Vertical};
	
	
	int x, y; //the top left corner
	int len;
	String name;
	
	Orientations orientation;
	
	Ship(int x1, int y1, int len, Ship.Orientations or){
		this.x = x1;
		this.y = y1;
		this.len = len;
		this.name = (5 == len ? "carrier" : (3 == len ? "submarine" : "Unknown ship type"));
		this.orientation = or;
		
	};
	
	@Override
	public String toString() {
		return String.format("Name: %s\nLeft corner = %d, top corner = %d,  length = %d", name, x, y, len);
	}
	
	public String getCoordsFormatted() {
	
		
		return String.format("%s found: (%d,%d) to (%d,%d)",this.name, x, y, (orientation == Orientations.Horizontal ? x+len-1 : x),
															(orientation == Orientations.Vertical ? y+len-1 : y));
	}
	
}
