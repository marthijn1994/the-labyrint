package nl.han.ica.oopd.labyrint.enemies;

import java.util.List;

import nl.han.ica.oopd.labyrint.Labyrint;
import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.AnimatedSpriteObject;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;

public class BaseEnemy extends AnimatedSpriteObject implements ICollidableWithGameObjects {
	
	private Labyrint world;

	public BaseEnemy(Labyrint world, Sprite sprite, int totalFrames) {
		super(sprite, totalFrames);
		
		this.world = world;
	}

	@Override
	public void update() {
		
	}

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		
	}	

}
