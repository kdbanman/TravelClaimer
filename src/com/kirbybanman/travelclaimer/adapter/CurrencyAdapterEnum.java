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
