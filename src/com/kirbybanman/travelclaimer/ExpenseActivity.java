package com.kirbybanman.travelclaimer;

import com.kirbybanman.travelclaimer.adapter.CategoryAdapter;
import com.kirbybanman.travelclaimer.adapter.CurrencyAdapter;
import com.kirbybanman.travelclaimer.core.TravelClaimerActivity;
import com.kirbybanman.travelclaimer.model.Claim;
import com.kirbybanman.travelclaimer.model.Expense;
import com.kirbybanman.travelclaimer.view.ExpenseStringRenderer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
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
	
	private CategoryAdapter categoryAdapter;
	private CurrencyAdapter currencyAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_expense);
		
		dateText = (TextView) findViewById(R.id.ExpenseDateText);
		descriptionText = (TextView) findViewById(R.id.ExpenseDescriptionText);
		
		amountText = (EditText) findViewById(R.id.ExpenseAmountNumber);
		amountText.setFilters(new InputFilter[] {new CurrencyInputFilter()});
	
		categoryAdapter = new CategoryAdapter(this);
		currencyAdapter = new CurrencyAdapter(this);
		
		categorySpinner = (Spinner) findViewById(R.id.ExpenseCategorySpinner);
		categorySpinner.setAdapter(categoryAdapter);
		categorySpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// save the changes to the local attribute, but do
				// not mutate/save global state yet.
				expense.setCategory(categoryAdapter.get(position));
				updateView();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// do nothing - claim unchanged
			}
		});
		
		currencySpinner = (Spinner) findViewById(R.id.ExpenseCountryCodeSpinner);
		currencySpinner.setAdapter(currencyAdapter);
		currencySpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// save the changes to the local attribute, but do
				// not mutate/save global state yet.
				expense.setCurrency(currencyAdapter.get(position).toString());
				updateView();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// do nothing - expense unchanged
			}
		});
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
	
	/*
	 * Render view elements according to local expense model state.
	 */
	private void updateView() {
		ExpenseStringRenderer expenseStrings = new ExpenseStringRenderer(expense);
		
		dateText.setText(expenseStrings.getDate());
		descriptionText.setText(expense.getDescription());
		amountText.setText(expenseStrings.getNumericalAmount());
		
		categorySpinner.setSelection(categoryAdapter.indexOf(expense.getCategory()));
		currencySpinner.setSelection(currencyAdapter.indexOf(expense.getCurrency().toString()));
	}
	
	public void doneButtonClicked(View view) {
		// Text description is now confirmed.  write it to local
		// model and the save that model globally. 
		expense.setDescription(descriptionText.getText().toString());
		
		// Detect if the expense is already known to the claim.
		// if it is, no need to explicitly add/replace it.  otherwise we must.
		if (!claim.getExpenses().contains(expense))
			claim.addExpense(expense);
		
		getApp().saveModel();
		Intent intent = new Intent(this, ExpensesListActivity.class);
		
		finish();
		startActivity(intent);
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
