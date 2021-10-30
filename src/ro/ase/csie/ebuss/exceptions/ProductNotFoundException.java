package ro.ase.csie.ebuss.exceptions;

public class ProductNotFoundException extends Exception {
	String message;

	public ProductNotFoundException(String message) {
		this.message = message;
	}
	
}
