package battleship;


/**
 * Implements SearchStrategy.
 * 
 * Runs horizontally along the given grid, propagates out to find the rest of any ship it finds on the way.
 * Returns when two ships are found.
 * @author Clay
 *
 */
public class HorizontalSearch extends SearchStrategy{
	
	@Override
	public Ship[] doSearch(int[][] grid) {
		super.doSearch(grid);
		
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
	public String getName() {
		return "Horizontal Search";
	}

}
