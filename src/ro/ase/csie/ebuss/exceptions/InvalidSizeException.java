package ro.ase.csie.ebuss.exceptions;

public class InvalidSizeException extends Exception {
	String message;

	public InvalidSizeException(String message) {
		this.message = message;
	}
	
}
