package com.kirbybanman.travelclaimer;

import java.util.ArrayList;
import java.util.Arrays;

import com.kirbybanman.travelclaimer.model.Claim.Status;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

public class StatusAdapter implements SpinnerAdapter {

	private Context context;
	
	private Status[] values;
	
	public StatusAdapter(Context context) {
		this.context = context;
		
		this.values = Status.values();
	}
	@Override
	public void registerDataSetObserver(DataSetObserver observer) {
		// omit - Status cannot change at runtime
	}

	@Override
	public void unregisterDataSetObserver(DataSetObserver observer) {
		// omit - Status cannot change at runtime
		
	}

	@Override
	public int getCount() {
		return values.length;
	}

	@Override
	public Object getItem(int position) {
		return values[position];
	}

	@Override
	public long getItemId(int position) {
		return values[position].ordinal();
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public int getItemViewType(int position) {
		return 0;
	}

	@Override
	public int getViewTypeCount() {
		return 1;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public View getView(int position, View statusView, ViewGroup parent) {
		TextView text = new TextView(context);
		text.setText(values[position].toString());
		text.setPadding(10, 5, 10, 5);
		return text;
	}

	@Override
	public View getDropDownView(int position, View statusView, ViewGroup parent) {
		return getView(position, statusView, parent);
	}

	public int getPosition(Status status) {
		return new ArrayList<Status>(Arrays.asList(values)).indexOf(status);
	}
	public Status getStatus(int position) {
		return (Status) getItem(position);
	}
}
