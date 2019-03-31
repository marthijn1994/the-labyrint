package nl.han.ica.oopd.labyrint.enemies;

import nl.han.ica.oopd.labyrint.Labyrint;
import nl.han.ica.oopd.labyrint.enemies.attacks.FireBall;
import nl.han.ica.oopd.labyrint.utils.FolderLocationsUtils;
import nl.han.ica.oopg.objects.Sprite;

public class Wizard extends BaseEnemy {

	public Wizard(Labyrint world, float direction) {
		super(world, new Sprite(FolderLocationsUtils.ENEMIES_FOLDER + "wizard.png"), 2, direction);
	}

	@Override
	public void attack() {
		world.addGameObject(new FireBall(world, direction), getX(), getY());
	}

}
