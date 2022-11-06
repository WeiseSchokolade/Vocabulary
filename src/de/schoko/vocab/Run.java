package de.schoko.vocab;

import javax.swing.JOptionPane;

import de.schoko.vocab.exceptions.LoadException;
import de.schoko.vocab.frame.Window;
import de.schoko.utility.SwingUtility;

public class Run {
	public static void main(String[] args) {
		try {
			Preloader preloader = new Preloader();
			SwingUtility.setupSystemLookAndFeel();
			
			try {
				preloader = new Preloader();
				preloader.load();
			} catch (LoadException e) {
				JOptionPane.showMessageDialog(null, "An exception occured while preloading:\n\n" + e.getMessage() + "\n\nPlease report this to the author of the project", "Loading Exception", JOptionPane.ERROR_MESSAGE);
				return;
			}
			new Window();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "An exception occured while starting Vocabulary:\n\n" + e.getMessage() + "\n\nPlease report this to the author of the project.", "Starting Exception", JOptionPane.ERROR_MESSAGE);
		}
	}
}
