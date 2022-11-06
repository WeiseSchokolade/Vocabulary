package de.schoko.vocab.exceptions;

public class NotLoadableException extends Exception {
	private static final long serialVersionUID = 3999369060108294910L;
	private int line = -1;
	
	public NotLoadableException(String message, int line) {
		super(message);
		this.line = line;
	}
	
	public int getLine() {
		return line;
	}
}
