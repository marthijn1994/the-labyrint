package nl.han.ica.oopd.labyrint.utils;

public class NumberUtils {
	
	public static boolean isNumber(String nummer) {
		return nummer.matches("-?\\d+(\\.\\d+)?");
	}

}
