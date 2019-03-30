package nl.han.ica.oopd.labyrint.enemies;

import nl.han.ica.oopd.labyrint.Labyrint;
import nl.han.ica.oopd.labyrint.Player;
import nl.han.ica.oopd.labyrint.enemies.spells.FireSpel;
import nl.han.ica.oopd.labyrint.utils.FolderLocationsUtils;
import nl.han.ica.oopg.objects.Sprite;

public class Wizard extends BaseEnemy {

	private Labyrint world;

	public Wizard(Labyrint world) {
		super(world, new Sprite(FolderLocationsUtils.ENEMIES_FOLDER + "wizard.png"), 2);
		this.world = world;
	}

	@Override
	public void attack() {
		FireSpel fireSpel = new FireSpel(world, getDirection());
		final int fireSpelSpriteSize = fireSpel.getSpriteSize();

		float xPos = getX();
		float yPos = getY();
		if (getDirection() == Player.NORTH || getDirection() == Player.SOUTH) {
			xPos += (((spriteSize - fireSpelSpriteSize) / 2.0f));
		} else if (getDirection() == Player.EAST || getDirection() == Player.WEST) {
			yPos += ((spriteSize - fireSpelSpriteSize) / 2.0f);
		}

		world.addGameObject(fireSpel, xPos + 1, yPos);
	}

}
