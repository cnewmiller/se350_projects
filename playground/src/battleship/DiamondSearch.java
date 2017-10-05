package battleship;

/**
 * Implements SearchStrategy to do an every-other-third square search.
 * No ships in the specified game rules are less than 3 squares long, so this is guaranteed to find both ships in one third the time.
 * @author Clay
 *
 */
public class DiamondSearch extends SearchStrategy {
	
	@Override
	public Ship[] doSearch(int[][] grid) {
		
		super.doSearch(grid);
		
		Ship[] ret = new Ship[2];
		int[] target = {0, 0};
		int shipIndex = 0;
		
		for (int tracker = 0 ; tracker < 25*25 ; tracker +=2 ) {
			int x = tracker%25, y = tracker / 25;
			numChecked++;
			if (grid[x][y] != 0 && target[grid[x][y]-1] == 0) {
				target[grid[x][y]-1]++;
				ret[shipIndex++] = this.propagate(x, y, grid, grid[x][y]);
				if (1 == target[0] && 1 == target[1]) {
					return ret;
				}
			}
		}
		
		return ret;
	}


	@Override
	public String getName() {
		return "Diamond Search";
	}

}
