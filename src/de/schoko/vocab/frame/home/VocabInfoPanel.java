package de.schoko.vocab.frame.home;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import de.schoko.vocab.GenericDataHolder;
import de.schoko.vocab.Preloader;
import de.schoko.vocab.Vocab;
import de.schoko.vocab.exceptions.FileParseException;
import de.schoko.vocab.resources.Strings;
import de.schoko.vocab.resources.Style;
import de.schoko.utility.SwingUtility;

public class VocabInfoPanel extends JPanel {
	private static final long serialVersionUID = 1273196960981688182L;
	
	private DisplayPanel panel;
	private Vocab vocab;
	private File file;
	
	private JButton confirmButton;
	private JButton editButton;
	
	public VocabInfoPanel() {
		this.setLayout(new BorderLayout());
		SwingUtility.addPadding(this, 15, 15, new String[]{BorderLayout.NORTH, BorderLayout.EAST});
		
		setVocabInfoLayout();
		
		this.setVisible(false);
	}
	
	public void setVocabInfoLayout() {
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new BorderLayout());
		confirmButton = new JButton(Strings.BUTTON_START);
		confirmButton.setFont(Style.BUTTON_FONT);
		confirmButton.addActionListener((event) -> {
			GenericDataHolder.start(vocab);
		});
		bottomPanel.add(confirmButton, BorderLayout.EAST);
		
		editButton = new JButton(Strings.BUTTON_EDIT);
		editButton.addActionListener((event) -> {
			if (vocab != null) {
				GenericDataHolder.edit(vocab.getSourceFile());
			} else if (file != null) {
				GenericDataHolder.edit(file);
			}
		});
		editButton.setFont(Style.BUTTON_FONT);
		bottomPanel.add(editButton, BorderLayout.WEST);
		
		this.add(bottomPanel, BorderLayout.SOUTH);
		
		panel = new DisplayPanel();
		this.add(panel, BorderLayout.CENTER);
	}
	
	public void display(Vocab vocab) {
		if (vocab != null) {
			this.removeAll();
			this.setVocabInfoLayout();
			this.setVisible(true);
			this.vocab = vocab;
			this.file = null;
			this.panel.display(vocab);
			confirmButton.setEnabled(true);
			confirmButton.requestFocusInWindow();
		} else {
			this.setVisible(false);
			this.file = null;
		}
	}
	
	public void displayException(File file, FileParseException e) {
		if (file != null) {
			this.removeAll();
			this.setVocabInfoLayout();
			this.setVisible(true);
			this.vocab = null;
			this.file = file;
			this.panel.displayException(file, e);
			editButton.requestFocusInWindow();
			confirmButton.setEnabled(false);
		} else {
			this.setVisible(false);
			this.file = null;
		}
	}

	public void displayException(File file, String exceptionMessage) {
		if (file != null) {
			this.removeAll();
			this.setVocabInfoLayout();
			this.setVisible(true);
			this.vocab = null;
			this.file = file;
			this.panel.displayException(file, exceptionMessage);
			editButton.requestFocusInWindow();
			confirmButton.setEnabled(false);
		} else {
			this.setVisible(false);
			this.file = null;
		}
	}

	public void displaySettings() {
		this.removeAll();
		this.setVisible(true);
		this.add(new SettingsPanel());
	}

	public void displayInfo() {
		this.removeAll();
		this.setVisible(true);
		
		final String[] texts = {
				"Version: " + Preloader.get().getVersion().trim() + " (Commit " + Preloader.get().getCommit() + ")",
				"Made by WeiseSchokolade",
				"Source code can be found at https://github.com/WeiseSchokolade/Vocabulary",
				"",
				"",
				"TO DO", // TODO Here are tasks that need to be done.
				"- Add/configure launch4j",
				"- Configure style",
				"- Translation for 'About' menu",
				"- Translation for styleguide",
				"- Scroll Support for 'Settings' menu",
				"- Delete Button for vocab",
				"- Animations and more colors?",
				"- Add fallback language for missing translations?",
				"- Website?",
				"- Support for Java 8?"
		};
		
		String text = "";
		
		for (String s : texts) {
			text += s + "\n";
		}
		
		JTextArea textArea = new JTextArea(text);
		textArea.setFont(Style.INFO_FONT);
		textArea.setBackground(null);
		textArea.setEditable(false);
		textArea.setFocusable(false);
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBorder(null);
		this.add(scrollPane);
	}
}
