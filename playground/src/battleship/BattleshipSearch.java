package battleship;

import java.io.BufferedReader;
import java.io.FileReader;


/**
 * The "Client" in the Strategy design pattern.
 * 
 * @author Clay
 *
 */
public class BattleshipSearch {
	
	private int[][] battleGrid = new int[25][25];
	private SearchStrategy strat = new HorizontalSearch();
	private Ship[] foundShips = new Ship[2];
	
	
	/**
	 * Sets the search strategy of the instance of BattleshipSearch
	 * @param s
	 */
	public void setStrategy(SearchStrategy s) {
		this.strat = s;
	}
	/**
	 * returns the currently associated SearchStrategy held by this object
	 * @return
	 */
	public SearchStrategy getStrategy() {
		return this.strat;
	}
	/**
	 * Accepts a String object representing a single game of Battleship, following the rules given in the assignment sheet.
	 * Parses it and puts the parsed ships into this instance's battleGrid member.
	 * No return type.
	 * Known problems: Parses more than two ships, but cannot parse two ships touching each other, or sharing a starting row/column with the previous ship's ending point.
	 * 
	 * @param input
	 */
	public void buildGrid(String input) { //this is a janky parse, but it works for now
		
		String[] split = input.split("[\\D|\\s]+");			//This leaves a split space at the front, meh whatever
		int prevCoordX=-1, prevCoordY=-1, shipNo = 0;
		
		for (int i = 1; i< split.length ; i+=2) {
			int coord1 = Integer.parseInt(split[i]);
			int coord2 = Integer.parseInt(split[i+1]);
			if (coord1 != prevCoordX && coord2 != prevCoordY) {
				shipNo++;
			}
			battleGrid[coord1][coord2] = shipNo;
			prevCoordX = coord1;
			prevCoordY = coord2;
			
		}
		
	}
	
	/**
	 * Runs the currently set SearchStrategy, puts the resulting ships into the current instance's foundShips field.
	 */
	public void findShips() {
		this.foundShips = strat.doSearch(this.battleGrid);
	}
	
	public static void main(String[] args) {
		
		try{
			BufferedReader input= new BufferedReader(new FileReader("input.txt"));
			
			for (int i = 1; i < 4 ; i++) {
				
				BattleshipSearch b = new BattleshipSearch();
				b.buildGrid(input.readLine());
				System.out.printf("\nGame %d\n", i);
								
				b.setStrategy(new HorizontalSearch());
				b.findShips();
				System.out.printf("Strategy: %s \n", b.getStrategy().getName());
				System.out.printf("Number of cells searched: %d\n", b.getStrategy().getChecked());
				System.out.printf("%s %s\n", b.foundShips[0].getCoordsFormatted(), b.foundShips[1].getCoordsFormatted());
				
				b.setStrategy(new RandomSearch());
				b.findShips();
				System.out.printf("Strategy: %s \n", b.getStrategy().getName());
				System.out.printf("Number of cells searched: %d\n", b.getStrategy().getChecked());
				System.out.printf("%s %s\n", b.foundShips[0].getCoordsFormatted(), b.foundShips[1].getCoordsFormatted());
				
				b.setStrategy(new DiamondSearch());
				b.findShips();
				System.out.printf("Strategy: %s \n", b.getStrategy().getName());
				System.out.printf("Number of cells searched: %d\n", b.getStrategy().getChecked());
				System.out.printf("%s %s\n", b.foundShips[0].getCoordsFormatted(), b.foundShips[1].getCoordsFormatted());
				
			}
			
			 
			 
			input.close();
		}
		catch(java.io.FileNotFoundException e) {
			System.out.println("Please place the input.txt file in the working directory.");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

}
