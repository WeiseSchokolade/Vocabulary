package de.schoko.vocab.exceptions;

import de.schoko.vocab.resources.Strings;

public class FileParseException extends RuntimeException {
	private static final long serialVersionUID = -8319022607745006504L;
	private String message = Strings.EXCEPTION_UNKNOWN;
	private String error = Strings.EXCEPTION_UNKNOWN;
	private int line = -1;
	
	public FileParseException(String error, String filename, int lineIndex) {
		super("An exception occured while trying to parse line " + lineIndex + " of file '" + filename + "': " + error);
		message = "An exception occured while trying to parse line " + lineIndex + " of file '" + filename + "': " + error;
		this.error = error;
		this.line = lineIndex;
	}
	
	public FileParseException(FileParseException simpleError, String filename, int lineIndex) {
		super("An exception occured while trying to parse line " + lineIndex + " of file " + filename + ": " + simpleError.getMessage());
		message = "An exception occured while trying to parse line " + lineIndex + " of file " + filename + ": " + simpleError.getMessage();
		this.error = simpleError.getMessage();
		this.line = lineIndex;
	}
	
	public FileParseException(String error) {
		super(error);
		message = error;
		this.error = error;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
	
	public String getError() {
		return error;
	}
	
	public int getLine() {
		return line;
	}
}
