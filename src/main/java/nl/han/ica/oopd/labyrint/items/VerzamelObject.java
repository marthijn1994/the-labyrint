package nl.han.ica.oopd.labyrint.items;

import java.util.List;

import nl.han.ica.oopd.labyrint.Player;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;
import nl.han.ica.oopg.sound.Sound;

public abstract class VerzamelObject extends SpriteObject {
	
	private Sound sound;

	public VerzamelObject(Sprite sprite) {
		super(sprite);
	}
	
	public VerzamelObject(Sprite sprite, Sound sound) {
		super(sprite);
		this.sound = sound;
	}
	
	public abstract void verzamelen(Player player);

	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		 for (GameObject g : collidedGameObjects) {
	            if (g instanceof Player) {
	            	Player player = (Player) g;
	            	
	            	// play sound if sound is not equal to null
	            	if (sound != null) {
	            		sound.play();
	            	}
	            	
	            	this.verzamelen(player);
	            }
	        }
	    }
}

