package nl.han.ica.oopd.labyrint.enemies.attacks;

import java.util.List;

import nl.han.ica.oopd.labyrint.Labyrint;
import nl.han.ica.oopd.labyrint.Player;
import nl.han.ica.oopd.labyrint.tiles.SolideTile;
import nl.han.ica.oopg.collision.CollidedTile;
import nl.han.ica.oopg.collision.CollisionSide;
import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.collision.ICollidableWithTiles;
import nl.han.ica.oopg.objects.AnimatedSpriteObject;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;

public abstract class Projectile extends AnimatedSpriteObject
		implements ICollidableWithTiles, ICollidableWithGameObjects {

	private Labyrint world;

	private int collisionDetectionDelay = 25;
	private long previousCollisionDetection;

	public float direction;

	public Projectile(Labyrint world, Sprite sprite, int totalFrames, float direction) {
		super(sprite, totalFrames);
		this.world = world;
		this.direction = direction;

		previousCollisionDetection = System.currentTimeMillis();
	}

	/**
	 * Beweeg de projectile in de juiste richting en snelheid
	 */
	@Override
	public void update() {
		fire();
	}

	/**
	 * Vuur af de projectile
	 */
	protected abstract void fire();

	/**
	 * Raakt de projectile de player? richt schade aan de player.
	 */
	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		for (GameObject gameObject : collidedGameObjects) {
			if (gameObject instanceof Player) {
				((Player) gameObject).takeDamage();
				world.deleteGameObject(this);
			}
		}
	}

	/**
	 * Dirty hack: projectile wordt bij het aanmaken direct weer verwijderd, zelfs
	 * als de direction ie opgaat geen solideTile tegenkomt
	 */
	@Override
	public void tileCollisionOccurred(List<CollidedTile> collidedTiles) {
		for (CollidedTile collidedTile : collidedTiles) {
			if (collidedTile.getTile() instanceof SolideTile) {
				long currentTime = System.currentTimeMillis();
				if (currentTime - previousCollisionDetection >= collisionDetectionDelay) {
					boolean solidTop = CollisionSide.TOP.equals(collidedTile.getCollisionSide());
					boolean solidRight = CollisionSide.RIGHT.equals(collidedTile.getCollisionSide());
					boolean solidBottom = CollisionSide.BOTTOM.equals(collidedTile.getCollisionSide());
					boolean solidLeft = CollisionSide.LEFT.equals(collidedTile.getCollisionSide());

					if (solidTop || solidRight || solidBottom || solidLeft) {
						world.deleteGameObject(this);
					}
				}				
			}
		}
	}

}
