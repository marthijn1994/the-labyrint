package nl.han.ica.oopd.labyrint.enemies;

import nl.han.ica.oopd.labyrint.Labyrint;
import nl.han.ica.oopd.labyrint.enemies.spells.FireSpel;
import nl.han.ica.oopd.labyrint.enemies.spells.ICastSpells;
import nl.han.ica.oopd.labyrint.utils.FolderLocationsUtils;
import nl.han.ica.oopg.objects.Sprite;

public class Wizard extends BaseEnemy implements ICastSpells {

	private static final int DELAY_MIN = 2000;
	private static final int DELAY_MAX = 3500;

	private long previousCastTime;
	private int castingDelay;

	private Labyrint world;

	public Wizard(Labyrint world) {
		super(world, new Sprite(FolderLocationsUtils.ENEMIES_FOLDER + "wizard.png"), 1);

		this.world = world;

		castingDelay = (int) world.random(DELAY_MIN, DELAY_MAX);
		previousCastTime = System.currentTimeMillis();
	}

	@Override
	public void update() {
		castSpell();
	}

	@Override
	public void castSpell() {
		if (System.currentTimeMillis() - previousCastTime >= castingDelay) {
			previousCastTime = System.currentTimeMillis();

			world.addGameObject(new FireSpel(world, getDirection()), getX(), getY());
		}
	}

}
