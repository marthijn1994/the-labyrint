package nl.han.ica.oopd.labyrint.level;

/**
 * @author Marthijn Kip
 * @author Werner van Voorts
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

import nl.han.ica.oopd.labyrint.Labyrint;
import nl.han.ica.oopd.labyrint.Player;
import nl.han.ica.oopd.labyrint.enemies.Wizard;
import nl.han.ica.oopd.labyrint.items.Diamand;
import nl.han.ica.oopd.labyrint.items.Key;
import nl.han.ica.oopd.labyrint.tiles.TileManager;
import nl.han.ica.oopd.labyrint.utils.FolderLocationsUtils;
import nl.han.ica.oopd.labyrint.utils.NumberUtils;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.sound.Sound;
import nl.han.ica.oopg.tile.TileMap;

public class Level {

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

	/**
	 * Level Constructor
	 * 
	 * @param world
	 * @param player
	 * @param path
	 */
	public Level(Labyrint world, Player player, String path) {
		this.world = world;
		this.player = player;
		this.path = path;

		random = new Random();

		width = world.getWidth();
		height = world.getHeight() - 100;
		aantalTilesX = width / TileManager.tileSize;
		aantalTilesY = height / TileManager.tileSize;
		tilesMap = new int[aantalTilesY][aantalTilesX];
	}

	/**
	 * Extra constructor zodat je per level ook een achtergrondmuziekje kan
	 * instellen
	 * 
	 * @param world
	 * @param player
	 * @param path
	 * @param soundClipPath
	 */
	public Level(Labyrint world, Player player, String path, String soundClipPath) {
		this(world, player, path);
		sound = new Sound(world, FolderLocationsUtils.SOUND_FOLDER + soundClipPath);
	}

	/**
	 * Laad de level
	 */
	public void load() {
		loadLevelCsvFile();
		loadObjectsIntoMap();
		loadEnemiesIntoMap();
		loadPlayerIntoMap();
//		loadBackgroundMusic();
		world.setTileMap(generateTileMap());
	}

	/**
	 * laad de csv file in de tilesMap array
	 */
	private void loadLevelCsvFile() {
		try {
			File file = new File(FolderLocationsUtils.LEVEL_FOLDER + path);
			Scanner scanner = new Scanner(file);

			String[] csvSingleLineIds;
			String[] textIds;
			int[] numberIds;
			int row = 0;
			while (scanner.hasNextLine()) {
				csvSingleLineIds = scanner.next().split(",");
				textIds = new String[aantalTilesX];
				numberIds = new int[aantalTilesX];

				int i = 0;
				while (i < csvSingleLineIds.length && i < aantalTilesX) {
					textIds[i] = csvSingleLineIds[i];
					i++;
				}
				for (int id = 0; id < textIds.length; id++) {
					if (textIds[id] == null)
						textIds[id] = "0";
					numberIds[id] = (NumberUtils.isNumber(textIds[id])) ? Integer.parseInt(textIds[id]) : 0;
				}

				tilesMap[row++] = numberIds;
				if (row == aantalTilesY)
					break;
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * laad alle objecten in de map. Loop door de twee dimensionale array tilesMap.
	 * Als er een value gelijk aan DIAMAND_TILE_ID of SLEUTEL_TILE_ID, plaatst op de
	 * desbetreffende plek een diamand/sleutel.
	 */
	private void loadObjectsIntoMap() {
		for (int y = 0; y < aantalTilesY; y++) {
			for (int x = 0; x < aantalTilesX; x++) {
				if (tilesMap[y][x] == TileManager.DIAMAND_TILE_ID) {
					tilesMap[y][x] = 1;

					int randomPuntenWaarden = random.nextInt(30);

					Sprite diamandSprite = new Sprite(FolderLocationsUtils.ITEMS_FOLDER + "diamond.png");
					Diamand diamand = new Diamand(diamandSprite, randomPuntenWaarden, world);

					// Plaats een diamand op je juiste positie in de map
					float xPos = ((float) x * TileManager.tileSize);
					float yPos = ((float) y * TileManager.tileSize);
					world.addGameObject(diamand, xPos, yPos);
				} else if (tilesMap[y][x] == TileManager.SLEUTEL_TILE_ID) {
					tilesMap[y][x] = 1;

					Sprite keySprite = new Sprite(FolderLocationsUtils.ITEMS_FOLDER + "key.png");
					Key key = new Key(keySprite, world);

					// Plaats een key op je juiste positie in de map
					float xPos = ((float) x * TileManager.tileSize);
					float yPos = ((float) y * TileManager.tileSize);
					world.addGameObject(key, xPos, yPos);
				}
			}
		}
	}
	
	/**
	 * Spawn alle enemies op de map
	 */
	private void loadEnemiesIntoMap() {
		for (int y = 0; y < aantalTilesY; y++) {
			for (int x = 0; x < aantalTilesX; x++) {
				if (tilesMap[y][x] == TileManager.WIZARD_SPAWN_POINT) {
					tilesMap[y][x] = 1;
					
					Wizard wizard = new Wizard(world);
					
					float xPos = ((float) x * TileManager.tileSize);
					float yPos = ((float) y * TileManager.tileSize);
					world.addGameObject(wizard, xPos, yPos);
				}
			}
		}
	}

	/**
	 * Spawn de player op de spawn positie
	 */
	private void loadPlayerIntoMap() {
		for (int y = 0; y < aantalTilesY; y++) {
			for (int x = 0; x < aantalTilesX; x++) {
				if (tilesMap[y][x] == TileManager.PLAYER_SPAWN_POINT) {
					tilesMap[y][x] = 1;

					// Spawn de player op de juiste positie in de map
					float spawnX = ((float) x * TileManager.tileSize);
					float spawnY = ((float) y * TileManager.tileSize);
					player.setX(spawnX);
					player.setY(spawnY);
				}
			}
		}
	}

	/**
	 * Laad en speel af de achtergrond muziek van de level, heeft een level geen
	 * achtergrond muziek? Gebruik dan een standaard achtergrond muziekje...
	 */
	private void loadBackgroundMusic() {
		if (sound == null)
			sound = new Sound(world, FolderLocationsUtils.SOUND_FOLDER + "defaultMusic.mp3");

		sound.loop(-1);
		sound.play();
	}

	/**
	 * genereer op basis van de tileTypes en tilesMap een map met tiles.
	 * 
	 * @return TileMap
	 */
	private TileMap generateTileMap() {
		return new TileMap(TileManager.tileSize, TileManager.tileTypes, tilesMap);
	}

}
