package ro.ase.csie.ebuss.exceptions;

public class InvalidShopNameException extends Exception {
	String message;

	public InvalidShopNameException(String message) {
		this.message = message;
	}
	
	
}
