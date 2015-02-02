package com.kirbybanman.travelclaimer;

import com.kirbybanman.travelclaimer.core.TravelClaimerActivity;
import com.kirbybanman.travelclaimer.model.Claim;
import com.kirbybanman.travelclaimer.view.ClaimStringRenderer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

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
		//TODO long click listeners
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
	
	private boolean intentHasClaim() {
		return getIntent().getIntExtra("claimPosition", -1) != -1;
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
}