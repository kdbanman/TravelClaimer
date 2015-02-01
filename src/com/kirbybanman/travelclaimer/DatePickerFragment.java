package com.kirbybanman.travelclaimer;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;

import com.kirbybanman.travelclaimer.callbacks.DateSetter;

/* Date picker adapted from http://developer.android.com/guide/topics/ui/controls/pickers.html
 * 
 * Takes a DateSetter so that the activity knows which date to set, start or end date.
 */
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
	private DateSetter dateSetter;
	private Date date;
	
	public DatePickerFragment(DateSetter dateSetter) {
		this(new Date(), dateSetter);
	}
	
	public DatePickerFragment(Date date, DateSetter dateSetter) {
		this.dateSetter = dateSetter;
		this.date = date;
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        c.setTime(DatePickerFragment.this.date);
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
	}
	
	@Override
	public void onDateSet(DatePicker view, int year, int month, int day) {
		String dateStr = year + "/" + (month + 1) + "/" + day;
		try {
			DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
			dateSetter.setDate(formatter.parse(dateStr));
		} catch (ParseException e) {
			Log.e("date_picker", e.toString());
		}
	}

}