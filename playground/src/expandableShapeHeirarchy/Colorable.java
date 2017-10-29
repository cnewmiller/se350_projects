package expandableShapeHeirarchy;

import javafx.scene.paint.Paint;


/**
 * 
 * Interface representing any object that can have color.
 * 
 * @author Clay
 *
 */
public interface Colorable {
	/**
	 * Sets this Colorable's color to c
	 * @param c the color to set
	 */
	public void setColor(Paint c);
	/**
	 * Returns this Colorable's color, as a Paint object
	 * @return this object's color
	 */
	public Paint getColor();
	
	/**
	 * 
	 * Resets this Colorable's color to the color it was initialized with
	 */
	public void resetColor();
}
