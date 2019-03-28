package nl.han.ica.oopd.labyrint.level;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import nl.han.ica.oopd.labyrint.Labyrint;
import nl.han.ica.oopd.labyrint.Player;
import nl.han.ica.oopd.labyrint.items.Diamand;
import nl.han.ica.oopd.labyrint.items.Key;
import nl.han.ica.oopd.labyrint.tiles.TileManager;
import nl.han.ica.oopd.labyrint.utils.FolderLocationsUtils;
import nl.han.ica.oopd.labyrint.utils.NumberUtils;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.sound.Sound;
import nl.han.ica.oopg.tile.TileMap;

public class Level {

	private static final int DIAMAND_TILE_ID = 50;
	private static final int SLEUTEL_TILE_ID = 51;
	private static final int PLAYER_SPWAN_POINT = 99;

	private Labyrint world;
	private Player player;
	private Sound sound;
	private Random random;

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

		random = new Random();

		width = world.getWidth();
		height = world.getHeight();
		aantalTilesX = width / TileManager.tileSize;
		aantalTilesY = height / TileManager.tileSize;
		tilesMap = new int[aantalTilesY][aantalTilesX];
	}

	public Level(Labyrint world, Player player, String path, Sound soundClip) {
		this(world, player, path);
		sound = soundClip;
	}

	public void load() {
		loadLevelCsvFile();
		loadObjectsIntoMap();
		loadPlayerIntoMap();
		loadLevelBackgroundMusic();
		world.setTileMap(generateTileMap());
	}

	private void loadLevelCsvFile() {
		try {
			File file = new File(FolderLocationsUtils.LEVEL_FOLDER + path);
			Scanner scanner = new Scanner(file);

			String[] tileTypeIdsInText;
			int[] ids;
			int row = 0;
			while (scanner.hasNextLine()) {
				tileTypeIdsInText = scanner.next().split(",");
				ids = new int[aantalTilesX];

				int i = 0;
				while (i < tileTypeIdsInText.length) {
					if (NumberUtils.isNumber(tileTypeIdsInText[i])) {
						ids[i] = Integer.parseInt(tileTypeIdsInText[i]);
					} else {
						ids[i] = 0;
					}
					i++;
				}

				tilesMap[row++] = ids;
				System.out.println("Rij: " + row + " " + Arrays.toString(ids));
				if (row == aantalTilesY)
					break;
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void loadObjectsIntoMap() {
		for (int y = 0; y < aantalTilesY; y++) {
			for (int x = 0; x < aantalTilesX; x++) {
				if (tilesMap[y][x] == DIAMAND_TILE_ID) {
					tilesMap[y][x] = 1;

					int randomPuntenWaarden = random.nextInt(30);

					Sprite diamandSprite = new Sprite(FolderLocationsUtils.ITEMS_FOLDER + "diamond.png");
					Diamand diamand = new Diamand(diamandSprite, randomPuntenWaarden, world);

					float xPos = ((float) x * TileManager.tileSize);
					float yPos = ((float) y * TileManager.tileSize);
					world.addGameObject(diamand, xPos, yPos);
				} else if (tilesMap[y][x] == SLEUTEL_TILE_ID) {
					tilesMap[y][x] = 1;

					Sprite keySprite = new Sprite(FolderLocationsUtils.ITEMS_FOLDER + "key.png");
					Key key = new Key(keySprite, world);

					float xPos = ((float) x * TileManager.tileSize);
					float yPos = ((float) y * TileManager.tileSize);
					world.addGameObject(key, xPos, yPos);
				}
			}
		}
	}

	private void loadPlayerIntoMap() {
		for (int y = 0; y < aantalTilesY; y++) {
			for (int x = 0; x < aantalTilesX; x++) {
				if (tilesMap[y][x] == PLAYER_SPWAN_POINT) {
					tilesMap[y][x] = 1;

					float spawnX = ((float) x * TileManager.tileSize);
					float spawnY = ((float) y * TileManager.tileSize);
					player.setX(spawnX);
					player.setY(spawnY);
				}
			}
		}
	}

	private void loadLevelBackgroundMusic() {
		if (sound == null)
			sound = new Sound(world, FolderLocationsUtils.SOUND_FOLDER + "backgroundMusic.mp3");

		sound.loop(-1);
		sound.play();
	}

	public TileMap generateTileMap() {
		return new TileMap(TileManager.tileSize, TileManager.tileTypes, tilesMap);
	}

}
