package nl.han.ica.oopd.labyrint.enemies;

import nl.han.ica.oopd.labyrint.Labyrint;
import nl.han.ica.oopd.labyrint.utils.Direction;
import nl.han.ica.oopg.objects.AnimatedSpriteObject;
import nl.han.ica.oopg.objects.Sprite;

public abstract class BaseEnemy extends AnimatedSpriteObject {

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
		switch ((int) direction) {
			case Direction.NORTH:
			case Direction.EAST:
			case Direction.SOUTH:
				spriteIndex = 1;
				break;
			default:
				spriteIndex = 0;
				break;
		}
		return spriteIndex;
	}

	public int getSpriteSize() {
		return spriteSize;
	}

}
