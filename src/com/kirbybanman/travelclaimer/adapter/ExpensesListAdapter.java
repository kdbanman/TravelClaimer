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

import java.util.List;

import com.kirbybanman.travelclaimer.R;
import com.kirbybanman.travelclaimer.model.Expense;
import com.kirbybanman.travelclaimer.view.ExpenseStringRenderer;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/*
 * Custom adapter for a list of expenses to a list view.
 * Note, this could be refactored along with ClaimsListAdapter to have an abstract superclass TravelClaimerListAdapter.
 */
public class ExpensesListAdapter extends ArrayAdapter<Expense> {

	public ExpensesListAdapter(Context context, int textViewResourceId, List<Expense> objects) {
		super(context, textViewResourceId, objects);
	}
	
	@Override
	public View getView(int position, View listItemView, ViewGroup listView) {
		
		// if not already done, grab the view from xml and inflate it
		if (listItemView == null) {
			LayoutInflater inflater = LayoutInflater.from(getContext());
			listItemView = inflater.inflate(R.layout.expenses_list_item, listView, false);
		}
		
		Expense expense = this.getItem(position);
		
		if (expense != null) {
			
			ExpenseStringRenderer expenseStrings = new ExpenseStringRenderer(expense);
			
			// set list item text components
			setText(listItemView, R.id.ExpensesListItemDescription, expenseStrings.getDescription());
			setText(listItemView, R.id.ExpensesListItemDate, expenseStrings.getDate());
			setText(listItemView, R.id.ExpensesListItemCategory, expenseStrings.getCategory());
			setText(listItemView, R.id.ExpensesListItemAmount, expenseStrings.getAmount());
			
		} else {
			Log.e("adapter", "expense " + position + " not found!");
		}
		return listItemView;
	}
	
	private boolean setText(View v, int id, String text) {
		TextView t = (TextView) v.findViewById(id);
		
		if (t != null) {
			t.setText(text);
			return true;
		} else {
			Log.e("adapter", "could not set text " + text + " for claim list item");
			return false;
		}
	}
}
