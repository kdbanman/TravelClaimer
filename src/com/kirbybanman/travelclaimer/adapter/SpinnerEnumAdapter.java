package com.kirbybanman.travelclaimer.adapter;

import java.util.Arrays;

import com.kirbybanman.travelclaimer.interfaces.Stringable;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

/*
 * Abstract adapter for turning stringable enums into spinners.
 */
public abstract class SpinnerEnumAdapter<E extends Stringable<E>> implements SpinnerAdapter {

	private Context context;
	private E[] values;
	
	protected abstract E[] getValues();
	
	public SpinnerEnumAdapter(Context context) {
		this.context = context;
		this.values = getValues();
	}
	
	public E get(int position) {
		return values[position];
	}
	
	public int indexOf(E item) {
		return Arrays.asList(values).indexOf(item);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TextView text = new TextView(context);
		text.setText(values[position].toString());
		text.setPadding(10, 5, 10, 5);
		return text;
	}

	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		return getView(position, convertView, parent);
	}
	
	@Override
	public void registerDataSetObserver(DataSetObserver observer) {
		// omit - Enum cannot change at runtime
	}

	@Override
	public void unregisterDataSetObserver(DataSetObserver observer) {
		// omit - Enum cannot change at runtime
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
		return position;
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

}
