package de.schoko.vocab.resources;

import java.awt.Font;

public class Style {
	public static final String font = "Tahoma Tahoma";
	
	public static Font INFO_FONT = null;
	public static Font LIST_BUTTON_FONT = null;
	public static Font BUTTON_FONT = null;
	public static Font INPUT_FIELD_FONT = null;
	
	public static void load() {
		INFO_FONT = new Font(font, Font.PLAIN, 20);
		LIST_BUTTON_FONT = new Font(font, Font.PLAIN, 18);
		BUTTON_FONT = new Font(font, Font.PLAIN, 14);
		INPUT_FIELD_FONT = new Font(font, Font.PLAIN, 20);
	}
}
