package nl.han.ica.oopd.labyrint.enemies.attacks;

import java.util.List;

import nl.han.ica.oopd.labyrint.Labyrint;
import nl.han.ica.oopd.labyrint.Player;
import nl.han.ica.oopd.labyrint.tiles.SolideTile;
import nl.han.ica.oopg.collision.CollidedTile;
import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.collision.ICollidableWithTiles;
import nl.han.ica.oopg.objects.AnimatedSpriteObject;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;

public abstract class Projectile extends AnimatedSpriteObject implements ICollidableWithTiles, ICollidableWithGameObjects {

	private Labyrint world;
	
	private int collisionDetectionDelay = 25;
	private long previousCollisionDetection;

	public Projectile(Labyrint world, Sprite sprite, int totalFrames) {
		super(sprite, totalFrames);
		this.world = world;
		
		previousCollisionDetection = System.currentTimeMillis();
	}
	
	@Override
	public void update() {
		fire();		
	}
	
	protected abstract void fire();

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
					world.deleteGameObject(this);
				}
			}
		}
	}

}
