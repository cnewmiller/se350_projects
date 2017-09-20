package battleshipArbitrary.copy;


public abstract class SearchStrategy {
		
	public abstract Ship[] doSearch(int[][] grid, int numOfShips);
	
	public abstract int getChecked();
	
	public abstract String getName();
	
	
	//make this so that it's counted in the checked cells count
	protected Ship propagate(int x, int y, int[][] grid, int target) {
		
		int top = y, left=x, len = 0;
		
		if (x < 24 && target == grid[x+1][y] || x>0 && target == grid[x-1][y]) {
			//check left
			int tracer = x;
			while (tracer > 0 && target == grid[--tracer][y]) {				
				left--;
				//tracer--;
			}
			//check down
			while (target == grid[++tracer][y]) {
				len++;
			}
			return new Ship(left, top, len, Ship.Orientations.Horizontal);
		}
		else if (y < 24 && target == grid[x][y+1] || y>0 && target == grid[x][y-1]){
			//check up
			int tracer = y;
			while (tracer > 0 && target == grid[x][tracer-1]) {				
				top--;
				tracer--;
			}
			//check down
			while (target == grid[x][tracer]) {
				len++;
				tracer++;
			}
			return new Ship(left, top, len, Ship.Orientations.Vertical);
		}

		return null;
		
	};
}
