package de.schoko.vocab.frame.edit;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import de.schoko.utility.Logging;
import de.schoko.vocab.GenericDataHolder;
import de.schoko.vocab.Preloader;
import de.schoko.vocab.VocabData;
import de.schoko.vocab.VocabLoader;
import de.schoko.vocab.exceptions.NotLoadableException;
import de.schoko.vocab.frame.Window;
import de.schoko.vocab.resources.Strings;

public class VocabEditPanel extends JPanel {
	private static final long serialVersionUID = -8657970039727612284L;
	
	private CheckingFrame checkingFrame;
	private TextFrame styleguideFrame;
	
	private VocabData data = null;
	private File file;
	private JTextArea textArea;
	
	public VocabEditPanel(File file) {
		this.setLayout(new BorderLayout());
		this.file = file;
		checkingFrame = new CheckingFrame();
		styleguideFrame = new TextFrame(() -> {
			return Preloader.get().getStyleguideText();
		});
		
		try {
			Logging.logInfo(file.toPath().toString());
			data = VocabData.createFromText(Files.readString(file.toPath(), Charset.forName("Cp1252")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (data == null) {
			data = new VocabData();
		}
		String text = data.getStrippedSourceText();
		
		textArea = new JTextArea(text);
		textArea.setFont(getFont().deriveFont(20.0f));
		JScrollPane scrollPane = new JScrollPane(textArea);
		this.add(scrollPane, BorderLayout.CENTER);
		
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu(Strings.MENUBAR_FILE);
		JMenuItem saveItem = new JMenuItem(Strings.MENUBAR_FILE_SAVE);
		saveItem.addActionListener((event) -> {
			saveFile();
		});
		saveItem.setMnemonic(KeyEvent.VK_S);
		fileMenu.add(saveItem);
		
		JMenuItem checkItem = new JMenuItem(Strings.MENUBAR_FILE_CHECK);
		checkItem.addActionListener((event) -> {
			try {
				new VocabLoader().isLoadable(textArea.getText());
				checkingFrame.update(textArea.getText(), null);
			} catch (NotLoadableException e) {
				checkingFrame.update(textArea.getText(), e);
			}
		});
		checkItem.setMnemonic(KeyEvent.VK_C);
		fileMenu.add(checkItem);
		menuBar.add(fileMenu);
		
		JMenuItem closeItem = new JMenuItem(Strings.MENUBAR_FILE_CLOSE);
		closeItem.addActionListener((event) -> {
			int response = JOptionPane.showConfirmDialog(checkingFrame, Strings.QUESTION_SAVE, Strings.PROCESS_CLOSING_FILE, JOptionPane.YES_NO_CANCEL_OPTION);
			if (response == JOptionPane.CANCEL_OPTION) {
				return;
			}
			if (response == JOptionPane.YES_OPTION) {
				saveFile();
			}
			this.checkingFrame.dispose();
			this.checkingFrame.setVisible(false);
			this.styleguideFrame.dispose();
			this.styleguideFrame.setVisible(false);
			GenericDataHolder.mainMenu();
		});
		fileMenu.add(closeItem);
		
		JMenu helpMenu = new JMenu(Strings.MENUBAR_HELP);
		JMenuItem styleguideItem = new JMenuItem(Strings.MENUBAR_HELP_STYLEGUIDE);
		styleguideItem.addActionListener((event) -> {
			styleguideFrame.setVisible(true);
		});
		helpMenu.add(styleguideItem);
		menuBar.add(helpMenu);
		Window.get().setJMenuBar(menuBar);
	}
	
	private void saveFile() {
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter(file);
			data.setSourceText(textArea.getText());
			fileWriter.write(data.getUnstrippedSourceText());
			Logging.logInfo("Saved file " + file.getAbsolutePath());
			fileWriter.close();
			String vocabLocalPath = file.getAbsolutePath().substring(Preloader.get().getVocabLocation().length() + 1);
			Preloader.get().getVocabLoader().reloadFromFile(vocabLocalPath);
		} catch (IOException e) {
			Logging.logException(e);
		}
	}
}
