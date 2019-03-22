package nl.han.ica.oopd.labyrint;

import nl.han.ica.oopd.labyrint.tiles.MuurTile;
import nl.han.ica.oopd.waterworld.tiles.BoardsTile;
import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.tile.TileMap;
import nl.han.ica.oopg.tile.TileType;
import nl.han.ica.oopg.view.EdgeFollowingViewport;
import nl.han.ica.oopg.view.View;
import processing.core.PApplet;

@SuppressWarnings("serial")
public class Labyrint extends GameEngine {
	
	private static final int WIDTH = 500; 				// Breedte van de window
	private static final int HEIGHT = 500;	// 16:9 beeldverhouding
	
	private Player player;

	public static void main(String[] args) {
		String[] processingArgs = {"nl.han.ica.oopd.labyrint.Labyrint"};
		Labyrint mySketch = new Labyrint();

        PApplet.runSketch(processingArgs, mySketch);
	}

	@Override
	public void setupGame() {
		player = new Player(this);
		addGameObject(player, 100, 100);
		
		createViewWithoutViewport(WIDTH, HEIGHT);
		initializeTileMap();
	}

	@Override
	public void update() {
		
	}
	
	private void createViewWithoutViewport(int screenWidth, int screenHeight) {
        View view = new View(screenWidth, screenHeight);
        setView(view);
        size(screenWidth, screenHeight);
    }
	
	private void initializeTileMap() {
		Sprite muurSprite = new Sprite("src/main/java/nl/han/ica/oopd/labyrint/media/muur.png");
        TileType<MuurTile> muurTileType = new TileType<>(MuurTile.class, muurSprite);
        
        TileType[] tileTypes = { muurTileType };
        int tileSize = 50;
        int tilesMap[][] = {
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
        tileMap = new TileMap(tileSize, tileTypes, tilesMap);
	}

}
