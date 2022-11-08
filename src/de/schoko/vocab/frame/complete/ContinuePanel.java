package de.schoko.vocab.frame.complete;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;

import de.schoko.vocab.GenericDataHolder;
import de.schoko.vocab.resources.Strings;

public class ContinuePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public ContinuePanel() {
		JButton retryButton = new JButton(Strings.BUTTON_RETRY);
		retryButton.addActionListener((event) -> {
			GenericDataHolder.start(GenericDataHolder.getVocab().copy());
		});
		retryButton.setPreferredSize(new Dimension(250, 30));
		this.add(retryButton, BorderLayout.WEST);
		
		JButton continueButton = new JButton(Strings.BUTTON_CONTINUE);
		continueButton.addActionListener((event) -> {
			GenericDataHolder.mainMenu();
		});
		continueButton.setPreferredSize(new Dimension(250, 30));
		this.add(continueButton, BorderLayout.EAST);
		continueButton.requestFocusInWindow();
	}
}
