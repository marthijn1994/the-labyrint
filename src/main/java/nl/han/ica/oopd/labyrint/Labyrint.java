package nl.han.ica.oopd.labyrint;

import nl.han.ica.oopd.labyrint.items.Diamand;
import nl.han.ica.oopd.labyrint.items.Key;
import nl.han.ica.oopd.labyrint.level.Level;
import nl.han.ica.oopd.labyrint.level.LevelManager;
import nl.han.ica.oopd.labyrint.tiles.CactusTile;
import nl.han.ica.oopd.labyrint.tiles.DeurTile;
import nl.han.ica.oopd.labyrint.tiles.MuurTile;
import nl.han.ica.oopd.labyrint.tiles.VloerTile;
import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.sound.Sound;
import nl.han.ica.oopg.tile.TileMap;
import nl.han.ica.oopg.tile.TileType;
import nl.han.ica.oopg.view.View;
import processing.core.PApplet;

@SuppressWarnings("serial")
public class Labyrint extends GameEngine {

	private static final int WIDTH = 800;
	private static final int HEIGHT = 800;
	public static final String MEDIA_FOLDER = "src/main/java/nl/han/ica/oopd/labyrint/media/";

	private Player player;
	private LevelManager levelManager;

	public static void main(String[] args) {
		String[] processingArgs = { "nl.han.ica.oopd.labyrint.Labyrint" };
		Labyrint mySketch = new Labyrint();

		PApplet.runSketch(processingArgs, mySketch);
	}

	@Override
	public void setupGame() {
		initializePlayer();
		
		createWindow(WIDTH, HEIGHT);
		initializeBackgroundMusic();
		initializeLevel();
	}

	@Override
	public void update() {

	}

	private void createWindow(int width, int height) {
		View view = new View(width, height);
		setView(view);
		size(width, height);
	}

	private void initializePlayer() {
		player = new Player(this);
		addGameObject(player, 55, 0);
	}

	private void initializeBackgroundMusic() {
		Sound backgroundSound = new Sound(this, MEDIA_FOLDER + "sounds/backgroundMusic.mp3");
		backgroundSound.loop(-1);
		backgroundSound.play();
	}

	private void initializeLevel() {
		levelManager = new LevelManager(this);
		
		// Alle levels
		levelManager.addLevel(new Level(this, player, "level1.csv"));
		
		try {
			levelManager.loadLevel(LevelManager.START_LEVEL);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
