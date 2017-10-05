package battleship;

import java.util.Random;


/**
 * Implements SearchStrategy.
 * Randomly hits squares in the given grid until two ships are found. Propagates out when it hits one. 
 * 
 * Returns the ships in the order they were found.
 * 
 * @author Clay
 *
 */
public class RandomSearch extends SearchStrategy {

	
	@Override
	public Ship[] doSearch(int[][] grid) {
		super.doSearch(grid);
		
		Ship[] ret = new Ship[2];
		Random r = new Random();
		ret[0] = null;
		ret[1] = null;
		int[] target = {0, 0};
		int shipIndex = 0;
		
		while (null == ret[0] || null == ret[1]) {
			int x = r.nextInt(25), y = r.nextInt(25);
			this.numChecked++;
			if (grid[x][y] != 0 && target[grid[x][y]-1] == 0) {
				target[grid[x][y]-1]++;
				ret[shipIndex++] = this.propagate(x, y, grid, grid[x][y]);
			}			
		}
		return ret;
	}
	
	@Override
	public String getName() {
		return "Random Search";
	}
}
