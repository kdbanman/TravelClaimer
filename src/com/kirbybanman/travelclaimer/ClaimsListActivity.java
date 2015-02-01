package com.kirbybanman.travelclaimer;

import com.kirbybanman.travelclaimer.callbacks.TravelClaimerActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

public class ClaimsListActivity extends TravelClaimerActivity {

	private ClaimsListAdapter claimsAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.claims_list);
		
		claimsAdapter = new ClaimsListAdapter(this, R.layout.claims_list_item, getApp().getClaimsList().getList());
		((ListView) findViewById(R.id.claimsListView)).setAdapter(claimsAdapter);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		claimsAdapter.notifyDataSetChanged();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
	
	public void newClaimButtonClicked(View view) {
		startActivity(new Intent(this, NewClaimActivity.class));
	}
}
