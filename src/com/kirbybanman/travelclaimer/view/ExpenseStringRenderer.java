package com.kirbybanman.travelclaimer.view;

import java.text.DateFormat;
import java.util.Locale;

import android.util.Log;

import com.kirbybanman.travelclaimer.model.Expense;

public class ExpenseStringRenderer {
	private Expense expense;
	private DateFormat dateFormatter;
	
	public ExpenseStringRenderer(Expense expense) {
		this.expense = expense;

		dateFormatter = DateFormat.getDateInstance(DateFormat.LONG, Locale.CANADA);
	}
	
	public String getFullDescription() {
		return  "Description: " + getDescription() + "\n" +
				"  " + String.format(Locale.getDefault(), "%-25s", getDate()) + getCategory() + "\n" +
				"  " + getAmount();
	}

	public String getDescription() {
		return expense.getDescription();
	}

	public String getAmount() {
		String code = expense.getCurrency().getCurrencyCode();
		String amount = String.format(Locale.getDefault(), "%.2f", expense.getAmount());
		return  "" + code + " "+ amount;
	}

	public String getCategory() {
		switch (expense.getCategory()) {
			case ACCOMODATION: return "Accomodation";
			case AIR_FARE: return "Air Fare";
			case FUEL: return "Fuel";
			case GROUND_TRANSPORT: return "Ground Transport";
			case MEAL: return "Meal";
			case MISC: return "Miscellaneous";
			case PARKING: return "Parking";
			case REGISTRATION: return "Registration";
			case VEHICLE_RENTAL: return "Vehicle Rental";
		}
		
		Log.e("expense stringer", "unknown category passed to Expense string viewer");
		return "ERR: UNKNOWN";
	}

	public String getDate() {
		return dateFormatter.format(expense.getDate());
	}
}
