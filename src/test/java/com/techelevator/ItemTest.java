package com.techelevator;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ItemTest {

	private Item itemTest;


	@Before
	public void setup() {
		itemTest = new Item("Cowtales", "B2", new BigDecimal("1.00"), 5);
	}

	@Test
	public void get_sound__a_test() {
		itemTest.setId("A2");
		Assert.assertEquals(itemTest.getSound(),("Crunch Crunch, Yum!"));
	}

	@Test
	public void get_sound__b_test() {
		itemTest.setId("B2");
		Assert.assertEquals(itemTest.getSound(),("Munch Munch, Yum!"));
	}

	@Test
	public void get_sound__c_test() {
		itemTest.setId("C2");
		Assert.assertEquals(itemTest.getSound(),("Glug Glug, Yum!"));
	}

	@Test
	public void get_sound__d_test() {
		itemTest.setId("D2");
		Assert.assertEquals(itemTest.getSound(),("Chew Chew, Yum!"));
	}

	@Test
	public void set_sound_test() {
		String sound = "Munch Munch, Yum!";
		itemTest.setSound(sound);
	}

	@Test
	public void to_string_test() {
		itemTest.toString();
	}

	@Test
	public void get_id_test() {
		itemTest.getId();
	}

	@Test
	public void set_id_test() {
		String id = "B2";
		itemTest.setId(id);
	}

	@Test
	public void get_name_test() {
		itemTest.getName();
	}

	@Test
	public void set_name_test() {
		String name = "Cowtales";
		itemTest.setName(name);
	}

	@Test
	public void get_price_test() {
		itemTest.getPrice();
	}


	@Test	
	public void set_price_test() {
		BigDecimal price = new BigDecimal("1.00");
		itemTest.setPrice(price);
	}

	@Test
	public void get_quantity_test() {
		itemTest.getQuantity();
	}

	@Test
	public void set_quantity_test() {
		int quantity = 5;
		itemTest.setQuanity(quantity);
	}

}
