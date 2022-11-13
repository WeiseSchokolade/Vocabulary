package de.schoko.vocab.frame.home;

import java.awt.Component;
import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import de.schoko.vocab.Preloader;
import de.schoko.vocab.Settings;
import de.schoko.vocab.resources.InternalResourceList;
import de.schoko.vocab.resources.Strings;

public class SettingsPanel extends JPanel {
	private static final long serialVersionUID = -4131792053126979265L;
	
	public SettingsPanel() {
		super();
		this.setLayout(null);

		Font font = getFont();
		setFont(font.deriveFont(15.0f));
		this.display();
	}
	
	public void display() {
		this.removeAll();
		this.setBorder(new TitledBorder(Strings.MENU_SETTINGS));
		
		final Settings settings = Preloader.get().getSettings();
		
		JComboBox<String> jcomboBox = new JComboBox<>(InternalResourceList.SUPPORTED_LANGUAGES);
		
		int index = -1;
		for (int i = 0; i < InternalResourceList.SUPPORTED_LANGUAGES.length; i++) {
			String s = InternalResourceList.SUPPORTED_LANGUAGES[i];
			if (s.equalsIgnoreCase(settings.getLanguageCode())) {
				index = i;
			}
		}
		jcomboBox.setSelectedIndex(index);
		jcomboBox.addActionListener((event) -> {
			settings.setLanguage(InternalResourceList.SUPPORTED_LANGUAGES[jcomboBox.getSelectedIndex()]);
		});
		jcomboBox.setFocusable(false);
		
		placeAt(jcomboBox, 0, 150, 2, 50, 20);
		
		displayText(new String[]{
				Strings.DISPLAY_USED_LANGUAGE,
				Strings.fillIn(Strings.DISPLAY_WORKSPACE_LOCATION, settings.getWorkspaceLocation().replaceAll("\\\\", "/")),
				""});
	}
	
	public void placeAt(Component c, int index) {
		placeAt(c, index, 20, 0, 2000, 25);
	}
	
	public void placeAt(Component c, int index, int xOffset, int yOffset, int width, int height) {
		c.setBounds(15 + xOffset, index * 25 + 15 + yOffset, width, height);
		this.add(c);
	}
	
	public void displayText(String[] text) {
		for (int i = 0; i < text.length; i++) {
			String s = text[i];
			JLabel label = new JLabel(s);
			label.setFont(getFont());
			placeAt(label, i);
		}
	}
}

