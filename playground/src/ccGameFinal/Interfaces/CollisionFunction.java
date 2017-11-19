package ccGameFinal.Interfaces;

/**
 * Allows functions to be stored in maps for collision detection.
 * @author Clay
 *
 */
public interface CollisionFunction {
	public void doCollision(Collidable self, Collidable other);
}
