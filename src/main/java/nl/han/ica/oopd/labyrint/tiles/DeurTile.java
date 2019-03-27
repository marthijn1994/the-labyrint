package nl.han.ica.oopd.labyrint.tiles;

import nl.han.ica.oopd.labyrint.Labyrint;
import nl.han.ica.oopd.labyrint.Player;
import nl.han.ica.oopg.collision.CollidedTile;
import nl.han.ica.oopg.objects.Sprite;
import processing.core.PVector;

public class DeurTile extends SolideTile {

	public DeurTile(Sprite sprite) {
		super(sprite);
	}
	
	public void open(Player player, Labyrint world, CollidedTile collidedTile) {
		PVector vector;
		
		if (world.key == ' ') {
			if (player.getInventory().getKeys().size() > 0) {
				player.getInventory().getKeys().remove(0); // verwijder een sleuten van de inventory
				vector = world.getTileMap().getTilePixelLocation(collidedTile.getTile());
				world.getTileMap().setTile((int)vector.x / 50, (int)vector.y / 50, 1);
			} else {
				System.out.println("Je hebt geen sleutels om een deur te openen!");
			}
		}
	}

}
