package de.schoko.utility;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.UIManager;

/**
 * 
 * @author WeiseSchokolade
 * 
 * Inteded to be used for swing components
 */
public class SwingUtility {
	/**
	 * @param c Component that uses BorderLayout
	 * @param width
	 * @param height
	 */
	public static void addPadding(Container c, int width, int height) {
		addPadding(c, width, height, new String[]{BorderLayout.NORTH, BorderLayout.EAST, BorderLayout.SOUTH, BorderLayout.WEST});
	}
	
	/**
	 * @param c Component that uses BorderLayout
	 * @param width
	 * @param height
	 */
	public static void addPadding(Container c, int width, int height, String[] layouts) {
		for (String s : layouts) {
			JPanel panel = new JPanel();
			panel.setPreferredSize(new Dimension(width, height));
			panel.setOpaque(true);
			panel.setBackground(null);
			panel.setVisible(true);
			c.add(panel, s);
		}
	}
	
	/**
	 * Sets the look and feel to the system's look and feel
	 */
	public static void setupSystemLookAndFeel() {
		try {
	        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	    } catch(Exception e) {
	    	e.printStackTrace();
	    }
	}
}
