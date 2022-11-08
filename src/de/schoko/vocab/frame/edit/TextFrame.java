package de.schoko.vocab.frame.edit;

import java.awt.Dimension;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import de.schoko.utility.ImgGetter;
import de.schoko.vocab.frame.Window;
import de.schoko.vocab.resources.InternalResourceList;
import de.schoko.vocab.resources.Strings;

public class TextFrame extends JFrame {
	private static final long serialVersionUID = -1447766974767767484L;
	
	private JEditorPane editorPane;
	
	private TextSource textSource;
	
	public TextFrame(TextSource textSource) {
		super();
		this.textSource = textSource;
		this.setTitle(Strings.MENU_STYLEGUIDE);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		editorPane = new JEditorPane("text/html", textSource.getText());
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
