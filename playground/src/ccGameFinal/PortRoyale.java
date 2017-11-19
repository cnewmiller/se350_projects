package ccGameFinal;

import java.util.HashMap;
import java.util.Map;

import ccGameFinal.Interfaces.Collidable;
import ccGameFinal.Interfaces.CollisionFunction;
import ccGameFinal.Interfaces.GridChecker;
import ccGameFinal.UtilityClasses.IslandFinderChecker;
import ccGameFinal.UtilityClasses.Point;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 * Class for the port on the OceanMap. Spawns next to one and only one island.
 * 
 * 
 * @author Clay
 *
 */
public class PortRoyale implements Collidable {
	
	String imageLocation = "ccGameFinal/images/port.png";
	Point location;
	ImageView image;
	public int totalScore;
	public static Map<Class<?>, CollisionFunction> collisions = new HashMap<>();
	public void addCollisionClass(Class<?> otherClass, CollisionFunction f) {
		collisions.put(otherClass, f);
	}
	public void removeCollisionClass(Class<?> otherClass) {
		collisions.remove(otherClass);
	}
	
	public PortRoyale() {
		totalScore = 0;
		GridChecker checker = new IslandFinderChecker();
		location = OceanMap.getInstance().getOpenSquare();
		OceanMap oceanMap = OceanMap.getInstance();
		boolean[][] grid = oceanMap.getMap();
		//finds a square with one and only one island bordering it
		while( !( checker.checkRight(location, grid) ^
				( checker.checkLeft(location, grid) ^
				( checker.checkUp(location, grid) ^
				  checker.checkDown(location, grid))))) {
			location = oceanMap.getOpenSquare();
		}
		
				
		image = new ImageView(new Image(imageLocation, OceanExplorer.scale, OceanExplorer.scale, true, true));
		image.setX(location.getX()*OceanExplorer.scale);
		image.setY(location.getY()*OceanExplorer.scale);
	}
	
	
	@Override
	public Point getPoint() {
		return location;
	}

	@Override
	public void collideWith(Collidable c) {
		//collide with ship, take treasure, deposit cash in bank
		
		if (collisions.containsKey(c.getClass())) {
			collisions.get(c.getClass()).doCollision(this, c);
		}
		
	}
	
	
	
}
