package de.schoko.utility;

/**
 * 
 * @author WeiseSchokolade
 */
public class StringUtility {
	/**
	 * Makes the first character of a string uppercase
	 * 
	 * @param s the original string
	 * @return the edited string
	 */
	public static String makeFirstCharacterUppercase(String s) {
		String firstCharacter = s.substring(0, 1);
		String otherCharacters = s.substring(1, s.length());
		return firstCharacter.toUpperCase() + otherCharacters;
	}
	
	/**
	 * Removes a file extension from a path if the path ends with
	 * the fileExtension
	 * 
	 * @param s The original string
	 * @param fileExtension The file extension to be removed
	 * @return The edited string
	 */
	public static String removeFileExtensionIfExists(String s, String fileExtension) {
		String removing = s;
		if (removing.endsWith(fileExtension)) {
			removing = removing.substring(0, removing.length() - fileExtension.length());
		}
		return removing;
	}

	/**
	 * Checks whether a String array contains a String. Formats the strings
	 * before comparing them.
	 * @param arr The array to be checked
	 * @param s The string looked for in the array
	 * @return Whether the array contains the string.
	 */
	public static boolean unifiedContains(String[] arr, String s) {
		if (arr == null) return false;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i].toLowerCase().trim().equals(s.toLowerCase().trim())) {
				return true;
			};
		}
		return false;
	}
	
	/**
	 * Converts a String array to a filepath string.
	 * 
	 * @param arr The array to be converted
	 * @return A string filepath representing the array
	 */
	public static String toFilePathString(String[] arr) {
		String path = "";
		for (int i = 0; i < arr.length; i++) {
			path += "/" + arr[i];
		}
		return path;
	}
	
}
