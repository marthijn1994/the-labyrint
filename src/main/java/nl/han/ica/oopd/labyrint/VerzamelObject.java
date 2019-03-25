package nl.han.ica.oopd.labyrint;

import java.util.List;

import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;

public abstract class VerzamelObject extends SpriteObject {

	public VerzamelObject(Sprite sprite) {
		super(sprite);
		// TODO Auto-generated constructor stub
	}
	
	public abstract void verzamelen();

	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		 for (GameObject g : collidedGameObjects) {
	            if (g instanceof Player) {
	            	this.verzamelen();
	            }
	        }
	    }
}

