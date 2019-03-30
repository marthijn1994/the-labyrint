package nl.han.ica.oopd.labyrint.items;

import nl.han.ica.oopd.labyrint.Labyrint;
import nl.han.ica.oopd.labyrint.Player;
import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.Sprite;

public class Key extends CollectableItem implements ICollidableWithGameObjects {

	private Labyrint world;

	public Key(Sprite sprite, Labyrint world) {
		super(sprite);
		this.world = world;
	}

	@Override
	public void collect(Player player) {
		player.getInventory().addKey(this);
		world.deleteGameObject(this);
	}

	@Override
	public void update() {

	}

}
