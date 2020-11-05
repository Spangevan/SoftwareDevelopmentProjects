package com.techelevator.reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.techelevator.inventory.Appetizer;
import com.techelevator.inventory.Beverage;
import com.techelevator.inventory.Dessert;
import com.techelevator.inventory.Entree;
import com.techelevator.inventory.InventoryCount;
import com.techelevator.inventory.Item;

public class FileInventoryReader implements InventoryReader {

	private String filename;

	public FileInventoryReader(String filename) {
		this.filename = filename;
	}

	@Override
	public Map<String, InventoryCount> read() throws FileNotFoundException {

		List<String> lines = readAllLines();

		return createInventoryMap(lines);
	}

	private Map<String, InventoryCount> createInventoryMap(List<String> lines) {
		Map<String, InventoryCount> inventoryMap = new LinkedHashMap<String, InventoryCount>();

		for (String line : lines) {

			String[] pieces = line.split("\\|");

			InventoryCount inventoryCount = createCountsFromSplit(pieces);

			inventoryMap.put(pieces[0], inventoryCount);
		}

		return inventoryMap;
	}

	private InventoryCount createCountsFromSplit(String[] pieces) {

		InventoryCount newCount = null;
		String name = pieces[1];
		double price = Double.parseDouble(pieces[2]);
		String code = pieces[3];

		Item item = null;

		if (code.equalsIgnoreCase("A")) {
			item = new Appetizer(name, price, code);
		}

		if (code.equalsIgnoreCase("B")) {
			item = new Beverage(name, price, code);
		}

		if (code.equalsIgnoreCase("D")) {
			item = new Dessert(name, price, code);
		}

		if (code.equalsIgnoreCase("E")) {
			item = new Entree(name, price, code);
		}

		newCount = new InventoryCount(item);

		return newCount;
	}

	private List<String> readAllLines() throws FileNotFoundException {

		List<String> lines = new ArrayList<String>();

		File inventoryFile = new File(filename);

		try (Scanner fileScanner = new Scanner(inventoryFile)) {

			while (fileScanner.hasNextLine()) {
				String line = fileScanner.nextLine();
				lines.add(line);
			}
		}
		return lines;
	}

}
