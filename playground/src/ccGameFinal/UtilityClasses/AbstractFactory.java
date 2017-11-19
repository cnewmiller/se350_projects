package ccGameFinal.UtilityClasses;

import ccGameFinal.PirateShip;

/**
 * Abstract factory class for factory pattern.
 * @author Clay
 *
 */
public abstract class AbstractFactory {
	public PirateShip getPirate() {
		return createShip();
	}
	
	protected abstract PirateShip createShip();
}
