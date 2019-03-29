package nl.han.ica.oopd.labyrint;

import nl.han.ica.oopg.dashboard.Dashboard;
import nl.han.ica.oopg.objects.TextObject;

public class UserInterface extends Dashboard {
	private Inventory inventory;
	private int score;
	private TextObject drawScore = new TextObject("TEST"+ getScore(), 25);
	
	UserInterface(Inventory inventory){
		super(0, Labyrint.HEIGHT - Labyrint.USERINTERFACEHEIGHT,Labyrint.WIDTH, Labyrint.USERINTERFACEHEIGHT);
		this.inventory = inventory;
		resetScore();
		drawScore.setForeColor(255, 255, 255, 255);
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
	
	public void resetScore() {
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