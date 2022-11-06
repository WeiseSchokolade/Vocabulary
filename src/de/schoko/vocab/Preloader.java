package de.schoko.vocab;

import java.io.File;
import java.net.URISyntaxException;

import de.schoko.vocab.exceptions.LoadException;
import de.schoko.utility.Empty;
import de.schoko.utility.Logging;

public class Preloader {
	private String jarLocation = "";
	private String workspaceLocation = "";
	private String vocabLocation = "";
	
	private VocabLoader vocabLoader;
	
	private static Preloader instance;
	
	public Preloader() {
		instance = this;
		Logging.logInfo("Preloader instanciated");
	}
	
	public static Preloader get() {
		return instance;
	}
	
	public void load() throws LoadException {
		try {
			jarLocation = new File(Empty.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParent();
		} catch (URISyntaxException e) {
			throw new LoadException("Exception while getting location of jar: " + e.getMessage());
		}
		workspaceLocation = checkAndCreateDir(jarLocation + "\\" + "vocab");
		vocabLocation = workspaceLocation;
		Logging.logInfo("Workspace Location: " + workspaceLocation);
		this.vocabLoader = new VocabLoader();
	}
	
	private String checkAndCreateDir(String path) throws LoadException {
		File file = new File(path);
		if (!file.exists()) {
			file.mkdir();
		} else {
			if (file.isFile()) {
				throw new LoadException("Vocab location is not a directory");
			}
		}
		return file.getAbsolutePath();
	}
	
	public String getJarLocation() {
		return jarLocation;
	}
	
	public String getWorkspaceLocation() {
		return workspaceLocation;
	}
	
	public String getVocabLocation() {
		return vocabLocation;
	}
	
	public VocabLoader getVocabLoader() {
		return vocabLoader;
	}
}
