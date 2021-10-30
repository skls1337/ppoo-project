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

	public static void main(String[] args)
			throws InvalidNameException, InvalidPriceException, InvalidQuantityException, InvalidShopNameException,
			InvalidShopProductsException, InvalidAvailableQuantityException, IOException, InterruptedException {

		Map<String, AbstractProduct> products = Utils.loadData("file.txt");

		Shop shop = Shop.createShopInstance("magazin", products);

		boolean intrerupt = false;

		Utils.printOptions();
		try {
			Scanner scanner = new Scanner(System.in);
			while (true) {
				if (intrerupt) {
					break;
				}
				int option = scanner.nextInt();

				switch (option) {
				case 1:
					// view product list
					shop.viewProducts();
					System.out.println("Select another option:");
					break;
				case 2:
					// add new product
					shop.add();
					System.out.println("Select another option:");
					break;
				case 3:
					// update existing product
					System.out.println("Select another option:");
					break;
				case 4:
					// delete existing product
					shop.delete();
					System.out.println("Select another option:");
					break;
				case 5:
					// add stock to specific product
					shop.addStock();
					System.out.println("Select another option:");
					break;
				case 6:
					// exit
					System.out.println("Exiting store . . .");
					intrerupt = true;
					break;
				default:
					System.out.println("Invalid option!");
					System.out.println("Select another option:");
					break;
				}
				Utils.printOptions();
			}
			scanner.close();

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
