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
import de.schoko.vocab.VocabLoader;
import de.schoko.vocab.exceptions.NotLoadableException;
import de.schoko.vocab.frame.Window;
import de.schoko.vocab.resources.Strings;

public class VocabEditPanel extends JPanel {
	private static final long serialVersionUID = -8657970039727612284L;
	
	private CheckingFrame checkingFrame;
	
	public VocabEditPanel(File file) {
		// TODO: Styleguide für Benutzer zur Eingabe von Vokabeln
		
		this.setLayout(new BorderLayout());
		checkingFrame = new CheckingFrame();
		
		String text = "un exemple - ein Beispiel";
		try {
			Logging.logInfo(file.toPath().toString());
			text = Files.readString(file.toPath(), Charset.forName("Cp1252"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		JTextArea textArea = new JTextArea(text);
		textArea.setFont(getFont().deriveFont(20.0f));
		JScrollPane scrollPane = new JScrollPane(textArea);
		this.add(scrollPane, BorderLayout.CENTER);
		
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu(Strings.MENUBAR_FILE);
		JMenuItem saveItem = new JMenuItem(Strings.MENUBAR_FILE_SAVE);
		saveItem.addActionListener((event) -> {
			try {
				FileWriter fileWriter = new FileWriter(file);
				fileWriter.write(textArea.getText());
				Logging.logInfo("Saved file " + file.getAbsolutePath());
				fileWriter.close();
				String vocabLocalPath = file.getAbsolutePath().substring(Preloader.get().getVocabLocation().length() + 1);
				Preloader.get().getVocabLoader().reloadFromFile(vocabLocalPath);
			} catch (IOException e) {
				Logging.logException(e);
			}
		});
		saveItem.setMnemonic(KeyEvent.VK_S);
		menu.add(saveItem);
		
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
		menu.add(checkItem);
		
		JMenuItem closeItem = new JMenuItem(Strings.MENUBAR_FILE_CLOSE);
		closeItem.addActionListener((event) -> {
			int response = JOptionPane.showConfirmDialog(checkingFrame, Strings.QUESTION_SAVE, Strings.PROCESS_CLOSING_FILE, JOptionPane.YES_NO_CANCEL_OPTION);
			if (response == JOptionPane.CANCEL_OPTION) {
				return;
			}
			if (response == JOptionPane.YES_OPTION) {
				try {
					FileWriter fileWriter = new FileWriter(file);
					fileWriter.write(textArea.getText());
					Logging.logInfo("Saved file " + file.getAbsolutePath());
					fileWriter.close();
					String vocabLocalPath = file.getAbsolutePath().substring(Preloader.get().getVocabLocation().length() + 1);
					Preloader.get().getVocabLoader().reloadFromFile(vocabLocalPath);
				} catch (IOException e) {
					Logging.logException(e);
				}
			}
			GenericDataHolder.mainMenu();
		});
		menu.add(closeItem);
		menuBar.add(menu);
		Window.get().setJMenuBar(menuBar);
	}
}
