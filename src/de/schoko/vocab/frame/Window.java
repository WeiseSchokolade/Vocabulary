package de.schoko.vocab.frame;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import de.schoko.vocab.Preloader;
import de.schoko.vocab.frame.home.HomePanel;
import de.schoko.vocab.resources.InternalResourceList;
import de.schoko.vocab.resources.Strings;
import de.schoko.utility.ImgGetter;
import de.schoko.utility.Logging;

public class Window extends JFrame {
	private static final long serialVersionUID = 1L;
	private static Window instance;
	private WindowPanel panel;
	private BottomInfoBar bottomInfoBar;
	private String activity;
	
	public Window() {
		super();
		instance = this;
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setTitle(Strings.WINDOW_ACTIVITY_PREFIX + Strings.ACTIVITY_MAIN_MENU);
		this.setIconImage(ImgGetter.getImageFromResources(InternalResourceList.ICON_LOCATION));
		
		panel = new WindowPanel();
		panel.add(new HomePanel());
		this.add(panel);
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				Preloader.get().getSettings().save();
				e.getWindow().dispose();
			}
		});
		
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		Logging.logInfo("Window setup complete");
	}
	
	public static Window get() {
		return instance;
	}
	
	public void refresh() {
		this.revalidate();
		this.repaint();
		this.updateTitle();
	}
	
	public void updateTitle() {
		this.setTitle(Strings.WINDOW_ACTIVITY_PREFIX + activity);
	}
	
	public void addComponent(Component c) {
		this.panel.add(c, BorderLayout.CENTER);
	}
	
	public void setBottomInfoBar(BottomInfoBar b) {
		if (bottomInfoBar != null) {
			this.panel.remove(bottomInfoBar);
		}
		this.bottomInfoBar = b;
		if (bottomInfoBar != null) {
			this.panel.add(bottomInfoBar, BorderLayout.SOUTH);
		}
	}
	
	public void removeAllComponents() {
		this.setJMenuBar(null);
		this.panel.removeAll();
	}
	
	public BottomInfoBar getBottomInfoBar() {
		return bottomInfoBar;
	}
	
	public void setActivity(String activity) {
		this.activity = activity;
		updateTitle();
	}
}
