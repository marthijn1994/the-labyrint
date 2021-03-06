package nl.han.ica.oopd.labyrint.utils;

/**
 * @author Marthijn Kip
 * @author Werner van Voorts
 */

public class FolderLocationsUtils {

	/**
	 * Alle media folder locations
	 */

	// root folder voor alle media elementen
	public static final String MEDIA_ROOT = "src/main/java/nl/han/ica/oopd/labyrint/media/";

	// specifieke folder voor alle geluiden
	public static final String SOUND_FOLDER = MEDIA_ROOT.concat("sounds/");

	// specifieke folder voor alle items
	public static final String ITEMS_FOLDER = MEDIA_ROOT.concat("items/");

	// speciefieke folder voor alle tiles
	public static final String TILES_FOLDER = MEDIA_ROOT.concat("tiles/");

	// speciefeke folder voor alle obstakels
	public static final String OBSTAKELS_FOLDER = MEDIA_ROOT.concat("obstacles/");

	// speciefeke folder voor alle obstakels
	public static final String ENEMIES_FOLDER = MEDIA_ROOT.concat("enemies/");

	// specieke folder voor alle level csv bestanden
	public static final String LEVEL_FOLDER = MEDIA_ROOT.concat("levels/");

}
