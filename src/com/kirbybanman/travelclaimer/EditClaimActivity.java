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

import com.kirbybanman.travelclaimer.adapter.StatusAdapter;
import com.kirbybanman.travelclaimer.core.TravelClaimerActivity;
import com.kirbybanman.travelclaimer.interfaces.DateSetter;
import com.kirbybanman.travelclaimer.model.Claim;
import com.kirbybanman.travelclaimer.view.ClaimStringRenderer;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

/*
 * Activity for editing the non-expense related data of an claim.
 */
public class EditClaimActivity extends TravelClaimerActivity {

	private Claim claim;
	
	private StatusAdapter spinnerAdapter;
	
	private Spinner statusSpinner;
	
	private TextView startDateText;
	private TextView endDateText;
	private TextView descriptionText;
	
	private Button startDateButton;
	private Button endDateButton;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_claim);
		
		spinnerAdapter = new StatusAdapter(this);
		
		statusSpinner = (Spinner) findViewById(R.id.ExpenseCategorySpinner);
		statusSpinner.setAdapter(spinnerAdapter);
		statusSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// save the changes to the local attribute, but do
				// not mutate/save global state yet.
				claim.setStatus(spinnerAdapter.get(position));
				updateView();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// do nothing - claim unchanged
			}
			
		});
		
		startDateText = (TextView) findViewById(R.id.ExpenseDateText);
		endDateText = (TextView) findViewById(R.id.editClaimEndDateText);
		
		startDateButton = (Button) findViewById(R.id.ExpenseDateButton);
		endDateButton = (Button) findViewById(R.id.editClaimEndDateButton);

		descriptionText = (TextView) findViewById(R.id.ExpenseDescriptionText);
		descriptionText.setOnKeyListener(new OnKeyListener() {
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				claim.setDescription(descriptionText.getText().toString());
				return false;
			}
		});
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		int claimPosition = getIntent().getIntExtra("claimPosition", -1);
		
		// get claim and set veiw contents
		if (claimPosition != -1)
			claim = getApp().getClaimsList().get(claimPosition);
			
		updateView();
	}

	/*
	 * Render view elements according to local claim model state.
	 */
	private void updateView() {
		ClaimStringRenderer claimStringRenderer = new ClaimStringRenderer(claim);

		statusSpinner.setSelection(spinnerAdapter.indexOf(claim.getStatus()));
		
		startDateText.setText(claimStringRenderer.getStartDate());
		endDateText.setText(claimStringRenderer.getEndDate());
		descriptionText.setText(claimStringRenderer.getDescription());
		
		startDateButton.setEnabled(claim.isEditable());
		endDateButton.setEnabled(claim.isEditable());
		descriptionText.setEnabled(claim.isEditable());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_claim, menu);
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
	
	/* Get date from fragment for start date button click
	 * 
	 */
	public void startDateButtonClicked(View view) {
		DialogFragment frag = new DatePickerFragment(claim.getStartDate(), new DateSetter() {
			@Override
			public void setDate(Date date) { 
				claim.setStartDate(date);
				startDateText.setText(new ClaimStringRenderer(claim).getStartDate());
			}
		});
		frag.show(getFragmentManager(), "start_date");
	}
	
	/* Get date from fragment for end date button click.
	 * 
	 */
	public void endDateButtonClicked(View view) {
		DialogFragment frag = new DatePickerFragment(claim.getEndDate(), new DateSetter() {
			@Override
			public void setDate(Date date) {
				claim.setEndDate(date);
				endDateText.setText(new ClaimStringRenderer(claim).getEndDate());
			}
		});
		frag.show(getFragmentManager(), "end_date");
	}
	
	/* Save the new claim to the model and transition to the individual claims view.
	 */
	public void doneButtonClicked(View view) {
		// Text description is now confirmed.  write it to local
		// model and the save that model globally.
		claim.setDescription(descriptionText.getText().toString());
		
		getApp().saveModel();
		
		Intent intent = new Intent(this, IndividualClaimActivity.class);
		intent.putExtra("claimPosition", getApp().getClaimsList().indexOf(claim));

		finish(); // new claim creation is done --> remove self from stack.
		startActivity(intent);
		
	}
}
