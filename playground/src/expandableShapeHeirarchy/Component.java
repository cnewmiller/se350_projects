package expandableShapeHeirarchy;

import javafx.geometry.Point2D;

/**
 * 
 * Interface representing any component in this hierarchy model: any item that can move, and check for a collision with the mouse. 
 * 
 * @author Clay
 *
 */
public interface Component {
	
	/**
	 * Applies a change to the Component's position 
	 * 
	 * @param dx The change in the x coordinate to apply (horizontal)
	 * @param dy The change in the y coordinate to apply (vertical)
	 */
	public void move (double dx, double dy);
	
	/**
	 * Checks to see if the parameter Point2D collides with this Component's shape.
	 * @param point The point to check for collision
	 * @return true if the given point touches this Component or its fill, false otherwise 
	 */
	public boolean ContainsPoint(Point2D point);
}
