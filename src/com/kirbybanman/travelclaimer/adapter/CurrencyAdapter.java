package com.kirbybanman.travelclaimer.adapter;

import android.content.Context;

public class CurrencyAdapter extends SpinnerEnumAdapter<CurrencyAdapterEnum> {
	
	public CurrencyAdapter(Context context) {
		super(context);
	}

	@Override
	protected CurrencyAdapterEnum[] getValues() {
		return CurrencyAdapterEnum.values();
	}

	public int indexOf(String countryCode) {
		return indexOf(CurrencyAdapterEnum.valueOf(countryCode));
	}
}
