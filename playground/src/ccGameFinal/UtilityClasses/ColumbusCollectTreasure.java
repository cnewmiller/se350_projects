package ccGameFinal.UtilityClasses;

import ccGameFinal.ColumbusShip;
import ccGameFinal.Treasure;
import ccGameFinal.Interfaces.Collidable;
import ccGameFinal.Interfaces.CollisionFunction;


/**
 * Function class for Columbus to collect the treasure.
 * 
 * @author Clay
 *
 */
public class ColumbusCollectTreasure implements CollisionFunction {

	@Override
	public void doCollision(Collidable self, Collidable other) {
		if ((self instanceof ColumbusShip) && (other instanceof Treasure)) {
			ColumbusShip ship = (ColumbusShip) self;
			Treasure t = (Treasure) other;
			Treasure booty = ship.getBooty();
			if (booty == null) {
				ship.setBooty(t);
				t.collect();
				
				booty = t;
			}
			else {
				booty.collect();
			}
			ship.getTreasureLabel().setText(booty.describe());
			
			
		}
		
	}

}
