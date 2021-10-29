package ro.ase.csie.ebuss.exceptions;

public class InvalidShopProductsException extends Exception {
	String message;

	public InvalidShopProductsException(String message) {
		this.message = message;
	}
	
	
}
