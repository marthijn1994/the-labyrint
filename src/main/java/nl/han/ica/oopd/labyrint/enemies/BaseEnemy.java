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

public class BaseEnemy extends AnimatedSpriteObject implements ICollidableWithGameObjects, ICollidableWithTiles {

	public BaseEnemy(Labyrint world, Sprite sprite, int totalFrames) {
		super(sprite, totalFrames);
	}

	@Override
	public void update() {

	}

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {

	}

	@Override
	public void tileCollisionOccurred(List<CollidedTile> collidedTiles) {
		for (CollidedTile collidedTile : collidedTiles) {
			if (collidedTile.getTile() instanceof SolideTile) {
				if (CollisionSide.TOP.equals(collidedTile.getCollisionSide())) {
					setDirection(Player.NORTH);
				}
				if (CollisionSide.LEFT.equals(collidedTile.getCollisionSide())) {
					setDirection(Player.WEST);
				}
				if (CollisionSide.RIGHT.equals(collidedTile.getCollisionSide())) {
					setDirection(Player.EAST);
				}
				if (CollisionSide.BOTTOM.equals(collidedTile.getCollisionSide())) {
					setDirection(Player.SOUTH);
				}
			}
		}
	}

}
