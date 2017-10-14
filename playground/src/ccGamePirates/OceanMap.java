package ccGamePirates;

import java.util.Observable;
import java.util.Observer;
import java.util.Random;

public class OceanMap implements Observer {

	public static boolean OPENSQUARE = false, TAKENSQUARE = true;
	
	//false is open ocean, true is island
	private boolean[][] map = new boolean [OceanExplorer.dimension][OceanExplorer.dimension];
	public boolean[][] getMap () {return this.map;}
	
	public Point getOpenSquare(Random r) {
		
		Point p = new Point(r.nextInt(OceanExplorer.dimension), r.nextInt(OceanExplorer.dimension));
		while(this.map[p.getX()][p.getY()]) {
			p = new Point(r.nextInt(OceanExplorer.dimension), r.nextInt(OceanExplorer.dimension));
		}
		
		return p;
	}
	
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
				map[rX][rY] = TAKENSQUARE;
			}
			
		}
	}
	
	@Override
	public void update(Observable o, Object arg) {
		
		if (arg instanceof Point && o instanceof PointHistory) {
			Point newLocation = (Point) arg;
			PointHistory prev = (PointHistory) o;
			Point prevLocation = prev.getPrevPoint();
			
			map[prevLocation.getX()][prevLocation.getY()] = false;
			map[newLocation.getX()][newLocation.getY()] = true;
			
		}
	}

	
	
}
