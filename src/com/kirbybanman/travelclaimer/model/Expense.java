package com.kirbybanman.travelclaimer.model;

/*
 *    Copyright 2015 Kirby Banman
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

import java.util.Currency;
import java.util.Date;

import com.kirbybanman.travelclaimer.interfaces.Stringable;

import android.util.Log;

/**
 * Similar to Claim, an Expense knows only its data and accessors, as well as a nested
 * enumeration (of expense categories).
 * 
 * An expense is a single monetary expense belonging to a Claim.  It is an amount of money
 * associated with data like description, date, category, etc.
 * 
 * @author kdbanman
 *
 */
public class Expense {
	
	public enum Category implements Stringable<Category> {
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
		
		@Override
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
		this(0.0f, "CAD", "", new Date(), Category.MISC);
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
