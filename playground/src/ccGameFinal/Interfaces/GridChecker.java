package ccGameFinal.Interfaces;

import ccGameFinal.UtilityClasses.Point;

/**
 * 
 * Interface for checking grid.
 * 
 * @author Clay
 *
 */
public interface GridChecker {
	public boolean checkUp(Point p, boolean[][] grid);
	public boolean checkDown(Point p, boolean[][] grid);
	public boolean checkLeft(Point p, boolean[][] grid);
	public boolean checkRight(Point p, boolean[][] grid);
}
