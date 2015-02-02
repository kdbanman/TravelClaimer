package com.kirbybanman.travelclaimer.adapter;

import com.kirbybanman.travelclaimer.interfaces.Stringable;

/* This enum looks like it's modelling currencies.  So why is it in the
 * adapter package?
 * 
 * Because currencies are already more generally modelled within the model
 * package.  This is just the listing I wish to support at the UI level so
 * that the currency spinner isn't unweildy.
 */
public enum CurrencyAdapterEnum implements Stringable<CurrencyAdapterEnum> {

	AUD("AUD"),
	CAD("CAD"),
	CHF("CHF"),
	EUR("EUR"),
	GBP("GBP"),
	NZD("NZD"),
	USD("USD");
	
	private String asString;
	
	CurrencyAdapterEnum(String asString) {
		this.asString = asString;
	}
	
	@Override
	public String toString() {
		return asString;
	}

}
