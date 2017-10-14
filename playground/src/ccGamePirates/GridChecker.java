package ccGamePirates;

/**
 * 
 * Interface for checking grid. One idea was to make a PathfindingChecker that also checks for goal states, but it didn't end up being necessary.
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
