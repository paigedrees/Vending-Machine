package com.techelevator;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

public class LogTest {

	private Log logTest;


	@Before
	public void setup() {
		logTest = new Log();
	}


	@Test
	public void add_purchases_test() {

		String fakePurchase = "Snack";
		logTest.addPurchases(fakePurchase);
	}

	@Test
	public void feed_money_log_test() {

		BigDecimal fakeMoney = BigDecimal.ONE;
		BigDecimal fakeCurrentBalance = BigDecimal.TEN;
		logTest.feedMoneyLog(fakeMoney, fakeCurrentBalance);
	}

	@Test
	public void purchase_log_test() {
		String fakeName = "Cowtales";
		String fakeID = "B2";

		BigDecimal fakeCurrentBalance = BigDecimal.ONE;
		BigDecimal fakeUpdatedBalance = BigDecimal.TEN;
		logTest.purchaseLog(fakeName, fakeID, fakeCurrentBalance, fakeUpdatedBalance);
	}

	@Test
	public void make_change_log_test() {

		BigDecimal fakeCurrentBalance = BigDecimal.ONE;
		BigDecimal fakeMoney = BigDecimal.TEN;
		logTest.makeChangeLog(fakeCurrentBalance, fakeMoney);
	}

	@Test
	public void make_sales_report_log_test() {
		BigDecimal fakeTotalSales = BigDecimal.TEN;
		logTest.makeSalesReport(fakeTotalSales);
	}


}
