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
	
	public String getNumericalAmount() {
		return String.format(Locale.getDefault(), "%.2f", expense.getAmount());
	}

	public String getAmount() {
		String code = expense.getCurrency().getCurrencyCode();
		String amount = getNumericalAmount();
		return  "" + code + " "+ amount;
	}

	public String getCategory() {
		return expense.getCategory().toString();
	}

	public String getDate() {
		return dateFormatter.format(expense.getDate());
	}
}
