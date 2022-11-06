package de.schoko.vocab.frame.edit;

import java.awt.Dimension;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import de.schoko.utility.ImgGetter;
import de.schoko.vocab.exceptions.NotLoadableException;
import de.schoko.vocab.frame.Window;
import de.schoko.vocab.resources.InternalResourceList;
import de.schoko.vocab.resources.Strings;

public class CheckingFrame extends JFrame {
	private static final long serialVersionUID = -1447766974767767484L;
	
	private JEditorPane editorPane;
	
	public CheckingFrame() {
		super();
		this.setTitle(Strings.MENU_MISTAKES);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		editorPane = new JEditorPane("text/html", "");
		editorPane.setEditable(false);
		editorPane.setFont(Window.get().getFont().deriveFont(18.0f));
		
		JScrollPane scrollPane = new JScrollPane(editorPane);
		scrollPane.setPreferredSize(new Dimension(500, 500));
		this.add(scrollPane);
		
		this.pack();
		this.setIconImage(ImgGetter.getImageFromResources(InternalResourceList.ICON_LOCATION));
		this.setLocationRelativeTo(Window.get());
	}
	
	public void update(String text, NotLoadableException e) {
		if (e == null) {
			String displayedText = "<html>";
			displayedText += text.replace("\n", "<br>");
			displayedText += "</html>";
			editorPane.setText(displayedText);
			this.repaint();
		} else {
			String[] lines = text.split("\n");
			String displayedText = "<html>";
			for (int i = 0; i < lines.length; i++) {
				String s = lines[i];
				if (i == e.getLine()) {
					displayedText += "<div style=\"color: #FF0000\">";
					displayedText += i + 1 + " -> " + s + "<br>";
				} else {
					displayedText += i + 1 + " " + s + "<br>";
				}
			}
			if (e.getLine() != -1) {
				displayedText += "</div>";
			}
			displayedText += "</body></html>";
			editorPane.setText(displayedText);
		}
		this.setVisible(true);
	}
}
