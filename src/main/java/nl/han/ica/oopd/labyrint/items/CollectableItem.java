package nl.han.ica.oopd.labyrint.items;

/**
 * @author Marthijn Kip
 * @author Werner van Voorts
 */

import java.util.List;

import nl.han.ica.oopd.labyrint.Player;
import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;
import nl.han.ica.oopg.sound.Sound;

public abstract class CollectableItem extends SpriteObject implements ICollidableWithGameObjects {

	protected Sound sound;

	public CollectableItem(Sprite sprite) {
		super(sprite);
	}

	public CollectableItem(Sprite sprite, Sound sound) {
		super(sprite);
		this.sound = sound;
	}

	protected abstract void collect(Player player);

	/**
	 * Bij botsing met een item, voeg de item toe aan de inventory van de speler
	 */
	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		for (GameObject g : collidedGameObjects) {
			if (g instanceof Player) {
				Player player = (Player) g;

				// play sound if sound is not equal to null
				if (sound != null) {
					sound.play();
				}

				collect(player);
			}
		}
	}
}
