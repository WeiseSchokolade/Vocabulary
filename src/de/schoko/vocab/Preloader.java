package de.schoko.vocab;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import de.schoko.vocab.exceptions.LoadException;
import de.schoko.vocab.resources.InternalResourceList;
import de.schoko.vocab.resources.StringLoader;
import de.schoko.utility.ArrayUtility;
import de.schoko.utility.Empty;
import de.schoko.utility.Logging;

public class Preloader {
	private String jarLocation = "";
	private String workspaceLocation = "";
	private String vocabLocation = "";
	/**
	 * Language code from ISO 639-1
	 * <br>
	 * <a href="https://en.wikipedia.org/wiki/List_of_ISO_639-1_codes">List Of ISO 639-1 Codes On Wikipedia</a>
	 */
	private String languageCode = "";
	private String languageLocation = "";
	
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
		
		languageCode = Locale.getDefault().getLanguage();
		if (!ArrayUtility.contains(InternalResourceList.SUPPORTED_LANGUAGES, languageCode)) {
			Logging.logWarning("Couldn't find " + languageCode + " in supported languages. Using default (english en).");
			languageCode = Locale.ENGLISH.getLanguage();
		}
		languageLocation = InternalResourceList.LANGUAGES_LOCATION + languageCode + InternalResourceList.RESOURCE_PATH_SEPERATOR;
		Logging.logInfo("Language location: " + languageLocation);
		
		Map<String, String> translations = new HashMap<>();
		try {
			InputStream in = getClass().getResourceAsStream(languageLocation + "Strings.txt");
			BufferedReader fileReader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
			
			int translationAmount = 0;
			while (true) {
				String line = fileReader.readLine();
				if (line == null) {
					break;
				}
				String[] pairs = line.split(" = ");
				if (pairs.length == 2) {
					translations.put(pairs[0], pairs[1]);
					translationAmount++;
				} else {
					continue;
				}
			}
			
			in.close();
			fileReader.close();
			Logging.logInfo("Successfully loaded " + translationAmount + " translation(s)");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		StringLoader stringLoader = new StringLoader();
		stringLoader.load(translations);
		
		try {
			InputStream in = getClass().getResourceAsStream(InternalResourceList.STYLEGUIDE_LOCATION);
			BufferedReader fileReader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
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
