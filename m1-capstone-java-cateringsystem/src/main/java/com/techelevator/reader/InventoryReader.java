package com.techelevator.reader;

import java.io.FileNotFoundException;
import java.util.Map;

import com.techelevator.inventory.InventoryCount;

public interface InventoryReader {
	
	Map<String, InventoryCount> read() throws FileNotFoundException;

}
