package battleship;

import java.util.Random;

public class RandomSearch extends SearchStrategy {

	private int numChecked = 0;
	
	@Override
	public Ship[] doSearch(int[][] grid) {
		this.numChecked=0;
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
	public int getChecked() {
		return this.numChecked;
	}
	
	@Override
	public String getName() {
		return "Random Search";
	}
}
