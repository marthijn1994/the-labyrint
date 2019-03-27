package nl.han.ica.oopd.labyrint;

import nl.han.ica.oopd.labyrint.items.Diamand;
import nl.han.ica.oopd.labyrint.items.Key;
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
	private static final int HEIGHT = 600;
	public static final String MEDIA_FOLDER = "src/main/java/nl/han/ica/oopd/labyrint/media/";
	
	private Player player;

	public static void main(String[] args) {
		String[] processingArgs = {"nl.han.ica.oopd.labyrint.Labyrint"};
		Labyrint mySketch = new Labyrint();

        PApplet.runSketch(processingArgs, mySketch);
	}

	@Override
	public void setupGame() {
		initializePlayer();
		initializeDiamonds();
		initializeKeys();
		
		createWindow(WIDTH, HEIGHT);
		initializeBackgroundMusic();
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
	
	private void initializePlayer() {
		player = new Player(this);
		addGameObject(player, 55, 0);
	}
	
	private void initializeDiamonds() {
		Sprite DiamandSprite = new Sprite(MEDIA_FOLDER + "diamond.png");
		Diamand d1 = new Diamand(DiamandSprite, 10, this);
		Diamand d2 = new Diamand(DiamandSprite, 20, this);
		
		addGameObject(d1, 100, 150);
		addGameObject(d2, 150, 50);
	}
	
	private void initializeKeys() {
		Sprite keySprite = new Sprite(MEDIA_FOLDER + "key.png");
		Key key = new Key(keySprite, this);
		
		addGameObject(key, 50, 100);
	}
	
	private void initializeBackgroundMusic() {
		Sound backgroundSound = new Sound(this, MEDIA_FOLDER + "sounds/backgroundMusic.mp3");
		backgroundSound.loop(-1);
		backgroundSound.play();
	}
	
	@SuppressWarnings("rawtypes")
	private void initializeTileMap() {
		
		// Muur Tile
		Sprite muurSprite = new Sprite(MEDIA_FOLDER + "muur.png");
        TileType<MuurTile> muurTileType = new TileType<>(MuurTile.class, muurSprite);
        
        // Vloer Tile
		Sprite vloerSprite = new Sprite(MEDIA_FOLDER + "vloer.png");
        TileType<VloerTile> vloerTileType = new TileType<>(VloerTile.class, vloerSprite);
        
        // Deur Tile
        Sprite deurSprite = new Sprite(MEDIA_FOLDER + "deur.png");
        TileType<DeurTile> deurTileType = new TileType<DeurTile>(DeurTile.class, deurSprite);
        
        // Cactus Tile
	 	Sprite CactusSprite = new Sprite(MEDIA_FOLDER + "cactus.png");
	 	TileType<CactusTile> cactusTileType = new TileType<CactusTile>(CactusTile.class, CactusSprite);
        
		TileType[] tileTypes = { muurTileType, vloerTileType, deurTileType, cactusTileType };
        int tileSize = 50;
        int tilesMap[][] = {
                {0, 1, 0, 0, 0, 0, 0, 1, 2, 1, 0, 0, 0, 0, 0, 0},
                {0, 1, 2, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0},
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
