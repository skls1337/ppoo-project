package ro.ase.csie.ebuss.shop;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import javax.naming.InvalidNameException;

import ro.ase.csie.ebuss.enums.Size;
import ro.ase.csie.ebuss.enums.TransactionType;
import ro.ase.csie.ebuss.exceptions.InvalidAvailableQuantityException;
import ro.ase.csie.ebuss.exceptions.InvalidPriceException;
import ro.ase.csie.ebuss.exceptions.InvalidQuantityException;
import ro.ase.csie.ebuss.exceptions.InvalidShopNameException;
import ro.ase.csie.ebuss.exceptions.InvalidShopProductsException;
import ro.ase.csie.ebuss.exceptions.ProductNotFoundException;
import ro.ase.csie.ebuss.interfaces.ShopInterface;
import ro.ase.csie.ebuss.models.AbstractProduct;
import ro.ase.csie.ebuss.models.Cloathing;
import ro.ase.csie.ebuss.models.Vegetable;

public class Shop implements ShopInterface {
	private static Shop shop = null;

	private String shopName;
	private Map<String, AbstractProduct> products = new HashMap<>();

	private Shop(String shopName, Map<String, AbstractProduct> products)
			throws InvalidShopNameException, InvalidShopProductsException {
		setShopName(shopName);
		setProducts(products);
	}

	public String getShopName() {
		return shopName;
	}

	private void setShopName(String shopName) throws InvalidShopNameException {
		if (shopName.length() < 3 || shopName == null) {
			throw new InvalidShopNameException("Invalid Shop Name. The shop name must contain at least 3 characters");
		}
		this.shopName = shopName;
	}

	public Map<String, AbstractProduct> getProducts() {
		return products;
	}

	private void setProducts(Map<String, AbstractProduct> products) throws InvalidShopProductsException {
		if (products == null) {
			throw new InvalidShopProductsException("The shop must contain products");
		}
		this.products = products;
	}

	public static Shop createShopInstance(String shopName, Map<String, AbstractProduct> products)
			throws InvalidShopNameException, InvalidShopProductsException {
		if (shop == null) {
			shop = new Shop(shopName, products);
		}
		return shop;
	}

	@Override
	public void addProduct(String productKey, AbstractProduct product) {
		if (products.containsKey(productKey)) {
			throw new UnsupportedOperationException(
					"Existing Product. There is already an existing product with that key");
		}
		products.put(productKey, product);

	}

	@Override
	public void updateProduct(String oldProductKey, AbstractProduct newProduct) {
		if (!products.containsKey(oldProductKey)) {
			throw new UnsupportedOperationException(
					"Product Not Found. The product with the key " + oldProductKey + " was not found.");
		}
		products.replace(oldProductKey, newProduct);

	}

	@Override
	public void deleteProduct(String productKey) {
		if (!products.containsKey(productKey)) {
			throw new UnsupportedOperationException(
					"Product Not Found. The product with the key " + productKey + " was not found.");
		}
		products.remove(productKey);

	}

	@Override
	public void buyProduct(String productKey, int quantity) throws InvalidQuantityException {
		if (!products.containsKey(productKey)) {
			throw new UnsupportedOperationException(
					"Product Not Found. The product with the key " + productKey + " was not found.");
		}
		AbstractProduct product = products.get(productKey);
		if (product.getAvailableQuantity() < quantity) {
			throw new UnsupportedOperationException(
					"Not Enough Quantity. The available quantity is not enough for the request quantity");
		}
		product.changeQuantity(TransactionType.BUY_PRODUCT, quantity);
		products.replace(productKey, product);

	}

	@Override
	public void refreshStock(String productKey, int quantity) throws InvalidQuantityException {
		if (!products.containsKey(productKey)) {
			throw new UnsupportedOperationException(
					"Product Not Found. The product with the key " + productKey + " was not found.");
		}

		AbstractProduct product = products.get(productKey);
		product.changeQuantity(TransactionType.ADD_STOCK, quantity);
		products.replace(productKey, product);
	}

	@Override
	public String toString() {
		return "Shop [shopName=" + shopName + ", products=" + products + "]";
	}

