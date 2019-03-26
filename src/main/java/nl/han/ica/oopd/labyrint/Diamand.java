package nl.han.ica.oopd.labyrint;

import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.Sprite;

public class Diamand extends VerzamelObject implements ICollidableWithGameObjects {
	
	protected int puntenWaarde;
	private Labyrint world;

	public Diamand(Sprite sprite, int puntenWaarde, Labyrint world) {
		super(sprite);
		this.puntenWaarde = puntenWaarde;
		this.world = world;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void verzamelen(Player player) {
		// TODO Auto-generated method stub
		// Geef de speler puntenWaarde en verwijdered de diamand.
		System.out.println("Je krijgt nu zoveel punten: " + puntenWaarde);
		world.deleteGameObject(this);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

}
