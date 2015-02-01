package com.kirbybanman.travelclaimer.activities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.kirbybanman.travelclaimer.R;
import com.kirbybanman.travelclaimer.R.id;
import com.kirbybanman.travelclaimer.R.layout;
import com.kirbybanman.travelclaimer.R.menu;
import com.kirbybanman.travelclaimer.callbacks.DateSetter;
import com.kirbybanman.travelclaimer.callbacks.ModelMutator;
import com.kirbybanman.travelclaimer.core.TravelClaimerActivity;
import com.kirbybanman.travelclaimer.model.Claim;
import com.kirbybanman.travelclaimer.model.ClaimsList;
import com.kirbybanman.travelclaimer.view.ClaimStringView;

import android.os.Bundle;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

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
		startDateText.setText(new ClaimStringView(newClaim).getStartDate());
		
		endDateText = (TextView) findViewById(R.id.newClaimEndDateText);
		endDateText.setText(new ClaimStringView(newClaim).getEndDate());
		

		descriptionText = (TextView) findViewById(R.id.newClaimDescription);
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
				startDateText.setText(new ClaimStringView(newClaim).getStartDate());
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
				endDateText.setText(new ClaimStringView(newClaim).getEndDate());
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
			public boolean mutate(ClaimsList claimsList) {
				claimsList.add(newClaim);
				return true;
			}
		});
		
		Intent intent = new Intent(this, IndividualClaimActivity.class);
		intent.putExtra("claimPosition", getApp().getClaimsList().size() - 1);
		startActivity(intent);
		finish(); // new claim creation is done --> remove self from stack.
		
	}
	
	/* Date picker adapted from http://developer.android.com/guide/topics/ui/controls/pickers.html
	 * 
	 * Takes a DateSetter so that the activity knows which date to set, start or end date.
	 */
	
	public static class DatePickerFragment extends DialogFragment
										   implements DatePickerDialog.OnDateSetListener {
		private DateSetter dateSetter;
		
		public DatePickerFragment(DateSetter dateSetter) {
			this.dateSetter = dateSetter;
		}
		
		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			// Use the current date as the default date in the picker
	        final Calendar c = Calendar.getInstance();
	        int year = c.get(Calendar.YEAR);
	        int month = c.get(Calendar.MONTH);
	        int day = c.get(Calendar.DAY_OF_MONTH);

	        // Create a new instance of DatePickerDialog and return it
	        return new DatePickerDialog(getActivity(), this, year, month, day);
		}
		
		@Override
		public void onDateSet(DatePicker view, int year, int month, int day) {
			String dateStr = year + "/" + month + "/" + day;
			try {
				DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
				dateSetter.setDate(formatter.parse(dateStr));
			} catch (ParseException e) {
				Log.e("date_picker", e.toString());
			}
		}

	}

}