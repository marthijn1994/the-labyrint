package nl.han.ica.oopd.labyrint.enemies.spells;

import java.util.List;

import nl.han.ica.oopd.labyrint.Labyrint;
import nl.han.ica.oopd.labyrint.Player;
import nl.han.ica.oopd.labyrint.tiles.SolideTile;
import nl.han.ica.oopd.labyrint.utils.FolderLocationsUtils;
import nl.han.ica.oopg.collision.CollidedTile;
import nl.han.ica.oopg.collision.CollisionSide;
import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.collision.ICollidableWithTiles;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;

public class FireSpel extends Projectile implements ICollidableWithTiles, ICollidableWithGameObjects {

	private Labyrint world;
	private int spriteSize = 35;

	private int collisionDetectionDelay = 25;
	private long previousCollisionDetection;

	public FireSpel(Labyrint world, float direction) {
		super(new Sprite(FolderLocationsUtils.ENEMIES_FOLDER + "fireball.png"), 1);
		this.world = world;

		setDirection(direction);
		previousCollisionDetection = System.currentTimeMillis();
	}
	
	@Override
	public void fire() {
		final float speed = 2.5f;
		setDirectionSpeed(getDirection(), speed);
	}

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		for (GameObject gameObject : collidedGameObjects) {
			long currentTime = System.currentTimeMillis();
			if (currentTime - previousCollisionDetection >= collisionDetectionDelay) {
				previousCollisionDetection = currentTime;
				if (gameObject instanceof Player) {
					((Player) gameObject).takeDamage();
					world.deleteGameObject(this);
				}
			}
		}
	}

	@Override
	public void tileCollisionOccurred(List<CollidedTile> collidedTiles) {
		for (CollidedTile collidedTile : collidedTiles) {
			long currentTime = System.currentTimeMillis();
			if (currentTime - previousCollisionDetection >= collisionDetectionDelay) {
				previousCollisionDetection = currentTime;
				if (collidedTile.getTile() instanceof SolideTile) {
					CollisionSide collisionSide = collidedTile.getCollisionSide();
					boolean top = CollisionSide.TOP.equals(collisionSide);
					boolean left = CollisionSide.LEFT.equals(collisionSide);
					boolean right = CollisionSide.RIGHT.equals(collisionSide);
					boolean bottom = CollisionSide.BOTTOM.equals(collisionSide);

					if (top) {
						world.deleteGameObject(this);
					} else if (bottom) {
						world.deleteGameObject(this);
					} else if (left) {
						world.deleteGameObject(this);
					} else if (right) {
						world.deleteGameObject(this);
					}
				}
			}
		}
	}

	public int getSpriteSize() {
		return spriteSize;
	}

}
