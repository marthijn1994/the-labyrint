package nl.han.ica.oopd.labyrint.tiles;

import nl.han.ica.oopd.labyrint.Labyrint;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.tile.TileType;

public class TileManager {
	
	// Tile size
	public static int tileSize = 50;

	// Muur Tile
	private static Sprite muurSprite = new Sprite(Labyrint.MEDIA_FOLDER + "muur.png");
	private static TileType<MuurTile> muurTileType = new TileType<>(MuurTile.class, muurSprite);

	// Vloer Tile
	private static Sprite vloerSprite = new Sprite(Labyrint.MEDIA_FOLDER + "vloer.png");
	private static TileType<VloerTile> vloerTileType = new TileType<>(VloerTile.class, vloerSprite);

	// Deur Tile
	private static Sprite deurSprite = new Sprite(Labyrint.MEDIA_FOLDER + "deur.png");
	private static TileType<DeurTile> deurTileType = new TileType<DeurTile>(DeurTile.class, deurSprite);

	// Cactus Tile
	private static Sprite CactusSprite = new Sprite(Labyrint.MEDIA_FOLDER + "cactus.png");
	private static TileType<CactusTile> cactusTileType = new TileType<CactusTile>(CactusTile.class, CactusSprite);
	
	@SuppressWarnings("rawtypes")
	public static TileType[] tileTypes = { muurTileType, vloerTileType, deurTileType, cactusTileType };

}
