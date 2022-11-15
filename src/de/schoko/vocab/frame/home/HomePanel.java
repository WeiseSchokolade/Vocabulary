package de.schoko.vocab.frame.home;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class HomePanel extends JPanel {
	private static final long serialVersionUID = -8247951575321417180L;
	private VocabInfoPanel vocabInfoPanel;
	
	public HomePanel() {
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(600, 600));
		
		vocabInfoPanel = new VocabInfoPanel();
		vocabInfoPanel.setBorder(null);
		this.add(vocabInfoPanel, BorderLayout.CENTER);
		this.setBackground(Color.WHITE);
		
		this.add(new ListOfVocabs(vocabInfoPanel), BorderLayout.WEST);
	}
}
