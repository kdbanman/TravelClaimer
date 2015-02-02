package com.kirbybanman.travelclaimer;

import com.kirbybanman.travelclaimer.R;
import com.kirbybanman.travelclaimer.R.id;
import com.kirbybanman.travelclaimer.R.layout;
import com.kirbybanman.travelclaimer.R.menu;
import com.kirbybanman.travelclaimer.core.TravelClaimerActivity;
import com.kirbybanman.travelclaimer.model.Claim;
import com.kirbybanman.travelclaimer.view.ClaimStringRenderer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
		
		// receive claim position in model from intent
		int claimPosition = getIntent().getIntExtra("claimPosition", -1);
		
		try {
			claim = getApp().getClaimsList().get(claimPosition);
			summaryText.setText(new ClaimStringRenderer(claim).getFullDescription());
			findViewById(R.id.IndividualClaimEditExpensesButton).setEnabled(claim.isEditable());
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
	
	public void editDetailsButtonClicked(View view) {
		Intent intent = new Intent(this, EditClaimActivity.class);
		intent.putExtra("claimPosition", getApp().getClaimsList().indexOf(claim));
		startActivity(intent);
	}
	
	public void editExpensesButtonClicked(View view) {
		Intent intent = new Intent(this, ExpensesListActivity.class);
		intent.putExtra("claimPosition", getApp().getClaimsList().indexOf(claim));
		startActivity(intent);
	}
	
	/* Adapted from 
	 * https://developer.android.com/guide/components/intents-common.html#Email
	 * on 1 Feb 2015 
	 */
	public void emailButtonClicked(View view) {
		ClaimStringRenderer claimStrings = new ClaimStringRenderer(claim);
		
		Intent send = new Intent(Intent.ACTION_SENDTO);
		send.setData(Uri.parse("mailto:"));
		send.putExtra(Intent.EXTRA_SUBJECT, claimStrings.getStartDate() + " Claim Details");
		send.putExtra(Intent.EXTRA_TEXT, claimStrings.getFullDescription());
		/*String uriText = "mailto:" + Uri.encode("kdbanman@ualberta.ca") + "?subject=" + 
						 Uri.encode("Claim Details") +
						 "&body=" + 
						 Uri.encode(new ClaimStringRenderer(claim).getFullDescription());
		send.setData(Uri.parse(uriText));*/
		//startActivity(Intent.createChooser(send, "Send mail..."));
		startActivity(send);
	}
}
