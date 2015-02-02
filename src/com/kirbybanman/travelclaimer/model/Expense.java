package com.kirbybanman.travelclaimer.model;

import java.util.Currency;
import java.util.Date;

import android.util.Log;

public class Expense {
	
	public enum Category {
		ACCOMODATION("Accomodation"),
		AIR_FARE("Air Fare"),
		FUEL("Fuel"),
		GROUND_TRANSPORT("Ground Transport"),
		MEAL("Meal"),
		MISC("Miscellaneous"),
		PARKING("Parking"),
		REGISTRATION("Registration"),
		VEHICLE_RENTAL("Vehicle Rental");
		
		private String asString;
		
		Category(String asString) {
			this.asString = asString;
		}
		
		public String toString() {
			return asString;
		}
	}

	private float amount;
	private Currency currency;
	private String description;
	private Date date;
	private Category category;
	
	public Expense() {
		this(0.0f, "CAD", "Expense Description", new Date(), Category.MISC);
	}
	
	public Expense(float amount, Currency currency, String description, Date date, Category category) {
		this.amount = amount;
		this.currency = currency;
		this.description = description;
		this.date = date;
		this.category = category;
	}
	
	public Expense(float amount, String currencyCode, String description, Date date, Category category) {
		this(amount, Currency.getInstance("CAD"), description, date, category);
		
		try {
			this.currency = Currency.getInstance(currencyCode);
		} catch (IllegalArgumentException e) {
			Log.e("expense currency", "illegal ISO currency code passed to Expense constructor.  CAD default used.");
		}
		
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
	
	public void setCurrency(String currencyCode) {
		try {
			this.currency = Currency.getInstance(currencyCode);
		} catch (IllegalArgumentException e) {
			Log.e("expense currency", "illegal ISO currency code passed to Expense constructor");
		}
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
}
