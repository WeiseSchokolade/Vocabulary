package de.schoko.vocab;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.Locale;

import de.schoko.utility.Logging;
import de.schoko.vocab.containers.Saved;

public class Settings {
	private static final String FILENAME = ".settings";
	/**
	 * Language code from ISO 639-1
	 * <br>
	 * <a href="https://en.wikipedia.org/wiki/List_of_ISO_639-1_codes">List Of ISO 639-1 Codes On Wikipedia</a>
	 */
	@Saved
	private String languageCode = "en";
	private String workspaceLocation;
	
	public Settings(String wspLocation) {
		this.workspaceLocation = wspLocation;
		
		File file = new File(workspaceLocation + File.separator + FILENAME);
		if (file.exists()) {
			try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
				String line;
				while ((line = fileReader.readLine()) != null) {
					String[] splitline = line.split(" = ");
					if (splitline.length == 2) {
						String key = splitline[0];
						String value = splitline[1];
						for (Field field : getClass().getDeclaredFields()) {
							if (field.getName().equals(key)) {
								if (field.isAnnotationPresent(Saved.class)) {
									field.set(this, value);
								}
							}
						}
					} else {
						continue;
					}
				}
				fileReader.close();
			} catch (IOException | IllegalArgumentException | IllegalAccessException e) {
				Logging.logException(e);
			}
		} else {
			languageCode = Locale.getDefault().getLanguage();
		}
	}
	
	public void save() {
		File file = new File(workspaceLocation + File.separator + FILENAME);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				Logging.logException(e);
			}
		}
		
		if (file.exists()) {
			try {
				String text = "";
				for (Field field : getClass().getDeclaredFields()) {
					if (field.isAnnotationPresent(Saved.class)) {
						text += field.getName() + " = " + field.get(this).toString() + "\n";
					}
				}
				FileWriter writer = new FileWriter(file);
				writer.write(text);
				writer.close();
				Logging.logInfo("Successfully saved settings file");
			} catch (IOException | IllegalArgumentException | IllegalAccessException e) {
				Logging.logException(e);
			}
		}
	}

	public String getLanguageCode() {
		return languageCode;
	}
	
	public String getWorkspaceLocation() {
		return workspaceLocation;
	}

	public void setLanguage(String languageCode) {
		this.languageCode = languageCode;
		Preloader.get().loadLanguage(languageCode);
	}
}
