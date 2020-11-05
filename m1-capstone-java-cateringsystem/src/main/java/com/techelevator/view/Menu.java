package com.techelevator.view;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import com.techelevator.AccountingSystem;
import com.techelevator.CateringSystem;
import com.techelevator.ShoppingCart;
import com.techelevator.inventory.InventoryCount;
import com.techelevator.inventory.Item;

public class Menu {

	private PrintWriter out;
	private Scanner in;
	public CateringSystem cateringSystem;
	private DecimalFormat df = new DecimalFormat("#.##");
	

	AccountingSystem wallet = new AccountingSystem();
	ShoppingCart cart = new ShoppingCart();
	double checkOut = wallet.getBalance() - cart.getCartBalance(cart.getUpdatedCart());

	public Menu(InputStream input, OutputStream output) {
		this.out = new PrintWriter(output);
		this.in = new Scanner(input);
	}

	public String getInventoryFileFromUser() {
		// TODO remove hard code and create input field
		return "cateringsystem.csv";
	}

	public void showErrorMessage(String message) {
		System.out.println("****** " + message + " ******");
		System.out.flush();
	}

	public void displayInventory(Map<String, InventoryCount> inventory) {
		for (Entry<String, InventoryCount> entry : inventory.entrySet()) {

			String quantity = entry.getValue().getCount() > 0 ? String.valueOf(entry.getValue().getCount())
					: "Sold Out";

			
			System.out.printf("%-5s  %-20s  %4.2f  %-10s %n", entry.getKey(), entry.getValue().getItem().getName(),
					entry.getValue().getItem().getPrice(), quantity);

		}
	}
	

	public Object getChoiceFromChoices(Object[] choices) {

		Object choice = null;
		while (choice == null) {
			displayMenuChoices(choices);
			choice = getChoiceFromUserInput(choices);
		}
		return choice;
	}

	private Object getChoiceFromUserInput(Object[] choices) {
		Object choice = null;
		String userInput = in.nextLine();
		try {
			int selectedOption = Integer.valueOf(userInput);
			if (selectedOption > 0 && selectedOption <= choices.length) {
				choice = choices[selectedOption - 1];
			}
		} catch (NumberFormatException e) {

		}
		if (choice == null) {
			System.out.println("\n***" + userInput + " is not a valid option, please try again. ***\n");
		}
		return choice;
	}

	private void displayMenuChoices(Object[] choices) {
		System.out.println();
		for (int i = 0; i < choices.length; i++) {
			int choiceNum = i + 1;
			System.out.println(choiceNum + ") " + choices[i]);
		}
		System.out.println("\nCurrent Account Balance: " + wallet.getBalance());
		System.out.println("\nPlease choose an option --->");
		System.out.flush();

	}

	public double getDepositEntry(double amount) {
		System.out.println("Please enter desired whole dollar amount to deposit:");
		double amounts = 0.0;
		amounts = in.nextDouble();
		in.nextLine();
		double userInput = Double.valueOf(amounts);

		if (userInput < 0 || amounts + wallet.getBalance() > 5000) {
			System.out.println("\n**** " + userInput
					+ " is not a valid amount, wallet cannot exceed 5000 or be less than 0. ****\n");
		}

		else if (userInput > 0 && userInput <= 5000 && (amounts + wallet.getBalance() <= 5000)) {
			wallet.deposit(amounts);
			System.out.println("Thank you! Your wallet now contains: " + wallet.getBalance());

		}

		return amounts;
	}

	public String getProductSelection() {
		System.out.println("Please type the code of the item you wish you purchase:");
		String codes;
		codes = in.nextLine();
		String userInput = codes;
	

		return codes;
	}
	
	public double askQuantity() {
		System.out.println("Please input the quantity you would like you order, up to 50:");
		double quantity = 0.0;
		quantity = in.nextDouble();
		in.nextLine();
		
		return quantity;
		
	}
	
	public void getReceipt(Map<Item, Double> receipt) {
		
		double priceSum = 0.0;
		
		
		for (Entry<Item, Double> entry: receipt.entrySet()) {
			
			
			System.out.printf("%-20s %-20s %-20s %n", entry.getKey().getName(), entry.getKey().getPrice(), entry.getValue());
			
			priceSum += entry.getKey().getPrice() * entry.getValue();
			
		}
		System.out.println("Total Cart Balance: " + df.format(priceSum));
	}
	
	

}
