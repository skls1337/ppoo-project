package ro.ase.csie.ebuss.exceptions;

public class InvalidNameException extends Exception {
	String message;

	public InvalidNameException(String message) {
		super();
		this.message = message;
	}
	
	
}
