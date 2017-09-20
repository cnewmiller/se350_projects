package battleshipArbitrary.copy;

public class HorizontalSearch extends SearchStrategy{

	private int numChecked = 0;
	
	@Override
	public Ship[] doSearch(int[][] grid, int numOfShips) {
		this.numChecked = 0;
		Ship[] ret = new Ship[numOfShips];
		int shipIndex = 0;
		int[] target = new int[numOfShips];
		for (int i = 0 ; i < target.length ; target[i++] = 0);
		
		for (int y = 0; y < grid.length ; y++) {
			for (int x = 0 ; x < grid[0].length ; x++) {
				numChecked++;
				if (grid[x][y] != 0 && target[grid[x][y]-1] == 0) {
					target[grid[x][y]-1]++;
					ret[shipIndex++] = this.propagate(x, y, grid, grid[x][y]);
					if (shipIndex == numOfShips) {
						return ret;
					}
				}
				
			}
		}
		
		return null;
	}
	
	@Override
	public int getChecked() {
		return this.numChecked;
	}
	@Override
	public String getName() {
		return "Horizontal Search";
	}

}
