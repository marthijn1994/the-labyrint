package nl.han.ica.oopd.labyrint;

import java.util.List;

import nl.han.ica.oopd.labyrint.tiles.DeurTile;
import nl.han.ica.oopd.labyrint.tiles.SolideTile;
import nl.han.ica.oopg.collision.CollidedTile;
import nl.han.ica.oopg.collision.CollisionSide;
import nl.han.ica.oopg.collision.ICollidableWithTiles;
import nl.han.ica.oopg.exceptions.TileNotFoundException;
import nl.han.ica.oopg.objects.AnimatedSpriteObject;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import processing.core.PVector;

public class Player extends AnimatedSpriteObject implements ICollidableWithTiles {

	private static final int NORTH = 0;
	private static final int EAST = 90;
	private static final int SOUTH = 180;
	private static final int WEST = 270;

	private final Labyrint world;
	private final Inventory inventory;
	
	private final int spriteSize = 50;
	private static int DEATHS = 3;
	
	@SuppressWarnings("unused")
	private List<CollidedTile> collidedTiles;

	public Player(Labyrint world, Inventory inventory) {
		super(new Sprite("src/main/java/nl/han/ica/oopd/labyrint/media/player.png"), 4);
		this.world = world;		
		this.inventory = inventory;
		setFriction(0.075f);
	}

	@Override
	public void tileCollisionOccurred(List<CollidedTile> collidedTiles) {
		this.collidedTiles = collidedTiles;
		for (CollidedTile collidedTile : collidedTiles) {
			if (collidedTile.getTile() instanceof SolideTile) {
				checkWallCollision(collidedTile, collidedTile.getCollisionSide());
			}
			if (collidedTile.getTile() instanceof ISchadelijk) {
				((ISchadelijk)collidedTile.getTile()).handelSchade(this);
			}
			if (collidedTile.getTile() instanceof DeurTile) {
				((DeurTile)collidedTile.getTile()).open(this, world, collidedTile);
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

	@SuppressWarnings("static-access")
	@Override
	public void keyPressed(int keyCode, char key) {
		final float speed = 2.0f;
		if (keyCode == world.LEFT) {
			setDirectionSpeed(WEST, speed);
			setCurrentFrameIndex(1);
		}
		if (keyCode == world.UP) {
			setDirectionSpeed(NORTH, speed);
			setCurrentFrameIndex(2);
		}
		if (keyCode == world.RIGHT) {
			setDirectionSpeed(EAST, speed);
			setCurrentFrameIndex(3);
		}
		if (keyCode == world.DOWN) {
			setDirectionSpeed(SOUTH, speed);
			setCurrentFrameIndex(0);
		}
	}

	/*
	 * check for collision with walls
	 */
	private void checkWallCollision(CollidedTile collidedTile, CollisionSide collisionSide) {
		PVector vector;
		if (CollisionSide.BOTTOM.equals(collisionSide)) {
			try {
				vector = world.getTileMap().getTilePixelLocation(collidedTile.getTile());
				setY(vector.y + getSpriteSize());
			} catch (TileNotFoundException e) {
				e.printStackTrace();
			}
		} else if (CollisionSide.TOP.equals(collisionSide)) {
			try {
				vector = world.getTileMap().getTilePixelLocation(collidedTile.getTile());
				setY(vector.y - getSpriteSize());
			} catch (TileNotFoundException e) {
				e.printStackTrace();
			}
		} else if (CollisionSide.RIGHT.equals(collisionSide)) {
			try {
				vector = world.getTileMap().getTilePixelLocation(collidedTile.getTile());
				setX(vector.x + getSpriteSize());
			} catch (TileNotFoundException e) {
				e.printStackTrace();
			}
		} else if (CollisionSide.LEFT.equals(collisionSide)) {
			try {
				vector = world.getTileMap().getTilePixelLocation(collidedTile.getTile());
				setX(vector.x - getSpriteSize());
			} catch (TileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	// check voor collision met vijanden
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		for (GameObject g : collidedGameObjects) {
			if (g instanceof ISchadelijk) {
				((ISchadelijk) g).handelSchade(this);
			}
		}
	}

	public boolean isDeath() {
		return DEATHS == 0;
	}

	public void neemSchade() {
		System.out.println("Ow!");
		DEATHS -= 1;
		if (isDeath()) {
			// GAME OVER MAN!
		}
	}
	
	public Inventory getInventory() {
		return inventory;
	}
	
	public int getSpriteSize() {
		return spriteSize;
	}

	public Labyrint getWorld() {
		return world;
	}
}
