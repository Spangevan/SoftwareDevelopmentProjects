package com.techelevator.inventory;

public class InventoryCount {
	
	public int count = 50;
	private Item item;
	
	public InventoryCount(Item item) {
		this.item = item;
	}
	
	public int getCount() {
		return count;
	}
	
	public int updatedInventory() {
		
		return count;
		
	}

	
	public Item getItem() {
		return item;
	}

}
