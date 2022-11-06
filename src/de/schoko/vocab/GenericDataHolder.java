package de.schoko.vocab;

import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;

import de.schoko.utility.Logging;
import de.schoko.vocab.frame.Window;
import de.schoko.vocab.frame.asking.QuestionPanel;
import de.schoko.vocab.frame.complete.VocabCompletePanel;
import de.schoko.vocab.frame.edit.VocabEditPanel;
import de.schoko.vocab.frame.home.HomePanel;
import de.schoko.vocab.resources.Strings;

public class GenericDataHolder {
	private static Vocab vocab;
	
	public static void start(Vocab vocab) {
		GenericDataHolder.vocab = vocab;
		vocab.start();
		Window.get().removeAllComponents();
		Window.get().addComponent(new QuestionPanel());
		Window.get().setActivity(Strings.ACTIVITY_ASKING + " " + vocab.getName());
		Window.get().refresh();
	}
	
	public static void edit(String path) {
		File file = getNewFileForEditPanel(path);
		if (file != null) {
			GenericDataHolder.edit(file);
		}
	}
	
	public static void edit(File file) {
		Window.get().removeAllComponents();
		Window.get().addComponent(new VocabEditPanel(file));
		Window.get().setActivity(Strings.ACTIVITY_EDITING + " " + file.getAbsolutePath());
		Window.get().refresh();
	}
	
	public static void complete() {
		Window.get().removeAllComponents();
		Window.get().addComponent(new VocabCompletePanel(vocab));
		Window.get().setActivity(Strings.ACTIVITY_COMPLETE + " " + vocab.getName());
		Window.get().refresh();
	}
	
	public static void mainMenu() {
		Window.get().removeAllComponents();
		Window.get().addComponent(new HomePanel());
		Window.get().setActivity(Strings.ACTIVITY_MAIN_MENU);
		Window.get().refresh();
	}
	
	public static Vocab getVocab() {
		return vocab;
	}
	
	public static File getNewFileForEditPanel(String path) {
		String message = JOptionPane.showInputDialog(Window.get(), Strings.PROMPT_FILE_NAME + ":", Strings.PROCESS_CREATING_FILE, JOptionPane.QUESTION_MESSAGE);
		
		if (message == null) {
			return null;
		}
		
		while (message.equals("") || message.equals(" ") || message.equals("\n")) {
			message = JOptionPane.showInputDialog(Window.get(), Strings.EXCEPTION_INVALID_FILE_NAME + "\n" + Strings.PROMPT_FILE_NAME, Strings.PROCESS_CREATING_FILE, JOptionPane.QUESTION_MESSAGE);
			if (message == null) {
				return null;
			}
		}
		
		File file = new File(Preloader.get().getVocabLocation() + File.separator + path + File.separator + message + ".vocab");
		file.getParentFile().mkdirs();
		if (file.exists()) {
			int selectedOption = JOptionPane.showConfirmDialog(null, Strings.fillIn(Strings.EXCEPTION_FILE_ALREADY_EXISTS, message) + Strings.QUESTION_OVERWRITE_FILE, Strings.PROCESS_CREATING_FILE, JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
			if (selectedOption == JOptionPane.YES_OPTION) {
				file.delete();
				try {
					file.createNewFile();
				} catch (IOException e) {
					Logging.logException(e);
				}
			} else {
				return null;
			}
		} else {
			try {
				file.createNewFile();
			} catch (IOException e) {
				Logging.logException(e);
			}
		}
		return file;
	}
}
