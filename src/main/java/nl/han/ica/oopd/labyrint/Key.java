package nl.han.ica.oopd.labyrint;

import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.Sprite;

public class Key  extends VerzamelObject implements ICollidableWithGameObjects {
	
	private Labyrint world;

	public Key(Sprite sprite, Labyrint world) {
		super(sprite);
		this.world = world;
	}

	@Override
	public void verzamelen(Player player) {
		player.getInventory().addKey(this);
		world.deleteGameObject(this);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
