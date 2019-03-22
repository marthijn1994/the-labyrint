package nl.han.ica.oopd.labyrint;

import nl.han.ica.oopd.labyrint.tiles.MuurTile;
import nl.han.ica.oopd.labyrint.tiles.VloerTile;
import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.tile.TileMap;
import nl.han.ica.oopg.tile.TileType;
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
		addGameObject(player, 50, 50);
		
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
		
		// Muur Tile
		Sprite muurSprite = new Sprite("src/main/java/nl/han/ica/oopd/labyrint/media/muur.png");
        TileType<MuurTile> muurTileType = new TileType<>(MuurTile.class, muurSprite);
        
        // Vloer Tile
		Sprite vloerSprite = new Sprite("src/main/java/nl/han/ica/oopd/labyrint/media/vloer.png");
        TileType<VloerTile> vloerTileType = new TileType<>(VloerTile.class, vloerSprite);
        
        TileType[] tileTypes = { muurTileType, vloerTileType };
        int tileSize = 50;
        int tilesMap[][] = {
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1},
                {0, 1, 0, 0, 0, 0, 0, 1, 0, 1},
                {0, 1, 0, 1, 1, 1, 0, 1, 0, 1},
                {0, 1, 1, 1, 0, 1, 0, 1, 0, 1},
                {0, 0, 0, 0, 0, 1, 0, 1, 0, 1},
                {1, 1, 1, 1, 1, 1, 0, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 0, 1, 0, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 0, 1},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 1}
        };
        tileMap = new TileMap(tileSize, tileTypes, tilesMap);
	}

}