	@Override
	public void viewProducts() {
		if (products.size() < 0) {
			System.out.println("The shop has no products");
		}
		for (Entry<String, AbstractProduct> entry : products.entrySet()) {
			System.out.println("Product key: " + entry.getKey());
			System.out.println("Product: " + entry.getValue().print());
		}

	}

	public void add() throws InterruptedException, InvalidNameException, InvalidPriceException,
			InvalidQuantityException, InvalidAvailableQuantityException {
		Scanner scanner = new Scanner(System.in);
		String productKey;
		double productPrice;
		String productName;
		int defaultQuantity;
		AbstractProduct product;

		System.out.println("Enter product type");
		String productType = scanner.nextLine();
		switch (productType) {
		case "vegetable":
			System.out.println("Enter product key");
			productKey = scanner.nextLine();
			System.out.println("Enter product price");
			productPrice = scanner.nextDouble();
			scanner.nextLine();
			System.out.println("Enter product name");
			productName = scanner.nextLine();
			System.out.println("Enter starting quantity");
			defaultQuantity = scanner.nextInt();
			product = new Vegetable(productPrice, productName, defaultQuantity);
			addProduct(productKey, product);
			break;
		case "cloathing":
			System.out.println("Enter product key");
			productKey = scanner.nextLine();
			System.out.println("Enter product price");
			productPrice = scanner.nextDouble();
			scanner.nextLine();
			System.out.println("Enter product name");
			productName = scanner.nextLine();
			System.out.println("Enter starting quantity");
			defaultQuantity = scanner.nextInt();
			scanner.nextLine();
			System.out.println("Enter size");
			String size = scanner.nextLine().toUpperCase();
			product = new Cloathing(productPrice, productName, defaultQuantity, Size.valueOf(size));
			addProduct(productKey, product);
			break;
		default:
			System.out.println("Invalid product type . . .");
			Thread.sleep(3000);
			add();
		}
	}

	public void update() throws ProductNotFoundException, InvalidNameException, InvalidPriceException,
			InvalidAvailableQuantityException, InvalidQuantityException, InterruptedException {
		Scanner scanner = new Scanner(System.in);
		String productKey;
		double productPrice;
		String productName;
		int defaultQuantity;
		AbstractProduct product;
		System.out.println("Enter product key");
		productKey = scanner.nextLine();
		product = products.get(productKey);
		if (product == null) {
			throw new ProductNotFoundException("Product Not Found");
		}
		String classType = product.getClass().toString();
		switch (classType) {
		case "class ro.ase.csie.ebuss.models.Cloathing":

			System.out.println("Enter new product price (old: " + product.getPrice() + ")");
			productPrice = scanner.nextDouble();
			scanner.nextLine();
			System.out.println("Enter product name (old: " + product.getProductName() + ")");
			productName = scanner.nextLine();
			System.out.println("Enter starting quantity (old: " + product.getAvailableQuantity() + ")");
			defaultQuantity = scanner.nextInt();
			scanner.nextLine();
			System.out.println("Enter size");
			String size = scanner.nextLine().toUpperCase();
			product = new Cloathing(productPrice, productName, defaultQuantity, Size.valueOf(size));
			updateProduct(productKey, product);
			break;
		case "class ro.ase.csie.ebuss.models.Vegetable":

			System.out.println("Enter new product price (old: " + product.getPrice() + ")");
			productPrice = scanner.nextDouble();
			scanner.nextLine();
			System.out.println("Enter product name (old: " + product.getProductName() + ")");
			productName = scanner.nextLine();
			System.out.println("Enter starting quantity (old: " + product.getAvailableQuantity() + ")");
			defaultQuantity = scanner.nextInt();
			product = new Vegetable(productPrice, productName, defaultQuantity);
			updateProduct(productKey, product);
			break;
		default:
			System.out.println("Invalid product type . . .");
			Thread.sleep(3000);
			break;

		}

	}

	public void delete() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter product key to delete it");
		String productKey = scanner.nextLine();
		deleteProduct(productKey);

	}

	public void addStock() throws InvalidQuantityException {
		System.out.println("Enter product key you want to add stock");
		Scanner scanner = new Scanner(System.in);
		String productKey = scanner.nextLine();
		int additionalQuantity = scanner.nextInt();
		refreshStock(productKey, additionalQuantity);

	}

}
