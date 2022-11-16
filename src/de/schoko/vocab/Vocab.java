package de.schoko.vocab;

import de.schoko.vocab.exceptions.VocabCompleteException;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import de.schoko.utility.Logging;
import de.schoko.utility.StringUtility;

public class Vocab {
	private String name;
	private VocabPair[] vocabulary;
	private int current = 0;
	private String firstLanguageCode;
	private String secondLanguageCode;
	private boolean displayFirst = false;
	
	private VocabPair lastWords;
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
	
	public Vocab(String name, VocabPair[] vocabulary, File sourceFile, String firstLanguageCode, String secondLanguageCode) {
		this.name = name;
		this.vocabulary = vocabulary;
		this.sourceFile = sourceFile;
		this.firstLanguageCode = firstLanguageCode;
		this.secondLanguageCode = secondLanguageCode;
		enteredWords = new String[vocabulary.length];
		correctWords = new boolean[vocabulary.length];
		for (VocabPair pair : this.vocabulary) {
			pair.init(this);
		}
	}
	
	public void start() {
		if (order == FORWARDS_ORDER) {
			current = 0;
		} else if (order == BACKWARDS_ORDER) {
			current = vocabulary.length - 1;
		} else if (order == RANDOM_ORDER) {
			current = 0;
			List<VocabPair> asList = Arrays.asList(vocabulary);
			Collections.shuffle(asList);
		}
		Logging.logInfo("Started vocabulary " + name);
	}
	
	public Vocab copy() {
		Vocab copy = new Vocab(name, vocabulary, sourceFile, firstLanguageCode, secondLanguageCode);
		copy.order = this.order;
		copy.displayFirst = this.displayFirst;
		copy.firstLanguageCode = this.firstLanguageCode;
		return copy;
	}
	
	public boolean checkAndNext(String s) throws VocabCompleteException {
		VocabPair currentPair = vocabulary[current];
		String[] solution = currentPair.getSolutions();
		
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

	public String[] getDisplayedSolutions() {
		String[] ret = new String[vocabulary.length];
		for (int i = 0; i < ret.length; i++) {
			ret[i] = vocabulary[i].getDisplayedSolution();
		}
		return ret;
	}
	
	public String getAskedPhrase() {
		return vocabulary[current].getAskedPhrase();
	}
	
	private String[][] getSide(boolean isFirstSide) {
		String[][] ret = new String[vocabulary.length][];
		
		for (int i = 0; i < ret.length; i++) {
			ret[i] = vocabulary[i].getLanguage(isFirstSide);
		}
		
		return ret;
	}
	
	public String[][] getFirstLanguageVocab() {
		return getSide(true);
	}
	
	public String[][] getSecondLanguageVocab() {
		return getSide(false);
	}
	
	public String getName() {
		return this.name;
	}
	
	public VocabPair[] getVocabulary() {
		return vocabulary;
	}
	
	public int getCurrentIndex() {
		return current;
	}
	
	public VocabPair getCurrentPair() {
		return vocabulary[current];
	}

	public String getFirstLanguage() {
		return firstLanguageCode;
	}

	public void setFirstLanguage(String firstLanguage) {
		this.firstLanguageCode = firstLanguage;
	}
	
	public String getSecondLanguage() {
		return secondLanguageCode;
	}
	
	public void setSecondLanguage(String secondLanguage) {
		this.secondLanguageCode = secondLanguage;
	}
	
	public VocabPair getLastWordPair() {
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