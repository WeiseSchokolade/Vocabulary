package de.schoko.utility;

/**
 * A static classs used for methods not existing in [] Arrays
 * 
 * @author WeiseSchokolade
 *
 */
public class ArrayUtility {
	/**
	 * Checks whether an array contains an object
	 * 
	 * @param arr The array to be checked
	 * @param obj The object looked for in the array.
	 * @return Whether the array contains the object.
	 */
	public static <T> boolean contains(T[] arr, T obj) {
		if (arr == null) return false;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i].equals(obj)) {
				return true;
			};
		}
		return false;
	}
	
	/**
	 * Converts the array to a String by using each object's
	 * {@link Object#toString())} method.
	 * 
	 * @param arr The array to convert
	 * @return A String
	 */
	public static <T> String toString(T[] arr) {
		if (arr == null) return null;
		String ret = "[";
		for (int i = 0; i < arr.length; i++) {
			ret += arr[i].toString();
			if (i + 1 != arr.length) {
				ret += ", ";
			}
		}
		ret += "]";
		return ret;
	}
	

	/**
	 * Converts the array to a String by iterating over each array and using the object's
	 * {@link Object#toString())} method
	 * 
	 * @param arr The array to convert
	 * @return A string
	 */
	public static <T> String toString(T[][] arr) {
		if (arr == null) return null;
		String ret = "[";
		for (int i = 0; i < arr.length; i++) {
			ret += "[";
			for (int j = 0; j < arr[i].length; j++) {
				ret += arr[i][j].toString();
				if (j + 1 != arr[i].length) {
					ret += ", ";
				}
			}
			ret += "]";
			if (i + 1 != arr.length) {
				ret += ", ";
			}
		}
		ret += "]";
		return ret;
	}
	

	/**
	 * Converts the array to a String by iterating over each array and iterating over each array and using the object's
	 * {@link Object#toString())} method
	 * 
	 * @param arr The array to convert
	 * @return A string
	 */
	public static <T> String toString(T[][][] arr) {
		if (arr == null) return null;
		String ret = "[";
		for (int i = 0; i < arr.length; i++) {
			ret += "[";
			for (int j = 0; j < arr[i].length; j++) {
				ret += "[";
				for (int k = 0; k < arr[i][j].length; k++) {
					ret += arr[i][j][k].toString();
					if (k + 1 != arr[i][j].length) {
						ret += ", ";
					}
				}
				ret += "]";
				if (j + 1 != arr[i].length) {
					ret += ", ";
				}
			}
			ret += "]";
			if (i + 1 != arr.length) {
				ret += ", ";
			}
		}
		ret += "]";
		return ret;
	}
}
