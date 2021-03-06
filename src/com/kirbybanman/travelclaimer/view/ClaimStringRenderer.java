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
import java.util.Currency;
import java.util.Locale;
import java.util.Map;

import com.kirbybanman.travelclaimer.model.Claim;
import com.kirbybanman.travelclaimer.model.Expense;

/**
 * Class for rendering Claims as strings.  Represents separation of (textual) view information
 * from model information.  This ended up being *very* nice.  My model was squeeky clean, and 
 * I could still have powerful string representations of it whenever I wanted.
 * 
 * @author kdbanman
 */
public class ClaimStringRenderer {

	private Claim claim;
	private DateFormat dateFormatter;
	
	public ClaimStringRenderer(Claim claim) {
		this.claim = claim;
		
		dateFormatter = DateFormat.getDateInstance(DateFormat.LONG, Locale.CANADA);
	}
	
	public String getFullDescription() {
		return "Details\n" +
	           "=======\n" +
			   getAllDetails() + "\n" +
			   "\n" +
			   "Expenses\n" +
			   "========\n" +
			   getAllExpenses();
	}
	
	public String getAllDetails() {
		return  getDescription() + "\n" +
				"\n" +
				"Status:  " + getStatus()    + "\n" +
				"Start:   " + getStartDate() + "\n" +
		        "Finish:  " + getEndDate()   + "\n";
	}
	
	public String getAllExpenses() {
		
		String expenses = "Totals: \n" +
				          getTotals() + "\n" +
				          "\n" + 
				          "Individual Expenses:\n" +
				          "\n";
		
		for (Expense expense : claim.getExpenses()) {
			expenses += new ExpenseStringRenderer(expense).getFullDescription() + "\n\n";
		}
		
		return expenses;
	}
	
	public String getStartDate() {
		return dateFormatter.format(claim.getStartDate());
	}
	
	public String getEndDate() {
		return dateFormatter.format(claim.getEndDate());
	}
	
	public String getStatus() {
		return claim.getStatus().toString();
	}
	
	public String getTotals() {
		
		if (claim.getExpenses().size() < 1) return "(no expenses)";
		
		String totals = "";
		
		for (Map.Entry<Currency, Float> total : claim.getExpenses().getTotals().entrySet()) {
			String code = total.getKey().getCurrencyCode();
			String amount = String.format(Locale.getDefault(), "%.2f", total.getValue());
			totals +=  "" + code + " "+ amount + "\n";
		}
		
		return totals.substring(0, totals.length() - 1);
	}
	
	public String getDescription() {
		return claim.getDescription();
	}

}
