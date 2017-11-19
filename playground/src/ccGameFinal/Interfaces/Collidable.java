package ccGameFinal.Interfaces;

import ccGameFinal.UtilityClasses.Point;


/**
 * Interface for anything that can share a point with another object and do something specific when that collision is detected.
 * @author Clay
 *
 */
public interface Collidable {
	public Point getPoint();
	public void collideWith(Collidable c);
	public void addCollisionClass(Class<?> otherClass, CollisionFunction f);
	public void removeCollisionClass(Class<?> otherClass);
	
}
