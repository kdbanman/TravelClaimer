package com.kirbybanman.travelclaimer.view;

import java.text.DateFormat;
import java.util.Locale;

import android.util.Log;

import com.kirbybanman.travelclaimer.model.Expense;

public class ExpenseStringView {
	private Expense expense;
	private DateFormat dateFormatter;
	
	public ExpenseStringView(Expense expense) {
		this.expense = expense;

		dateFormatter = DateFormat.getDateInstance(DateFormat.LONG, Locale.CANADA);
	}
	
	public String getFullDescription() {
		return  String.format(Locale.getDefault(), "%1$25s", getDate()) + getCategory() + "\n" +
				getAmount() + "\n" +
				getDescription();
	}

	private String getDescription() {
		return expense.getDescription();
	}

	private String getAmount() {
		String code = expense.getCurrency().getCurrencyCode();
		String amount = String.format(Locale.getDefault(), "%.2f", expense.getAmount());
		return  "" + code + " "+ amount;
	}

	private String getCategory() {
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

	private Object getDate() {
		return dateFormatter.format(expense.getDate());
	}
}
