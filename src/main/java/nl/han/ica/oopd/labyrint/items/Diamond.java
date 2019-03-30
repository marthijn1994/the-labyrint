package nl.han.ica.oopd.labyrint.items;

/**
 * @author Marthijn Kip
 * @author Werner van Voorts
 */

import nl.han.ica.oopd.labyrint.Labyrint;
import nl.han.ica.oopd.labyrint.Player;
import nl.han.ica.oopd.labyrint.utils.FolderLocationsUtils;
import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.sound.Sound;

public class Diamond extends CollectableItem implements ICollidableWithGameObjects {

	protected int puntenWaarde;
	private Labyrint world;

	public Diamond(Sprite sprite, int puntenWaarde, Labyrint world) {
		super(sprite, new Sound(world, FolderLocationsUtils.SOUND_FOLDER + "collected.mp3"));
		this.puntenWaarde = puntenWaarde;
		this.world = world;
	}

	@Override
	public void collect(Player player) {
		System.out.println("Je krijgt nu zoveel punten: " + puntenWaarde);
		world.getUserInterface().updateScore(puntenWaarde);
		player.getInventory().addDiamond(this);
		world.deleteGameObject(this);
	}

	@Override
	public void update() {
	}

}
