package de.schoko.vocab;

public class VocabData {
	private String firstLanguageCode = "FR";
	private String secondLanguageCode = "DE";
	
	private String sourceText = "un exemple - ein Beispiel";
	
	public VocabData() {
		
	}
	
	public static VocabData createFromText(String vocabText) {
		VocabData data = new VocabData();
		if (vocabText.split("\n")[0].startsWith(Vocab.DATA_LINE)) {
			String dataLine = vocabText.split("\n")[0];

			String subStr = dataLine.substring(Vocab.DATA_LINE.length());
			String[] strData = subStr.split("\\|");
			if (strData.length >= 2) {
				data.setFirstLanguageCode(strData[0]);
				data.setSecondLanguageCode(strData[1]);
			}
		}
		data.sourceText = vocabText;
		return data;
	}
	
	public String getStrippedSourceText() {
		String[] lines = sourceText.split("\n");
		if (lines[0].startsWith(Vocab.DATA_LINE)) {
			String ret = "";
			for (int i = 1; i < lines.length; i++) {
				ret += lines[i] + "\n";
			}
			return ret;
		} else {
			return sourceText;
		}
	}
	
	public String getUnstrippedSourceText() {
		String ret = getStrippedSourceText();
		String dataLine = Vocab.DATA_LINE;
		dataLine += firstLanguageCode + "|";
		dataLine += secondLanguageCode;
		dataLine += "\n";
		ret = dataLine + ret;
		return ret;
	}
	
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
	
	public String getSourceText() {
		return sourceText;
	}
	
	public void setSourceText(String sourceText) {
		this.sourceText = sourceText;
	}
}
