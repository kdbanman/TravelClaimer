package com.kirbybanman.travelclaimer.adapter;

import com.kirbybanman.travelclaimer.model.Claim.Status;

import android.content.Context;

public class StatusAdapter extends SpinnerEnumAdapter<Status> {
	
	public StatusAdapter(Context context) {
		super(context);
	}

	@Override
	protected Status[] getValues() {
		return Status.values();
	}
}
