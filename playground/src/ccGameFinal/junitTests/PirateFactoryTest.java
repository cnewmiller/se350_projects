package ccGameFinal.junitTests;


import org.junit.Test;

import ccGameFinal.OceanMap;
import ccGameFinal.UtilityClasses.AStarSearch;
import ccGameFinal.UtilityClasses.BreadthLimitedSearch;
import ccGameFinal.UtilityClasses.PirateFactory;
import ccGameFinal.UtilityClasses.Point;

/**
 * Tests the factory and its parameters. Unfortunately, I cannot test the pirate class that's returned without weird exceptions from JavaFX,
 * 	and that was a low priority.
 * 
 * @author Clay
 *
 */
public class PirateFactoryTest {

	@Test
	public void factoryDefaultTest() {
		PirateFactory pf = new PirateFactory();
		assert(pf.getCreationLocation().equals(new Point(0,0)));
		assert(pf.getImageLocation().equals("ccGameFinal/pirateShip_1.png"));
		assert(pf.getStrategy() instanceof AStarSearch);
	}
	@Test
	public void changePointTest() {
		PirateFactory pf = new PirateFactory();
		Point p = new Point(5,5);
		pf.setCreationLocation(p);
		assert(pf.getCreationLocation().equals(p));
		assert(pf.getImageLocation().equals("ccGameFinal/pirateShip_1.png"));
		assert(pf.getStrategy() instanceof AStarSearch);
	}
	@Test
	public void changeImageTest() {
		PirateFactory pf = new PirateFactory();
		String p = "Some location!";
		pf.setImageLocation(p);
		assert(pf.getCreationLocation().equals(new Point(0,0)));
		assert(pf.getImageLocation().equals(p));
		assert(pf.getStrategy() instanceof AStarSearch);
	}
	@Test
	public void changeStrategyTest() {
		PirateFactory pf = new PirateFactory();
		assert(pf.getCreationLocation().equals(new Point(0,0)));
		assert(pf.getImageLocation().equals("ccGameFinal/pirateShip_1.png"));
		assert(pf.getStrategy() instanceof AStarSearch);
		
		pf.setStrategy(new BreadthLimitedSearch(OceanMap.getInstance().getMap(), 5));
		
		assert(!(pf.getStrategy() instanceof AStarSearch));
		assert(pf.getStrategy() instanceof BreadthLimitedSearch);
	}
	

}
