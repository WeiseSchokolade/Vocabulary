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
		this.setLayout(new BorderLayout());
		JButton continueButton = new JButton(Strings.BUTTON_CONTINUE);
		continueButton.addActionListener((event) -> {
			GenericDataHolder.mainMenu();
		});
		continueButton.requestFocusInWindow();
		continueButton.setPreferredSize(new Dimension(100, 30));
		this.add(continueButton, BorderLayout.EAST);
	}
}
