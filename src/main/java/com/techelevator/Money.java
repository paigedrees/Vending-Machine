package com.techelevator;

import java.math.BigDecimal;

public class Money {

	public BigDecimal currentBalance = new BigDecimal("0");
	private BigDecimal quarters = new BigDecimal("0");
	private BigDecimal dimes = new BigDecimal("0");
	private BigDecimal nickels =  new BigDecimal("0");
	private BigDecimal pennies =  new BigDecimal("0");
	private BigDecimal totalSales =  new BigDecimal("0");

	public String getQuarters() {
		return quarters.toPlainString();
	}
	public String getDimes() {
		return dimes.toPlainString();
	}
	public String getNickels() {
		return nickels.toPlainString();
	}
	public String getPennies() {
		return pennies.toPlainString();
	}

	public BigDecimal getTotalSales() {
		return totalSales;
	}

	public void feedMoney(BigDecimal money) {
		currentBalance = currentBalance.add(money);
	}

	public void withdraw(BigDecimal money) {
		currentBalance = currentBalance.subtract(money);
		totalSales = totalSales.add(money);
	}

	public void makeChange(BigDecimal balanceDue) {
		BigDecimal totalDue = balanceDue;
		BigDecimal remainder = totalDue;

		if ((totalDue.remainder(new BigDecimal("0.25")).compareTo(BigDecimal.ZERO) == 0)) {

			quarters = totalDue.divide(new BigDecimal("0.25"));
			remainder = BigDecimal.ZERO;
		}	
		else if ((totalDue.remainder(new BigDecimal("0.25")).compareTo(BigDecimal.ZERO) != 0)) {

			remainder = (totalDue.remainder(new BigDecimal("0.25")));
			totalDue = totalDue.subtract(remainder);
			quarters = totalDue.divide(new BigDecimal("0.25"));

		}
		totalDue = remainder;


		if ((totalDue.remainder(new BigDecimal("0.10")).compareTo(BigDecimal.ZERO) == 0)) {

			dimes = totalDue.divide(new BigDecimal("0.10"));
			remainder = BigDecimal.ZERO;

		} else if ((totalDue.remainder(new BigDecimal("0.10")).compareTo(BigDecimal.ZERO) != 0)) {

			remainder = (totalDue.remainder(new BigDecimal("0.10")));
			totalDue = totalDue.subtract(remainder);
			dimes = totalDue.divide(new BigDecimal("0.10"));

		}

		totalDue = remainder;

		if ((totalDue.remainder(new BigDecimal("0.05")).compareTo(BigDecimal.ZERO) == 0)) {

			nickels = totalDue.divide(new BigDecimal("0.05"));
			remainder = BigDecimal.ZERO;

		} else if ((totalDue.remainder(new BigDecimal("0.05")).compareTo(BigDecimal.ZERO) != 0) && remainder.compareTo(new BigDecimal("0.05")) > 0) {

			remainder = (totalDue.remainder(new BigDecimal("0.05")));
			totalDue = totalDue.subtract(remainder);
			nickels = totalDue.divide(new BigDecimal("0.05"));

		}

		pennies = remainder.divide(new BigDecimal("0.01"));
		currentBalance = BigDecimal.ZERO;
	}

}
