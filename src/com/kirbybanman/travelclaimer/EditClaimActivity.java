package com.kirbybanman.travelclaimer;

import java.util.Date;

import com.kirbybanman.travelclaimer.callbacks.DateSetter;
import com.kirbybanman.travelclaimer.callbacks.ModelMutator;
import com.kirbybanman.travelclaimer.core.TravelClaimerActivity;
import com.kirbybanman.travelclaimer.model.Claim;
import com.kirbybanman.travelclaimer.model.ClaimsList;
import com.kirbybanman.travelclaimer.view.ClaimStringRenderer;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

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
		
		statusSpinner = (Spinner) findViewById(R.id.editClaimStatusSpinner);
		statusSpinner.setAdapter(spinnerAdapter);
		statusSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				claim.setStatus(spinnerAdapter.getStatus(position));
				updateView();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// do nothing - claim unchanged
			}
			
		});
		
		startDateText = (TextView) findViewById(R.id.editClaimStartDateText);
		endDateText = (TextView) findViewById(R.id.editClaimEndDateText);
		
		startDateButton = (Button) findViewById(R.id.editClaimStartDateButton);
		endDateButton = (Button) findViewById(R.id.editClaimEndDateButton);

		descriptionText = (TextView) findViewById(R.id.editClaimDescription);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		int claimPosition = getIntent().getIntExtra("claimPosition", -1);
		
		// get claim and set veiw contents
		try {
			claim = getApp().getClaimsList().get(claimPosition);
			
			updateView();
		} catch (IndexOutOfBoundsException e) {
			Log.e("intent fail", e.toString());
		}
	}
	
	private void updateView() {
		ClaimStringRenderer claimStringRenderer = new ClaimStringRenderer(claim);

		statusSpinner.setSelection(spinnerAdapter.getPosition(claim.getStatus()));
		
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
		claim.setDescription(descriptionText.getText().toString());
		
		getApp().mutateModel(new ModelMutator() {
			@Override
			public boolean mutate(ClaimsList claimsList) {
				//no need to set claims list - claim edited in place.
				//mutator called to trigger persistence code
				return true;
			}
		});
		
		Intent intent = new Intent(this, IndividualClaimActivity.class);
		intent.putExtra("claimPosition", getApp().getClaimsList().indexOf(claim));

		finish(); // new claim creation is done --> remove self from stack.
		startActivity(intent);
		
	}
}
