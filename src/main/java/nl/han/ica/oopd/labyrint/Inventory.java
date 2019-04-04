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

	/**
	 * Geeft een lijst met alle verzamelde diamanten terug
	 * 
	 * @return List<Diamond>
	 */
	public List<Diamond> getDiamonds() {
		return this.diamonds;
	}

	/**
	 * Geeft een lijst met alle verzamelde sleutels terug
	 * 
	 * @return List<Keu>
	 */
	public List<Key> getKeys() {
		return this.keys;
	}

	/**
	 * Voeg een nieuw diamand toe aan de inventory
	 * 
	 * @param diamond
	 */
	public void addDiamond(Diamond diamond) {
		diamonds.add(diamond);
	}

	/**
	 * Voeg een nieuw sleutel toe aan de inventory
	 * 
	 * @param key
	 */
	public void addKey(Key key) {
		keys.add(key);
	}

	/**
	 * 	Leeg de inventory
	 */
	public void clearInventory() {
		diamonds.clear();
		keys.clear();
	}

}
