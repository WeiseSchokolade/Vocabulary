package de.schoko.vocab;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import de.schoko.vocab.exceptions.FileParseException;
import de.schoko.vocab.exceptions.NotLoadableException;
import de.schoko.utility.Logging;

public class VocabLoader {
	public static final int maxLines = 150000;
	private HashMap<String, Vocab> existingVocab;
	
	public VocabLoader() {
		this.existingVocab = new HashMap<>();
	}

	public void reset() {
		this.existingVocab = new HashMap<>();
	}
	
	public Vocab loadFromFile(String name) {
		if (existingVocab.containsKey(name)) {
			return existingVocab.get(name).copy();
		}
		
		return reloadFromFile(name);
	}
	
	public Vocab reloadFromFile(String name) {
		Logging.logInfo("Reloading vocab from file " + Preloader.get().getVocabLocation() + File.separator + name);
		try {
			File file = new File(Preloader.get().getVocabLocation() + File.separator + name);
			if (!file.exists()) {
				throw new FileParseException("File '" + file.getAbsolutePath() + "' doesn't exist!");
			} else {
				if (file.isDirectory()) {
					throw new FileParseException("File '" + file.getAbsolutePath() + "' is a directory!");
				}
			}
			
			InputStream input = new FileInputStream(file);
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			VocabData data = new VocabData();
			ArrayList<VocabPair> vocab = new ArrayList<>();
			boolean goOn = true;
			int readLines = 0;
			
			while (goOn) {
				if (readLines > maxLines) {
					reader.close();
					throw new StringIndexOutOfBoundsException("File was too big! Maximum " + maxLines + " lines!");
				}
				String line = reader.readLine();
				readLines++;
				if (line == null) {
					goOn = false;
					break;
				}
				if (readLines == 1) {
					if (line.startsWith(Vocab.DATA_LINE)) {
						String subStr = line.substring(Vocab.DATA_LINE.length());
						String[] strData = subStr.split("\\|");
						if (strData.length >= 2) {
							data.setFirstLanguageCode(strData[0]);
							data.setSecondLanguageCode(strData[1]);
						}
						continue;
					}
				}
				try {
					VocabPair loadedVocabPair = loadLine(line.trim());
					if (loadedVocabPair != null) {
						vocab.add(loadedVocabPair);
					}
				} catch (FileParseException e) {
					reader.close();
					throw new FileParseException(e, name, readLines);
				}
			}
			reader.close();
			input.close();
			
			VocabPair[] vocabulary = new VocabPair[vocab.size()];
			for (int i = 0; i < vocabulary.length; i++) {
				vocabulary[i] = vocab.get(i);
			}
			
			Vocab loadedVocab = new Vocab(name, vocabulary, file, data.getFirstLanguageCode(), data.getSecondLanguageCode());
			existingVocab.put(name, loadedVocab);
			return loadedVocab;
		} catch (IOException e) {
			Logging.logException(e);
		}
		return null;
	}
	
	private VocabPair loadLine(String line) {
		if (line.startsWith("#") || line.isBlank()) {
			return null;
		}
		String[] translations = line.split(" - ");
		if (translations.length != 2) {
			throw new FileParseException("Couldn't find two sides!");
		}
		String[] firstLangWords = translations[0].trim().split(";");
		String[] secondLangWords = translations[1].trim().split(";");
		if (firstLangWords.length < 1) {
			throw new FileParseException("Couldn't find word in first language!");
		}
		if (secondLangWords.length < 1) {
			throw new FileParseException("Couldn't find word in second language!");
		}
		
		for (int i = 0; i < firstLangWords.length; i++) {
			firstLangWords[i] = firstLangWords[i].trim();
		}

		for (int i = 0; i < secondLangWords.length; i++) {
			secondLangWords[i] = secondLangWords[i].trim();
		}
		secondLangWords = extendArray(secondLangWords, "etw.", new String[]{"etw", "etwas"});
		secondLangWords = extendArray(secondLangWords, "jdn.", new String[]{"jdn", "jemanden"});
		secondLangWords = extendArray(secondLangWords, "jdm.", new String[]{"jdm", "jemandem"});
		
		return new VocabPair(firstLangWords, secondLangWords);
	}
	
	private String[] extendArray(String[] arr, String extendableString, String[] extendings) {
		String[] newArr = arr;
		String regexExtendableString = extendableString.replaceAll("\\.", "\\\\.");
		for (int i = 0; i < newArr.length; i++) {
			String filteredString = newArr[i];
			if (filteredString.contains(extendableString)) {
				newArr = Arrays.copyOf(arr, arr.length + extendings.length);
				for (int j = 0; j < extendings.length; j++) {
					String extending = extendings[j];
					newArr[newArr.length - j - 1] = filteredString.replaceAll(regexExtendableString, extending);
				}
			}
		}
		return newArr;
	}
	
	public boolean isLoadable(String s) throws NotLoadableException {
		String[] splitLines = s.split("\n");
		for (int i = 0; i < splitLines.length; i++) {
			String line = splitLines[i];
			try {
				loadLine(line);
			} catch (FileParseException e) {
				throw new NotLoadableException(e.getMessage(), i);
			}
		}
		return true;
	}
}
