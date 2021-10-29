package ro.ase.csie.ebuss.interfaces;

import ro.ase.csie.ebuss.exceptions.InvalidQuantityException;
import ro.ase.csie.ebuss.models.AbstractProduct;

public interface ShopInterface {
	public void addProduct(String productKey,AbstractProduct product);
	public void updateProduct(String oldProductKey,AbstractProduct newProduct);
	public void deleteProduct(String productKey);
	public void buyProduct(String productKey, int quantity)throws InvalidQuantityException;
	public void refreshStock(String productKey, int quantity)throws InvalidQuantityException;
	public void viewProducts();
}
