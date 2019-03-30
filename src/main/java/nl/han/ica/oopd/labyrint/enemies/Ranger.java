package nl.han.ica.oopd.labyrint.enemies;

import nl.han.ica.oopd.labyrint.Labyrint;
import nl.han.ica.oopd.labyrint.enemies.attacks.Arrow;
import nl.han.ica.oopd.labyrint.utils.Direction;
import nl.han.ica.oopd.labyrint.utils.FolderLocationsUtils;
import nl.han.ica.oopg.objects.Sprite;

public class Ranger extends BaseEnemy {

	public Ranger(Labyrint world, float direction) {
		super(world, new Sprite(FolderLocationsUtils.ENEMIES_FOLDER + "ranger.png"), 1, direction);
	}

	@Override
	protected void attack() {
		Arrow arrow = new Arrow(world, direction);
		final int arrowSpriteSize = arrow.getSpriteSize();

		float xPos = getX();
		float yPos = getY();
		if (getDirection() == Direction.NORTH || getDirection() == Direction.SOUTH) {
			xPos += (((spriteSize - arrowSpriteSize) / 2.0f));
		} else if (getDirection() == Direction.EAST || getDirection() == Direction.WEST) {
			yPos += ((spriteSize - arrowSpriteSize) / 2.0f);
		}

		world.addGameObject(arrow, xPos + 1, yPos);
	}

}
