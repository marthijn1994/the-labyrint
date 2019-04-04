package nl.han.ica.oopd.labyrint.level;

/**
 * @author Marthijn Kip
 * @author Werner van Voorts
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import nl.han.ica.oopd.labyrint.Labyrint;
import nl.han.ica.oopd.labyrint.Player;
import nl.han.ica.oopd.labyrint.enemies.BaseEnemy;
import nl.han.ica.oopd.labyrint.enemies.Ranger;
import nl.han.ica.oopd.labyrint.enemies.Wizard;
import nl.han.ica.oopd.labyrint.items.CollectableItem;
import nl.han.ica.oopd.labyrint.items.Diamond;
import nl.han.ica.oopd.labyrint.items.Key;
import nl.han.ica.oopd.labyrint.tiles.TileManager;
import nl.han.ica.oopd.labyrint.utils.DirectionUtils;
import nl.han.ica.oopd.labyrint.utils.FolderLocationsUtils;
import nl.han.ica.oopd.labyrint.utils.NumberUtils;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.sound.Sound;
import nl.han.ica.oopg.tile.TileMap;
import processing.core.PVector;

public class Level {

	private Labyrint world;
	private Player player;
	private Sound sound;

	private int width;
	private int height;
	private int numberOfTilesX;
	private int numberOfTilesY;
	private int[][] tilesMap;

	private String path;

	private static List<BaseEnemy> enemies = new ArrayList<BaseEnemy>();
	private static List<CollectableItem> items = new ArrayList<CollectableItem>();

	/**
	 * Level Constructor
	 * 
	 * @param world
	 * @param player
	 * @param path
	 */
	public Level(Labyrint world, Player player, String path) {
		this.player = player;
		this.path = path;
		this.world = world;

		width = world.getWidth();
		height = world.getHeight();
		numberOfTilesX = width / TileManager.tileSize;
		numberOfTilesY = height / TileManager.tileSize;
		tilesMap = new int[numberOfTilesY][numberOfTilesX];
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
		clearLists();
		resetPlayer();
		loadLevelCsvFile();
		loadObjectsIntoMap();
		loadPlayerIntoMap();
		loadEnemiesIntoMap();
		loadBackgroundMusic();
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
				textIds = new String[numberOfTilesX];
				numberIds = new int[numberOfTilesX];

				int i = 0;
				while (i < csvSingleLineIds.length && i < numberOfTilesX) {
					textIds[i] = csvSingleLineIds[i];
					i++;
				}
				for (int id = 0; id < textIds.length; id++) {
					if (textIds[id] == null)
						textIds[id] = "0";
					numberIds[id] = (NumberUtils.isNumber(textIds[id])) ? Integer.parseInt(textIds[id]) : 0;
				}

				tilesMap[row++] = numberIds;
				if (row == numberOfTilesY)
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
		for (int y = 0; y < numberOfTilesY; y++) {
			for (int x = 0; x < numberOfTilesX; x++) {
				if (tilesMap[y][x] == TileManager.DIAMOND_TILE_ID) {
					tilesMap[y][x] = 1;

					int diamondPoints = (int) world.random(1, 10);

					Sprite diamondSprite = new Sprite(FolderLocationsUtils.ITEMS_FOLDER + "diamond.png");
					Diamond diamond = new Diamond(diamondSprite, diamondPoints, world);

					items.add(diamond);

					// Plaats een diamand op je juiste positie in de map
					PVector vector = calculatePixelPosition(x, y);
					world.addGameObject(diamond, vector.x, vector.y);

				} else if (tilesMap[y][x] == TileManager.KEY_TILE_ID) {
					tilesMap[y][x] = 1;

					Sprite keySprite = new Sprite(FolderLocationsUtils.ITEMS_FOLDER + "key.png");
					Key key = new Key(keySprite, world);

					items.add(key);

					// Plaats een key op je juiste positie in de map
					PVector vector = calculatePixelPosition(x, y);
					world.addGameObject(key, vector.x, vector.y);
				}
			}
		}
		refreshTileMap();
	}

	/**
	 * Spawn alle enemies op de map
	 */
	private void loadEnemiesIntoMap() {
		for (int y = 0; y < numberOfTilesY; y++) {
			for (int x = 0; x < numberOfTilesX; x++) {
				if (tilesMap[y][x] == TileManager.WIZARD_SPAWN_POINT) {
					tilesMap[y][x] = 1;

					float direction = DirectionUtils.calculateFacingDirection(world, y, x);
					Wizard wizard = new Wizard(world, direction);

					enemies.add(wizard);

					PVector vector = calculatePixelPosition(x, y);
					world.addGameObject(wizard, vector.x, vector.y);
				} else if (tilesMap[y][x] == TileManager.RANGER_SPAWN_POINT) {
					tilesMap[y][x] = 1;

					float direction = DirectionUtils.calculateFacingDirection(world, y, x);
					Ranger ranger = new Ranger(world, direction);

					enemies.add(ranger);

					PVector vector = calculatePixelPosition(x, y);
					world.addGameObject(ranger, vector.x, vector.y);
				}
			}
		}
		refreshTileMap();
	}

	/**
	 * Spawn de player op de spawn positie
	 */
	private void loadPlayerIntoMap() {
		for (int y = 0; y < numberOfTilesY; y++) {
			for (int x = 0; x < numberOfTilesX; x++) {
				if (tilesMap[y][x] == TileManager.PLAYER_SPAWN_POINT) {
					tilesMap[y][x] = 1;

					// Spawn de player op de juiste positie in de map
					PVector vector = calculatePixelPosition(x, y);
					player.setX(vector.x);
					player.setY(vector.y);
				}
			}
		}
		refreshTileMap();
	}

	/**
	 * Laad en speel af de achtergrond muziek van de level, heeft een level geen
	 * achtergrond muziek? Gebruik dan een standaard achtergrond muziekje...
	 */
	private void loadBackgroundMusic() {
		if (sound == null)
			sound = new Sound(world, FolderLocationsUtils.SOUND_FOLDER + "default_music.mp3");

		sound.loop(-1);
		sound.play();
	}

	/**
	 * Verwijder elk enemy en item op de map en lists
	 */
	private void clearLists() {
		for (BaseEnemy enemy : enemies) {
			world.deleteGameObject(enemy);
		}
		for (CollectableItem item : items) {
			world.deleteGameObject(item);
		}
		enemies.clear();
		items.clear();
	}

	/**
	 * Reset de speler
	 */
	private void resetPlayer() {
		player.getInventory().clearInventory();
		player.setLives(Player.MAX_LIVES);
		world.getUserInterface().resetScore();
		world.getUserInterface().updateKeys();
		world.getUserInterface().updateHealth();
	}

	/**
	 * Refresh de TileMap na het wijzigen van de tilesMap[][] array
	 */
	private void refreshTileMap() {
		world.setTileMap(generateTileMap());
	}

	/**
	 * genereer op basis van de tileTypes en tilesMap een map met tiles.
	 * 
	 * @return TileMap
	 */
	private TileMap generateTileMap() {
		return new TileMap(TileManager.tileSize, TileManager.tileTypes, tilesMap);
	}

	/**
	 * Bereken de pixel positie voor alle objecten / entities
	 * 
	 * @param x de horizontale index positie in de tileMap[][]
	 * @param y de vertical index positie in de tileMap[][]
	 * @return vector wordt de pixel positie in opgeslagen
	 */
	private PVector calculatePixelPosition(int x, int y) {
		PVector vector = new PVector();

		float xPos = ((float) x * TileManager.tileSize);
		float yPos = ((float) y * TileManager.tileSize);
		vector.x = xPos;
		vector.y = yPos;

		return vector;
	}

}
