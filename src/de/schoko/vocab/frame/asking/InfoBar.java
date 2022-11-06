package de.schoko.vocab.frame.asking;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JLabel;

import de.schoko.vocab.GenericDataHolder;
import de.schoko.vocab.frame.BottomInfoBar;
import de.schoko.vocab.resources.Strings;

public class InfoBar extends BottomInfoBar {
	private static final long serialVersionUID = 8465566976919833077L;
	
	private String[] info;
	private ArrayList<JLabel> labels;
	
	public InfoBar() {
		super();
		this.info = new String[]{GenericDataHolder.getVocab().getName(),
				"", 
				Strings.fillIn(Strings.DISPLAY_INFO, new String[]{"0", "0", "0"})};
		
		this.setLayout(new GridLayout(1, info.length));
		this.labels = new ArrayList<>();
		
		for (int i = 0; i < info.length; i++) {
			String s = info[i];
			JLabel label = new JLabel();
			label.setText(s);
			if (i == 0) {
				label.setHorizontalAlignment(JLabel.LEFT);
			} else if (i == info.length - 1) {
				label.setHorizontalAlignment(JLabel.RIGHT);
			} else {
				label.setHorizontalAlignment(JLabel.CENTER);
			}
			this.add(label);
			this.labels.add(label);
		}
	}
	
	public String[] getInfo() {
		return info;
	}
	
	public void setInfo(String s, int slot) {
		info[slot] = s;
		labels.get(slot).setText(s);
		this.repaint();
	}
}
