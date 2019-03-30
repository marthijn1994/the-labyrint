package nl.han.ica.oopd.labyrint.enemies;

import java.util.List;

import nl.han.ica.oopd.labyrint.Labyrint;
import nl.han.ica.oopd.labyrint.Player;
import nl.han.ica.oopd.labyrint.tiles.SolideTile;
import nl.han.ica.oopg.collision.CollidedTile;
import nl.han.ica.oopg.collision.CollisionSide;
import nl.han.ica.oopg.collision.ICollidableWithTiles;
import nl.han.ica.oopg.objects.AnimatedSpriteObject;
import nl.han.ica.oopg.objects.Sprite;

public abstract class BaseEnemy extends AnimatedSpriteObject implements ICollidableWithTiles {

	private static final int CAST_DELAY_MIN = 2000;
	private static final int CAST_DELAY_MAX = 3500;

	private long previousCastTime;
	private int castingDelay;

	protected int spriteSize = 52;
	protected Labyrint world;

	public BaseEnemy(Labyrint world, Sprite sprite, int totalFrames) {
		super(sprite, totalFrames);
		this.world = world;

		castingDelay = (int) world.random(CAST_DELAY_MIN, CAST_DELAY_MAX);
		previousCastTime = System.currentTimeMillis();
	}

	@Override
	public void update() {
		executeAttack();
	}

	private void executeAttack() {
		final long currentTime = System.currentTimeMillis();
		if (currentTime - previousCastTime >= castingDelay) {
			previousCastTime = currentTime;
			attack();
		}
	}

	protected abstract void attack();

	@Override
	public void tileCollisionOccurred(List<CollidedTile> collidedTiles) {
		for (CollidedTile collidedTile : collidedTiles) {
			if (collidedTile.getTile() instanceof SolideTile) {
				CollisionSide collisionSide = collidedTile.getCollisionSide();
				boolean top = CollisionSide.TOP.equals(collisionSide);
				boolean left = CollisionSide.LEFT.equals(collisionSide);
				boolean right = CollisionSide.RIGHT.equals(collisionSide);
				boolean bottom = CollisionSide.BOTTOM.equals(collisionSide);

				if (top) {
					setDirection(Player.NORTH);
				} else if (bottom) {
					setDirection(Player.SOUTH);
				} else if (left) {
					setDirection(Player.WEST);
				} else if (right) {
					setDirection(Player.EAST);
				}
			}
		}
	}

	@Override
	public void setCurrentFrameIndex(int currentFrameIndex) {
		if (currentFrameIndex > getTotalFrames() && currentFrameIndex < 0)
			currentFrameIndex = 0;
		super.setCurrentFrameIndex(currentFrameIndex);
	}

	public int getSpriteSize() {
		return spriteSize;
	}

}
