package nl.han.ica.oopd.labyrint.enemies;

import java.util.Random;

import nl.han.ica.oopd.labyrint.Labyrint;
import nl.han.ica.oopd.labyrint.enemies.spells.FireSpel;
import nl.han.ica.oopd.labyrint.utils.FolderLocationsUtils;
import nl.han.ica.oopg.objects.Sprite;

public class Wizard extends BaseEnemy implements ICastSpells {
	
	private long previousCastTime;
	private int castingDelay;
	
	private Labyrint world;
	private Random random;

	public Wizard(Labyrint world) {
		super(world, new Sprite(FolderLocationsUtils.ENEMIES_FOLDER), 1);
		
		this.world = world;
		random = new Random();
		
		castingDelay = random.nextInt(3000 - 1500) + 1500;
		previousCastTime = System.currentTimeMillis();
	}

	@Override
	public void castSpell() {
		if (System.currentTimeMillis() - previousCastTime >= castingDelay) {
			previousCastTime = System.currentTimeMillis();
			
			FireSpel fireSpel = new FireSpel();
			world.addGameObject(fireSpel);
			
			fireSpel.castSpel(getX(), getY());
		}
	}

}
