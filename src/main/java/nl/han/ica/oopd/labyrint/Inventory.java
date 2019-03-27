package nl.han.ica.oopd.labyrint;

import java.util.ArrayList;
import java.util.List;

public class Inventory {

	private List<Diamand> diamands;
	private List<Key> keys;

	public Inventory() {
		diamands = new ArrayList<Diamand>();
		keys = new ArrayList<Key>();
	}

	public List<Diamand> getDiamands() {
		return this.diamands;
	}

	public List<Key> getKeys() {
		return this.keys;
	}

	public void addDiamand(Diamand diamand) {
		diamands.add(diamand);
	}

	public void addKey(Key key) {
		keys.add(key);
	}
	
	@Override
	public String toString() {
		return String.format("Aantal keys: %s, aantal diamands: %s", keys.size(), diamands.size());
	}

}
