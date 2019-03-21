package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class VendingMachine {

	protected Money moneyHandler = new Money();

	File itemFile = new File("vendingmachine.csv");

	Log auditLog = new Log();

	Map<String, Item> vendingItems = new LinkedHashMap<String, Item>(); 

	public VendingMachine() {

		retrieveItems();

	}

	private void retrieveItems() {

		try (Scanner fileScanner = new Scanner(itemFile)) {

			while (fileScanner.hasNextLine()) {

				String line = fileScanner.nextLine();

				String[] tokens = line.split("\\|");

				String key = tokens[0];
				String priceStr = tokens[2];
				Double priceDbl = Double.parseDouble(priceStr);

				int quantity = 5;
				BigDecimal price = BigDecimal.valueOf(priceDbl);
				price = price.setScale(2, BigDecimal.ROUND_HALF_UP);

				Item newVendingItem = new Item(tokens[0], tokens[1], price, quantity);
				vendingItems.put(key, newVendingItem);

			} 
		}
		catch (FileNotFoundException e) {
			System.out.println("File not found.");
		}

	}


	public void displayItems() {
		for (Map.Entry<String, Item>entry : vendingItems.entrySet()) {
			System.out.print(entry.getValue());
		}
	}


	public boolean hasSlot(String selectedSlot) {
		return vendingItems.containsKey(selectedSlot);
	}

	public boolean hasItemsInSlot(String selectedSlot) {
		return vendingItems.get(selectedSlot).getQuantity() > 0;
	}

	public boolean purchase(String selectedSlot) {

		boolean result = false;

		Item purchasedItem = vendingItems.get(selectedSlot);
		BigDecimal purchasePrice = purchasedItem.getPrice();
		if (moneyHandler.currentBalance.compareTo(purchasePrice) >= 0) {
			moneyHandler.withdraw(purchasePrice);
			purchasedItem.setQuanity(purchasedItem.getQuantity()-1);
			auditLog.purchaseLog(purchasedItem.getName(), selectedSlot, moneyHandler.currentBalance.add(purchasePrice), moneyHandler.currentBalance);
			auditLog.addPurchases(purchasedItem.getName());
			result = true;
		}

		return result;
	}

	public BigDecimal returnBalance() {
		BigDecimal balanceDue = moneyHandler.currentBalance;
		moneyHandler.makeChange(balanceDue);
		auditLog.makeChangeLog(balanceDue, moneyHandler.currentBalance);
		return balanceDue;
	}


	public void isDone() {

		for (Map.Entry<String, Item>entry : vendingItems.entrySet()) {
			if (entry.getValue().getQuantity() != 5) {
				System.out.println(entry.getValue().getSound());
			}
		}
	}



	public void feedMoney(BigDecimal amount) {
		moneyHandler.feedMoney(amount);
		auditLog.feedMoneyLog(amount, moneyHandler.currentBalance);
	}

	public BigDecimal totalMoney() {
		return moneyHandler.currentBalance;
	}

}


