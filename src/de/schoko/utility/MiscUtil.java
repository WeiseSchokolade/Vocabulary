package de.schoko.utility;

/**
 * @author WeiseSchokolade
 */
public class MiscUtil {
	/**
	 * Makes the current thread sleep for the given
	 * amount of milliseconds and catches the
	 * InterruptedException
	 * 
	 * @param millis Time to sleep in milliseconds
	 */
	public static void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {}
	}
}
