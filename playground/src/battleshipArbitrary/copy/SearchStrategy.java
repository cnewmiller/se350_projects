package battleshipArbitrary.copy;


public abstract class SearchStrategy {
	
	protected int[][] foggedGrid;
	
	public abstract Ship[] doSearch(int[][] grid, int numOfShips);
	
	
	
	protected int numChecked = 0;
	public int getChecked() {
		return this.numChecked;
	}
	
	public abstract String getName();
		
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
	
	
	//the logic is incorrectly incrementing for things i've seen before
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
//				len++;
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
			while (checkDown(x,tracer++,grid,target)) {
//				len++;
			}
//			len += (tracer - y)+1;
			len = (tracer - top);
			return new Ship(left, top, len, Ship.Orientations.Vertical);
		}

		return null;
		
	};
}