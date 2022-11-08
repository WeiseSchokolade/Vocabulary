package de.schoko.vocab.resources;

public class Strings {
	public static String WINDOW_ACTIVITY_PREFIX = "WINDOW_ACTIVITY_PREFIX",
			ACTIVITY_ASKING = "ACTIVITY_ASKING",
			ACTIVITY_EDITING = "ACTIVITY_EDITING",
			ACTIVITY_COMPLETE = "ACTIVITY_COMPLETE",
			ACTIVITY_MAIN_MENU = "MAIN_MENU",
			ADVICE_EDIT = "ADVICE_EDIT",
			BUTTON_BACK = "BUTTON_BACK",
			BUTTON_BACK_TO_MAIN_MENU = "BUTTON_BACK_TO_MAIN_MENU",
			BUTTON_CONFIRM = "BUTTON_CONFIRM",
			BUTTON_CONTINUE = "BUTTON_CONTINUE",
			BUTTON_EDIT = "BUTTON_EDIT",
			BUTTON_ENTER = "BUTTON_ENTER",
			BUTTON_NEW = "BUTTON_NEW",
			BUTTON_RELOAD = "BUTTON_RELOAD",
			BUTTON_RETRY = "BUTTON_RETRY",
			BUTTON_SETTINGS = "BUTTON_SETTINGS",
			BUTTON_START = "BUTTON_START",
			DISPLAY_CORRECT = "DISPLAY_CORRECT",
			DISPLAY_GRADE = "DISPLAY_GRADE",
			DISPLAY_INFO = "DISPLAY_INFO",
			DISPLAY_LANGUAGE = "DISPLAY_LANGUAGE",
			DISPLAY_ORDER = "DISPLAY_ORDER",
			DISPLAY_TOTAL = "DISPLAY_TOTAL",
			DISPLAY_WORDS = "DISPLAY_WORDS",
			DISPLAY_WRONG = "DISPLAY_WRONG",
			EXCEPTION = "EXCEPTION",
			EXCEPTION_IN_LINE = "EXCEPTION_IN_LINE",
			EXCEPTION_COULDNT_LOAD_FILE = "EXCEPTION_COULDNT_LOAD_FILE",
			EXCEPTION_FILE_ALREADY_EXISTS = "EXCEPTION_FILE_ALREADY_EXISTS",
			EXCEPTION_INVALID_FILE_NAME = "EXCEPTION_INVALID_FILE_NAME",
			EXCEPTION_NO_VOCAB_FOUND = "EXCEPTION_NO_VOCAB_FOUND",
			EXCEPTION_UNKNOWN = "EXCEPTION_UNKNOWN",
			GRADE_A = "GRADE_A",
			GRADE_B = "GRADE_B",
			GRADE_C = "GRADE_C",
			GRADE_D = "GRADE_D",
			GRADE_E = "GRADE_E",
			GRADE_F = "GRADE_F",
			HEADER_CORRECT = "HEADER_CORRECT",
			HEADER_ENTERED = "HEADER_ENTERED",
			LANGUAGE_FIRST = "LANGUAGE_FIRST",
			LANGUAGE_SECOND = "LANGUAGE_SECOND",
			LOADING = "LOADING",
			MENU_SETTINGS = "MENU_SETTINGS",
			MENU_STATS = "MENU_STATS",
			MENU_MISTAKES = "MENU_MISTAKES",
			MENUBAR_FILE = "MENUBAR_FILE",
			MENUBAR_FILE_SAVE = "MENUBAR_FILE_SAVE",
			MENUBAR_FILE_CHECK = "MENUBAR_FILE_CHECK",
			MENUBAR_FILE_CLOSE = "MENUBAR_FILE_CLOSE",
			OPTION_FORWARDS = "OPTION_FORWARDS",
			OPTION_BACKWARDS = "OPTION_BACKWARDS",
			OPTION_RANDOM = "OPTION_RANDOM",
			PROCESS_CLOSING_FILE = "PROCESS_CLOSING_FILE",
			PROCESS_CREATING_FILE = "PROCESS_CREATING_FILE",
			PROMPT_TRANSLATE = "PROMPT_TRANSLATE",
			PROMPT_FILE_NAME = "PROMPT_FILE_NAME",
			QUESTION_SAVE = "QUESTION_SAVE",
			QUESTION_OVERWRITE_FILE = "QUESTION_OVERWRITE_FILE";
	
	/**
	 * Replaces the first occasion of {@code %s} with the given {@code Object#toString()} Method
	 * @param s The string to replace the value in
	 * @param value The value to replace %s with
	 * @return A new string with the replaced value
	 */
	public static <T> String fillIn(String s, T value) {
		return s.replaceFirst("%s", value.toString());
	}

	/**
	 * <p>Replaces the first occasion of {@code %s} with the given String.
	 * The first occasion of {@code %s} is replaced with the first string from values
	 * </p>
	 * <p>
	 * Example: <br>
	 * Strings.fillIn("Hello %s, your age is %s!", new String[]{"foo", "42"})
	 * will return "Hello foo, your age is 42!"
	 * </p>
	 * 
	 * @param s The string to replace the value in
	 * @param value The value to replace %s with
	 * @return A new string with the replaced value
	 */
	public static String fillIn(String s, String... values) {
		String ret = s;
		for (int i = 0; i < values.length; i++) {
			ret = ret.replaceFirst("%s", values[i]);
		}
		return ret;
	}
}
