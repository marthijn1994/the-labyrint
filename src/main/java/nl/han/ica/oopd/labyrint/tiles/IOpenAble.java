package nl.han.ica.oopd.labyrint.tiles;

import nl.han.ica.oopd.labyrint.Labyrint;
import nl.han.ica.oopd.labyrint.Player;
import nl.han.ica.oopg.collision.CollidedTile;

public interface IOpenAble {
	
	public void open(Player player, Labyrint world, CollidedTile collidedTile);

}
