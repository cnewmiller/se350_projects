package ccGameFinal.Interfaces;

import ccGameFinal.UtilityClasses.Point;

/**
 * Allows OceanMap to get point histories from moving objects that block the square they're in.
 * 
 * @author Clay
 *
 */
public interface PointHistory {
	public Point getPoint();
	public Point getPrevPoint();
}
