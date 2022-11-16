package de.schoko.vocab;

import de.schoko.vocab.resources.Strings;

public class VocabData {
	private String firstLanguageCode = Strings.LANGUAGE_FR;
	private String secondLanguageCode = Strings.LANGUAGE_DE;
	
	public String getFirstLanguageCode() {
		return firstLanguageCode;
	}
	public void setFirstLanguageCode(String firstLanguageCode) {
		this.firstLanguageCode = firstLanguageCode;
	}
	
	public String getSecondLanguageCode() {
		return secondLanguageCode;
	}
	public void setSecondLanguageCode(String secondLanguageCode) {
		this.secondLanguageCode = secondLanguageCode;
	}
}
