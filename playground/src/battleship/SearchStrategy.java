package battleship;

import battleship.Ship;


/**
 * 
 * The abstract superclass that represents the "Strategy" in the Strategy design pattern.
 * 
 * Implements much of the common functionality of the individual classes of search.
 * 
 * @author Clay
 *
 */
public abstract class SearchStrategy {
	
	/**
	 * Represents what the searching strategy has found so far.
	 */
	protected int[][] foggedGrid;
	
	
	/**
	 * Allows each search strategy to call super.doSearch() to clear and initialize foggedGrid
	 * @param grid
	 * @return
	 */
	protected Ship[] doSearch(int[][] grid) {
		this.foggedGrid = new int[grid.length][grid[0].length];
		for (int y = 0; y < foggedGrid.length ; y++) {
			for (int x = 0 ; x < foggedGrid[0].length ; x++) {
				foggedGrid[x][y] = 0;
			}
		}
		return null;
	};
	
	
	
	protected int numChecked = 0;
	public int getChecked() {
		return this.numChecked;
	}
	
	public abstract String getName();
	
	/**
	 * Checks to see if the square to the left of the given coordinate has a ship of the target type in it.
	 * Does not increment numChecked if it has already been seen before, like a real game of Battleship keeps track of the pegs.
	 * 
	 * @param xPos
	 * @param yPos
	 * @param grid
	 * @param target
	 * @return
	 */
	private boolean checkLeft(int xPos, int yPos, int[][] grid, int target) {
		if (xPos > 0) {
			if (this.foggedGrid[xPos-1][yPos] == 0) {
				this.numChecked++;
			}
			if (grid[xPos-1][yPos] == target) {
				this.foggedGrid[xPos-1][yPos] = target;
				return true;
			}
			else {
				this.foggedGrid[xPos-1][yPos] = -1;
			}
		}
		return false;
	}
	
	/**
	 * Checks to see if the square to the right of the given coordinate has a ship of the target type in it.
	 * Does not increment numChecked if it has already been seen before, like a real game of Battleship keeps track of the pegs.
	 * 
	 * @param xPos
	 * @param yPos
	 * @param grid
	 * @param target
	 * @return
	 */
	private boolean checkRight(int xPos, int yPos, int[][] grid, int target) {
		if (xPos < 24) {
			if (this.foggedGrid[xPos+1][yPos] == 0) {
				this.numChecked++;
			}
			if (grid[xPos+1][yPos] == target) {
				this.foggedGrid[xPos+1][yPos] = target;
				return true;
			}
			else {
				this.foggedGrid[xPos+1][yPos] = -1;
			}
		}
		return false;
	}
	/**
	 * Checks to see if the square above the given coordinate has a ship of the target type in it.
	 * Does not increment numChecked if it has already been seen before, like a real game of Battleship keeps track of the pegs.
	 * 
	 * @param xPos
	 * @param yPos
	 * @param grid
	 * @param target
	 * @return
	 */
	private boolean checkUp(int xPos, int yPos, int[][] grid, int target) {
		if (yPos > 0) {
			if (this.foggedGrid[xPos][yPos-1] == 0) {
				this.numChecked++;
			}
			if (grid[xPos][yPos-1] == target) {
				this.foggedGrid[xPos][yPos-1] = target;
				return true;
			}
			else {
				this.foggedGrid[xPos][yPos-1] = -1;
			}
		}
		return false;
	}
	/**
	 * Checks to see if the square below the given coordinate has a ship of the target type in it.
	 * Does not increment numChecked if it has already been seen before, like a real game of Battleship keeps track of the pegs.
	 * 
	 * @param xPos
	 * @param yPos
	 * @param grid
	 * @param target
	 * @return
	 */
	private boolean checkDown(int xPos, int yPos, int[][] grid, int target) {
		if (yPos < 24) {
			if (this.foggedGrid[xPos][yPos+1] == 0) {
				this.numChecked++;
			}
			if (grid[xPos][yPos+1] == target) {
				this.foggedGrid[xPos][yPos+1] = target;
				return true;
			}
			else {
				this.foggedGrid[xPos][yPos+1] = -1;
			}
		}
		return false;
	}
	
	/**
	 * Propagates out from the given coordinate to find the rest of the ship.
	 * Continues until the entire ship has been found
	 * Takes advantage of ships only being on one axis at a time (i.e. no ships that are thicker than one row/column)
	 * 
	 * @param x
	 * @param y
	 * @param grid
	 * @param target
	 * @return
	 */
	protected Ship propagate(int x, int y, int[][] grid, int target) {
		
		int top = y, left=x, len = 0;
		
		if (checkLeft(x, y, grid, target) || checkRight(x,y,grid,target)) {
			//check left
			while (checkLeft(left,y,grid,target)) {		
				left--;
			}
			int tracer = left;
			//check right
			while (checkRight(tracer++,y,grid,target)) {
			}
			len = tracer - left;
			return new Ship(left, top, len, Ship.Orientations.Horizontal);
		}
		else if (checkUp(x, y, grid, target) || checkDown(x,y,grid,target)){
			//check up
			while (checkUp(x, top, grid, target)) {				
				top--;
			}
			//check down
			int tracer = top;
			while (checkDown(x,tracer++,grid,target));
			len = (tracer - top);
			return new Ship(left, top, len, Ship.Orientations.Vertical);
		}

		return null;
		
	};
}