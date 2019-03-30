package nl.han.ica.oopd.labyrint.enemies;

import nl.han.ica.oopd.labyrint.Labyrint;
import nl.han.ica.oopd.labyrint.Player;
import nl.han.ica.oopd.labyrint.enemies.attacks.Arrow;
import nl.han.ica.oopd.labyrint.utils.FolderLocationsUtils;
import nl.han.ica.oopg.objects.Sprite;

public class Ranger extends BaseEnemy {

	public Ranger(Labyrint world) {
		super(world, new Sprite(FolderLocationsUtils.ENEMIES_FOLDER + "ranger.png"), 1);
	}

	@Override
	protected void attack() {
		Arrow arrow = new Arrow(world, getDirection());
		final int arrowSpriteSize = arrow.getSpriteSize();

		float xPos = getX();
		float yPos = getY();
		if (getDirection() == Player.NORTH || getDirection() == Player.SOUTH) {
			xPos += (((spriteSize - arrowSpriteSize) / 2.0f));
		} else if (getDirection() == Player.EAST || getDirection() == Player.WEST) {
			yPos += ((spriteSize - arrowSpriteSize) / 2.0f);
		}

		world.addGameObject(arrow, xPos + 1, yPos);
	}

}
