package de.schoko.vocab.frame.asking;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import de.schoko.vocab.GenericDataHolder;
import de.schoko.vocab.Vocab;
import de.schoko.vocab.resources.Strings;
import de.schoko.utility.SwingUtility;

public class QuestionSubLabel extends JPanel {
	private static final long serialVersionUID = 829553147359472212L;
	private JLabel translationLabel;
	private JLabel promptLabel;
	private JProgressBar progressBar;
	
	private double progressBarValue;
	
	public QuestionSubLabel() {
		this.setLayout(new GridLayout(3, 1));
		
		Vocab vocab = GenericDataHolder.getVocab();
		progressBarValue = 0;
		
		JPanel barPanel = new JPanel();
		barPanel.setLayout(new BorderLayout(0, 0));
		SwingUtility.addPadding(barPanel, 25, 25, new String[]{BorderLayout.WEST, BorderLayout.EAST, BorderLayout.SOUTH});
		
		JPanel barInnerPanel = new JPanel();
		barInnerPanel.setLayout(new BorderLayout());
		progressBar = new JProgressBar(0, vocab.getVocabulary().length * 10);
		progressBar.setBackground(Color.BLACK);
		progressBar.setForeground(new Color(80, 236, 71));
		progressBar.setBorderPainted(false);
		progressBar.setFont(new Font("Arial", Font.BOLD, 15));
		progressBar.setStringPainted(true);
		progressBar.setString(Strings.LOADING);
		
		barInnerPanel.add(progressBar);
		SwingUtility.addPadding(barInnerPanel, 75, 25, new String[]{BorderLayout.WEST, BorderLayout.EAST});
		
		barPanel.add(barInnerPanel, BorderLayout.NORTH);
		
		JLabel lastVocab = new JLabel("");
		lastVocab.setHorizontalAlignment(JLabel.CENTER);
		lastVocab.setVerticalAlignment(JLabel.CENTER);
		lastVocab.setFont(new Font("Arial", Font.BOLD, 20));
		barPanel.add(lastVocab, BorderLayout.CENTER);
		
		this.add(barPanel);
		
		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new GridLayout(3, 1));
		
		promptLabel = new JLabel();
		promptLabel.setText(Strings.LOADING);
		promptLabel.setHorizontalAlignment(JLabel.CENTER);
		promptLabel.setVerticalAlignment(JLabel.CENTER);
		infoPanel.add(promptLabel);
		
		translationLabel = new JLabel();
		translationLabel.setText(Strings.LOADING);
		translationLabel.setHorizontalAlignment(JLabel.CENTER);
		translationLabel.setVerticalAlignment(JLabel.CENTER);
		translationLabel.setOpaque(true);
		translationLabel.setFont(new Font("Arial", Font.BOLD, 30));
		infoPanel.add(translationLabel);
		
		
		JPanel subPanel = new JPanel();
		subPanel.setLayout(new FlowLayout());
		InputPanel l = new InputPanel(lastVocab, promptLabel);
		l.setMaximumSize(new Dimension(500000, 100));
		subPanel.add(l);
		subPanel.setOpaque(true);
		infoPanel.add(subPanel);
		
		this.add(infoPanel);
		JPanel bottomPanel = new JPanel(new BorderLayout());
		JPanel southBottomPanel = new JPanel();
		
		JButton backButton = new JButton(Strings.BUTTON_BACK_TO_MAIN_MENU);
		backButton.addActionListener((event) -> {
			GenericDataHolder.mainMenu();
		});
		backButton.setPreferredSize(new Dimension(150, 30));
		southBottomPanel.add(backButton);
		
		bottomPanel.add(southBottomPanel, BorderLayout.SOUTH);
		
		this.add(bottomPanel);
	}
	
	@Override
	public void paint(Graphics g) {
		updateTranslation();
		
		super.paint(g);
	}
	
	private void updateTranslation() {
		Vocab vocab = GenericDataHolder.getVocab();
		
		if (progressBarValue < vocab.getTotal() * 10) {
			progressBarValue += 0.05;
			this.repaint();
		}
		
		String wordsToTranslate = vocab.getAskedPhrase();
		translationLabel.setText("<html>" + wordsToTranslate + "</html>");
		progressBar.setValue((int) (progressBarValue));
		progressBar.setString(vocab.getTotal() + File.separator + vocab.getVocabulary().length);
	}
}
