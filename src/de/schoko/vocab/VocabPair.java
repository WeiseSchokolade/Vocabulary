package de.schoko.vocab;

import de.schoko.vocab.resources.Strings;

public class VocabPair {
	private String[] firstLanguagePair;
	private String[] secondLanguagePair;
	private Vocab vocab;
	private boolean displayFirstLanguage = false;
	
	public static final int EQUALLY_TRANSLATED_PAIR = 0;
	public static final int PHRASE_WITH_GAP = 1;
	
	private int type = 0;
	
	public VocabPair(String[] firstLanguagePair, String[] secondLanguagePair) {
		if (firstLanguagePair[0].contains(" _ ")) {
			type = PHRASE_WITH_GAP;
		}
		
		this.firstLanguagePair = firstLanguagePair;
		this.secondLanguagePair = secondLanguagePair;
	}
	
	public void init(Vocab vocab) {
		this.vocab = vocab;
	}
	
	public String getAskedPhrase() {
		if (type == PHRASE_WITH_GAP) {
			return firstLanguagePair[0];
		}
		if (vocab.getDisplayFirst()) {
			return firstLanguagePair[0];
		} else {
			return secondLanguagePair[0];
		}
	}
	
	public String getDisplayedSolution() {
		return getSolutions()[0];
	}
	
	public String[] getSolutions() {
		if (type == PHRASE_WITH_GAP) {
			return secondLanguagePair;
		}
		this.displayFirstLanguage = vocab.getDisplayFirst();
		if (this.displayFirstLanguage) {
			return secondLanguagePair;
		} else {
			return firstLanguagePair;
		}
	}
	
	public String[] getFirstLanguage() {
		return firstLanguagePair;
	}
	
	public String[] getSecondLanguage() {
		return secondLanguagePair;
	}
	
	public String getSolutionString() {
		if (type == PHRASE_WITH_GAP) {
			return firstLanguagePair[0].replaceAll(" _ ", " <u><i>" + secondLanguagePair[0] + "</i></u>");
		}
		
		if (vocab.getDisplayFirst()) {
			return firstLanguagePair[0] + " - " + secondLanguagePair[0];
		} else {
			return secondLanguagePair[0] + " - " + firstLanguagePair[0];
		}
	}
	
	protected String[] getLanguage(boolean getFirstLanguage) {
		if (getFirstLanguage) {
			return firstLanguagePair;
		} else {
			return secondLanguagePair;
		}
	}
	
	public int getType() {
		return type;
	}

	public String getPrompt() {
		if (type == EQUALLY_TRANSLATED_PAIR) {
			String language = vocab.getFirstLanguage();
			if (vocab.getDisplayFirst()) {
				language = vocab.getSecondLanguage();
			}
			return Strings.fillIn(Strings.PROMPT_TRANSLATE, language);
		} else if (type == PHRASE_WITH_GAP) {
			return Strings.PROMPT_FILL_IN_GAP;
		}
		return "";
	}
}
