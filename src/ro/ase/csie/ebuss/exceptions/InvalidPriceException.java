package ro.ase.csie.ebuss.exceptions;

public class InvalidPriceException extends Exception {
	String message;

	public InvalidPriceException(String message) {
		super();
		this.message = message;
	}
	
	
}
