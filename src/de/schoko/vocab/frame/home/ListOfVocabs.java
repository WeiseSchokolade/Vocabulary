package de.schoko.vocab.frame.home;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import de.schoko.vocab.GenericDataHolder;
import de.schoko.vocab.Preloader;
import de.schoko.vocab.Vocab;
import de.schoko.vocab.VocabLoader;
import de.schoko.vocab.exceptions.FileParseException;
import de.schoko.vocab.resources.Strings;
import de.schoko.utility.StringUtility;

public class ListOfVocabs extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private int index = 0;
	
	private String path = "";
	private String[] pathArr = {};
	
	private JPanel area;
	private VocabInfoPanel vocabInfoPanel;
	
	public ListOfVocabs(VocabInfoPanel vocabInfoPanel) {
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(300, 500));
		this.vocabInfoPanel = vocabInfoPanel;
		this.area = new JPanel();
		this.area.setLayout(null);
		this.add(area, BorderLayout.CENTER);
		JPanel bottomArea = new JPanel();
		bottomArea.setLayout(null);
		bottomArea.setPreferredSize(new Dimension(300, 20));
		bottomArea.setBackground(Color.YELLOW);
		addButton(bottomArea, Strings.BUTTON_SETTINGS, "⁉", (event) -> {
			vocabInfoPanel.displaySettings();
		});
		this.add(bottomArea, BorderLayout.SOUTH);
		this.update("");
	}
	
	public void update(String path) {
		this.path = path;
		String split = File.separator;
		if (split.contains("\\")) {
			split = "\\" + split;
		}
		this.pathArr = path.split(split);
		
		File file = new File(Preloader.get().getVocabLocation() + File.separator + path);
		String[] vocabFiles = file.list();
		index = 0;
		
		List<String> folders = new ArrayList<>();
		List<String> txtFiles = new ArrayList<>();
		for (String s : vocabFiles) {
			if (s.endsWith(".vocab")) {
				txtFiles.add(s);
			} else if (s.split("\\.").length > 1) {
			} else {
				folders.add(s);
			}
		}
		
		addButton(area, Strings.BUTTON_RELOAD, "🔄", (event) -> {
			removeAllComponents();
			update(path);
		});
		
		if (path.length() > 0) {
			addButton(area, Strings.BUTTON_BACK, "📤", (event) -> {
				vocabInfoPanel.display(null);
				String[] copy = new String[pathArr.length - 1];
				for (int i = 0; i < copy.length; i++) {
					copy[i] = pathArr[i];
				}
				String backPath = (StringUtility.toFilePathString(copy) + File.separator).substring(1);
				removeAllComponents();
				update(backPath);
			});
		}
		
		for (String s : folders) {
			addButton(area, s, "📁", (event) -> {
				interact(s);
			});
		}
		for (String s : txtFiles) {
			addButton(area, StringUtility.removeFileExtensionIfExists(s, ".vocab"), "📝", (event) -> {
				interact(s);
			});
		}
		
		addButton(area, Strings.BUTTON_NEW, "➕", (event) -> {
			GenericDataHolder.edit(path);
		});
	}
	
	private void addButton(Container c, String s, String prefix, ClickAction action) {
		JLabel label = new JLabel();
		Font font = label.getFont();
		label.setFont(font.deriveFont(18.0f));
		label.setText(" " + prefix + " " + StringUtility.makeFirstCharacterUppercase(s));
		label.addMouseListener(new MouseListener() {
			private boolean isHovering = false;
			@Override
			public void mouseReleased(MouseEvent e) {
				if (isHovering) {
					action.invoke(e);
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				isHovering = false;
				label.setFont(label.getFont().deriveFont(label.getFont().getSize() - 1.0f));
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				isHovering = true;
				label.setFont(label.getFont().deriveFont(label.getFont().getSize() + 1.0f));
			}
			
			@Override public void mousePressed(MouseEvent e) {}
			@Override public void mouseClicked(MouseEvent e) {}
		});
		label.setBounds(0, index*20, 250, 20);
		index++;
		c.add(label);
	}
	
	public void interact(String s) {
		File file = new File(Preloader.get().getVocabLocation() + File.separator + path + File.separator + s);
		try {
			VocabLoader loader = Preloader.get().getVocabLoader();
			
			if (file.isDirectory()) {
				removeAllComponents();
				update(path + s + File.separator);
				return;
			}
			Vocab vocab = loader.loadFromFile(path + s);
			
			if (vocab.getVocabulary().length == 0) {
				vocabInfoPanel.displayException(file, Strings.EXCEPTION_NO_VOCAB_FOUND);
				return;
			}
			vocabInfoPanel.display(vocab);
		} catch (FileParseException e) {
			vocabInfoPanel.displayException(file, e);
		}
	}
	
	private void removeAllComponents() {
		this.area.removeAll();
		this.area.repaint();
	}
}
