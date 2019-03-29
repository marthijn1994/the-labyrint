package nl.han.ica.oopd.labyrint;

import nl.han.ica.oopd.labyrint.utils.LabyrintTextObject;
import nl.han.ica.oopg.dashboard.Dashboard;

public class UserInterface extends Dashboard {
	private Inventory inventory;
	private int score;
	private LabyrintTextObject drawScore;
	
	public UserInterface(Inventory inventory){
		//super(0, Labyrint.HEIGHT - Labyrint.USERINTERFACEHEIGHT,Labyrint.WIDTH, Labyrint.USERINTERFACEHEIGHT);
		super(0,0, Labyrint.WIDTH, Labyrint.USERINTERFACEHEIGHT);
		this.inventory = inventory;
		drawScore =  new LabyrintTextObject("TEST"+ getScore(), Labyrint.USERINTERFACEHEIGHT);
		resetScore();
		addGameObject(drawScore);
	}

	/**
	 * 
	 * @return
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
	
	public  void resetScore() {
		score = 0;
		drawScore.setText("" + getScore());
	}
	
	public int getAantalKey() {
		return inventory.getKeys().size();
	}
//	// Hebben we getters/setters nodig voor hoogte/breedte, of lossen we dat op met tilemap?
//	public void drawScore() {
//		drawScore.setText("" + getScore());
//		//drawScore.draw();
//	}
}