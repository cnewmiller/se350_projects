package ccGameFinal.junitTests;

import org.junit.Test;

import ccGameFinal.OceanExplorer;
import ccGameFinal.OceanMap;
import ccGameFinal.UtilityClasses.Point;


/**
 * Tests the singleton-ness (enforces that the reference does not change) and the random generation
 * 
 * @author Clay
 *
 */
public class OceanMapTest {

	@Test
	public void singletonTest() {
		OceanMap m = OceanMap.getInstance();
		OceanMap n = OceanMap.getInstance();
		assert(m == n);
		OceanMap.setIslands(15);
		assert(m == n);
	}
	@Test
	public void sizeTest() {
		OceanMap m = OceanMap.getInstance();
		assert(m != null);
		
		boolean[][] b = m.getMap();
		
		assert(b != null);
		
		assert(b.length == OceanExplorer.dimension);
		assert(b[0].length == OceanExplorer.dimension);
		
	}
	@Test
	public void islandsTest() {
		OceanMap.setIslands(15);
		OceanMap m = OceanMap.getInstance();
		assert(m != null);
		boolean[][] b = m.getMap();
		assert(b != null);
		
		int islandCount = 0;
		for (int i = 0 ; i<OceanExplorer.dimension ; i++) {
			for (int j = 0 ; j<OceanExplorer.dimension ; j++) {
				if (b[i][j] == OceanMap.TAKENSQUARE) {
					islandCount++;
				}
			}
		}
		
		assert(islandCount == 15);
		
		OceanMap.setIslands(3);
		
		islandCount = 0;
		for (int i = 0 ; i<OceanExplorer.dimension ; i++) {
			for (int j = 0 ; j<OceanExplorer.dimension ; j++) {
				if (b[i][j] == OceanMap.TAKENSQUARE) {
					islandCount++;
				}
			}
		}
		
		assert(islandCount == 3);
		
	}
	public void openSquareTest() {//this is nondeterministic, but whatever
		OceanMap m = OceanMap.getInstance();
		boolean b[][] = m.getMap();
		for (int i = 0 ; i<OceanExplorer.dimension ; i++) {
			for (int j = 0 ; j<OceanExplorer.dimension ; j++) {
				Point p = m.getOpenSquare();
				assert(b[p.getX()][p.getY()] == OceanMap.OPENSQUARE);
			}
		}
		
		
	}
	

}
