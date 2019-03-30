package nl.han.ica.oopd.labyrint.enemies;

import nl.han.ica.oopd.labyrint.Labyrint;
import nl.han.ica.oopd.labyrint.enemies.attacks.FireSpel;
import nl.han.ica.oopd.labyrint.utils.Direction;
import nl.han.ica.oopd.labyrint.utils.FolderLocationsUtils;
import nl.han.ica.oopg.objects.Sprite;

public class Wizard extends BaseEnemy {

	public Wizard(Labyrint world, float direction) {
		super(world, new Sprite(FolderLocationsUtils.ENEMIES_FOLDER + "wizard.png"), 2, direction);
	}

	@Override
	public void attack() {
		FireSpel fireSpel = new FireSpel(world, direction);
		final int fireSpelSpriteSize = fireSpel.getSpriteSize();

		float xPos = getX();
		float yPos = getY();
		if (getDirection() == Direction.NORTH || getDirection() == Direction.SOUTH) {
			xPos += (((spriteSize - fireSpelSpriteSize) / 2.0f));
		} else if (getDirection() == Direction.EAST || getDirection() == Direction.WEST) {
			yPos += ((spriteSize - fireSpelSpriteSize) / 2.0f);
		}

		world.addGameObject(fireSpel, xPos, yPos);
	}

}
