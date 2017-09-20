package battleshipArbitrary.copy;

import java.util.Random;

public class RandomSearch extends SearchStrategy {

	private int numChecked = 0;
	
	@Override
	public Ship[] doSearch(int[][] grid, int numOfShips) {
		this.numChecked=0;
		Ship[] ret = new Ship[numOfShips];
		Random r = new Random();
		
		
		int[] target = new int[numOfShips];
		for (int i = 0 ; i < target.length ; target[i++] = 0);
		
		int shipIndex = 0;
		
		while (shipIndex < numOfShips) {
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
