package com.kirbybanman.travelclaimer.adapter;

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

import java.util.Arrays;

import com.kirbybanman.travelclaimer.interfaces.Stringable;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

/**
 * Abstract adapter for turning stringable enums into spinners.  The Stringable interface only
 * enforces the .toString() method.  This abstract adapter is designed to work with Java enum's
 * uniquely compiler-constrained `values` array property in order to pair with a dropdown spinner.
 * 
 * @author kdbanman
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
