package nl.han.ica.oopd.labyrint.ui;

import nl.han.ica.oopd.labyrint.Labyrint;
import nl.han.ica.oopd.labyrint.Player;
import nl.han.ica.oopd.labyrint.level.LevelManager;
import nl.han.ica.oopg.dashboard.Dashboard;
import processing.core.PGraphics;

public class GameOver extends Dashboard {

	private int LAYER_ABOVE_OTHER_ITEMS = 99;
	private Labyrint world;
	private boolean gameOver;
	private final int TEXTSIZE_FOR_GAME_OVER = 50;

	public void setGameOver() {
		this.gameOver = true;
	}

	public GameOver(Labyrint world) {
		super(0, 0, Labyrint.WIDTH, Labyrint.HEIGHT);
		setBackground(0, 0, 0);
		this.world = world;
		gameOver = false;
	}

	@Override
	public void draw(PGraphics g) {
		if (gameOver) {
			super.draw(g);
			g.textAlign(g.CENTER);
			g.textSize(TEXTSIZE_FOR_GAME_OVER);
			g.text("Game over! Press 'R' to restart.", Labyrint.WIDTH / 2, Labyrint.HEIGHT / 2,
					LAYER_ABOVE_OTHER_ITEMS);
		}
	}

	@Override
	public void update() {
		if (world.key == 'r' && gameOver) {
			LevelManager.loadLevel(Player.CURRENT_LEVEL);
			gameOver = false;
		}
	}

}
