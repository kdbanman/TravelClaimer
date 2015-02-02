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

import java.util.Date;

import com.kirbybanman.travelclaimer.R;
import com.kirbybanman.travelclaimer.core.TravelClaimerActivity;
import com.kirbybanman.travelclaimer.interfaces.DateSetter;
import com.kirbybanman.travelclaimer.interfaces.ModelMutator;
import com.kirbybanman.travelclaimer.model.Claim;
import com.kirbybanman.travelclaimer.model.ClaimsList;
import com.kirbybanman.travelclaimer.view.ClaimStringRenderer;

import android.os.Bundle;
import android.app.DialogFragment;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.TextView;

/**
 * Activity for creating a new Claim.
 * Allows input of all claim data other than member expenses and status (defaults to IN_PROGRESS).
 * Allows transition back to the claims list, or to the entry point for more detailed
 * editing the newly created claim.
 * 
 * @author kdbanman
 */
public class NewClaimActivity extends TravelClaimerActivity {

	Claim newClaim;
	
	TextView startDateText;
	TextView endDateText;
	TextView descriptionText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_claim);
		
		newClaim = new Claim();
		
		startDateText = (TextView) findViewById(R.id.newClaimStartDateText);
		startDateText.setText(new ClaimStringRenderer(newClaim).getStartDate());
		
		endDateText = (TextView) findViewById(R.id.newClaimEndDateText);
		endDateText.setText(new ClaimStringRenderer(newClaim).getEndDate());
		

		descriptionText = (TextView) findViewById(R.id.newClaimDescription);
		descriptionText.setOnKeyListener(new OnKeyListener() {
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				newClaim.setDescription(descriptionText.getText().toString());
				return false;
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_claim, menu);
		return true;
	}
	
	/* Get date from fragment for start date button click
	 * 
	 */
	public void startDateButtonClicked(View view) {
		DialogFragment frag = new DatePickerFragment(new DateSetter() {
			@Override
			public void setDate(Date date) { 
				newClaim.setStartDate(date);
				startDateText.setText(new ClaimStringRenderer(newClaim).getStartDate());
			}
		});
		frag.show(getFragmentManager(), "start_date");
	}
	
	/* Get date from fragment for end date button click.
	 * 
	 */
	public void endDateButtonClicked(View view) {
		DialogFragment frag = new DatePickerFragment(new DateSetter() {
			@Override
			public void setDate(Date date) {
				newClaim.setEndDate(date);
				endDateText.setText(new ClaimStringRenderer(newClaim).getEndDate());
			}
		});
		frag.show(getFragmentManager(), "end_date");
	}
	
	/* Save the new claim to the model and transition to the individual claims view.
	 */
	public void doneButtonClicked(View view) {
		newClaim.setDescription(descriptionText.getText().toString());
		
		getApp().mutateModel(new ModelMutator() {
			@Override
			public void mutate(ClaimsList claimsList) {
				claimsList.add(newClaim);
			}
		});
		
		Intent intent = new Intent(this, IndividualClaimActivity.class);
		intent.putExtra("claimPosition", getApp().getClaimsList().indexOf(newClaim));
		startActivity(intent);
		finish(); // new claim creation is done --> remove self from stack.
		
	}
}
