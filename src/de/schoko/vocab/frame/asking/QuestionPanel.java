package de.schoko.vocab.frame.asking;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import de.schoko.vocab.frame.Window;
import de.schoko.utility.SwingUtility;

public class QuestionPanel extends JPanel {
	private static final long serialVersionUID = -1060788470171398539L;

	public QuestionPanel() {
		this.setLayout(new BorderLayout(50, 50));
		this.add(new QuestionSubLabel());
		Window.get().setBottomInfoBar(new InfoBar());
		SwingUtility.addPadding(this, 100, 100, new String[]{BorderLayout.WEST, BorderLayout.EAST});
	}
}
