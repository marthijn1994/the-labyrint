package nl.han.ica.oopd.labyrint.enemies;

import nl.han.ica.oopd.labyrint.Labyrint;
import nl.han.ica.oopd.labyrint.enemies.attacks.Arrow;
import nl.han.ica.oopd.labyrint.utils.FolderLocationsUtils;
import nl.han.ica.oopg.objects.Sprite;

public class Ranger extends BaseEnemy {

	public Ranger(Labyrint world, float direction) {
		super(world, new Sprite(FolderLocationsUtils.ENEMIES_FOLDER + "ranger.png"), 1, direction);
	}

	@Override
	protected void attack() {
		world.addGameObject(new Arrow(world, direction), getX(), getY());
	}

}
