package nl.han.ica.oopd.labyrint;

/**
 * @author Marthijn Kip
 * @author Werner van Voorts
 */

import nl.han.ica.oopd.labyrint.level.Level;
import nl.han.ica.oopd.labyrint.level.LevelManager;
import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.view.View;
import processing.core.PApplet;

@SuppressWarnings("serial")
public class Labyrint extends GameEngine {

	public static final int WIDTH = 1200;
	public static final int HEIGHT = 800;
	public static final int USERINTERFACEHEIGHT = 50;

	private Player player;

	public static void main(String[] args) {
		String[] processingArgs = { "nl.han.ica.oopd.labyrint.Labyrint" };
		Labyrint mySketch = new Labyrint();

		PApplet.runSketch(processingArgs, mySketch);
	}

	@Override
	public void setupGame() {
		initializePlayer();
		createWindow(WIDTH, HEIGHT);
		initializeLevel();
		initializeUserInterface();
	}

	@Override
	public void update() {

	}

	/**
	 * Maak een nieuwe player instantie aan
	 */
	private void initializePlayer() {
		player = new Player(this);
		addGameObject(player, 0, 0);
	}

	/**
	 * Maak een nieuwe window op basis van de meegegeven width & height
	 * 
	 * @param width
	 * @param height
	 */
	private void createWindow(int width, int height) {
		View view = new View(width, height);
		view.setWorldSize(WIDTH, HEIGHT - USERINTERFACEHEIGHT);
		setView(view);
		size(worldWidth, worldHeight);
	}

	/**
	 * Maak alle levels aan en laad de eerste level.
	 */
	private void initializeLevel() {
		// Alle levels
		LevelManager.addLevel(new Level(this, player, "level1.csv"));
		LevelManager.addLevel(new Level(this, player, "level2.csv", "level2BackgroundMusic.mp3"));

		// Start de eerste level
		LevelManager.loadLevel(LevelManager.START_LEVEL);
	}
	private void initializeUserInterface() {
		addDashboard(new UserInterface(player.getInventory()));
	}
}
