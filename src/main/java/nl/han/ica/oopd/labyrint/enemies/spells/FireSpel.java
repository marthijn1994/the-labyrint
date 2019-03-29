package nl.han.ica.oopd.labyrint.enemies.spells;

import java.util.List;

import nl.han.ica.oopd.labyrint.utils.FolderLocationsUtils;
import nl.han.ica.oopg.collision.CollidedTile;
import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.collision.ICollidableWithTiles;
import nl.han.ica.oopg.objects.AnimatedSpriteObject;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;

public class FireSpel extends AnimatedSpriteObject implements ICollidableWithTiles, ICollidableWithGameObjects {

	public FireSpel() {
		super(new Sprite(FolderLocationsUtils.ENEMIES_FOLDER), 1);
	}

	@Override
	public void update() {
		
	}
	
	public void castSpel(float x, float y) {
		
	}

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tileCollisionOccurred(List<CollidedTile> collidedTiles) {
		// TODO Auto-generated method stub
		
	}

}
