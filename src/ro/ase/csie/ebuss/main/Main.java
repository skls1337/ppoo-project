package ro.ase.csie.ebuss.main;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.naming.InvalidNameException;

import ro.ase.csie.ebuss.enums.Size;
import ro.ase.csie.ebuss.exceptions.InvalidAvailableQuantityException;
import ro.ase.csie.ebuss.exceptions.InvalidPriceException;
import ro.ase.csie.ebuss.exceptions.InvalidQuantityException;
import ro.ase.csie.ebuss.exceptions.InvalidShopNameException;
import ro.ase.csie.ebuss.exceptions.InvalidShopProductsException;
import ro.ase.csie.ebuss.models.AbstractProduct;
import ro.ase.csie.ebuss.models.Cloathing;
import ro.ase.csie.ebuss.models.Vegetable;
import ro.ase.csie.ebuss.shop.Shop;
import ro.ase.csie.ebuss.utils.Utils;

public class Main {

	public static void main(String[] args) throws InvalidNameException, InvalidPriceException, InvalidQuantityException,
			InvalidShopNameException, InvalidShopProductsException, InvalidAvailableQuantityException, IOException, InterruptedException {
		Map<String, AbstractProduct> products = Utils.loadData("file.txt");

		Shop shop = Shop.createShopInstance("magazin", products);

		boolean intrerupt = true;
		Scanner scanner = new Scanner(System.in);
		int option = scanner.nextInt();

		switch (option) {
		case 1:
			// view product list
			shop.add(scanner);
			break;
		case 2:
			// add new product
			break;
		case 3:
			// update existing product
			break;
		case 4:
			// delete existing product
			break;
		case 5:
			// add stock to specific product
			break;
		case 6:
			// exit
			break;
		default:
			System.out.println("Invalid option!");

		}
		scanner.close();

		try {
			shop.viewProducts();
			shop.buyProduct("tomato", 2);
//		while(true) {			

			

			shop.deleteProduct("cucumber");

			shop.refreshStock("camasa", 5);

			shop.updateProduct("camasa", new Cloathing(0.2, "camasa", 2, Size.S));

//			if(!intrerupt) {
//				break;
//			}
//		}

		} catch (Exception e) {
			e.printStackTrace();
		}

		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Utils.saveData(shop, "file.txt");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}));

	}

}
