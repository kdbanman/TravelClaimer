package com.kirbybanman.travelclaimer;

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

import com.kirbybanman.travelclaimer.adapter.ExpensesListAdapter;
import com.kirbybanman.travelclaimer.core.TravelClaimerActivity;
import com.kirbybanman.travelclaimer.model.Claim;
import com.kirbybanman.travelclaimer.model.Expense;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

/**
 * Activity for viewing the member Expenses of a Claim.  As such, it is associated with a single
 * claim that must already exist.
 * Allows deletion of Expenses.  Allows transition to activities for editing or creating Expenses.
 * 
 * @author kdbanman
 *
 */
public class ExpensesListActivity extends TravelClaimerActivity {

	private Claim claim;
	
	private ExpensesListAdapter expensesAdapter;
	
	private ListView expensesListView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_expenses_list);
		
		expensesListView = (ListView) findViewById(R.id.ExpensesListView);
		expensesListView.setOnItemClickListener(new ExpenseClickListener());
		expensesListView.setOnItemLongClickListener(new ExpenseLongClickListener());
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		// reset adapter on resume - claim has changed
		claim = getClaimFromIntent();
		expensesAdapter = new ExpensesListAdapter(this, R.layout.expenses_list_item, claim.getExpenses().getList());
		expensesListView.setAdapter(expensesAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.expenses_list, menu);
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
	
	public void newExpenseButtonClicked(View view) {
		Intent intent = new Intent(ExpensesListActivity.this, ExpenseActivity.class);
		intent.putExtra("claimPosition", getApp().getClaimsList().indexOf(claim));
		startActivity(intent);
	}
	
	/* 
	 * On a expense tap, transition to claim overview activity.
	 */
	private class ExpenseClickListener implements OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			Intent intent = new Intent(ExpensesListActivity.this, ExpenseActivity.class);
			intent.putExtra("claimPosition", getApp().getClaimsList().indexOf(claim));
			intent.putExtra("expensePosition", position);
			startActivity(intent);
		}
	}
	
	/*
	 * On a long click, delete the expense with confirmation dialog
	 */
	private class ExpenseLongClickListener implements OnItemLongClickListener {
		@Override
		public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
			new ConfirmDelete(expensesAdapter.getItem(position)).show(getFragmentManager(), "Confirm_Delete_Claim");;
			return true;
		}
	}
	
	/* Delete confirmation dialog adapted from
	 * http://stackoverflow.com/questions/12912181/simplest-yes-no-dialog-fragment
	 */
	private class ConfirmDelete extends DialogFragment {
		private Expense expense;
		
		public ConfirmDelete(Expense expense) {
			this.expense = expense;
		}
		
	    @Override
	    public Dialog onCreateDialog(Bundle savedInstanceState) {
	        return new AlertDialog.Builder(getActivity())
	                .setMessage("Delete Expense?")
	                .setNegativeButton("No", null)
	                .setPositiveButton("Yes", new OnClickListener() {
						@Override 
						public void onClick(DialogInterface dialog, int which) {
							if (which == DialogInterface.BUTTON_POSITIVE) {
								claim.removeExpense(expense);
								expensesAdapter.notifyDataSetChanged();
								ExpensesListActivity.this.getApp().saveModel();
							}
						}
	                })
	                .create();
	    }

	}

	/* Look in Intent and try to get a claim from it.
	 * 
	 */
	private Claim getClaimFromIntent() {
		int claimPosition = getIntent().getIntExtra("claimPosition", -1);
		
		try {
			return getApp().getClaimsList().get(claimPosition);
		} catch (IndexOutOfBoundsException e) {
			Log.e("intent fail", e.toString());
		}
		return null;
	}
}
