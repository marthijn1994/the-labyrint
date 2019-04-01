package nl.han.ica.oopd.labyrint.ui;

import nl.han.ica.oopd.labyrint.Inventory;
import nl.han.ica.oopd.labyrint.Labyrint;
import nl.han.ica.oopd.labyrint.utils.FolderLocationsUtils;
import nl.han.ica.oopd.labyrint.utils.LabyrintSpriteObject;
import nl.han.ica.oopd.labyrint.utils.LabyrintTextObject;
import nl.han.ica.oopg.dashboard.Dashboard;
import nl.han.ica.oopg.objects.Sprite;

public class UserInterface extends Dashboard {

	public static final int USER_INTERFACE_HEIGHT = 50;
	private final int LAYER_ABOVE_BACKGROUND = 1;
	private final int DISTANCE_BETWEEN_DASHBOARD_ITEMS = 50;

	private Inventory inventory;
	private int score;
	private LabyrintTextObject drawScore;
	private LabyrintTextObject drawKeys;

	private Sprite scoreSprite;
	private LabyrintSpriteObject objectScoreSprite;
	private Sprite keySprite;
	private LabyrintSpriteObject objectKeySprite;

	public UserInterface(Inventory inventory) {
		super(0, Labyrint.HEIGHT - USER_INTERFACE_HEIGHT, Labyrint.WIDTH, USER_INTERFACE_HEIGHT);
		this.inventory = inventory;
		setDashboardSpritesAndObjects();
		setBackground(255, 0, 255);
		setScoreCounter();
		setKeyCounter();
	}

	/**
	 * 
	 * @return score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * 
	 * @param score
	 */
	public void updateScore(int score) {
		this.score += score;
		drawScore.setText("" + getScore());
	}

	public void resetScore() {
		score = 0;
		drawScore.setText("" + getScore());
	}

	public void updateKeys() {
		drawKeys.setText("" + getAmountOfKeys());
	}

	/**
	 * 
	 * @return aantal keys
	 */
	public int getAmountOfKeys() {
		return inventory.getKeys().size();
	}

	public void setDashboardSpritesAndObjects() {
		scoreSprite = new Sprite(FolderLocationsUtils.ITEMS_FOLDER + "diamond.png");
		objectScoreSprite = new LabyrintSpriteObject(scoreSprite);
		keySprite = new Sprite(FolderLocationsUtils.ITEMS_FOLDER + "key.png");
		objectKeySprite = new LabyrintSpriteObject(keySprite);
		
		addGameObject(objectScoreSprite, LAYER_ABOVE_BACKGROUND);
		objectScoreSprite.setX(DISTANCE_BETWEEN_DASHBOARD_ITEMS * 0); // Note: this line is a formality (X is already 0),
																	// but it makes it easier to change later.
		addGameObject(objectKeySprite, LAYER_ABOVE_BACKGROUND);
		objectKeySprite.setX(DISTANCE_BETWEEN_DASHBOARD_ITEMS * 2);
	}

	public void setScoreCounter() {
		drawScore = new LabyrintTextObject("" + getScore(), USER_INTERFACE_HEIGHT);
		drawScore.setX(DISTANCE_BETWEEN_DASHBOARD_ITEMS);
		resetScore();
		addGameObject(drawScore, LAYER_ABOVE_BACKGROUND);
	}

	public void setKeyCounter() {
		drawKeys = new LabyrintTextObject("" + getAmountOfKeys(), USER_INTERFACE_HEIGHT);
		drawKeys.setX(DISTANCE_BETWEEN_DASHBOARD_ITEMS * 3);
		addGameObject(drawKeys, LAYER_ABOVE_BACKGROUND);
	}
}