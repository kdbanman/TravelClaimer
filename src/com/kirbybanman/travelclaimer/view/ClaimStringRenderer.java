package com.kirbybanman.travelclaimer.view;

import java.text.DateFormat;
import java.util.Currency;
import java.util.Locale;
import java.util.Map;

import android.util.Log;

import com.kirbybanman.travelclaimer.model.Claim;
import com.kirbybanman.travelclaimer.model.Expense;

/*
 * Class for rendering Claims as strings.
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
		String status = null;
		
		switch (claim.getStatus()) {
		case APPROVED: status = "APPROVED";
			break;
		case IN_PROGRESS: status = "IN PROGRESS";
			break;
		case RETURNED: status = "RETURNED";
			break;
		case SUBMITTED: status = "SUBMITTED";
			break;
		}
		
		if (status == null) Log.e("view", "status not found!");
		
		return status;
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
