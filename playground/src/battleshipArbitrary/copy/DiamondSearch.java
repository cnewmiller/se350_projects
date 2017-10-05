package battleshipArbitrary.copy;


public class DiamondSearch extends SearchStrategy {

	private int numChecked = 0;
	
	@Override
	public Ship[] doSearch(int[][] grid, int numOfShips) {
		
		this.foggedGrid = new int[grid.length][grid[0].length];
		for (int y = 0; y < foggedGrid.length ; y++) {
			for (int x = 0 ; x < foggedGrid[0].length ; x++) {
				foggedGrid[x][y] = 0;
			}
		}
		

		this.numChecked=0;
		Ship[] ret = new Ship[numOfShips];
		int shipIndex = 0;
		int[] target = new int[numOfShips];
		for (int i = 0 ; i < target.length ; target[i++] = 0);
		
		for (int tracker = 0 ; tracker < 25*25 ; tracker +=2 ) {
			int x = tracker%25, y = tracker / 25;
			if (this.foggedGrid[x][y] == 0) {
				numChecked++;
			}
			if (grid[x][y] != 0 && target[grid[x][y]-1] == 0) {
				target[grid[x][y]-1]++;
				ret[shipIndex++] = this.propagate(x, y, grid, grid[x][y]);
				if (shipIndex == numOfShips) {
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
