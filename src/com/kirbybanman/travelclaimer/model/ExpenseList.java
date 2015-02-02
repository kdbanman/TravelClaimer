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

import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ExpenseList implements Iterable<Expense> {
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
	
	public int size() {
		return expenses.size();
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
	
	public List<Expense> getList() {
		return expenses;
	}
	
	public Expense get(int index) {
		return expenses.get(index);
	}
	
	public boolean contains(Expense expense) {
		return expenses.contains(expense);
	}

	@Override
	public Iterator<Expense> iterator() {
		return expenses.iterator();
	}
}
