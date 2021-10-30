package ro.ase.csie.ebuss.models;

import javax.naming.InvalidNameException;

import ro.ase.csie.ebuss.enums.Size;
import ro.ase.csie.ebuss.exceptions.InvalidAvailableQuantityException;
import ro.ase.csie.ebuss.exceptions.InvalidPriceException;

public class Cloathing extends AbstractProduct {
	private Size size;

	public Cloathing(double price, String productName, int availableQuantity, Size size)
			throws InvalidPriceException, InvalidNameException, InvalidAvailableQuantityException {
		super(price, productName, availableQuantity);
		this.setSize(size);
	}

	public Size getSize() {
		return size;
	}

	private void setSize(Size size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return price + "\n" + productName + "\n" + availableQuantity + "\n" + size;
	}
	
	@Override
	public String print() {
		return "Price: " + price + "\n" + "Product Name: " + productName + "\n" + "Available Quantity: "
				+ availableQuantity + "\n" + "Size: " + size;
	}

}
