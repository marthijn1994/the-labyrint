package nl.han.ica.oopd.labyrint.tiles;

/**
 * @author Marthijn Kip
 * @author Werner van Voorts
 */

import nl.han.ica.oopd.labyrint.utils.FolderLocationsUtils;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.tile.TileType;

public class TileManager {

	/**
	 * 0 - 49 gereserveerd voor tiles 50 - 98 gereserveerd voor object id's
	 */

	// Tile size
	public static int tileSize = 50;

	// Non-tiles, ids voor het plaatsen van objecten in de map
	public static final int DIAMOND_TILE_ID = 50;
	public static final int KEY_TILE_ID = 51;
	public static final int PLAYER_SPAWN_POINT = 99;
	public static final int WIZARD_SPAWN_POINT = 100;
	public static final int RANGER_SPAWN_POINT = 101;

	// Muur Tile & Geheime Muur Tile
	private static Sprite wallSprite = new Sprite(FolderLocationsUtils.TILES_FOLDER + "wall.png");
	private static TileType<WallTile> wallTileType = new TileType<>(WallTile.class, wallSprite);
	private static TileType<SecretWall> secretWallTileType = new TileType<>(SecretWall.class, wallSprite);

	// Vloer Tile
	private static Sprite floorSprite = new Sprite(FolderLocationsUtils.TILES_FOLDER + "floor.png");
	private static TileType<FloorTile> floorTileType = new TileType<>(FloorTile.class, floorSprite);

	// Deur Tile
	private static Sprite doorSprite = new Sprite(FolderLocationsUtils.TILES_FOLDER + "door.png");
	private static TileType<DoorTile> doorTileType = new TileType<DoorTile>(DoorTile.class, doorSprite);

	// Cactus Tile
	private static Sprite CactusSprite = new Sprite(FolderLocationsUtils.OBSTAKELS_FOLDER + "cactus.png");
	private static TileType<CactusTile> cactusTileType = new TileType<CactusTile>(CactusTile.class, CactusSprite);

	// DIAMAND_TILE_ID = 50
	// SLEUTEL_TILE_ID = 51
	// PLAYER_SPWAN_POINT = 99
	// WIZARD_SPAWN_POINT = 100
	@SuppressWarnings("rawtypes") // 0 1 2 3 4
	public static TileType[] tileTypes = { wallTileType, floorTileType, doorTileType, cactusTileType,
			secretWallTileType };

}
