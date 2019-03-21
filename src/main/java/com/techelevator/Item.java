package com.techelevator;

import java.math.BigDecimal;

public class Item {

	private String id;
	private String name;
	private BigDecimal price;
	private int quantity = 5;
	public String Sound;

	public Item(String id, String name, BigDecimal price, int quantity) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.quantity = quantity;

	}

	public String getSound() {
		String result = "";

		if (id.contains("A")) {
			result = "Crunch Crunch, Yum!";
		} else if (id.contains("B")) {
			result = "Munch Munch, Yum!";
		} else if (id.contains("C")) {
			result = "Glug Glug, Yum!";
		} else {
			result = "Chew Chew, Yum!";
		}

		return result;
	}

	public void setSound(String sound) {
		Sound = sound;
	}

	public String toString() {
		return ("\n" + getId() + " " + getName() + " $" + getPrice() + "  QTY: " + getQuantity() + "\n");
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuanity(int quantity) {

		this.quantity = quantity;
	}


}
