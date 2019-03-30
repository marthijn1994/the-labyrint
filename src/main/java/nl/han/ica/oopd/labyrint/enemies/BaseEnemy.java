package nl.han.ica.oopd.labyrint.enemies;

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

public abstract class BaseEnemy extends AnimatedSpriteObject implements ICollidableWithGameObjects, ICollidableWithTiles {

	@SuppressWarnings("unused")
	private Labyrint world;

	public BaseEnemy(Labyrint world, Sprite sprite, int totalFrames) {
		super(sprite, totalFrames);

		this.world = world;
	}

	@Override
	public void update() {
		attack();
	}
	
	protected abstract void attack();

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		//
	}

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

}
