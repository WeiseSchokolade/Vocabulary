package de.schoko.vocab.resources;

public class Strings {
	public static String 
			ACTIVITY_ASKING = "ACTIVITY_ASKING",
			ACTIVITY_EDITING = "ACTIVITY_EDITING",
			ACTIVITY_COMPLETE = "ACTIVITY_COMPLETE",
			ACTIVITY_MAIN_MENU = "MAIN_MENU",
			ADVICE_EDIT = "ADVICE_EDIT",
			BUTTON_BACK = "BUTTON_BACK",
			BUTTON_BACK_TO_MAIN_MENU = "BUTTON_BACK_TO_MAIN_MENU",
			BUTTON_CONFIRM = "BUTTON_CONFIRM",
			BUTTON_CONTINUE = "BUTTON_CONTINUE",
			BUTTON_DELETE = "BUTTON_DELETE",
			BUTTON_EDIT = "BUTTON_EDIT",
			BUTTON_ENTER = "BUTTON_ENTER",
			BUTTON_INFO = "BUTTON_INFO",
			BUTTON_NEW = "BUTTON_NEW",
			BUTTON_RELOAD = "BUTTON_RELOAD",
			BUTTON_RETRY = "BUTTON_RETRY",
			BUTTON_SETTINGS = "BUTTON_SETTINGS",
			BUTTON_SKIP = "BUTTON_SKIP",
			BUTTON_START = "BUTTON_START",
			DISPLAY_ASKED_LANGUAGE = "DISPLAY_ASKED_LANGUAGE",
			DISPLAY_CORRECT = "DISPLAY_CORRECT",
			DISPLAY_GRADE = "DISPLAY_GRADE",
			DISPLAY_INFO = "DISPLAY_INFO",
			DISPLAY_ORDER = "DISPLAY_ORDER",
			DISPLAY_SKIPPED = "DISPLAY_SKIPPED",
			DISPLAY_TOTAL = "DISPLAY_TOTAL",
			DISPLAY_USED_LANGUAGE = "DISPLAY_USED_LANGUAGE",
			DISPLAY_WORDS = "DISPLAY_WORDS",
			DISPLAY_WORKSPACE_LOCATION = "DISPLAY_WORKSPACE_LOCATION",
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
			LANGUAGE_DE = "LANGUAGE_DE",
			LANGUAGE_EN = "LANGUAGE_EN",
			LANGUAGE_EO = "LANGUAGE_EO",
			LANGUAGE_ES = "LANGUAGE_ES",
			LANGUAGE_FR = "LANGUAGE_FR",
			LANGUAGE_IT = "LANGUAGE_IT",
			LANGUAGE_JA = "LANGUAGE_JA",
			LANGUAGE_KO = "LANGUAGE_KO",
			LANGUAGE_LA = "LANGUAGE_LA",
			LANGUAGE_PT = "LANGUAGE_PT",
			LANGUAGE_RU = "LANGUAGE_RU",
			LANGUAGE_TR = "LANGUAGE_TR",
			LANGUAGE_ZH = "LANGUAGE_ZH",
			LOADING = "LOADING",
			MENU_MISTAKES = "MENU_MISTAKES",
			MENU_SETTINGS = "MENU_SETTINGS",
			MENU_STATS = "MENU_STATS",
			MENU_STYLEGUIDE = "MENU_STYLEGUIDE",
			MENU_VOCAB_SETTINGS = "MENU_VOCAB_SETTINGS",
			MENUBAR_EDIT = "MENUBAR_EDIT",
			MENUBAR_EDIT_LANGUAGES = "MENUBAR_EDIT_LANGUAGES",
			MENUBAR_FILE = "MENUBAR_FILE",
			MENUBAR_FILE_SAVE = "MENUBAR_FILE_SAVE",
			MENUBAR_FILE_CHECK = "MENUBAR_FILE_CHECK",
			MENUBAR_FILE_CLOSE = "MENUBAR_FILE_CLOSE",
			MENUBAR_HELP = "MENUBAR_HELP",
			MENUBAR_HELP_STYLEGUIDE = "MENUBAR_HELP_STYLEGUIDE",
			OPTION_FIRST_LANGUAGE = "OPTION_FIRST_LANGUAGE",
			OPTION_FORWARDS = "OPTION_FORWARDS",
			OPTION_BACKWARDS = "OPTION_BACKWARDS",
			OPTION_RANDOM = "OPTION_RANDOM",
			OPTION_SECOND_LANGUAGE = "OPTION_SECOND_LANGUAGE",
			PROCESS_CLOSING_FILE = "PROCESS_CLOSING_FILE",
			PROCESS_CREATING_FILE = "PROCESS_CREATING_FILE",
			PROCESS_DELETING_FILE = "PROCESS_DELETING_FILE",
			PROMPT_FILE_NAME = "PROMPT_FILE_NAME",
			PROMPT_FILL_IN_GAP = "PROMPT_FILL_IN_GAP",
			PROMPT_TRANSLATE = "PROMPT_TRANSLATE",
			QUESTION_DELETE_FILE = "QUESTION_DELETE_FILE",
			QUESTION_OVERWRITE_FILE = "QUESTION_OVERWRITE_FILE",
			QUESTION_SAVE = "QUESTION_SAVE",
			WINDOW_ACTIVITY_PREFIX = "WINDOW_ACTIVITY_PREFIX";
	
	public static String 
			STYLEGUIDE = "STYLEGUIDE",
			ABOUT = "ABOUT";
	
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
	
	public static String getTranslation(String name) {
		try {
			return (String) Strings.class.getDeclaredField(name).get(null);
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		return null;
	}
}
