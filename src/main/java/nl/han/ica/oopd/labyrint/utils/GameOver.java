package nl.han.ica.oopd.labyrint.utils;

import nl.han.ica.oopd.labyrint.Labyrint;
import nl.han.ica.oopd.labyrint.Player;
import nl.han.ica.oopd.labyrint.level.LevelManager;
import nl.han.ica.oopg.objects.GameObject;
import processing.core.PGraphics;

public class GameOver extends GameObject {

	private int LAYERABOVEOTHERITEMS = 99;
	private Labyrint world;
	private boolean gameOver;
	
	public void setGameOver() {
		this.gameOver = true;
	}

	public GameOver(Labyrint world){
		this.world = world;
		gameOver = false;
	}
	
	@Override
	public void draw(PGraphics g) {
		if (gameOver) { 
		g.fill(0);
		g.rect(0,0, Labyrint.WIDTH, Labyrint.HEIGHT, LAYERABOVEOTHERITEMS);
		g.fill(255);
		g.text("Game over! Press 'r' to restart.", Labyrint.WIDTH /2, Labyrint.HEIGHT /2, LAYERABOVEOTHERITEMS);
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
