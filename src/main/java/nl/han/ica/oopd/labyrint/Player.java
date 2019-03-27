package nl.han.ica.oopd.labyrint;

import java.util.List;

import nl.han.ica.oopd.labyrint.tiles.DeurTile;
import nl.han.ica.oopd.labyrint.tiles.SolideTile;
import nl.han.ica.oopg.collision.CollidedTile;
import nl.han.ica.oopg.collision.CollisionSide;
import nl.han.ica.oopg.collision.ICollidableWithTiles;
import nl.han.ica.oopg.exceptions.TileNotFoundException;
import nl.han.ica.oopg.objects.AnimatedSpriteObject;
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
	private static int LIFES = 3;

	@SuppressWarnings("unused")
	private List<CollidedTile> collidedTiles;

	public Player(Labyrint world) {
		super(new Sprite(Labyrint.MEDIA_FOLDER + "player.png"), 4);

		this.world = world;
		inventory = new Inventory();

		setFriction(0.15f);
	}

	@Override
	public void tileCollisionOccurred(List<CollidedTile> collidedTiles) {
		this.collidedTiles = collidedTiles;
		for (CollidedTile collidedTile : collidedTiles) {
			if (collidedTile.getTile() instanceof SolideTile) {
				checkSolidTileCollision(collidedTile, collidedTile.getCollisionSide());
			}
			if (collidedTile.getTile() instanceof ISchadelijk) {
				((ISchadelijk) collidedTile.getTile()).handelSchade(this);
			}
			if (collidedTile.getTile() instanceof DeurTile) {
				((DeurTile) collidedTile.getTile()).open(this, world, collidedTile);
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
		final float speed = 3.5f;
		if (keyCode == Labyrint.LEFT) {
			setDirectionSpeed(WEST, speed);
			setCurrentFrameIndex(1);
		}
		if (keyCode == Labyrint.UP) {
			setDirectionSpeed(NORTH, speed);
			setCurrentFrameIndex(2);
		}
		if (keyCode == Labyrint.RIGHT) {
			setDirectionSpeed(EAST, speed);
			setCurrentFrameIndex(3);
		}
		if (keyCode == Labyrint.DOWN) {
			setDirectionSpeed(SOUTH, speed);
			setCurrentFrameIndex(0);
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
		final float offset = 11.0f;

		if (CollisionSide.BOTTOM.equals(collisionSide)) {
			try {
				vector = world.getTileMap().getTilePixelLocation(collidedTile.getTile());
				setY((vector.y + offset) + getSpriteSize());
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
				setX(vector.x - (getSpriteSize() - offset));
			} catch (TileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Check om te kijken of de speler geen levens meer heeft
	 */
	public boolean isDeath() {
		return LIFES == 0;
	}

	/**
	 * Neem een leven van de speler als hij schade oploopt door een obstakel
	 */
	public void neemSchade() {
		System.out.println("Ow!");
		LIFES -= 1;
		if (isDeath()) {
			// GAME OVER MAN!
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
}
