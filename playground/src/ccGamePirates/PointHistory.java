package ccGamePirates;

/**
 * Allows OceanMap to get point histories from moving objects that block the square they're in.
 * 
 * @author Clay
 *
 */
public interface PointHistory {
	public Point getPrevPoint();
}
