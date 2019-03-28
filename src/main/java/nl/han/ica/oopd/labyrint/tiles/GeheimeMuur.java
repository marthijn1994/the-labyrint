package nl.han.ica.oopd.labyrint.tiles;

import nl.han.ica.oopd.labyrint.Labyrint;
import nl.han.ica.oopd.labyrint.Player;
import nl.han.ica.oopg.collision.CollidedTile;
import nl.han.ica.oopg.objects.Sprite;
import processing.core.PVector;

public class GeheimeMuur extends SolideTile implements IOpenAble {

	public GeheimeMuur(Sprite sprite) {
		super(sprite);
	}

	/**
	 * Het openen van een geheime muur, de muurtile wordt vervangen door een
	 * vloertile.
	 * 
	 * @param player
	 * @param world
	 * @param collidedTile
	 */
	@Override
	public void open(Player player, Labyrint world, CollidedTile collidedTile) {
		final PVector vector;

		if (world.key == ' ') {
			vector = world.getTileMap().getTilePixelLocation(collidedTile.getTile());
			int tileWidth = collidedTile.getTile().getSprite().getWidth();
			int tileHeight = collidedTile.getTile().getSprite().getHeight();

			world.getTileMap().setTile((int) vector.x / tileWidth, (int) vector.y / tileHeight, 1);
		}
	}

}
