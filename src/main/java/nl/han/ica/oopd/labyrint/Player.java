package nl.han.ica.oopd.labyrint;

/**
 * @author Marthijn Kip
 * @author Werner van Voorts
 */

import java.util.List;

import nl.han.ica.oopd.labyrint.level.LevelManager;
import nl.han.ica.oopd.labyrint.tiles.IDamagable;
import nl.han.ica.oopd.labyrint.tiles.IOpenAble;
import nl.han.ica.oopd.labyrint.tiles.SolideTile;
import nl.han.ica.oopd.labyrint.utils.DirectionUtils;
import nl.han.ica.oopd.labyrint.utils.FolderLocationsUtils;
import nl.han.ica.oopg.collision.CollidedTile;
import nl.han.ica.oopg.collision.CollisionSide;
import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.collision.ICollidableWithTiles;
import nl.han.ica.oopg.exceptions.TileNotFoundException;
import nl.han.ica.oopg.objects.AnimatedSpriteObject;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import processing.core.PVector;

public class Player extends AnimatedSpriteObject implements ICollidableWithTiles, ICollidableWithGameObjects {

	private Labyrint world;
	private Inventory inventory;

	private int spriteSize = 40;

	public static final int MAX_LIVES = 5;
	private static int LIVES = MAX_LIVES;
	
	public static int CURRENT_LEVEL = LevelManager.START_LEVEL;

	public Player(Labyrint world) {
		super(new Sprite(FolderLocationsUtils.MEDIA_ROOT + "player.png"), 4);

		this.world = world;
		inventory = new Inventory();

		setFriction(0.19f);
	}

	@Override
	public void tileCollisionOccurred(List<CollidedTile> collidedTiles) {
		for (CollidedTile collidedTile : collidedTiles) {
			if (collidedTile.getTile() instanceof SolideTile) {
				checkSolidTileCollision(collidedTile, collidedTile.getCollisionSide());
			}
			if (collidedTile.getTile() instanceof IDamagable) {
				((IDamagable) collidedTile.getTile()).handleDamage(this);
			}
			if (collidedTile.getTile() instanceof IOpenAble) {
				((IOpenAble) collidedTile.getTile()).open(this, world, collidedTile);
			}
		}
	}

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameobjects) {
		for (GameObject gameObject : collidedGameobjects) {
			if (gameObject instanceof IDamagable) {
				((IDamagable) gameObject).handleDamage(this);
			}
		}
	}

	@Override
	public void update() {
		if (getX() <= 0) {
			setX(0);
		}
		if (getY() <= 0) {
			setY(0);
		}
		if (getX() >= world.width - spriteSize) {
			setX(world.width - spriteSize);
		}
		if (getY() >= world.height - spriteSize) {
			setY(world.height - spriteSize);
		}
	}

	@Override
	public void keyPressed(int keyCode, char key) {
		final float speed = 3.75f;
		if (keyCode == Labyrint.LEFT) {
			setDirectionSpeed(DirectionUtils.WEST, speed);
			setCurrentFrameIndex(1);
		}
		if (keyCode == Labyrint.UP) {
			setDirectionSpeed(DirectionUtils.NORTH, speed);
			setCurrentFrameIndex(2);
		}
		if (keyCode == Labyrint.RIGHT) {
			setDirectionSpeed(DirectionUtils.EAST, speed);
			setCurrentFrameIndex(3);
		}
		if (keyCode == Labyrint.DOWN) {
			setDirectionSpeed(DirectionUtils.SOUTH, speed);
			setCurrentFrameIndex(0);
		}

		if (key == 'n') {
			LevelManager.loadLevel(++CURRENT_LEVEL);
		}
	}

	/**
	 * Controleer collision met de muren
	 * 
	 * @param collidedTile
	 * @param collisionSide
	 */
	private void checkSolidTileCollision(CollidedTile collidedTile, CollisionSide collisionSide) {
		final PVector vector;
		int yOffset = ((collidedTile.getTile().getSprite().getWidth() - getSpriteSize()) / 2 + 5);
		int xOffset = ((collidedTile.getTile().getSprite().getWidth() - getSpriteSize()) / 2 - 5);

		if (CollisionSide.BOTTOM.equals(collisionSide)) {
			try {
				vector = world.getTileMap().getTilePixelLocation(collidedTile.getTile());
				setY(vector.y + getSpriteSize() + yOffset);
			} catch (TileNotFoundException e) {
				e.printStackTrace();
			}
		} else if (CollisionSide.TOP.equals(collisionSide)) {
			try {
				vector = world.getTileMap().getTilePixelLocation(collidedTile.getTile());
				setY(vector.y - getSpriteSize() - xOffset);
			} catch (TileNotFoundException e) {
				e.printStackTrace();
			}
		} else if (CollisionSide.RIGHT.equals(collisionSide)) {
			try {
				vector = world.getTileMap().getTilePixelLocation(collidedTile.getTile());
				setX(vector.x + getSpriteSize() + yOffset);
			} catch (TileNotFoundException e) {
				e.printStackTrace();
			}
		} else if (CollisionSide.LEFT.equals(collisionSide)) {
			try {
				vector = world.getTileMap().getTilePixelLocation(collidedTile.getTile());
				setX(vector.x - getSpriteSize() - xOffset);
			} catch (TileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Check om te kijken of de speler geen levens meer heeft
	 */
	public boolean isDeath() {
		return LIVES <= 0;
	}

	/**
	 * Neem een leven van de speler als hij schade oploopt door een obstakel
	 */
	public void takeDamage() {
		LIVES--;
		world.getUserInterface().updateHealth();
		if (isDeath()) {
			world.getGameOver().setGameOver();
		}
	}

	/*
	 * GETTERS & SETTERS
	 */
	public Inventory getInventory() {
		return inventory;
	}

	public int getSpriteSize() {
		return spriteSize;
	}

	public Labyrint getWorld() {
		return world;
	}

	public static int getLives() {
		return LIVES;
	}
	
	public void setLives(int lives) {
		LIVES = lives;
	}
	
}
