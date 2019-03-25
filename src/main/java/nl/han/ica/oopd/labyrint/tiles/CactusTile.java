package nl.han.ica.oopd.labyrint.tiles;

import nl.han.ica.oopd.labyrint.ISchadelijk;
import nl.han.ica.oopd.labyrint.Player;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.tile.Tile;

public class CactusTile extends Tile implements ISchadelijk {
	protected final int lengteOnschadelijk = 500;
	protected long laatsteKeerSchade;

	public CactusTile(Sprite sprite) {
		super(sprite);
		laatsteKeerSchade = 0;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void handelSchade(Player p) {
		if (magSchadeDoen()) {
			p.neemSchade();
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
