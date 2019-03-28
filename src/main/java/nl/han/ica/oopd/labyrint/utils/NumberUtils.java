package nl.han.ica.oopd.labyrint.utils;

/**
 * @author Marthijn Kip
 * @author Werner van Voorts
 */

public class NumberUtils {
	
	// Kijkt of de string die meegegeven is, alleen nummers bevatten.
	public static boolean isNumber(String nummer) {
		return nummer.matches("-?\\d+(\\.\\d+)?");
	}

}
