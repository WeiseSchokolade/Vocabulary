package de.schoko.vocab.resources;

public class Strings {
	public static final String WINDOW_ACTIVITY_PREFIX = "Vokabelabfrage - ",
			ACTIVITY_ASKING = "Abfrage von",
			ACTIVITY_EDITING = "Bearbeiten von",
			ACTIVITY_COMPLETE = "Ergebnisse von",
			ACTIVITY_MAIN_MENU = "Hauptmenü",
			ADVICE_EDIT = "Klicke auf Bearbeiten um die Datei zu bearbeiten.",
			BUTTON_BACK = "Zurück",
			BUTTON_CONFIRM = "Bestätigen",
			BUTTON_CONTINUE = "Weiter",
			BUTTON_EDIT = "Bearbeiten",
			BUTTON_ENTER = "Eingeben",
			BUTTON_NEW = "Neu",
			BUTTON_RELOAD = "Neu laden",
			BUTTON_START = "Starten",
			DISPLAY_CORRECT = "Richtig: %s (%s%)",
			DISPLAY_GRADE = "Gesamtnote: %s",
			DISPLAY_INFO = "Insgesamt: %s, Richtig: %s, Falsch: %s",
			DISPLAY_LANGUAGE = "Wörter werden auf %s abgefragt",
			DISPLAY_ORDER = "Reihenfolge",
			DISPLAY_TOTAL = "Insgesamt: %s",
			DISPLAY_WORDS = "%s Wörter",
			DISPLAY_WRONG = "Falsch: %s (%s%)",
			EXCEPTION = "Fehler: %s",
			EXCEPTION_IN_LINE = "Fehler in Zeile %s: %s",
			EXCEPTION_COULDNT_LOAD_FILE = "Datei konnte nicht geladen werden.",
			EXCEPTION_FILE_ALREADY_EXISTS = "%s.vocab existiert bereits!",
			EXCEPTION_INVALID_FILE_NAME = "Ungültiger Dateiname.",
			EXCEPTION_NO_VOCAB_FOUND = "Keine Vokabeln vorhanden!",
			EXCEPTION_UNKNOWN = "Unbekannt",
			GRADE_A = "1",
			GRADE_B = "2",
			GRADE_C = "3",
			GRADE_D = "4",
			GRADE_E = "5",
			GRADE_F = "6",
			HEADER_CORRECT = "Korrekt",
			HEADER_ENTERED = "Eingegeben",
			LANGUAGE_FIRST = "Französisch",
			LANGUAGE_SECOND = "Deutsch",
			LOADING = "Lädt...",
			MENU_SETTINGS = "Einstellungen",
			MENU_STATS = "Statistik",
			MENU_MISTAKES = "Fehler",
			MENUBAR_FILE = "Datei",
			MENUBAR_FILE_SAVE = "Speichern",
			MENUBAR_FILE_CHECK = "Überprüfen",
			MENUBAR_FILE_CLOSE = "Schließen",
			OPTION_FORWARDS = "Vorwärts",
			OPTION_BACKWARDS = "Rückwärts",
			OPTION_RANDOM = "Zufällig",
			PROCESS_CLOSING_FILE = "Datei schließen",
			PROCESS_CREATING_FILE = "Neue Datei erstellen",
			PROMPT_TRANSLATE = "Übersetze die folgende Phrase in %se:",
			PROMPT_FILE_NAME = "Bitte geben Sie einen Namen für die neue Datei ein",
			QUESTION_SAVE = "Speichern?",
			QUESTION_OVERWRITE_FILE = "Datei überschreiben?";
	
	/**
	 * Replaces the first occasion of {@code %s} with the given {@code Object#toString()} Method
	 * @param s The string to replace the value in
	 * @param value The value to replace %s with
	 * @return A new string with the replaced value
	 */
	public static <T> String fillIn(String s, T value) {
		return s.replaceFirst("%s", value.toString());
	}
	
	public static String fillIn(String s, String... values) {
		String ret = s;
		for (int i = 0; i < values.length; i++) {
			ret = ret.replaceFirst("%s", values[i]);
		}
		return ret;
	}
}
