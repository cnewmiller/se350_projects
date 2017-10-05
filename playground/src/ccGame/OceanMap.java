package ccGame;

import java.util.Random;

import javafx.application.*;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.*;


public class OceanMap {

	private boolean[][] map = new boolean [OceanExplorer.dimension][OceanExplorer.dimension];
	public boolean[][] getMap () {return this.map;}
	
	int numOfIslands;
	int chanceOfIslands;
	
	public OceanMap(int islands) {
		this.numOfIslands = islands;
		Random r = new Random();
		for (int i = 0 ; i<OceanExplorer.dimension ; i++) {
			for (int j = 0 ; j<OceanExplorer.dimension ; j++) {
				map[i][j] = false;
			}
		}
		
		for (int i = 0 ; i < numOfIslands ; i++) {
			int rX =r.nextInt(OceanExplorer.dimension), rY = r.nextInt(OceanExplorer.dimension);
			if (map[rX][rY]) { //there's an island already
				i--;
			}
			else {
				map[rX][rY] = true;
			}
			
		}
		
	}
	
	
}
