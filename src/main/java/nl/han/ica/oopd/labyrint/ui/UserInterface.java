package nl.han.ica.oopd.labyrint.ui;

import nl.han.ica.oopd.labyrint.Inventory;
import nl.han.ica.oopd.labyrint.Labyrint;
import nl.han.ica.oopd.labyrint.Player;
import nl.han.ica.oopd.labyrint.utils.FolderLocationsUtils;
import nl.han.ica.oopd.labyrint.utils.LabyrintSpriteForDashboard;
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
	private LabyrintTextObject drawHealth;

	private Sprite scoreSprite;
	private LabyrintSpriteForDashboard dashboardScoreSprite;
	private Sprite keySprite;
	private LabyrintSpriteForDashboard dashboardKeySprite;
	private Sprite healthSprite;
	private LabyrintSpriteForDashboard dashboardHealthSprite;

	public UserInterface(Inventory inventory) {
		super(0, Labyrint.HEIGHT - USER_INTERFACE_HEIGHT, Labyrint.WIDTH, USER_INTERFACE_HEIGHT);
		this.inventory = inventory;
		setDashboardSpritesAndObjects();
		setBackground(255, 0, 255);
		setScoreCounter();
		setKeyCounter();
		setHealthCounter();
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

	public void updateHealth() {
		drawHealth.setText("" + Player.getLives());
	}

	protected void setDashboardSpritesAndObjects() {
		scoreSprite = new Sprite(FolderLocationsUtils.ITEMS_FOLDER + "diamond.png");
		dashboardScoreSprite = new LabyrintSpriteForDashboard(scoreSprite);
		keySprite = new Sprite(FolderLocationsUtils.ITEMS_FOLDER + "key.png");
		dashboardKeySprite = new LabyrintSpriteForDashboard(keySprite);
		healthSprite = new Sprite(FolderLocationsUtils.ITEMS_FOLDER + "health.png");
		dashboardHealthSprite = new LabyrintSpriteForDashboard(healthSprite);

		addGameObject(dashboardScoreSprite, LAYER_ABOVE_BACKGROUND);
		dashboardScoreSprite.setX(DISTANCE_BETWEEN_DASHBOARD_ITEMS * 0); // Note: this line is a formality (X is already
																			// 0),
		// but it makes it easier to change later.
		addGameObject(dashboardKeySprite, LAYER_ABOVE_BACKGROUND);
		dashboardKeySprite.setX(DISTANCE_BETWEEN_DASHBOARD_ITEMS * 2);

		addGameObject(dashboardHealthSprite, LAYER_ABOVE_BACKGROUND);
		dashboardHealthSprite.setX(DISTANCE_BETWEEN_DASHBOARD_ITEMS * 4);
	}

	protected void setScoreCounter() {
		drawScore = new LabyrintTextObject("" + getScore(), USER_INTERFACE_HEIGHT);
		drawScore.setX(DISTANCE_BETWEEN_DASHBOARD_ITEMS);
		resetScore();
		addGameObject(drawScore, LAYER_ABOVE_BACKGROUND);
	}

	protected void setKeyCounter() {
		drawKeys = new LabyrintTextObject("" + getAmountOfKeys(), USER_INTERFACE_HEIGHT);
		drawKeys.setX(DISTANCE_BETWEEN_DASHBOARD_ITEMS * 3);
		addGameObject(drawKeys, LAYER_ABOVE_BACKGROUND);
	}

	protected void setHealthCounter() {
		drawHealth = new LabyrintTextObject("" + Player.getLives(), USER_INTERFACE_HEIGHT);
		drawHealth.setX(DISTANCE_BETWEEN_DASHBOARD_ITEMS * 5);
		addGameObject(drawHealth, LAYER_ABOVE_BACKGROUND);
	}

}