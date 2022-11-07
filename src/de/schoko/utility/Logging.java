package de.schoko.utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A simple framework for logging text to the
 * {@code System.out} stream
 * 
 * @author WeiseSchokolade
 */
public class Logging {
	/**
	 * This {@link java.time.format.DateTimeFormatter} is used
	 * for formatting the current time.
	 * 
	 * <p>The format can be configured using
	 * {@code Logging#setTimeFormat(String)}</p>
	 */
	private static DateTimeFormatter dateTimeFormatter = null;
	
	/**
	 * Returns a formatted string with the current time, formatted
	 * using {@code de.schoko.utility.Logging#dateTimeFormatter} 
	 * 
	 * @return A formatted string
	 * @see de.schoko.utility.Logging#dateTimeFormatter
	 * @see de.schoko.utility.Logging#setTimeFormat(String)
	 */
	private static String getTime() {
		if (dateTimeFormatter == null) {
			dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		}
		return dateTimeFormatter.format(LocalDateTime.now());
	}
	
	/**
	 * Sets the format the current time is formatted with
	 * 
	 * @param format A format used in {@link java.time.format.DateTimeFormatter#ofPattern(String)}
	 * @see java.time.format.DateTimeFormatter#ofPattern(String)
	 */
	public static void setTimeFormat(String format) {
		dateTimeFormatter = DateTimeFormatter.ofPattern(format);
	}
	
	/**
	 * Logs a message in a formatted way.
	 * <p>
	 * Example:
	 * <li>
	 * {@code Logging.log("Debug", "Lorem ipsum dolor sit amet.")}
	 * <br>
	 * Prints in the console
	 * <br>
	 * [17:05:37 / Debug] Lorem ipsum dolor sit amet.
	 * </li>
	 * </p>
	 * 
	 * @param type The type used in the resulting log
	 * @param info The message to be printed
	 */
	public static void log(String type, String message) {
		System.out.println("[" + getTime() + " / " + type + "] " + message);
	}
	
	/**
	 * Logs information about the exception using 
	 * {@link de.schoko.utility.Logging#log(String, String)} 
	 * and invokes {@link java.lang.Throwable#printStackTrace()}
	 * 
	 * @param e An exception
	 * 
	 * @see de.schoko.utility.Logging#log(String, String)
	 */
	public static void logException(Exception e) {
		log("Exception", "An exception occured!");
		e.printStackTrace();
	}
	
	/**
	 * Logs a message using {@link de.schoko.utility.Logging#log(String, String)}
	 * @param info The message to be logged
	 * 
	 * @see de.schoko.utility.Logging#log(String, String)
	 */
	public static void logInfo(String message) {
		log("info", message);
	}
}
