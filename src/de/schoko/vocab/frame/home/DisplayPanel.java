package de.schoko.vocab.frame.home;

import java.awt.Component;
import java.awt.Font;
import java.io.File;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;

import de.schoko.vocab.Vocab;
import de.schoko.vocab.exceptions.FileParseException;
import de.schoko.vocab.resources.Strings;

public class DisplayPanel extends JPanel {
	private static final long serialVersionUID = -4131792053126979265L;
	
	public DisplayPanel() {
		super();
		this.setLayout(null);

		Font font = getFont();
		setFont(font.deriveFont(15.0f));
	}
	
	public void display(Vocab vocab) {
		this.removeAll();
		
		this.setBorder(new TitledBorder(Strings.MENU_VOCAB_SETTINGS));
		
		JCheckBox languageCheckbox = new JCheckBox();
		languageCheckbox.addActionListener((event) -> {
			vocab.setDisplayFirst(languageCheckbox.isSelected());
		});
		placeAt(languageCheckbox, 2, -5, 2, 20, 20);
		
		ButtonGroup orderButtonGroup = new ButtonGroup();
		String[] orderButtons = {Strings.OPTION_FORWARDS, Strings.OPTION_BACKWARDS, Strings.OPTION_RANDOM};
		for (int i = 0; i < orderButtons.length; i++) {
			String s = orderButtons[i];
			final int type = i;
			JRadioButton radioButton = new JRadioButton(s);
			radioButton.addActionListener((event) -> {
				vocab.setOrder(type);
			});
			orderButtonGroup.add(radioButton);
			radioButton.setFont(getFont());
			radioButton.setFocusPainted(false);
			if (i == vocab.getOrder()) {
				radioButton.setSelected(true);
			}
			this.placeAt(radioButton, 3, i * 105 + 125, 2, 100, 25);
		}
		displayText(new String[]{
				vocab.getName(),
				Strings.fillIn(Strings.DISPLAY_WORDS, vocab.getVocabulary().size()),
				Strings.fillIn(Strings.DISPLAY_ASKED_LANGUAGE, Strings.getTranslation("LANGUAGE_" + vocab.getFirstLanguage())),
				Strings.DISPLAY_ORDER + ":",
				""});
	}
	
	public void displayException(File file, FileParseException e) {
		this.removeAll();
		this.setBorder(new TitledBorder(Strings.MENU_SETTINGS));
		displayText(new String[]{
				file.getAbsolutePath(),
				Strings.EXCEPTION_COULDNT_LOAD_FILE,
				Strings.ADVICE_EDIT,
				Strings.fillIn(Strings.fillIn(Strings.EXCEPTION_IN_LINE, e.getLine()), e.getError())
		});
	}
	
	public void displayException(File file, String exceptionMessage) {
		this.removeAll();
		this.setBorder(new TitledBorder(Strings.MENU_SETTINGS));
		displayText(new String[]{
				file.getAbsolutePath(),
				Strings.EXCEPTION_COULDNT_LOAD_FILE,
				Strings.ADVICE_EDIT,
				Strings.fillIn(Strings.EXCEPTION, exceptionMessage)
		});
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
