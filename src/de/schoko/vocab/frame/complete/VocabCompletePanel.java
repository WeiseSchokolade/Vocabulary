package de.schoko.vocab.frame.complete;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import de.schoko.vocab.Vocab;
import de.schoko.utility.SwingUtility;

public class VocabCompletePanel extends JPanel {
	private static final long serialVersionUID = -1756925467484971390L;
	
	public VocabCompletePanel(Vocab vocab) {
		this.setLayout(new BorderLayout());
		SwingUtility.addPadding(this, 50, 50, new String[]{BorderLayout.NORTH, BorderLayout.EAST, BorderLayout.WEST});
		this.add(new VocabInfoDisplayPanel(vocab), BorderLayout.CENTER);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new BorderLayout());
		SwingUtility.addPadding(bottomPanel, 10, 10);
		bottomPanel.add(new ContinuePanel());
		this.add(bottomPanel, BorderLayout.SOUTH);
	}
}
