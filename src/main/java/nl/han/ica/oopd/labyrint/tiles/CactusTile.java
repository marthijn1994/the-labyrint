package nl.han.ica.oopd.labyrint.tiles;

/**
 * @author Marthijn Kip
 * @author Werner van Voorts
 */

import nl.han.ica.oopd.labyrint.Player;
import nl.han.ica.oopg.objects.Sprite;

public class CactusTile extends SolideTile implements IDamagable {

	protected final int lengteOnschadelijk = 500;
	protected long laatsteKeerSchade;

	public CactusTile(Sprite sprite) {
		super(sprite);
		laatsteKeerSchade = 0;
	}

	@Override
	public void handelSchade(Player p) {
		if (magSchadeDoen()) {
			p.takeDamage();
			laatsteKeerSchade = System.currentTimeMillis();
		}
	}

	protected boolean magSchadeDoen() {
		boolean magSchadeDoen = true;
		if (System.currentTimeMillis() < laatsteKeerSchade + lengteOnschadelijk)
			magSchadeDoen = false;
		return magSchadeDoen;
	}

}
