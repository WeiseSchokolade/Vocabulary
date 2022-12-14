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
	private String languageLocation = "";
	private String languageCode;
	
	private String version = "Unknown";
	private String commit = "Unknown";
	
	private VocabLoader vocabLoader;
	private Settings settings;
	
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
			throw new LoadException("Exception while getting location of jar (workspace location): " + e.getMessage());
		}
		workspaceLocation = checkAndCreateDir(jarLocation + File.separator + "vocab");
		vocabLocation = workspaceLocation;
		Logging.logInfo("Workspace Location: " + workspaceLocation);
		this.vocabLoader = new VocabLoader();
		this.settings = new Settings(workspaceLocation);
		
		languageCode = settings.getLanguageCode();
		if (!ArrayUtility.contains(InternalResourceList.SUPPORTED_LANGUAGES, languageCode)) {
			Logging.logWarning("Couldn't find " + languageCode + " in supported languages. Using default (english en).");
			languageCode = Locale.ENGLISH.getLanguage();
		}
		
		loadLanguage(languageCode);
		
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
			Logging.logException(e);
		}
		Logging.logInfo("Running java version " + System.getProperty("java.version"));
		
		try {
			InputStream in = getClass().getResourceAsStream(InternalResourceList.VERSION_LOCATION);
			BufferedReader fileReader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
			version = fileReader.readLine();
			commit = fileReader.readLine();
			in.close();
			fileReader.close();
		} catch (IOException e) {
			Logging.logException(e);
		}
		Logging.logInfo("Vocabulary Version " + version.trim() + " (Commit " + commit + ")");
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
	
	public void loadLanguage(String languageCode) {
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
			Logging.logException(e);
		}
		
		StringLoader stringLoader = new StringLoader();
		stringLoader.load(translations);
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
	
	public Settings getSettings() {
		return settings;
	}

	public String getStyleguideText() {
		return styleguideText;
	}
	
	public String languageCode() {
		return languageCode;
	}
	
	public String getVersion() {
		return version;
	}
	
	public String getCommit() {
		return commit;
	}
}
