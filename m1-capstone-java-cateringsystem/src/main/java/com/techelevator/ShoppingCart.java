package com.techelevator;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.techelevator.inventory.InventoryCount;
import com.techelevator.inventory.Item;

public class ShoppingCart {

	private Item item;
	private Map<Item, Double> updatedCart = new LinkedHashMap<Item, Double>();
	private double quantity = 0;
	private double cartBalance = 0;
	
	

	
	public Map<Item, Double> updateCart(Item item, Double quantity){
		
		updatedCart.put(item, quantity);
		
		
		return this.updatedCart;
		
	}
	



	public Map<Item, Double> getUpdatedCart() {
	
		
		return this.updatedCart;
	}
	
	
	public double getCartBalance(Map<Item, Double> receipt) {
		
		double priceSum = 0.0;
		for(Entry<Item, Double> entry : receipt.entrySet()) {
			
			priceSum += entry.getKey().getPrice() * entry.getValue();
		}
				
				
		
		return priceSum;

	}
	

	
	



}
