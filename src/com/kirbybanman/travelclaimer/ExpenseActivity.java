package com.kirbybanman.travelclaimer;

import com.kirbybanman.travelclaimer.core.TravelClaimerActivity;
import com.kirbybanman.travelclaimer.model.Claim;
import com.kirbybanman.travelclaimer.model.Expense;
import com.kirbybanman.travelclaimer.view.ExpenseStringRenderer;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class ExpenseActivity extends TravelClaimerActivity {
	
	private Claim claim;
	private Expense expense;
	
	private TextView dateText;
	private TextView descriptionText;
	private EditText amountText;
	
	private Spinner categorySpinner;
	private Spinner currencySpinner;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_expense);
		
		dateText = (TextView) findViewById(R.id.ExpenseDateText);
		descriptionText = (TextView) findViewById(R.id.ExpenseDescriptionText);
		
		amountText = (EditText) findViewById(R.id.ExpenseAmountNumber);
		amountText.setFilters(new InputFilter[] {new CurrencyInputFilter()});
	
		//TODO spinners and adapters
	}

	@Override
	protected void onResume() {
		super.onResume();
		
		claim = getClaimFromIntent();
		
		if (intentHasExpense())
			expense = getExpenseFromIntent();
		else
			expense = new Expense();
		
		updateView();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.expense, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private void updateView() {
		ExpenseStringRenderer expenseStrings = new ExpenseStringRenderer(expense);
		
		dateText.setText(expenseStrings.getDate());
		descriptionText.setText(expense.getDescription());
		amountText.setText(expenseStrings.getNumericalAmount());
		
		// TODO spinners
	}
	
	public void doneButtonClicked(View view) {
		
	}
	
	public void dateButtonClicked(View view) {
		
	}
	
	private Claim getClaimFromIntent() {
		int claimPosition = getIntent().getIntExtra("claimPosition", -1);
		
		try {
			return getApp().getClaimsList().get(claimPosition);
		} catch (IndexOutOfBoundsException e) {
			Log.e("intent fail", e.toString());
		}
		return null;
	}
	
	private boolean intentHasExpense() {
		return getIntent().getIntExtra("expensePosition", -1) != -1;
	}
	
	private Expense getExpenseFromIntent() {
		int expensePosition = getIntent().getIntExtra("expensePosition", -1);
	
		try {
			return getClaimFromIntent().getExpenses().get(expensePosition);
		} catch (IndexOutOfBoundsException e) {
			Log.e("intent fail", e.toString());
		}
		return null;
	}
}
