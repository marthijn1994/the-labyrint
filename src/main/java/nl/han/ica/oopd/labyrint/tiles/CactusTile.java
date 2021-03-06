package nl.han.ica.oopd.labyrint.tiles;

/**
 * @author Marthijn Kip
 * @author Werner van Voorts
 */

import nl.han.ica.oopd.labyrint.Player;
import nl.han.ica.oopg.objects.Sprite;

public class CactusTile extends SolideTile implements IDamagable {

	private final int hitDelay = 500;
	private long lastTimeTakenDamage;

	public CactusTile(Sprite sprite) {
		super(sprite);
		lastTimeTakenDamage = 0;
	}

	/**
	 * Handel de schade af
	 */
	@Override
	public void handleDamage(Player p) {
		if (allowedToDoDamage()) {
			p.takeDamage();
			lastTimeTakenDamage = System.currentTimeMillis();
		}
	}

	/**
	 * Kijk of de tile al damage mag doen
	 * 
	 * @return true/false
	 */
	protected boolean allowedToDoDamage() {
		boolean canDoDamage = true;
		if (System.currentTimeMillis() < lastTimeTakenDamage + hitDelay)
			canDoDamage = false;
		return canDoDamage;
	}

}
