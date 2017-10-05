package battleshipArbitrary.copy;

import java.io.BufferedReader;
import java.io.FileReader;

public class BattleshipSearch {
	
	private int[][] battleGrid = new int[25][25];
	private SearchStrategy strat = new HorizontalSearch();
	private Ship[] foundShips = new Ship[2];
	private int numOfShips;
	
	public void setStrategy(SearchStrategy s) {
		this.strat = s;
	}
	public SearchStrategy getStrategy() {
		return this.strat;
	}
	
	public void buildGrid(String input) { //uggggh so janky
		
		String[] split = input.split("[\\D|\\s]+");			//fix this, it leaves a split space at the front
		int prevCoordX=-1, prevCoordY=-1;
		this.numOfShips = 0;
		
		for (int i = 1; i< split.length ; i+=2) {
			int coord1 = Integer.parseInt(split[i]);
			int coord2 = Integer.parseInt(split[i+1]);
			if (coord1 != prevCoordX && coord2 != prevCoordY) {
				numOfShips++;
			}
			battleGrid[coord1][coord2] = numOfShips;
			prevCoordX = coord1;
			prevCoordY = coord2;
			
		}
		
	}
	public void printGrid() {
		for (int i = 0; i < battleGrid.length ; i++) {
			for (int j = 0 ; j < battleGrid[0].length ; j++) {
				System.out.print(battleGrid[j][i]);
			}
			System.out.println();
		}
	}
	
	public void findShips() {
		this.foundShips = strat.doSearch(this.battleGrid, numOfShips);
	}
	
	public static void main(String[] args) {
		
		try{
//			String game = "(0,0)(0,1)(0,2)(0,3)(5,5)(5,6)(5,7)(12,8)(13,8)(14,8)(15,8)(16,8)";
			String game = "(0,0)(0,1)(0,2)(0,3)(0,4)(4,15)(4,16)(4,17)";
			for (int i = 1; i < 2 ; i++) {
				System.out.printf("\n");
				BattleshipSearch b = new BattleshipSearch();
				b.buildGrid(game);
				b.printGrid();
				System.out.printf("\nGame %d\n", i);
				
				b.setStrategy(new HorizontalSearch());
				b.findShips();
				System.out.printf("Strategy: %s \n", b.getStrategy().getName());
				System.out.printf("Number of cells searched: %d\n", b.getStrategy().getChecked());
				for(int j = 0 ; j< b.foundShips.length ; j++) {
					System.out.printf("%s\n", b.foundShips[j].getCoordsFormatted());
				}
				
				
				
				b.setStrategy(new RandomSearch());
				b.findShips();
				System.out.printf("\nStrategy: %s \n", b.getStrategy().getName());
				System.out.printf("Number of cells searched: %d\n", b.getStrategy().getChecked());
				for(int j = 0 ; j< b.foundShips.length ; j++) {
					System.out.printf("%s\n", b.foundShips[j].getCoordsFormatted());
				}
				
				b.setStrategy(new DiamondSearch());
				b.findShips();
				System.out.printf("\nStrategy: %s \n", b.getStrategy().getName());
				System.out.printf("Number of cells searched: %d\n", b.getStrategy().getChecked());
				for(int j = 0 ; j< b.foundShips.length ; j++) {
					System.out.printf("%s\n", b.foundShips[j].getCoordsFormatted());
				}
//				
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		//(0,0)(0,1)(0,2)(0,3)(0,4)(4,15)(4,16)(4,17)
		//(5,9)(5,10)(5,11)(5,12)(5,13)(20,5)(20,6)(20,7)
		//(15,3)(16,3)(17,3)(18,3)(19,3)(24,6)(24,7)(24,8)
	}

}
