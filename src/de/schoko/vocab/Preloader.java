package de.schoko.vocab;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import de.schoko.vocab.exceptions.LoadException;
import de.schoko.vocab.resources.InternalResourceList;
import de.schoko.vocab.resources.StringLoader;
import de.schoko.utility.Empty;
import de.schoko.utility.Logging;

public class Preloader {
	private String jarLocation = "";
	private String workspaceLocation = "";
	private String vocabLocation = "";
	
	private VocabLoader vocabLoader;
	
	private static Preloader instance;
	
	private String styleguideText = "";
	
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
		
		Map<String, String> translations = new HashMap<>();
		try {
			InputStream in = getClass().getResourceAsStream(InternalResourceList.TRANSLATION_LOCATION);
			BufferedReader fileReader = new BufferedReader(new InputStreamReader(in));
			
			while (true) {
				String line = fileReader.readLine();
				if (line == null) {
					break;
				}
				String[] pairs = line.split(" = ");
				if (pairs.length == 2) {
					translations.put(pairs[0], pairs[1]);
				} else {
					continue;
				}
			}
			
			in.close();
			fileReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		StringLoader stringLoader = new StringLoader();
		stringLoader.load(translations);
		
		try {
			InputStream in = getClass().getResourceAsStream(InternalResourceList.STYLEGUIDE_LOCATION);
			BufferedReader fileReader = new BufferedReader(new InputStreamReader(in));
			String s = "";
			String line;
			while ((line = fileReader.readLine()) != null) {
				s += line + "<br>";
			}
			this.styleguideText = s;
			
			in.close();
			fileReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
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

	public String getStyleguideText() {
		return styleguideText;
	}
}
