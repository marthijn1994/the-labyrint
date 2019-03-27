package nl.han.ica.oopd.labyrint.level;

import java.util.ArrayList;
import java.util.List;

import nl.han.ica.oopd.labyrint.Labyrint;

public class LevelManager {

	public static final int START_LEVEL = 0;

	private Labyrint world;

	private List<Level> levels = new ArrayList<Level>();

	public LevelManager(Labyrint world) {
		this.world = world;
	}

	public void loadLevel(int levelIndex) throws Exception {
		Level level = levels.get(levelIndex);

		if (level != null)
			world.setTileMap(level.generateTileMap());
		else
			throw new Exception("Level bestaat niet!");
	}

	public List<Level> getLevels() {
		return this.levels;
	}

	public void addLevel(Level level) {
		levels.add(level);
	}

}
