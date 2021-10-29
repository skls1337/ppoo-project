package ro.ase.csie.ebuss.exceptions;

public class InvalidAvailableQuantityException extends Exception {
	String message;

	public InvalidAvailableQuantityException(String message) {
		this.message = message;
	}
	
}
