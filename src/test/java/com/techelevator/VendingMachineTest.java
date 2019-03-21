package com.techelevator;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;



public class VendingMachineTest {

	private VendingMachine vendingTest;


	@Before
	public void setup() {
		vendingTest = new VendingMachine();
	}


	@Test
	public void displays_all_vending_items() {
		vendingTest.displayItems();

	}

	@Test
	public void has_slot_id_test() {
		String slot = "A2";
		vendingTest.hasSlot(slot);

	}

	@Test
	public void has_items_in_slot_test() {
		String slot = "A2";
		vendingTest.hasItemsInSlot(slot);
	}

	@Test
	public void purchase_test() {
		String slot = "A2";
		vendingTest.purchase(slot);
	}


	@Test
	public void vending_machine_is_done_test() {
		vendingTest.isDone();
	}

	@Test
	public void feed_money_test() {
		BigDecimal money = new BigDecimal("5.00");
		vendingTest.feedMoney(money);
	}

	@Test
	public void calculate_total_money_test() {
		vendingTest.totalMoney();
	}

}
