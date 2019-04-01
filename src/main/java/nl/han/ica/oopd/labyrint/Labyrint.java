package nl.han.ica.oopd.labyrint;

/**
 * @author Marthijn Kip
 * @author Werner van Voorts
 */

import nl.han.ica.oopd.labyrint.level.Level;
import nl.han.ica.oopd.labyrint.level.LevelManager;
import nl.han.ica.oopd.labyrint.ui.UserInterface;
import nl.han.ica.oopd.labyrint.utils.GameOver;
import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.view.View;
import processing.core.PApplet;

@SuppressWarnings("serial")
public class Labyrint extends GameEngine {

	public static final int WIDTH = 1200;
	public static final int HEIGHT = 800;

	private Player player;
	private UserInterface userInterface;
	private GameOver gameOver;

	public static void main(String[] args) {
		String[] processingArgs = { "nl.han.ica.oopd.labyrint.Labyrint" };
		Labyrint mySketch = new Labyrint();

		PApplet.runSketch(processingArgs, mySketch);
	}

	@Override
	public void setupGame() {
		initializePlayer();
		initializeUserInterface();
		createWindow(WIDTH, HEIGHT);
		initializeLevel();
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
		view.setWorldSize(width, height - UserInterface.USER_INTERFACE_HEIGHT);
		setView(view);
		size(width, height);
	}

	/**
	 * Maak alle levels aan en laad de eerste level.
	 */
	private void initializeLevel() {
		// Alle levels
		LevelManager.addLevel(new Level(this, player, "test_map.csv"));
		LevelManager.addLevel(new Level(this, player, "level_1.csv", "background_music.mp3"));

		// Start de eerste level
		LevelManager.loadLevel(LevelManager.START_LEVEL);
	}

	private void initializeUserInterface() {
		userInterface = new UserInterface(player.getInventory());
		addDashboard(userInterface);
		gameOver = new GameOver(this);
		addDashboard(gameOver);
	}

	public UserInterface getUserInterface() {
		return userInterface;
	}
	
	public GameOver getGameOver() {
		return gameOver;
	}
}
