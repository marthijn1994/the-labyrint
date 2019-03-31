package nl.han.ica.oopd.labyrint.tiles;

/**
 * @author Marthijn Kip
 * @author Werner van Voorts
 */

import nl.han.ica.oopd.labyrint.Labyrint;
import nl.han.ica.oopd.labyrint.Player;
import nl.han.ica.oopg.collision.CollidedTile;
import nl.han.ica.oopg.objects.Sprite;
import processing.core.PVector;

public class DoorTile extends SolideTile implements IOpenAble {

	public DoorTile(Sprite sprite) {
		super(sprite);
	}

	/**
	 * Het openen van een deur, de speler heeft een sleutel hiervoor nodig. Sleutel
	 * wordt verwijdert uit de inventory van de speler. De deurtile wordt vervangen
	 * door een non-solide vloertile.
	 * 
	 * @param player
	 * @param world
	 * @param collidedTile
	 */
	@Override
	public void open(Player player, Labyrint world, CollidedTile collidedTile) {
		final PVector vector;

		if (world.key == ' ') {
			if (player.getInventory().getKeys().size() > 0) {
				player.getInventory().getKeys().remove(0);
				world.getUserInterface().updateKeys();
				vector = world.getTileMap().getTilePixelLocation(collidedTile.getTile());
				int tileWidth = collidedTile.getTile().getSprite().getWidth();
				int tileHeight = collidedTile.getTile().getSprite().getHeight();

				world.getTileMap().setTile((int) vector.x / tileWidth, (int) vector.y / tileHeight, 1);
			} else {
				System.out.println("Je hebt geen sleutels om een deur te openen!");
			}
		}
	}

}
