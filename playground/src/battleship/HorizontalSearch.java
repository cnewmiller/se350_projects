package battleship;

public class HorizontalSearch extends SearchStrategy{

	private int numChecked = 0;
	
	@Override
	public Ship[] doSearch(int[][] grid) {
		this.numChecked = 0;
		Ship[] ret = new Ship[2];
		int shipIndex = 0;
		int[] target = {0, 0};
		
		for (int y = 0; y < grid.length ; y++) {
			for (int x = 0 ; x < grid[0].length ; x++) {
				numChecked++;
				if (grid[x][y] != 0 && target[grid[x][y]-1] == 0) {
					target[grid[x][y]-1]++;
					ret[shipIndex++] = this.propagate(x, y, grid, grid[x][y]);
					if (1 == target[0] && 1 == target[1]) {
						return ret;
					}
				}
				
			}
		}
		
		return ret;
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
