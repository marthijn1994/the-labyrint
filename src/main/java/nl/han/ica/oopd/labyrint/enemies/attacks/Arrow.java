package nl.han.ica.oopd.labyrint.enemies.attacks;

import nl.han.ica.oopd.labyrint.Labyrint;
import nl.han.ica.oopd.labyrint.utils.FolderLocationsUtils;
import nl.han.ica.oopg.objects.Sprite;

public class Arrow extends Projectile {

	private int spriteSize = 35;

	public Arrow(Labyrint world, float direction) {
		super(world, new Sprite(FolderLocationsUtils.ENEMIES_FOLDER + "arrow.png"), 2, direction);
	}

	@Override
	protected void fire() {
		final float speed = 2.5f;
		setDirectionSpeed(direction, speed);		
	}

	public int getSpriteSize() {
		return spriteSize;
	}

}
