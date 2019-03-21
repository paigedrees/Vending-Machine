package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.techelevator.view.Menu;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS,
			MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT };

	public static final String FEED = "Feed Money";
	public static final String SELECT = "Select Product";
	public static final String FINISH = "Finish transaction";
	public static final String[] CHOICES = {FEED, SELECT, FINISH}; 

	public static final BigDecimal ONE = new BigDecimal("1.00");
	public static final BigDecimal TWO = new BigDecimal("2.00");
	public static final BigDecimal FIVE = new BigDecimal("5.00");
	public static final BigDecimal TEN = new BigDecimal("10.00");
	public static final BigDecimal[] MONEY_CHOICES = {ONE, TWO, FIVE, TEN};

	private Menu menu;

	private VendingMachine vm;

	Scanner userInput = new Scanner(System.in);

	public VendingMachineCLI(Menu menu, VendingMachine vm) {
		this.menu = menu;
		this.vm = vm;
	}

	public void displayVendingItems() {

		vm.displayItems();
		
	} 


	public void run() {

		boolean readyToExit = false;
		while(readyToExit == false) {

			String choice = (String)menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if(choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				// display vending machine items - List
				displayVendingItems();

			} else if(choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				displayPurchaseMenu();


			} else if(choice.equals(MAIN_MENU_OPTION_EXIT)) {
				System.out.println("Thank you, please come again.");
				vm.auditLog.makeSalesReport(vm.moneyHandler.getTotalSales());
				readyToExit = true;
			}
		}

	}


	public void displayPurchaseMenu() {
		boolean readyToExit = false;
		while(readyToExit == false) {
			String purchaseChoice = (String)menu.getChoiceFromOptions(CHOICES);
			System.out.println("Current balance: " + vm.totalMoney());

			if(purchaseChoice.equals(FEED)) {

				BigDecimal moneyChoice = (BigDecimal)menu.getChoiceFromOptions(MONEY_CHOICES);


				vm.feedMoney(moneyChoice);


			} else if (purchaseChoice.equals(SELECT)) {

				displayVendingItems();
				
				System.out.println("\nB) Back to menu");
				
				System.out.println("\nCurrent balance: " + vm.totalMoney());

				System.out.println("Please pick an item: ");

				String itemPicked = userInput.nextLine();
				
				if (itemPicked.equalsIgnoreCase("B")) {
					
					readyToExit = true;
					
				} else if (vm.hasSlot(itemPicked)) {

					if (vm.hasItemsInSlot(itemPicked)) {
						
					} else { 

						System.out.println("Item Is Out Of Stock!");
					}

					if (vm.purchase(itemPicked)) {
						
					} else { 

						System.out.println("Please Insert More Money!");

					}

				} else { 
					System.out.println("Item ID Is Invalid!");


				}


			}			

			else if ((purchaseChoice.equals(FINISH))) {
				
				System.out.println("Your Change Today is $" + vm.returnBalance() + " which is " + vm.moneyHandler.getQuarters() + " quarters " + vm.moneyHandler.getDimes() + " dimes " + vm.moneyHandler.getNickels() + " nickels ");

				vm.isDone();
				readyToExit = true;

			}
		}
	}



	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachine vm = new VendingMachine();
		VendingMachineCLI cli = new VendingMachineCLI(menu, vm);
		System.out.println("****Welcome to the Vendo-Matic 500!!****");
		cli.run();

	}
}
