package nl.han.ica.oopd.labyrint;

import nl.han.ica.oopd.labyrint.tiles.CactusTile;
import nl.han.ica.oopd.labyrint.tiles.DeurTile;
import nl.han.ica.oopd.labyrint.tiles.MuurTile;
import nl.han.ica.oopd.labyrint.tiles.VloerTile;
import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.tile.Tile;
import nl.han.ica.oopg.tile.TileMap;
import nl.han.ica.oopg.tile.TileType;
import nl.han.ica.oopg.view.EdgeFollowingViewport;
import nl.han.ica.oopg.view.View;
import processing.core.PApplet;

@SuppressWarnings("serial")
public class Labyrint extends GameEngine {
	
	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
	
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
	 	Sprite DiamandSprite = new Sprite("src/main/java/nl/han/ica/oopd/labyrint/media/diamond.png");
    	Diamand d1 = new Diamand(DiamandSprite, 10, this);
    	addGameObject(d1, 50, 150);
		createWindow(WIDTH, HEIGHT);
		initializeTileMap();
	}

	@Override
	public void update() {
		
	}
	
	private void createWindow(int width, int height) {
		View view = new View(width, height);
		setView(view);
        size(width, height);
	}
	
	private void initializeTileMap() {
		
		// Muur Tile
		Sprite muurSprite = new Sprite("src/main/java/nl/han/ica/oopd/labyrint/media/muur.png");
        TileType<MuurTile> muurTileType = new TileType<>(MuurTile.class, muurSprite);
        
        // Vloer Tile
		Sprite vloerSprite = new Sprite("src/main/java/nl/han/ica/oopd/labyrint/media/vloer.png");
        TileType<VloerTile> vloerTileType = new TileType<>(VloerTile.class, vloerSprite);
        
        // Deur Tile
        Sprite deurSprite = new Sprite("src/main/java/nl/han/ica/oopd/labyrint/media/deur.png");
        TileType<DeurTile> deurTileType = new TileType<DeurTile>(DeurTile.class, deurSprite);
        
        // Cactus Tile
	 	Sprite CactusSprite = new Sprite("src/main/java/nl/han/ica/oopd/labyrint/tiles/cactus.png");
	 	TileType<CactusTile> cactusTielType = new TileType<CactusTile>(CactusTile.class, CactusSprite);
        
        TileType[] tileTypes = { muurTileType, vloerTileType, deurTileType, cactusTielType };
        int tileSize = 50;
        int tilesMap[][] = {
                {0, 0, 0, 0, 0, 0, 0, 1, 2, 1, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0},
                {1, 1, 3, 3, 1, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0}
        };
        tileMap = new TileMap(tileSize, tileTypes, tilesMap);
	}

}
