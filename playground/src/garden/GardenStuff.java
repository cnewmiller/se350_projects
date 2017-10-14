package garden;

import javafx.geometry.Point2D;

public interface GardenStuff {
	public void move (double dx, double dy);
	public boolean ContainsPoint(Point2D point);
}
