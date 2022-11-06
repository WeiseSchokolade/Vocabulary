package de.schoko.vocab;

import de.schoko.vocab.exceptions.VocabCompleteException;
import de.schoko.vocab.resources.Strings;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import de.schoko.utility.Logging;
import de.schoko.utility.StringUtility;

public class Vocab {
	private String name;
	private String[][][] vocabulary;
	private int current = 0;
	private String firstLanguage = Strings.LANGUAGE_FIRST;
	private String secondLanguage = Strings.LANGUAGE_SECOND;
	private boolean displayFirst = false;
	
	private String[][] lastWords = {};
	private String[] enteredWords;
	private boolean[] correctWords;
	
	private int total = 0;
	private int right = 0;
	private int wrong = 0;

	public static final int FORWARDS_ORDER = 0;
	public static final int BACKWARDS_ORDER = 1;
	public static final int RANDOM_ORDER = 2;
	
	private int order = RANDOM_ORDER;
	
	private File sourceFile;
	
	public Vocab(String name, String[][][] vocabulary, File sourceFile) {
		this.name = name;
		this.vocabulary = vocabulary;
		this.sourceFile = sourceFile;
		enteredWords = new String[vocabulary.length];
		correctWords = new boolean[vocabulary.length];
	}
	
	public void start() {
		if (order == FORWARDS_ORDER) {
			current = 0;
		} else if (order == BACKWARDS_ORDER) {
			current = vocabulary.length - 1;
		} else if (order == RANDOM_ORDER) {
			current = 0;
			List<String[][]> asList = Arrays.asList(vocabulary);
			Collections.shuffle(asList);
		}
		Logging.logInfo("Started vocabulary " + name);
	}
	
	public Vocab copy() {
		Vocab copy = new Vocab(name, vocabulary, sourceFile);
		copy.order = this.order;
		copy.displayFirst = this.displayFirst;
		copy.firstLanguage = this.firstLanguage;
		return copy;
	}
	
	public boolean checkAndNext(String s) throws VocabCompleteException {
		String[][] currentPair = vocabulary[current];
		String[] solution;
		if (displayFirst) {
			solution = (currentPair[1]);
		} else {
			solution = (currentPair[0]);
		}
		
		boolean correct = (StringUtility.unifiedContains(solution, s));
		lastWords = currentPair;
		
		total++;
		if (correct) {
			right++;
			correctWords[current] = true;
		} else {
			wrong++;
		}
		
		enteredWords[current] = s;
		
		if (order == FORWARDS_ORDER || order == RANDOM_ORDER) {
			if (current + 1 >= vocabulary.length) {
				throw new VocabCompleteException();
			}
			current++;
		} else if (order == BACKWARDS_ORDER) {
			if (current - 1 < 0) {
				throw new VocabCompleteException();
			}
			current--;
		}
		
		
		return correct;
	}

	public String[] getWordsToTranslate() {
		String[][] currentPair = vocabulary[current];
		if (displayFirst) {
			return currentPair[0];
		} else {
			return currentPair[1];
		}
	}
	
	private String[][] getSide(int index) {
		String[][] ret = new String[vocabulary.length][];
		
		for (int i = 0; i < ret.length; i++) {
			ret[i] = vocabulary[i][index];
		}
		
		return ret;
	}
	
	public String[][] getFirstLanguageVocab() {
		return getSide(0);
	}
	
	public String[][] getSecondLanguageVocab() {
		return getSide(1);
	}
	
	public String getName() {
		return this.name;
	}
	
	public String[][][] getVocabulary() {
		return vocabulary;
	}
	
	public int getCurrentIndex() {
		return current;
	}
	
	public String[][] getCurrentPair() {
		return vocabulary[current];
	}

	public String getFirstLanguage() {
		return firstLanguage;
	}

	public void setFirstLanguage(String firstLanguage) {
		this.firstLanguage = firstLanguage;
	}
	
	public String getSecondLanguage() {
		return secondLanguage;
	}
	
	public void setSecondLanguage(String secondLanguage) {
		this.secondLanguage = secondLanguage;
	}
	
	public String[][] getLastWords() {
		return lastWords;
	}
	
	public int getTotal() {
		return total;
	}
	
	public int getRight() {
		return right;
	}
	
	public int getWrong() {
		return wrong;
	}
	
	public void setDisplayFirst(boolean displayFirst) {
		this.displayFirst = displayFirst;
	}
	
	public boolean getDisplayFirst() {
		return displayFirst;
	}
	
	public String[] getEnteredWords() {
		return enteredWords;
	}
	
	public boolean[] getCorrectWords() {
		return correctWords;
	}
	
	public File getSourceFile() {
		return sourceFile;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public int getOrder() {
		return order;
	}
}