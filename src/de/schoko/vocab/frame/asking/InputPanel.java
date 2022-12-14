package de.schoko.vocab.frame.asking;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import de.schoko.vocab.GenericDataHolder;
import de.schoko.vocab.Vocab;
import de.schoko.vocab.exceptions.VocabCompleteException;
import de.schoko.vocab.frame.Window;
import de.schoko.vocab.resources.Strings;
import de.schoko.vocab.resources.Style;

public class InputPanel extends JPanel {
	private static final long serialVersionUID = -5599952273568147969L;
	
	private JButton confirmButton;
	private JTextField textField;
	private JLabel lastVocab;
	private JLabel promptLabel;
	
	public InputPanel(JLabel lastVocab, JLabel promptLabel) {
		super();
		
		this.setLayout(new BorderLayout(20, 0));
		this.setOpaque(true);
		this.lastVocab = lastVocab;
		this.promptLabel = promptLabel;
		
		textField = new JTextField();
		textField.setPreferredSize(new Dimension(200, 20));
		textField.addKeyListener(new KeyListener() {@Override public void keyTyped(KeyEvent e) {} @Override public void keyReleased(KeyEvent e) {}
			@Override
			public void keyPressed(KeyEvent e) {
				checkButtonEnabledStatus();
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					check();
				}
			}
		});
		textField.setFocusable(true);
		textField.setFont(Style.INPUT_FIELD_FONT);
		this.add(textField, BorderLayout.CENTER);
		textField.requestFocusInWindow();
		
		confirmButton = new JButton(Strings.BUTTON_ENTER);
		confirmButton.setEnabled(false);
		confirmButton.addActionListener((event) -> {
			checkButtonEnabledStatus();
			this.check();
		});
		confirmButton.setFont(Style.BUTTON_FONT);
		this.add(confirmButton, BorderLayout.EAST);
		
		this.promptLabel.setText(GenericDataHolder.getVocab().getCurrentPair().getPrompt());
	}
	
	public void check() {
		if (textField.getText().length() <= 0) {
			checkButtonEnabledStatus();
			return;
		}
		try {
			Vocab vocab = GenericDataHolder.getVocab();
			if (vocab.checkAndNext(textField.getText())) {
				lastVocab.setForeground(Color.GREEN);
			} else {
				lastVocab.setForeground(Color.RED);
			}
			lastVocab.setText("<html><body>" + vocab.getLastWordPair().getSolutionString() + "</body></html>");
			((InfoBar) Window.get().getBottomInfoBar()).setInfo(Strings.fillIn(Strings.DISPLAY_INFO, "" + vocab.getTotal(), "" + vocab.getRight(), "" + vocab.getWrong()), 2);
		} catch (VocabCompleteException e) {
			GenericDataHolder.complete();
			return;
		}
		textField.setText("");
		promptLabel.setText(GenericDataHolder.getVocab().getCurrentPair().getPrompt());
		confirmButton.setEnabled(false);
		Window.get().repaint();
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		checkButtonEnabledStatus();
	}

	public void checkButtonEnabledStatus() {
		if (textField.getText().length() >= 1) {
			confirmButton.setEnabled(true);
		} else {
			confirmButton.setEnabled(false);
		}
	}
}
