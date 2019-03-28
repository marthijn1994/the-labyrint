package nl.han.ica.oopd.labyrint.level;

import java.util.ArrayList;
import java.util.List;

public class LevelManager {

	public static final int START_LEVEL = 0;
	private static List<Level> levels = new ArrayList<Level>();

	public static void loadLevel(int levelIndex) {
		Level level = levels.get(levelIndex);

		if (level != null)
			level.load();
	}

	public static void addLevel(Level level) {
		levels.add(level);
	}

}
