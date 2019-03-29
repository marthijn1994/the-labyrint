package nl.han.ica.oopd.labyrint.enemies.spells;

import java.util.List;

import nl.han.ica.oopd.labyrint.Labyrint;
import nl.han.ica.oopd.labyrint.Player;
import nl.han.ica.oopd.labyrint.tiles.SolideTile;
import nl.han.ica.oopd.labyrint.utils.FolderLocationsUtils;
import nl.han.ica.oopg.collision.CollidedTile;
import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.collision.ICollidableWithTiles;
import nl.han.ica.oopg.objects.AnimatedSpriteObject;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;

public class FireSpel extends AnimatedSpriteObject implements ICollidableWithTiles, ICollidableWithGameObjects {

	private Labyrint world;
	
	private float direction;

	public FireSpel( Labyrint world, float direction) {
		super(new Sprite(FolderLocationsUtils.ENEMIES_FOLDER + "fireball.png"), 1);

		this.world = world;
		this.direction = direction;
	}

	@Override
	public void update() {
		castSpel();
	}

	public void castSpel() {
		final float speed = 2.5f;
		setDirectionSpeed(direction, speed);
	}

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		for (GameObject gameObject : collidedGameObjects) {
			if (gameObject instanceof Player) {
				((Player) gameObject).neemSchade();
				world.deleteGameObject(this);
			}
		}
	}

	@Override
	public void tileCollisionOccurred(List<CollidedTile> collidedTiles) {
		for (CollidedTile collidedTile : collidedTiles) {
			if (collidedTile.getTile() instanceof SolideTile) {
//				setSpeed(0);
//				world.deleteGameObject(this);
			}
		}
	}

}
