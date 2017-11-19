package ccGameFinal.UtilityClasses;

import ccGameFinal.OceanMap;
import ccGameFinal.Interfaces.GridChecker;

/**
 * A version of the checker that does not consider "off the map" to be an island. Instead, specifically seeks out Island squares.
 * 
 * Although, since they're just booleans, it can also register for ships. This is why the port is created first in the OceanExplorer class.
 * 
 * @author Clay
 *
 */
public class IslandFinderChecker implements GridChecker {

	@Override
	public boolean checkLeft(Point p, boolean[][] grid) {
		int xPos = p.getX(), yPos = p.getY();
		if (xPos > 1) {
			
			if (grid[xPos-1][yPos] == OceanMap.TAKENSQUARE) {
				return true;
			}
		}
		return false;
	}
	@Override
	public  boolean checkRight(Point p, boolean[][] grid) {
		int xPos = p.getX(), yPos = p.getY();
		if (xPos < grid.length-2) {
			if (grid[xPos+1][yPos] == OceanMap.TAKENSQUARE) {
				return true;
			}
		}
		return false;
	}
	@Override
	public  boolean checkUp(Point p, boolean[][] grid) {
		int xPos = p.getX(), yPos = p.getY();
		if (yPos > 1) {
			if (grid[xPos][yPos-1] == OceanMap.TAKENSQUARE ) {
				return true;
			}
		}
		return false;
	}
	@Override
	public  boolean checkDown(Point p, boolean[][] grid) {
		int xPos = p.getX(), yPos = p.getY();
		if (yPos < grid[0].length-2) {
			
			if (grid[xPos][yPos+1] == OceanMap.TAKENSQUARE ) {
				return true;
			}

		}
		return false;
	}

}
