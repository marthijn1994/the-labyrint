package nl.han.ica.oopd.labyrint.items;

import java.util.List;

import nl.han.ica.oopd.labyrint.Player;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;

public abstract class VerzamelObject extends SpriteObject {

	public VerzamelObject(Sprite sprite) {
		super(sprite);
		// TODO Auto-generated constructor stub
	}
	
	public abstract void verzamelen(Player player);

	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		 for (GameObject g : collidedGameObjects) {
	            if (g instanceof Player) {
	            	Player player = (Player) g;
	            	this.verzamelen(player);
	            }
	        }
	    }
}

