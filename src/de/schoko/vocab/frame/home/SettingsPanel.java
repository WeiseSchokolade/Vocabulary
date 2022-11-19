package de.schoko.vocab.frame.home;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import de.schoko.vocab.Preloader;
import de.schoko.vocab.Settings;
import de.schoko.vocab.resources.InternalResourceList;
import de.schoko.vocab.resources.Strings;
import de.schoko.vocab.resources.Style;

public class SettingsPanel extends JPanel {
	private static final long serialVersionUID = -4131792053126979265L;
	private JPanel settingPanel;
	private JPanel bottomPanel;
	
	public SettingsPanel() {
		super();
		this.setLayout(new BorderLayout());
		
		settingPanel = new JPanel();
		settingPanel.setLayout(null);
		
		bottomPanel = new JPanel();
		bottomPanel.setLayout(new BorderLayout());
		
		JScrollPane scrollPane = new JScrollPane(settingPanel);
		scrollPane.setBorder(null);
		this.add(scrollPane, BorderLayout.CENTER);
		this.add(bottomPanel, BorderLayout.SOUTH);
		this.display();
	}
	
	private void display() {
		settingPanel.removeAll();
		bottomPanel.removeAll();
		
		setFont(Style.INFO_FONT);
		settingPanel.setBorder(new TitledBorder(Strings.MENU_SETTINGS));
		
		Settings settings = Preloader.get().getSettings().derive();
		String[] translatedOptions = InternalResourceList.SUPPORTED_LANGUAGES.clone();
		int index = -1;
		for (int i = 0; i < translatedOptions.length; i++) {
			if (translatedOptions[i].equalsIgnoreCase(settings.getLanguageCode())) {
				index = i;
			}
			translatedOptions[i] = Strings.getTranslation("LANGUAGE_" + translatedOptions[i].toUpperCase());
		}
		
		JComboBox<String> jcomboBox = new JComboBox<>(translatedOptions);
		jcomboBox.setSelectedIndex(index);
		jcomboBox.addActionListener((event) -> {
			settings.setLanguage(InternalResourceList.SUPPORTED_LANGUAGES[jcomboBox.getSelectedIndex()]);
		});
		jcomboBox.setFocusable(false);
		
		placeAt(jcomboBox, 0, 150, 2, 100, 20);
		
		displayText(new String[]{
				Strings.DISPLAY_USED_LANGUAGE,
				Strings.fillIn(Strings.DISPLAY_WORKSPACE_LOCATION, settings.getWorkspaceLocation().replaceAll("\\\\", "/")),
				""});
		
		JButton confirmButton = new JButton(Strings.BUTTON_CONFIRM);
		confirmButton.addActionListener((event) -> {
			Preloader.get().getSettings().deriveUpdate(settings);
		});
		bottomPanel.add(confirmButton, BorderLayout.EAST);
	}
	
	public void placeAt(Component c, int index) {
		placeAt(c, index, 20, 0, 2000, 25);
	}
	
	public void placeAt(Component c, int index, int xOffset, int yOffset, int width, int height) {
		c.setBounds(15 + xOffset, index * 25 + 15 + yOffset, width, height);
		settingPanel.add(c);
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

