package nl.han.ica.oopd.labyrint.level;

/**
 * @author Marthijn Kip
 * @author Werner van Voorts
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import nl.han.ica.oopd.labyrint.Labyrint;
import nl.han.ica.oopd.labyrint.Player;
import nl.han.ica.oopd.labyrint.enemies.Ranger;
import nl.han.ica.oopd.labyrint.enemies.Wizard;
import nl.han.ica.oopd.labyrint.items.Diamond;
import nl.han.ica.oopd.labyrint.items.Key;
import nl.han.ica.oopd.labyrint.tiles.TileManager;
import nl.han.ica.oopd.labyrint.utils.DirectionUtils;
import nl.han.ica.oopd.labyrint.utils.FolderLocationsUtils;
import nl.han.ica.oopd.labyrint.utils.NumberUtils;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.sound.Sound;
import nl.han.ica.oopg.tile.TileMap;

public class Level {

	private Labyrint world;
	private Player player;
	private Sound sound;

	private int width;
	private int height;
	private int numberOfTilesX;
	private int numberOftilesY;
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

		width = world.getWidth();
		height = world.getHeight();
		numberOfTilesX = width / TileManager.tileSize;
		numberOftilesY = height / TileManager.tileSize;
		tilesMap = new int[numberOftilesY][numberOfTilesX];
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
				if (row == numberOftilesY)
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
		for (int y = 0; y < numberOftilesY; y++) {
			for (int x = 0; x < numberOfTilesX; x++) {
				if (tilesMap[y][x] == TileManager.DIAMOND_TILE_ID) {
					tilesMap[y][x] = 1;

					int diamondPoints = (int) world.random(1, 10);

					Sprite diamondSprite = new Sprite(FolderLocationsUtils.ITEMS_FOLDER + "diamond.png");
					Diamond diamond = new Diamond(diamondSprite, diamondPoints, world);

					// Plaats een diamand op je juiste positie in de map
					float xPos = ((float) x * TileManager.tileSize);
					float yPos = ((float) y * TileManager.tileSize);
					world.addGameObject(diamond, xPos, yPos);
				} else if (tilesMap[y][x] == TileManager.KEY_TILE_ID) {
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
		refreshTileMap();
	}
	
	/**
	 * Spawn alle enemies op de map
	 */
	private void loadEnemiesIntoMap() {
		for (int y = 0; y < numberOftilesY; y++) {
			for (int x = 0; x < numberOfTilesX; x++) {
				if (tilesMap[y][x] == TileManager.WIZARD_SPAWN_POINT) {
					tilesMap[y][x] = 1;
					
					float direction = DirectionUtils.calculateDirection(world, y, x);
					Wizard wizard = new Wizard(world, direction);
					
					float xPos = ((float) x * TileManager.tileSize) - ((wizard.getSpriteSize() - TileManager.tileSize) / 2.0f);
					float yPos = ((float) y * TileManager.tileSize) - ((wizard.getSpriteSize() - TileManager.tileSize) / 2.0f);
					world.addGameObject(wizard, xPos, yPos);
				} else if (tilesMap[y][x] == TileManager.RANGER_SPAWN_POINT) {
					tilesMap[y][x] = 1;

					float direction = DirectionUtils.calculateDirection(world, y, x);
					Ranger ranger = new Ranger(world, direction);
					
					float xPos = ((float) x * TileManager.tileSize) - ((ranger.getSpriteSize() - TileManager.tileSize) / 2.0f);
					float yPos = ((float) y * TileManager.tileSize) - ((ranger.getSpriteSize() - TileManager.tileSize) / 2.0f);
					world.addGameObject(ranger, xPos, yPos);
				}
			}
		}
		refreshTileMap();
	}

	/**
	 * Spawn de player op de spawn positie
	 */
	private void loadPlayerIntoMap() {
		for (int y = 0; y < numberOftilesY; y++) {
			for (int x = 0; x < numberOfTilesX; x++) {
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

}
