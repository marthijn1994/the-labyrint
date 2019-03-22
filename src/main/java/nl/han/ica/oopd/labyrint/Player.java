package nl.han.ica.oopd.labyrint;

import java.util.List;

import nl.han.ica.oopd.labyrint.tiles.MuurTile;
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
	private final int speed = 2;
	private final int spriteSize = 50;
	
	private static int DEATHS = 3;
	
	public Player(Labyrint world) {
		super(new Sprite("src/main/java/nl/han/ica/oopd/labyrint/media/player.png"), 4);
		this.world = world;
	}

	@Override
	public void tileCollisionOccurred(List<CollidedTile> collidedTiles) {
		PVector vector;

        for (CollidedTile collidedTile : collidedTiles) {
            if (collidedTile.getTile() instanceof MuurTile) {
            	checkWallCollision(collidedTile, collidedTile.getCollisionSide());
            }
        }
		
	}

	@Override
	public void update() {
		if (getX() <= 0) {
            setxSpeed(0);
            setX(0);
        }
        if (getY() <= 0) {
            setySpeed(0);
            setY(0);
        }
        if (getX() >= world.width - spriteSize) {
            setxSpeed(0);
            setX(world.width - spriteSize);
        }
        if (getY() >= world.height - spriteSize) {
            setySpeed(0);
            setY(world.height - spriteSize);
        }
	}
	
	@Override
	public void keyPressed(int keyCode, char key) {
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
	
	public boolean isDeath() {
		return DEATHS == 0;
	}
	
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

}
