package de.schoko.vocab.frame.edit;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.schoko.vocab.Vocab;
import de.schoko.vocab.VocabData;
import de.schoko.vocab.frame.BottomInfoBar;
import de.schoko.vocab.resources.Strings;

public class EditBar extends BottomInfoBar {
	private static final long serialVersionUID = 7175854693815530514L;
	private JComboBox<String> firstLanguageDropdown;
	private JComboBox<String> secondLanguageDropdown;
	
	public EditBar(VocabData data) {
		super();
		firstLanguageDropdown = addLanguageDropdown(Strings.OPTION_FIRST_LANGUAGE, data.getFirstLanguageCode(), (event) -> {
			data.setFirstLanguageCode(Vocab.SUPPORTED_LANGUAGES[firstLanguageDropdown.getSelectedIndex()]);
		});
		secondLanguageDropdown = addLanguageDropdown(Strings.OPTION_SECOND_LANGUAGE, data.getSecondLanguageCode(), (event) -> {
			data.setSecondLanguageCode(Vocab.SUPPORTED_LANGUAGES[secondLanguageDropdown.getSelectedIndex()]);
		});
	}
	
	private JComboBox<String> addLanguageDropdown(String name, String selectedOption, ActionListener a) {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(new JLabel("  " + name), BorderLayout.WEST);
		
		int selected = -1;
		String[] languages = Vocab.SUPPORTED_LANGUAGES.clone();
		for (int i = 0; i < languages.length; i++) {
			if (languages[i].equalsIgnoreCase(selectedOption)) {
				selected = i;
			}
			languages[i] = Strings.getTranslation("LANGUAGE_" + languages[i].toUpperCase());
		}
		JComboBox<String> box = new JComboBox<>(languages);
		box.setSelectedIndex(selected);
		box.addActionListener(a);
		box.setFocusable(false);
		
		JPanel secondHalf = new JPanel();
		secondHalf.add(box);
		panel.add(secondHalf, BorderLayout.CENTER);
		this.add(panel);
		return box;
	}
}