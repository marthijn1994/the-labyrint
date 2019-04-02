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

	public BaseEnemy(Labyrint world, Sprite sprite, int totalFrames, float direction) {
		super(sprite, totalFrames);
		this.world = world;
		this.direction = direction;

		castingDelay = (int) world.random(CAST_DELAY_MIN, CAST_DELAY_MAX);
		previousCastTime = System.currentTimeMillis();

		setCurrentFrameIndex(getSpriteIndex(direction));
	}

	@Override
	public void update() {
		executeAttack();
	}
	
	public void handleDamage(Player player) {
		player.setX(getX() - 5);
		player.setY(getY() - 5); 
		player.takeDamage();
	}

	@Override
	public void setCurrentFrameIndex(int currentFrameIndex) {
		if (currentFrameIndex > getTotalFrames() && currentFrameIndex < 0)
			currentFrameIndex = 0;
		super.setCurrentFrameIndex(currentFrameIndex);
	}

	private void executeAttack() {
		final long currentTime = System.currentTimeMillis();
		if (currentTime - previousCastTime >= castingDelay) {
			castingDelay = (int) world.random(CAST_DELAY_MIN, CAST_DELAY_MAX);
			previousCastTime = currentTime;

			attack();
		}
	}

	protected abstract void attack();

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

}
