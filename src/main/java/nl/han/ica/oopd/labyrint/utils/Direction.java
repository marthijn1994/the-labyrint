package nl.han.ica.oopd.labyrint.utils;

import nl.han.ica.oopd.labyrint.Labyrint;
import nl.han.ica.oopd.labyrint.tiles.SolideTile;
import nl.han.ica.oopg.exceptions.TileNotFoundException;
import nl.han.ica.oopg.tile.Tile;

public class Direction {
	
	public static final int NORTH = 0;
	public static final int EAST = 90;
	public static final int SOUTH = 180;
	public static final int WEST = 270;
	
	/**
	 * Bereken de correct direction voor de enemies
	 * 
	 * @param world
	 * @param tileY
	 * @param tileX
	 * @return direction
	 */
	public static float calculateDirection(Labyrint world, int tileY, int tileX) {
		int direction = Direction.SOUTH;
		
		try {
			Tile topTile = world.getTileMap().getTileOnIndex(tileX, tileY - 1);
			Tile rightTile = world.getTileMap().getTileOnIndex(tileX + 1, tileY);
			Tile bottomTile = world.getTileMap().getTileOnIndex(tileX, tileY + 1);
			Tile leftTile = world.getTileMap().getTileOnIndex(tileX - 1, tileY);
			
			boolean topIsSolid = topTile instanceof SolideTile;
			boolean leftIsSolid = leftTile instanceof SolideTile;
			boolean bottomIsSolid = bottomTile instanceof SolideTile;
			boolean rightIsSolid = rightTile instanceof SolideTile;
			
			if (!topIsSolid) {
				direction = Direction.NORTH;
			} else if (!rightIsSolid) {
				direction = Direction.EAST;
			} else if (!bottomIsSolid) {
				direction = Direction.SOUTH;
			}
			if (!leftIsSolid) {
				direction = Direction.WEST;
			}
			
		} catch (TileNotFoundException e) {
			e.printStackTrace();
		}
		return direction;
	}

}
