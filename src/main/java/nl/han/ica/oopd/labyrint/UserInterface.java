package nl.han.ica.oopd.labyrint;

import nl.han.ica.oopd.labyrint.utils.FolderLocationsUtils;
import nl.han.ica.oopd.labyrint.utils.LabyrintSpriteObject;
import nl.han.ica.oopd.labyrint.utils.LabyrintTextObject;
import nl.han.ica.oopg.dashboard.Dashboard;
import nl.han.ica.oopg.objects.Sprite;

public class UserInterface extends Dashboard {
	
	private Inventory inventory;
	private int score;
	private LabyrintTextObject drawScore;
	private LabyrintTextObject drawKeys;
	
	
	private Sprite scoreSprite;
	private LabyrintSpriteObject objectScoreSprite;
	private Sprite keySprite;
	private LabyrintSpriteObject objectKeySprite;
	
	private final int LAYERABOVEBACKGROUND = 1;
	private final int DISTANCEBETWEENDASHBOARDITEMS = 50;

	public UserInterface(Inventory inventory) {
		super(0, Labyrint.HEIGHT - Labyrint.USERINTERFACEHEIGHT,Labyrint.WIDTH, Labyrint.USERINTERFACEHEIGHT);
		this.inventory = inventory;
		setDashboardSpritesAndObjects();
		setBackground(255,0,255);
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
		scoreSprite = new Sprite (FolderLocationsUtils.ITEMS_FOLDER + "diamond.png");
		objectScoreSprite = new LabyrintSpriteObject(scoreSprite);
		keySprite = new Sprite (FolderLocationsUtils.ITEMS_FOLDER + "key.png");
		objectKeySprite = new LabyrintSpriteObject (keySprite);
		addGameObject(objectScoreSprite, LAYERABOVEBACKGROUND);
		objectScoreSprite.setX(DISTANCEBETWEENDASHBOARDITEMS*0); // Note: this line is a formality (X is already 0), but it makes it easier to change later.
		addGameObject(objectKeySprite, LAYERABOVEBACKGROUND);
		objectKeySprite.setX(DISTANCEBETWEENDASHBOARDITEMS*2);
	}
	
	public void setScoreCounter() {
		drawScore = new LabyrintTextObject("" + getScore(), Labyrint.USERINTERFACEHEIGHT);
		drawScore.setX(DISTANCEBETWEENDASHBOARDITEMS);
		resetScore();
		addGameObject(drawScore, LAYERABOVEBACKGROUND);
	}
	
	public void setKeyCounter() {
		drawKeys = new LabyrintTextObject("" + getAmountOfKeys(), Labyrint.USERINTERFACEHEIGHT);
		drawKeys.setX(DISTANCEBETWEENDASHBOARDITEMS*3);
		addGameObject(drawKeys, LAYERABOVEBACKGROUND);
	}
}