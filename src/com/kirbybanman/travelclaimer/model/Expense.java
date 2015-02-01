package com.kirbybanman.travelclaimer.model;

import java.util.Currency;
import java.util.Date;

import android.util.Log;

public class Expense {
	private String title;
	private float amount;
	private Currency currency;
	private String description;
	private Date date;
	
	public Expense() {
		this("New Expense", 0.0f, "CAD", "Expense Description", new Date());
	}
	
	public Expense(String title, float amount, Currency currency, String description, Date date) {
		this.title = title;
		this.amount = amount;
		this.currency = currency;
		this.description = description;
		this.date = date;
	}
	
	public Expense(String title, float amount, String currencyCode, String description, Date date) {
		this.currency = Currency.getInstance("CAD");
		try {
			this.currency = Currency.getInstance(currencyCode);
		} catch (IllegalArgumentException e) {
			Log.e("expense currency", "illegal ISO currency code passed to Expense constructor");
		}
		
		this.title = title;
		this.amount = amount;
		this.description = description;
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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
}
