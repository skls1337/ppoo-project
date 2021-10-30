package ro.ase.csie.ebuss.models;

import javax.naming.InvalidNameException;

import ro.ase.csie.ebuss.enums.TransactionType;
import ro.ase.csie.ebuss.exceptions.InvalidAvailableQuantityException;
import ro.ase.csie.ebuss.exceptions.InvalidPriceException;
import ro.ase.csie.ebuss.exceptions.InvalidQuantityException;

public abstract class AbstractProduct {
	protected double price;
	protected String productName;
	protected int availableQuantity;
	
	@SuppressWarnings("unused")
	private AbstractProduct() {}

	public AbstractProduct(double price, String productName, int availableQuantity)
			throws InvalidPriceException, InvalidNameException, InvalidAvailableQuantityException {
		setPrice(price);
		setProductName(productName);
		setAvailableQuantity(availableQuantity);
	}

	public double getPrice() {
		return price;
	}

	protected void setPrice(double price) throws InvalidPriceException {
		if (price < 0) {
			throw new InvalidPriceException("Invalid Price. The price cannot take negative values");
		} else {
			this.price = price;
		}
	}

	public String getProductName() {
		return productName;
	}

	protected void setProductName(String productName) throws InvalidNameException {
		if (productName.length() < 3 || productName == null) {
			throw new InvalidNameException("Invalid Name. The name should be at least 3 characters long");
		} else {
			this.productName = productName;
		}

	}

	public int getAvailableQuantity() {
		return availableQuantity;
	}

	protected void setAvailableQuantity(int availableQuantity) throws InvalidAvailableQuantityException {
		if (availableQuantity < 0) {
			throw new InvalidAvailableQuantityException(
					"Invalid Quantity. The available quantity must take positive values");
		} else {
			this.availableQuantity = availableQuantity;
		}
	}
	
	public void changeQuantity(TransactionType transactionType,int quantity) throws InvalidQuantityException {
		if(quantity<0) {
			throw new InvalidQuantityException("InvalidQuantity. The quantity cannot take negative values");
		}
		switch(transactionType) {
			case ADD_STOCK:
				availableQuantity+=quantity;
			break;
			case BUY_PRODUCT:
				availableQuantity-=quantity;
			break;
		}
	}
	
	public abstract String print();
	
	@Override
	public String toString() {
		return "AbstractProduct [price=" + price + ", productName=" + productName + ", availableQuantity="
				+ availableQuantity + "]";
	}
	

}
