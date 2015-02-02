package com.kirbybanman.travelclaimer.adapter;

import java.util.List;

import com.kirbybanman.travelclaimer.R;
import com.kirbybanman.travelclaimer.R.id;
import com.kirbybanman.travelclaimer.R.layout;
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
