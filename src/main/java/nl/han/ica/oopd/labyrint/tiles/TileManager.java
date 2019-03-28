package nl.han.ica.oopd.labyrint.tiles;

import nl.han.ica.oopd.labyrint.utils.FolderLocationsUtils;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.tile.TileType;

public class TileManager {

	// Tile size
	public static int tileSize = 50;
	
	public static final int DIAMAND_TILE_ID = 50;
	public static final int SLEUTEL_TILE_ID = 51;
	public static final int PLAYER_SPWAN_POINT = 99;

	// Muur Tile & Geheime Muur Tile
	private static Sprite muurSprite = new Sprite(FolderLocationsUtils.TILES_FOLDER + "muur.png");
	private static TileType<MuurTile> muurTileType = new TileType<>(MuurTile.class, muurSprite);
	private static TileType<GeheimeMuur> geheimeMuurTileType = new TileType<>(GeheimeMuur.class, muurSprite);

	// Vloer Tile
	private static Sprite vloerSprite = new Sprite(FolderLocationsUtils.TILES_FOLDER + "vloer.png");
	private static TileType<VloerTile> vloerTileType = new TileType<>(VloerTile.class, vloerSprite);

	// Deur Tile
	private static Sprite deurSprite = new Sprite(FolderLocationsUtils.TILES_FOLDER + "deur.png");
	private static TileType<DeurTile> deurTileType = new TileType<DeurTile>(DeurTile.class, deurSprite);

	// Cactus Tile
	private static Sprite CactusSprite = new Sprite(FolderLocationsUtils.OBSTAKELS_FOLDER + "cactus.png");
	private static TileType<CactusTile> cactusTileType = new TileType<CactusTile>(CactusTile.class, CactusSprite);

	//DIAMAND_TILE_ID    = 50
	//SLEUTEL_TILE_ID    = 51
	//PLAYER_SPWAN_POINT = 99
	@SuppressWarnings("rawtypes")	//           0              1             2              3                 4
	public static TileType[] tileTypes = { muurTileType, vloerTileType, deurTileType, cactusTileType, geheimeMuurTileType };

}
