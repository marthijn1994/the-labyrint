package nl.han.ica.oopd.labyrint.enemies.spells;

import nl.han.ica.oopd.labyrint.Labyrint;
import nl.han.ica.oopd.labyrint.utils.FolderLocationsUtils;
import nl.han.ica.oopg.objects.Sprite;

public class FireSpel extends Projectile {

	private int spriteSize = 35;

	public FireSpel(Labyrint world, float direction) {
		super(world, new Sprite(FolderLocationsUtils.ENEMIES_FOLDER + "fireball.png"), 1);

		setDirection(direction);
	}
	
	@Override
	public void fire() {
		final float speed = 2.5f;
		setDirectionSpeed(getDirection(), speed);
	}

	public int getSpriteSize() {
		return spriteSize;
	}

}
