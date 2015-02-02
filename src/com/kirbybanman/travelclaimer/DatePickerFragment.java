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

import com.kirbybanman.travelclaimer.interfaces.DateSetter;

/* Date picker adapted from http://developer.android.com/guide/topics/ui/controls/pickers.html
 * 
 * Takes a DateSetter so that the activity can accept the date.
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