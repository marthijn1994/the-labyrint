package nl.han.ica.oopd.labyrint.level;

/**
 * @author Marthijn Kip
 * @author Werner van Voorts
 */

import java.util.ArrayList;
import java.util.List;

public class LevelManager {

	/**
	 * De standaard start level index
	 */
	public static final int START_LEVEL = 0;
	
	/**
	 * Een list om alle levels in op te slaan.
	 */
	private static List<Level> levels = new ArrayList<Level>();

	/**
	 * Laad een level
	 * 
	 * @param levelIndex
	 */
	public static void loadLevel(int levelIndex) {
		if (levelIndex >= levels.size() || levelIndex < 0) {
			// Als de level niet bestaat, laad de eerste level
			Level level = levels.get(START_LEVEL);
			level.load();
		} else {
			Level level = levels.get(levelIndex);

			if (level != null)
				level.load();
		}
	}

	/**
	 * Voeg een level to aan de level list
	 * 
	 * @param level
	 */
	public static void addLevel(Level level) {
		levels.add(level);
	}

}
