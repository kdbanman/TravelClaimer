package com.kirbybanman.travelclaimer.adapter;

import java.util.List;

import com.kirbybanman.travelclaimer.R;
import com.kirbybanman.travelclaimer.R.id;
import com.kirbybanman.travelclaimer.R.layout;
import com.kirbybanman.travelclaimer.model.Claim;
import com.kirbybanman.travelclaimer.view.ClaimStringRenderer;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ClaimsListAdapter extends ArrayAdapter<Claim> {

	public ClaimsListAdapter(Context context, int textViewResourceId, List<Claim> objects) {
		super(context, textViewResourceId, objects);
	}

	@Override
	public View getView(int position, View listItemView, ViewGroup listView) {
		
		// if not already done, grab the view from xml and inflate it
		if (listItemView == null) {
			LayoutInflater inflater = LayoutInflater.from(getContext());
			listItemView = inflater.inflate(R.layout.claims_list_item, listView, false);
		}
		
		Claim claim = this.getItem(position);
		
		if (claim != null) {
			
			ClaimStringRenderer claimStrings = new ClaimStringRenderer(claim);
			
			// set list item text components
			setText(listItemView, R.id.claimListItemDescription, claimStrings.getDescription());
			setText(listItemView, R.id.claimListItemDate, claimStrings.getStartDate());
			setText(listItemView, R.id.claimListItemStatus, claimStrings.getStatus());
			setText(listItemView, R.id.claimListItemTotals, claimStrings.getTotals());
			
		} else {
			Log.e("adapter", "claim " + position + " not found!");
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
