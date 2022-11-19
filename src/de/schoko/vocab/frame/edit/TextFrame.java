package de.schoko.vocab.frame.edit;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import de.schoko.utility.ImgGetter;
import de.schoko.vocab.frame.Window;
import de.schoko.vocab.resources.InternalResourceList;
import de.schoko.vocab.resources.Strings;
import de.schoko.vocab.resources.Style;

public class TextFrame extends JFrame {
	private static final long serialVersionUID = -1447766974767767484L;
	
	private JTextArea editorPane;
	
	private TextSource textSource;
	
	public TextFrame(TextSource textSource) {
		super();
		this.textSource = textSource;
		this.setTitle(Strings.MENU_STYLEGUIDE);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		editorPane = new JTextArea(textSource.getText());
		editorPane.setFont(Style.INFO_FONT);
		editorPane.setEditable(false);
		editorPane.setFont(Window.get().getFont().deriveFont(18.0f));
		
		JScrollPane scrollPane = new JScrollPane(editorPane);
		scrollPane.setPreferredSize(new Dimension(500, 500));
		this.add(scrollPane);
		
		this.pack();
		this.setIconImage(ImgGetter.getImageFromResources(InternalResourceList.ICON_LOCATION));
		this.setLocationRelativeTo(Window.get());
	}
	
	public void update() {
		this.editorPane.setText(textSource.getText());
	}
}
