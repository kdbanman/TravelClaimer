package com.kirbybanman.travelclaimer.view;

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

import java.text.DateFormat;
import java.util.Locale;

import com.kirbybanman.travelclaimer.model.Expense;


/**
 * Another String renderer for a Model, this time Expense.  See ClaimStringRenderer for analogous rationale.
 */
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
