package nl.han.ica.oopd.labyrint.enemies;

import nl.han.ica.oopd.labyrint.Labyrint;
import nl.han.ica.oopd.labyrint.utils.FolderLocationsUtils;
import nl.han.ica.oopg.objects.Sprite;

public class Ranger extends BaseEnemy {

	private static final int CAST_DELAY_MIN = 2000;
	private static final int CAST_DELAY_MAX = 3500;
	
	private int spriteSize = 52;
	
	private long previousCastTime;
	private int castingDelay;

	private Labyrint world;

	public Ranger(Labyrint world) {
		super(world, new Sprite(FolderLocationsUtils.ENEMIES_FOLDER + "ranger.png"), 1);
	}

	@Override
	protected void attack() {
		
	}

}
