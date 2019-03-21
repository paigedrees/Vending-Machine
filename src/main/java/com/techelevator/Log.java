package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Log {

	public static List<String> itemsPurchased = new ArrayList<String>();
	public static List<String> items = new ArrayList<String>();

	File itemFile = new File("vendingmachine.csv");

	File log = new File("Log.txt");

	File salesReport = new File("SalesReport.txt");

	Date date = new Date();

	String dateString = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss a").format(date);


	public void addPurchases(String items) {

		itemsPurchased.add(items);
	}

	public void feedMoneyLog(BigDecimal money, BigDecimal currentBalance) {

		try {
			if (log.exists() == false) {
				log.createNewFile();
			}

			PrintWriter logWriter = new PrintWriter(new FileWriter(log, true));
			String feedMoney = "FEED MONEY:";
			logWriter.append(String.format("%-20s %-20s $%-10s $%-10s\n", dateString, feedMoney, money.toString(), currentBalance.toString()));
			logWriter.close();

		}

		catch (FileNotFoundException e) {
			System.out.println("File not found.");
		} catch (IOException e) {
			System.out.println("Log could not record money fed.");
		}

	}

	public void purchaseLog(String itemName, String id, BigDecimal currentBalance, BigDecimal updatedBalance) {

		try {
			if (log.exists() == false) {
				log.createNewFile();
			}

			PrintWriter logWriter = new PrintWriter(new FileWriter(log, true));
			String location = itemName + " " + id;
			logWriter.append(String.format("%-20s %-20s $%-10s $%-10s\n", dateString, location, currentBalance.toString(),updatedBalance.toString()));
			logWriter.close();

		}

		catch (FileNotFoundException e) {
			System.out.println("File not found.");
		} catch (IOException e) {
			System.out.println("Log could not record purchases.");
		}

	}

	public void makeChangeLog(BigDecimal currentBalance, BigDecimal money) {

		try {
			if (log.exists() == false) {
				log.createNewFile();
			}

			PrintWriter logWriter = new PrintWriter(new FileWriter(log, true));

			String giveChange = "GIVE CHANGE:";
			logWriter.append(String.format("%-20s %-20s $%-10s $%-10s\n", dateString, giveChange, currentBalance, money));
			logWriter.close();

		}

		catch (FileNotFoundException e) {
			System.out.println("File not found.");
		} catch (IOException e) {
			System.out.println("Log could not record change due.");
		}

	}
	
	/*private void writeLog(String line) {
		
		try {
			if (log.exists() == false) {
				log.createNewFile();
			}

			PrintWriter logWriter = new PrintWriter(new FileWriter(log, true));

			logWriter.append(line);
			logWriter.close();

		}

		catch (FileNotFoundException e) {
			System.out.println("File not found!");
		} catch (IOException e) {
			System.out.println("Log could not record!");
		}
		
		
		
	}*/

	public void makeSalesReport(BigDecimal TotalSales) {

		try {

			PrintWriter salesWriter = new PrintWriter(new FileWriter(salesReport, true));


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
					items.add(newVendingItem.getName());


				} 
			}
			catch (FileNotFoundException e) {
				System.out.println("File not found.");
			}


			for (String item : items) {

				int qtySold = 0;

				for(String itemPurchased : itemsPurchased) {
					if (item.equals(itemPurchased)) {

						qtySold++;

					}

				}

				salesWriter.println(item + "|" + qtySold);

			}

			salesWriter.print("\n" + "TOTAL SALES: $" + TotalSales + "\n");

			salesWriter.close();

		}

		catch (FileNotFoundException e) {
			System.out.println("File not found.");
		} catch (IOException e) {
			System.out.println("Sales report could not be created.");
		}


	}

}
