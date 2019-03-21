package com.techelevator;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MoneyTest {


	private Money moneyTest;


	@Before
	public void setup() {
		moneyTest = new Money();
	}

	@Test
	public void get_quarters_test() {
		moneyTest.getQuarters();
	}

	@Test
	public void get_dimes_test() {
		moneyTest.getDimes();
	}

	@Test
	public void get_nickels_test() {
		moneyTest.getNickels();
	}

	@Test
	public void get_pennies_test() {
		moneyTest.getPennies();
	}

	@Test
	public void get_total_sales_test() {
		moneyTest.getTotalSales();
	}

	@Test
	public void feed_money_test() {
		BigDecimal money = BigDecimal.ONE;
		moneyTest.feedMoney(money);
	}

	@Test
	public void transaction_test() {
		BigDecimal money = BigDecimal.ONE;
		moneyTest.withdraw(money);
	}

	@Test
	public void make_change_test_for_quarters() {
		BigDecimal balanceDue = BigDecimal.ONE;
		moneyTest.makeChange(balanceDue);
	}

	@Test
	public void make_change_test_quarters_and_nickels() {
		BigDecimal balanceDue = new BigDecimal("1.05");
		moneyTest.makeChange(balanceDue);
	}


	@Test
	public void make_change_for_nickels_test() {
		BigDecimal balanceDue = new BigDecimal(".09");
		moneyTest.makeChange(balanceDue);
	}


	@Test
	public void make_change_for_dimes_test() {
		BigDecimal balanceDue = new BigDecimal(".10");
		moneyTest.makeChange(balanceDue);
	}

}
