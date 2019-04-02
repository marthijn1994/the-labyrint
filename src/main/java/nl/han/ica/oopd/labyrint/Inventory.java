package nl.han.ica.oopd.labyrint;

/**
 * @author Marthijn Kip
 * @author Werner van Voorts
 */

import java.util.ArrayList;
import java.util.List;

import nl.han.ica.oopd.labyrint.items.Diamond;
import nl.han.ica.oopd.labyrint.items.Key;
/**
 * 
 * @author Wern212
 *
 */
public class Inventory {

	private List<Diamond> diamonds;
	private List<Key> keys;
	
	public Inventory() {
		diamonds = new ArrayList<Diamond>();
		keys = new ArrayList<Key>();
	}
/**
 * 
 * @return
 */
	public List<Diamond> getDiamonds() {
		return this.diamonds;
	}
/**
 * 
 * @return
 */
	public List<Key> getKeys() {
		return this.keys;
	}
/**
 * 
 * @param diamond
 */
	public void addDiamond(Diamond diamond) {
		diamonds.add(diamond);
	}
/**
 * 
 * @param key
 */
	public void addKey(Key key) {
		keys.add(key);
	}
/**
 * 	
 */
	public void clearInventory() {
		diamonds.clear();
		keys.clear();
	}
	@Override
	public String toString() {
		return String.format("Aantal keys: %s, aantal diamands: %s", keys.size(), diamonds.size());
	}

}
