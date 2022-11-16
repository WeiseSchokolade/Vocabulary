package de.schoko.vocab.frame;

import java.awt.GridLayout;

import javax.swing.JPanel;

public abstract class BottomInfoBar extends JPanel {
	private static final long serialVersionUID = -2607053352952190927L;

	public BottomInfoBar() {
		super();
		this.setLayout(new GridLayout(1, 0));
	}
	
	public BottomInfoBar(int rows, int cols) {
		super();
		this.setLayout(new GridLayout(rows, cols));
	}
}
