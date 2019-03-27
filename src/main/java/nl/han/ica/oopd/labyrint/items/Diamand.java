package nl.han.ica.oopd.labyrint.items;

import nl.han.ica.oopd.labyrint.Labyrint;
import nl.han.ica.oopd.labyrint.Player;
import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.sound.Sound;

public class Diamand extends VerzamelObject implements ICollidableWithGameObjects {
	
	protected int puntenWaarde;
	private Labyrint world;

	public Diamand(Sprite sprite, int puntenWaarde, Labyrint world) {
		super(sprite, new Sound(world, Labyrint.MEDIA_FOLDER + "sounds/collected.mp3"));
		this.puntenWaarde = puntenWaarde;
		this.world = world;
	}

	
	@Override
	public void verzamelen(Player player) {
		System.out.println("Je krijgt nu zoveel punten: " + puntenWaarde);
		
		player.getInventory().addDiamand(this);
		world.deleteGameObject(this);
	}

	@Override
	public void update() {
	}

}
