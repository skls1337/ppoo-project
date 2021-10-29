package ro.ase.csie.ebuss.models;
import javax.naming.InvalidNameException;

import ro.ase.csie.ebuss.exceptions.InvalidAvailableQuantityException;
import ro.ase.csie.ebuss.exceptions.InvalidPriceException;
import ro.ase.csie.ebuss.exceptions.InvalidQuantityException;

public class Vegetable extends AbstractProduct {

	public Vegetable(double price, String productName, int availableQuantity) throws InvalidNameException,
			InvalidPriceException, InvalidQuantityException, InvalidAvailableQuantityException {
		super(price, productName, availableQuantity);
	}

	@Override
	public String toString() {
		return "Price: "+price + "\n" + "Product Name: "+productName + "\n" + "Available quantity: "+ availableQuantity;
	}

}
