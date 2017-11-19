package ccGameFinal;

import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import ccGameFinal.Interfaces.PointHistory;
import ccGameFinal.UtilityClasses.Point;


/**
 * 
 * Singleton class to make the map accessible from anywhere. Resetting retains the same instance reference but clears the data.
 * 
 * @author Clay
 *
 */
public class OceanMap implements Observer {

	public static boolean OPENSQUARE = false, TAKENSQUARE = true;
	private static OceanMap instance = null;
	private boolean[][] map = new boolean [OceanExplorer.dimension][OceanExplorer.dimension];
	public boolean[][] getMap () {return this.map;}
	
	public Point getOpenSquare() {
		Point p = new Point(OceanExplorer.r.nextInt(OceanExplorer.dimension), OceanExplorer.r.nextInt(OceanExplorer.dimension));
		while(this.map[p.getX()][p.getY()]) {
			p.setX(OceanExplorer.r.nextInt(OceanExplorer.dimension));
			p.setY(OceanExplorer.r.nextInt(OceanExplorer.dimension));
		}
		return p;
	}
	
	private static int numOfIslands = 0;
	private void reset() {
		for (int i = 0 ; i<OceanExplorer.dimension ; i++) {
			for (int j = 0 ; j<OceanExplorer.dimension ; j++) {
				map[i][j] = false;
			}
		}
		
		for (int i = 0 ; i < numOfIslands ; i++) {
			int rX =OceanExplorer.r.nextInt(OceanExplorer.dimension), rY = OceanExplorer.r.nextInt(OceanExplorer.dimension);
			if (map[rX][rY]) { //there's an island already
				i--;
			}
			else {
				map[rX][rY] = TAKENSQUARE;
			}
		}
	}
	
	private OceanMap() {
		this.reset();
	}
	public static OceanMap getInstance() {
		if (instance == null) {
			instance = new OceanMap();
		}
		return instance;
	}
	public static void setIslands(int isles) {
		numOfIslands = isles;
		getInstance().reset();
	}
	
	@Override
	public void update(Observable o, Object arg) {
		
		if (o instanceof PointHistory) {
			PointHistory prev = (PointHistory) o;
			Point newLocation = prev.getPoint();
			Point prevLocation = prev.getPrevPoint();
			map[prevLocation.getX()][prevLocation.getY()] = OPENSQUARE;
			map[newLocation.getX()][newLocation.getY()] = TAKENSQUARE	;			
			
		}
	}

	
	
}
