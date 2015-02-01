package com.kirbybanman.travelclaimer.activities;

import com.kirbybanman.travelclaimer.R;
import com.kirbybanman.travelclaimer.R.id;
import com.kirbybanman.travelclaimer.R.layout;
import com.kirbybanman.travelclaimer.R.menu;
import com.kirbybanman.travelclaimer.core.TravelClaimerActivity;
import com.kirbybanman.travelclaimer.model.Claim;
import com.kirbybanman.travelclaimer.view.ClaimStringView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class IndividualClaimActivity extends TravelClaimerActivity {

	private Claim claim;
	
	TextView summaryText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_individual_claim);
		
		summaryText = (TextView) findViewById(R.id.IndividualClaimSummary);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		int claimPosition = getIntent().getIntExtra("claimPosition", -1);
		
		
		try {
			claim = getApp().getClaimsList().get(claimPosition);
			summaryText.setText(new ClaimStringView(claim).getFullDescription());
		} catch (IndexOutOfBoundsException e) {
			Log.e("intent fail", e.toString());
			summaryText.setText("Bad claim number: " + claimPosition);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.individual_claim, menu);
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
}
