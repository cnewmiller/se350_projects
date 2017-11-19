package ccGameFinal.UtilityClasses;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import ccGameFinal.Interfaces.Collidable;


/**
 * Singleton checker for collisions (singleton is to enforce that there's ever only one list of collisions being checked).
 * 
 * Honestly this is horribly inefficient, and it only works because I only add a couple things to the collisions list. But hey, this wasn't game dev.
 * 
 * @author Clay
 *
 */
public class CollisionChecker implements Observer {
	private static CollisionChecker instance;
	
	private ArrayList<Collidable> objects;
	
	private CollisionChecker() {
		objects = new ArrayList<>();
	}
	
	public static CollisionChecker getInstance() {
		return (instance == null ? instance = new CollisionChecker() : instance);
	}
	public void addChild(Collidable c) {
		objects.add(c);
	}
	public void removeChild(Collidable c) {
		objects.remove(c);
	}

	public void reset() {
		objects.clear();
	}
	
	@Override
	public void update(Observable o, Object arg) {

		if (o instanceof Collidable) {
			
			Collidable c = (Collidable) o;
			Point p = c.getPoint();
			for(Collidable other: objects) {
				if (other != c && p.equals(other.getPoint())) {
					c.collideWith(other);
					other.collideWith(c);
				}
			}
		}

	}
	
}
