package ccGameFinal.UtilityClasses;

import ccGameFinal.ColumbusShip;
import ccGameFinal.PortRoyale;
import ccGameFinal.Treasure;
import ccGameFinal.Interfaces.Collidable;
import ccGameFinal.Interfaces.CollisionFunction;

/**
 * Function class to allow the port to collect the treasure from the ColumbusShip.
 * 
 * @author Clay
 *
 */
public class PortCollectScore implements CollisionFunction {

	@Override
	public void doCollision(Collidable self, Collidable other) {
		
		if ((other instanceof ColumbusShip) && (self instanceof PortRoyale)) {
			ColumbusShip ship = (ColumbusShip) other;
			PortRoyale port = (PortRoyale) self;
			Treasure t = ship.getBooty();
			if (t != null) {
				port.totalScore +=  t.cashOut();
				t.clear();
				ship.getTreasureLabel().setText("Cashed out at the port. Cha-ching! $$$");
			}
		}
	}

}
