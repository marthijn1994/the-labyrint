package nl.han.ica.oopd.labyrint.enemies;

import nl.han.ica.oopd.labyrint.Labyrint;
import nl.han.ica.oopd.labyrint.Player;
import nl.han.ica.oopd.labyrint.enemies.spells.FireSpel;
import nl.han.ica.oopd.labyrint.utils.FolderLocationsUtils;
import nl.han.ica.oopg.objects.Sprite;

public class Wizard extends BaseEnemy {

	private static final int CAST_DELAY_MIN = 2000;
	private static final int CAST_DELAY_MAX = 3500;
	
	private int spriteSize = 52;
	
	private long previousCastTime;
	private int castingDelay;

	private Labyrint world;

	public Wizard(Labyrint world) {
		super(world, new Sprite(FolderLocationsUtils.ENEMIES_FOLDER + "wizard.png"), 1);
		this.world = world;

		castingDelay = (int) world.random(CAST_DELAY_MIN, CAST_DELAY_MAX);
		previousCastTime = System.currentTimeMillis();
		
		setDirection(Player.WEST);
	}
	
	@Override
	public void attack() {
		if (System.currentTimeMillis() - previousCastTime >= castingDelay) {
			previousCastTime = System.currentTimeMillis();
			
			FireSpel fireSpel = new FireSpel(world, getDirection());
			int fireSpelSpriteSize = fireSpel.getSpriteSize();
			
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
	
	public int getSpriteSize() {
		return spriteSize;
	}

}
