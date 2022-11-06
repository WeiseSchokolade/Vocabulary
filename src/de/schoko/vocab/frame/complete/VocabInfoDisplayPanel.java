package de.schoko.vocab.frame.complete;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import de.schoko.vocab.Vocab;
import de.schoko.vocab.resources.Strings;

public class VocabInfoDisplayPanel extends JPanel {
	private static final long serialVersionUID = -1900349251952911480L;
	
	public VocabInfoDisplayPanel(Vocab vocab) {
		this.setLayout(new GridLayout(1, 0));
		this.setBorder(new TitledBorder(Strings.MENU_STATS));
		
		JPanel statPanel = new JPanel();
		statPanel.setLayout(null);
		String[] stats = {vocab.getName(), "",
				Strings.fillIn(Strings.DISPLAY_TOTAL, vocab.getTotal()),
				Strings.fillIn(Strings.fillIn(Strings.DISPLAY_CORRECT, vocab.getRight()), Math.round((double) vocab.getRight() / (double) vocab.getTotal() * 100)),
				Strings.fillIn(Strings.fillIn(Strings.DISPLAY_WRONG, vocab.getWrong()), Math.round((double) vocab.getWrong() / (double) vocab.getTotal() * 100)),
				"",
				"",
				Strings.fillIn(Strings.DISPLAY_GRADE,calcGrade((double) vocab.getRight() / (double) vocab.getTotal())),
				"> 85% = " + Strings.GRADE_A + " | > 70% = " + Strings.GRADE_B + " | > 55% = " + Strings.GRADE_C + " | > 39% = " + Strings.GRADE_D + " | > 20% = " + Strings.GRADE_E + " | < 20% = " + Strings.GRADE_F + ""};
		int index = 0;
		for (String s : stats) {
			JLabel label = new JLabel();
			label.setText(s);
			label.setBounds(15, index * 25 + 15, 2000, 25);
			statPanel.add(label);
			index++;
		}
		this.add(statPanel);
		
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new BorderLayout());
		
		JPanel descriptionPanel = new JPanel();
		descriptionPanel.setLayout(new GridLayout(1, 0));
		
		JLabel enteredDescriptionLabel = new JLabel(Strings.HEADER_ENTERED);
		enteredDescriptionLabel.setHorizontalAlignment(JLabel.CENTER);
		enteredDescriptionLabel.setVerticalAlignment(JLabel.BOTTOM);
		descriptionPanel.add(enteredDescriptionLabel);
		
		JLabel correctDescriptionLabel = new JLabel(Strings.HEADER_CORRECT);
		correctDescriptionLabel.setHorizontalAlignment(JLabel.CENTER);
		correctDescriptionLabel.setVerticalAlignment(JLabel.BOTTOM);
		descriptionPanel.add(correctDescriptionLabel);
		
		rightPanel.add(descriptionPanel, BorderLayout.NORTH);
		
		JPanel wordsPanel = new JPanel();
		wordsPanel.setLayout(new GridLayout(1, 0));
		
		String[] enteredWords = vocab.getEnteredWords();
		boolean[] correctWords = vocab.getCorrectWords();
		String enteredTextString = "<html>";
		for (int i = 0; i < enteredWords.length; i++) {
			String color = "rgb(0, 255, 0)";
			if (!correctWords[i]) {
				color = "rgb(255, 0, 0)";
			}
			enteredTextString += "<div style=\"color: " + color + "\">" + enteredWords[i] + "</div>";
		}
		enteredTextString += "</html>";
		JEditorPane jEditorPane = new JEditorPane("text/html", enteredTextString);
		jEditorPane.setEditable(false);
		JScrollPane pane = new JScrollPane(jEditorPane);
		wordsPanel.add(pane);
		
		String[][] words;
		if (vocab.getDisplayFirst()) {
			words = vocab.getSecondLanguageVocab();
		} else {
			words = vocab.getFirstLanguageVocab();
		}
		String displayedText = "<html>";
		for (int i = 0; i < words.length; i++) {
			String word = words[i][0];
			displayedText += word + "<br>";
		}
		displayedText += "</html>";
		JEditorPane correctPane = new JEditorPane("text/html", displayedText);
		correctPane.setEditable(false);
		pane = new JScrollPane(correctPane);
		wordsPanel.add(pane);
		
		rightPanel.add(wordsPanel, BorderLayout.CENTER);
		
		this.add(rightPanel);
	}
	
	private String calcGrade(double perc) {
		if (perc < 0.2) {
			return Strings.GRADE_F;
		} else if (perc < 0.39) {
			return Strings.GRADE_E;
		} else if (perc < 0.55) {
			return Strings.GRADE_D;
		} else if (perc < 0.7) {
			return Strings.GRADE_C;
		} else if (perc < 0.85) {
			return Strings.GRADE_B;
		} else if (perc >= 0.85) {
			return Strings.GRADE_A;
		} else {
			return "???";
		}
	}
}
