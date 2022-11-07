package de.schoko.utility;

import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * @author WeiseSchokolade
 */
public class ImgGetter {
	/**
	 * Gets an image from the classpath using {@link javax.swing.ImageIcon}. Used for loading images in JARs.
	 * 
	 * @param imgPath A resource path where the image is located.
	 * @return An image loaded from the classpath.
	 */
	public static Image getImageFromResources(String imgPath) {
		ImageIcon imageIcon = new ImageIcon(Empty.class.getClassLoader().getResource(imgPath));
		Image image = imageIcon.getImage();
		return image;
	}
	
	/**
	 * Gets an image from the file system using {@link javax.swing.ImageIcon}. Used for loading images from the filesystem.
	 * 
	 * @param filepath A filepath where the image is located.
	 * @return An image loaded from the filepath.
	 */
	public static Image getImageFromFile(String filepath) {
		filepath = filepath.replaceAll("\\\\", "/");
		
		ImageIcon imageIcon = new ImageIcon(filepath);
		Image image = imageIcon.getImage();
		return image;
	}
	
	/**
	 * Gets an image from the Internet using {@link javax.imageio.ImageIO#read(URL)}.
	 * Catches MalformedURLExceptions and IOExceptions occuring during the process.
	 * 
	 * @param imgUrl A url where the image is located.
	 * @return An image loaded from the web
	 */
	public static Image getImageFromWeb(String imgUrl) {
		Image image = null;
		URL url;
		try {
			url = new URL(imgUrl);
			image = ImageIO.read(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}
}
