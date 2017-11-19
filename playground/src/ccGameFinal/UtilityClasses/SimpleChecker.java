package ccGameFinal.UtilityClasses;

import ccGameFinal.OceanMap;
import ccGameFinal.Interfaces.GridChecker;

/**
 * The simple common functionality of ship movement.
 * Checks points in contact with the given Point against the given grid. No special checks or conditions.
 * 
 * @author Clay
 *
 */
public class SimpleChecker implements GridChecker {

	@Override
	public boolean checkLeft(Point p, boolean[][] grid) {
		int xPos = p.getX(), yPos = p.getY();
		if (xPos > 0) {
			
			if (grid[xPos-1][yPos] == OceanMap.OPENSQUARE) {
				return true;
			}
		}
		return false;
	}
	@Override
	public  boolean checkRight(Point p, boolean[][] grid) {
		int xPos = p.getX(), yPos = p.getY();
		if (xPos < grid.length-1) {
			if (grid[xPos+1][yPos] == OceanMap.OPENSQUARE) {
				return true;
			}
		}
		return false;
	}
	@Override
	public  boolean checkUp(Point p, boolean[][] grid) {
		int xPos = p.getX(), yPos = p.getY();
		if (yPos > 0) {
			if (grid[xPos][yPos-1] == OceanMap.OPENSQUARE ) {
				return true;
			}
		}
		return false;
	}
	@Override
	public  boolean checkDown(Point p, boolean[][] grid) {
		int xPos = p.getX(), yPos = p.getY();
		if (yPos < grid[0].length-1) {
			
			if (grid[xPos][yPos+1] == OceanMap.OPENSQUARE ) {
				return true;
			}

		}
		return false;
	}

}
