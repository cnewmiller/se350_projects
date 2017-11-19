package ccGameFinal.UtilityClasses;

import ccGameFinal.OceanMap;
import ccGameFinal.PirateShip;

/**
 * Factory for creating pirates. Allows different strategies and images.
 * 
 * @author Clay
 *
 */
public class PirateFactory extends AbstractFactory{
	
	public static final int numOfPiratePictures = 3;
	
	private Point creationLocation;
	private String imageLocation;
	private SearchStrategy strategy;
	
	
	public Point getCreationLocation() {
		return creationLocation;
	}

	public void setCreationLocation(Point creationLocation) {
		this.creationLocation = creationLocation;
	}

	public String getImageLocation() {
		return imageLocation;
	}

	public void setImageLocation(String imageLocation) {
		this.imageLocation = imageLocation;
	}

	public SearchStrategy getStrategy() {
		return strategy;
	}

	public void setStrategy(SearchStrategy strategy) {
		this.strategy = strategy;
	}

	public PirateFactory() {
		this.creationLocation = new Point(); //default 0,0
		this.imageLocation = "ccGameFinal/pirateShip_1.png"; //default image
		this.strategy = new AStarSearch(OceanMap.getInstance().getMap()); //default search
	}
		
	@Override
	protected PirateShip createShip() {
		PirateShip pirate = new PirateShip(creationLocation, strategy);
		pirate.setImage(imageLocation);
		OceanMap.getInstance().getMap()[creationLocation.getX()][creationLocation.getY()] = true;
		
		return pirate;
	}
	
}
