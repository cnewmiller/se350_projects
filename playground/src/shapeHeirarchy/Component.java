package shapeHeirarchy;

import javafx.geometry.Point2D;

public interface Component {
	public void move (double dx, double dy);
	public boolean ContainsPoint(Point2D point);
}
