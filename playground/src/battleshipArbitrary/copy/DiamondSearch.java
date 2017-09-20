package battleshipArbitrary.copy;


public class DiamondSearch extends SearchStrategy {

	private int numChecked = 0;
	
	@Override
	public Ship[] doSearch(int[][] grid, int numOfShips) {
		// TODO Auto-generated method stub
		this.numChecked=0;
		Ship[] ret = new Ship[numOfShips];
		int shipIndex = 0;
		int[] target = new int[numOfShips];
		for (int i = 0 ; i < target.length ; target[i++] = 0);
		
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
		
		return null;
	}

	@Override
	public int getChecked() {
		// TODO Auto-generated method stub
		return numChecked;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Diamond Search";
	}

}
