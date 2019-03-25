package nl.han.ica.oopd.labyrint;

import java.util.List;
import nl.han.ica.oopd.labyrint.tiles.MuurTile;
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
	private final int spriteSize = 50;

	private static int DEATHS = 3;

	public Player(Labyrint world) {
		super(new Sprite("src/main/java/nl/han/ica/oopd/labyrint/media/player.png"), 4);
		this.world = world;
		setSpeed(2);
	}

	@Override
	public void tileCollisionOccurred(List<CollidedTile> collidedTiles) {
		for (CollidedTile collidedTile : collidedTiles) {
			if (collidedTile.getTile() instanceof MuurTile) {
				checkWallCollision(collidedTile, collidedTile.getCollisionSide());
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
		if (keyCode == world.LEFT) {
			setDirectionSpeed(WEST, getSpeed());
			setCurrentFrameIndex(1);
		}
		if (keyCode == world.UP) {
			setDirectionSpeed(NORTH, getSpeed());
			setCurrentFrameIndex(2);
		}
		if (keyCode == world.RIGHT) {
			setDirectionSpeed(EAST, getSpeed());
			setCurrentFrameIndex(3);
		}
		if (keyCode == world.DOWN) {
			setDirectionSpeed(SOUTH, getSpeed());
			setCurrentFrameIndex(0);
		}
		if (key == ' ') {
			System.out.println("Open deur");
			OpenDoor();
		}
	}

	private void OpenDoor() {
		PVector vector;
	}

	public boolean isDeath() {
		return DEATHS == 0;
	}

	public void neemSchade() {
		DEATHS -= 1;
		if (isDeath()) {
			// GAME OVER MAN!
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
				setY(vector.y + spriteSize);
			} catch (TileNotFoundException e) {
				e.printStackTrace();
			}
		} else if (CollisionSide.TOP.equals(collisionSide)) {
			try {
				vector = world.getTileMap().getTilePixelLocation(collidedTile.getTile());
				setY(vector.y - spriteSize);
			} catch (TileNotFoundException e) {
				e.printStackTrace();
			}
		} else if (CollisionSide.RIGHT.equals(collisionSide)) {
			try {
				vector = world.getTileMap().getTilePixelLocation(collidedTile.getTile());
				setX(vector.x + spriteSize);
			} catch (TileNotFoundException e) {
				e.printStackTrace();
			}
		} else if (CollisionSide.LEFT.equals(collisionSide)) {
			try {
				vector = world.getTileMap().getTilePixelLocation(collidedTile.getTile());
				setX(vector.x - spriteSize);
			} catch (TileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	// check for collision with doors
	private boolean checkDoorCollision(CollidedTile collidedTile, CollisionSide collisionSide) {
		return true;
	}

	// check voor collision met vijanden
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		for (GameObject g : collidedGameObjects) {
			if (g instanceof ISchadelijk) {
				((ISchadelijk) g).handelSchade(this);
			}
		}
	}

	public Labyrint getWorld() {
		return world;
	}
}
