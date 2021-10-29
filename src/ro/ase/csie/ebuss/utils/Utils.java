package ro.ase.csie.ebuss.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import javax.naming.InvalidNameException;

import ro.ase.csie.ebuss.enums.Size;
import ro.ase.csie.ebuss.exceptions.InvalidAvailableQuantityException;
import ro.ase.csie.ebuss.exceptions.InvalidPriceException;
import ro.ase.csie.ebuss.exceptions.InvalidQuantityException;
import ro.ase.csie.ebuss.models.AbstractProduct;
import ro.ase.csie.ebuss.models.Cloathing;
import ro.ase.csie.ebuss.models.Vegetable;
import ro.ase.csie.ebuss.shop.Shop;

public class Utils {

	public static void saveData(Shop shop,String fileName) throws IOException {
		FileWriter file = new FileWriter(fileName);
		Map<String, AbstractProduct> products = shop.getProducts();
		for(Entry<String, AbstractProduct> product : products.entrySet()) {
			file.write(product.getValue().getClass().toString()+"\n");
			file.write(product.getKey()+"\n");
			file.write(product.getValue().toString()+"\n");
		}
		file.close();
		
	}
	
	public static Map<String,AbstractProduct> loadData(String fileName) throws FileNotFoundException, InvalidNameException, InvalidPriceException, InvalidAvailableQuantityException, InvalidQuantityException {
		File file = new File(fileName);
		Scanner scanner=  new Scanner(file);
		
		Map<String,AbstractProduct> products= new HashMap<>();
		while(scanner.hasNextLine()) {
			String classType = scanner.nextLine();
			String key = scanner.nextLine();
			double price;
			String productName;
			int availableQuantity;
			Size size;
			AbstractProduct product;
			switch(classType){
			
				case "class ro.ase.csie.ebuss.models.Cloathing":
					
				price = Double.parseDouble(scanner.nextLine());
				productName = scanner.nextLine();
				availableQuantity = Integer.parseInt(scanner.nextLine());
				size = Size.valueOf(scanner.nextLine());
				
				product = new Cloathing(price, productName, availableQuantity, size);
				products.put(key, product);
			
				break;
				
				case "class ro.ase.csie.ebuss.models.Vegetable":
					
				price = Double.parseDouble(scanner.nextLine());
				productName = scanner.nextLine();
				availableQuantity = Integer.parseInt(scanner.nextLine());
				
				product = new Vegetable(price, productName, availableQuantity);
				products.put(key, product);
				break;
				
				default:
				break;
					
			}
		}
		scanner.close();
		return products;
	}
}
