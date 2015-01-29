package com.kirbybanman.travelclaimer.model;

import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;

public class ExpenseList {
	ArrayList<Expense> expenses; 
	
	public ExpenseList() {
		expenses = new ArrayList<Expense>();
	}
	
	public void addExpense(Expense expense) {
		expenses.add(expense);
	}
	
	public boolean removeExpense(Expense expense) {
		return expenses.remove(expense);
	}
	
	public Map<Currency, Float> getTotals() {
		HashMap<Currency, Float> totals = new HashMap<Currency, Float>();
		
		// Iterate through all expenses, adding currencies and increasing totals
		for (Expense expense : expenses) {
			if (!totals.containsKey(expense.getCurrency())) {
				totals.put(expense.getCurrency(), 0.0f);
			}
			
			totals.put(expense.getCurrency(), totals.get(expense.getCurrency()) + expense.getAmount());
		}
		
		return totals;
	}
}
