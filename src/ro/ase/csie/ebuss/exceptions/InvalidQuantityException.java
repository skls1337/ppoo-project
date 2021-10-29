package ro.ase.csie.ebuss.exceptions;

public class InvalidQuantityException extends Exception {
	String message;

	public InvalidQuantityException(String message) {
		this.message = message;
	}
	
	
}
