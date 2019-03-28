package nl.han.ica.oopd.labyrint;

import nl.han.ica.oopd.labyrint.level.Level;
import nl.han.ica.oopd.labyrint.level.LevelManager;
import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.view.View;
import processing.core.PApplet;

@SuppressWarnings("serial")
public class Labyrint extends GameEngine {

	private static final int WIDTH = 1700;
	private static final int HEIGHT = 800;

	private Player player;

	public static void main(String[] args) {
		String[] processingArgs = { "nl.han.ica.oopd.labyrint.Labyrint" };
		Labyrint mySketch = new Labyrint();

		PApplet.runSketch(processingArgs, mySketch);
	}

	@Override
	public void setupGame() {
		player = new Player(this);
		addGameObject(player, 0, 0);

		createWindow(WIDTH, HEIGHT);
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

	private void initializeLevel() {
		// Alle levels
		LevelManager.addLevel(new Level(this, player, "level1.csv"));
		LevelManager.addLevel(new Level(this, player, "level2.csv"));

		// Start de eerste level
		LevelManager.loadLevel(LevelManager.START_LEVEL);
	}

}
