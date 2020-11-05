package com.techelevator;

import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.techelevator.inventory.InventoryCount;
import com.techelevator.reader.FileInventoryReader;
import com.techelevator.reader.InventoryReader;

public class CateringSystem {
	
	private Map<String, InventoryCount> inventory = new LinkedHashMap<String, InventoryCount>();
	
	
	public CateringSystem(String filename) throws FileNotFoundException {
		InventoryReader reader = new FileInventoryReader(filename);
		this.inventory = reader.read();
		
	}
	
	public Map<String, InventoryCount> getInventory() {
		return this.inventory;
	}
	
	
	

}
