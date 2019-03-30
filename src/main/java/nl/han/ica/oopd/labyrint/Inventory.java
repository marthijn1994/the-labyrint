package nl.han.ica.oopd.labyrint;

/**
 * @author Marthijn Kip
 * @author Werner van Voorts
 */

import java.util.ArrayList;
import java.util.List;

import nl.han.ica.oopd.labyrint.items.Diamond;
import nl.han.ica.oopd.labyrint.items.Key;

public class Inventory {

	private List<Diamond> diamonds;
	private List<Key> keys;

	public Inventory() {
		diamonds = new ArrayList<Diamond>();
		keys = new ArrayList<Key>();
	}

	public List<Diamond> getDiamonds() {
		return this.diamonds;
	}

	public List<Key> getKeys() {
		return this.keys;
	}

	public void addDiamond(Diamond diamond) {
		diamonds.add(diamond);
	}

	public void addKey(Key key) {
		keys.add(key);
	}

	@Override
	public String toString() {
		return String.format("Aantal keys: %s, aantal diamands: %s", keys.size(), diamonds.size());
	}

}
