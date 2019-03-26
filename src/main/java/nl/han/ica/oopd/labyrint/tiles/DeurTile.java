package nl.han.ica.oopd.labyrint.tiles;

import nl.han.ica.oopd.labyrint.IOpenAble;
import nl.han.ica.oopd.labyrint.Key;
import nl.han.ica.oopd.labyrint.Labyrint;
import nl.han.ica.oopd.labyrint.Player;
import nl.han.ica.oopg.collision.CollidedTile;
import nl.han.ica.oopg.objects.Sprite;
import processing.core.PVector;

public class DeurTile extends SolideTile implements IOpenAble {

	public DeurTile(Sprite sprite) {
		super(sprite);
	}
	
	@Override
	public void open(Player player, Labyrint world, CollidedTile collidedTile) {
		if (player.getKeys().size() > 0) {
			Key key = player.getKeys().get(0);
			
			PVector vector = world.getTileMap().getTilePixelLocation(collidedTile.getTile());
			world.getTileMap().setTile((int) vector.x + 50, (int) vector.y + 50, 0);
			
			player.getKeys().remove(key);
		} else {
			System.out.println("Geen sleutels...");
		}
	}

}
