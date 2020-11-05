package com.techelevator;

import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Map.Entry;

import com.techelevator.view.Menu;
import com.techelevator.AccountingSystem;
import com.techelevator.inventory.InventoryCount;
import com.techelevator.inventory.Item;

public class CateringSystemCLI {

	private static final String ORDER_MENU_ADD_MONEY = "Add Money";
	private static final String ORDER_MENU_SELECT_PRODUCT = "Select Products";
	private static final String ORDER_MENU_COMPLETE_TRANSACTION = "Complete Transaction";
	private static final String[] ORDER_MENU_CHOICES = { ORDER_MENU_ADD_MONEY, ORDER_MENU_SELECT_PRODUCT,
			ORDER_MENU_COMPLETE_TRANSACTION };

	private static final String MAIN_MENU_CHOICE_DISPLAY_ITEMS = "Display Catering Items";
	private static final String MAIN_MENU_CHOICE_ORDER = "Order";
	private static final String EXIT_MAIN_MENU = "Quit";
	private static final String[] MAIN_MENU_CHOICES = { MAIN_MENU_CHOICE_DISPLAY_ITEMS, MAIN_MENU_CHOICE_ORDER,
			EXIT_MAIN_MENU };

	private Menu menu;
	private CateringSystem cateringSystem;
	AccountingSystem wallet = new AccountingSystem();
	ShoppingCart cart = new ShoppingCart();
	double checkOut = wallet.getBalance() - cart.getCartBalance(cart.getUpdatedCart());
	

	public CateringSystemCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() {

		String inventoryFile = menu.getInventoryFileFromUser();
		try {
			cateringSystem = new CateringSystem(inventoryFile);
		} catch (FileNotFoundException e) {
			menu.showErrorMessage(inventoryFile + " not found!");
			return;
		}

		while (true) {
			String choice = (String) menu.getChoiceFromChoices(MAIN_MENU_CHOICES);

			if (choice.equals(MAIN_MENU_CHOICE_DISPLAY_ITEMS)) {
				menu.displayInventory(cateringSystem.getInventory());
			} else if (choice.equals(MAIN_MENU_CHOICE_ORDER)) {
				showPurchaseMenu();
			} else if (choice.equals(EXIT_MAIN_MENU)) {
				// TODO add exit method for main menu
			}

		}
	}

	private void showPurchaseMenu() {
		while (true) {
			String choice = (String) menu.getChoiceFromChoices(ORDER_MENU_CHOICES);

			if (choice.equals(ORDER_MENU_ADD_MONEY)) {
				showAddMoney();
			} else if (choice.equals(ORDER_MENU_SELECT_PRODUCT)) {
				showSelectProducts();
			} else if (choice.equals(ORDER_MENU_COMPLETE_TRANSACTION)) {
				showCheckOut();

				break;
			}
		}
	}

	private void showAddMoney() {
		String[] testChoices = { "Add Money", "Exit" };
		double amounts = 0.0;

		while (true) {
			String choice = (String) menu.getChoiceFromChoices(testChoices);

			if (choice.equals("Add Money")) {

				double inputField = menu.getDepositEntry(amounts);

			} else if (choice.equals("Exit")) {
				break;
			}
		}
	}

	private void showSelectProducts() {

		menu.displayInventory(cateringSystem.getInventory());
		String code = menu.getProductSelection();
		Item item;

		Map<String, InventoryCount> stock = cateringSystem.getInventory();
		for (Entry<String, InventoryCount> entry : stock.entrySet()) {

			if (code.equalsIgnoreCase(entry.getKey())) {
				double quantity = menu.askQuantity();
				item = entry.getValue().getItem();

				cart.updateCart(item, quantity);
				double updatedInventory = entry.getValue().getCount() - quantity;
				System.out.println("Thank you! your order has been added to your cart!");
				break;
			}
		}
	}

	public void showCheckOut() {
		menu.getReceipt(cart.getUpdatedCart());
		String[] testChoices = { "Check Out", "Exit" };

		while (true) {
			String choice = (String) menu.getChoiceFromChoices(testChoices);

			if (choice.equals("Check Out")) {
				System.out.println("Thank you, you new balance is: " + checkOut);

			} else if (choice.equals("Exit")) {
				break;
			}
		}
	}

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		CateringSystemCLI cli = new CateringSystemCLI(menu);
		cli.run();
	}

}
