package com.techelevator;

public class AccountingSystem {
	
	public double balance = 0;
	
	public double deposit(double amountToDeposit) {
	
		
		balance = balance + (amountToDeposit);
		return balance;
	}

	public double getBalance() {
		
		return balance;
	}


}
