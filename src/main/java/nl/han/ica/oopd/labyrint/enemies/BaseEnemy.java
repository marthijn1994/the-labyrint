package nl.han.ica.oopd.labyrint.enemies;

import nl.han.ica.oopd.labyrint.Labyrint;
import nl.han.ica.oopd.labyrint.Player;
import nl.han.ica.oopd.labyrint.tiles.IDamagable;
import nl.han.ica.oopd.labyrint.utils.DirectionUtils;
import nl.han.ica.oopg.objects.AnimatedSpriteObject;
import nl.han.ica.oopg.objects.Sprite;

public abstract class BaseEnemy extends AnimatedSpriteObject implements IDamagable {

	private static final int CAST_DELAY_MIN = 2000;
	private static final int CAST_DELAY_MAX = 3000;

	private long previousCastTime;
	private int castingDelay;

	protected int spriteSize = 50;
	protected Labyrint world;

	public float direction;

	private final int hitDelay = 500;
	private long lastTimeTakenDamage;

	public BaseEnemy(Labyrint world, Sprite sprite, int totalFrames, float direction) {
		super(sprite, totalFrames);
		this.world = world;
		this.direction = direction;

		castingDelay = (int) world.random(CAST_DELAY_MIN, CAST_DELAY_MAX);
		previousCastTime = System.currentTimeMillis();

		setCurrentFrameIndex(getSpriteIndex(direction));
	}

	/**
	 * 
	 */
	@Override
	public void update() {
		executeAttack();
	}

	/**
	 * Zorg ervoor dat de player damage krijgt als hij bots met dit object
	 */
	public void handleDamage(Player player) {
		if (allowedToDoDamage()) {
			player.takeDamage();
			lastTimeTakenDamage = System.currentTimeMillis();
		}
	}

	/**
	 * Bepaal welke sprite dit object moet krijgen op basis van de direction
	 */
	@Override
	public void setCurrentFrameIndex(int currentFrameIndex) {
		if (currentFrameIndex > getTotalFrames() && currentFrameIndex < 0)
			currentFrameIndex = 0;
		super.setCurrentFrameIndex(currentFrameIndex);
	}

	/**
	 * Voer een aanval uit
	 */
	private void executeAttack() {
		final long currentTime = System.currentTimeMillis();
		if (currentTime - previousCastTime >= castingDelay) {
			castingDelay = (int) world.random(CAST_DELAY_MIN, CAST_DELAY_MAX);
			previousCastTime = currentTime;

			attack();
		}
	}

	/**
	 * 
	 */
	protected abstract void attack();

	/**
	 * Pak de juiste sprite uit een png bestand
	 * 
	 * @param direction
	 * @return spriteIndex
	 */
	private int getSpriteIndex(float direction) {
		int spriteIndex = 0;

		switch (getTotalFrames()) {
		case 4:
			if (direction == DirectionUtils.NORTH)
				spriteIndex = 2;
			if (direction == DirectionUtils.EAST)
				spriteIndex = 1;
			if (direction == DirectionUtils.SOUTH)
				spriteIndex = 3;
			if (direction == DirectionUtils.WEST)
				spriteIndex = 0;
			break;
		case 2:
			if (direction == DirectionUtils.WEST)
				spriteIndex = 0;
			if (direction == DirectionUtils.EAST)
				spriteIndex = 1;
			break;
		}

		return spriteIndex;
	}

	public int getSpriteSize() {
		return spriteSize;
	}

	/**
	 * Kijk of dit object al damage mag aanrichten
	 * 
	 * @return canDoDamage
	 */
	private boolean allowedToDoDamage() {
		boolean canDoDamage = true;
		if (System.currentTimeMillis() < lastTimeTakenDamage + hitDelay)
			canDoDamage = false;
		return canDoDamage;
	}

}
