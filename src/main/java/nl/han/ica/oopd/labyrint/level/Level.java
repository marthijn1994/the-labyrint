package nl.han.ica.oopd.labyrint.level;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import nl.han.ica.oopd.labyrint.Labyrint;
import nl.han.ica.oopd.labyrint.Player;
import nl.han.ica.oopd.labyrint.items.Diamand;
import nl.han.ica.oopd.labyrint.items.Key;
import nl.han.ica.oopd.labyrint.tiles.TileManager;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.tile.TileMap;

public class Level {

	private static final String LEVEL_MEDIA_FOLDER = Labyrint.MEDIA_FOLDER + "levels/";
	private static final int DIAMAND_TILE_ID = 50;
	private static final int SLEUTEL_TILE_ID = 51;

	private Labyrint world;
	private Player player;

	private int width;
	private int height;
	private int aantalTilesX;
	private int aantalTilesY;
	private int[][] tilesMap;

	private String path;

	public Level(Labyrint world, Player player, String path) {
		this.world = world;
		this.player = player;
		this.path = path;

		width = world.getWidth();
		height = world.getHeight();
		aantalTilesX = width / TileManager.tileSize;
		aantalTilesY = height / TileManager.tileSize;
		tilesMap = new int[aantalTilesX][aantalTilesY];

		loadLevelTextFile();
		loadObjectsIntoMap();
	}

	private void loadLevelTextFile() {
		try {
			File file = new File(LEVEL_MEDIA_FOLDER + path);
			Scanner scanner = new Scanner(file);

			String[] tileTypeIds;
			int[] ids;
			int row = 0;
			while (scanner.hasNextLine()) {
				tileTypeIds = scanner.next().split(",");
				ids = new int[tileTypeIds.length];

				for (int i = 0; i < ids.length; i++) {
					ids[i] = Integer.parseInt(tileTypeIds[i]);
				}

				tilesMap[row] = ids;
				row++;
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void loadObjectsIntoMap() {
		for (int x = 0; x < aantalTilesX; x++) {
			for (int y = 0; y < aantalTilesY; y++) {
				if (tilesMap[x][y] == DIAMAND_TILE_ID) {
					tilesMap[x][y] = 1;
					
					Sprite diamandSprite = new Sprite(Labyrint.MEDIA_FOLDER + "diamond.png");
					Diamand diamand = new Diamand(diamandSprite, 10, world);
					world.addGameObject(diamand, ((float) y * TileManager.tileSize), ((float) x * TileManager.tileSize));
				} else if (tilesMap[x][y] == SLEUTEL_TILE_ID) {
					tilesMap[x][y] = 1;
					
					Sprite keySprite = new Sprite(Labyrint.MEDIA_FOLDER + "key.png");
					Key key = new Key(keySprite, world);
					world.addGameObject(key, ((float) y * TileManager.tileSize), ((float) x * TileManager.tileSize));
				}
			}
		}
	}

	public TileMap generateTileMap() {
		return new TileMap(TileManager.tileSize, TileManager.tileTypes, tilesMap);
	}

}
