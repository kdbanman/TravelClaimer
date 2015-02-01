package com.kirbybanman.travelclaimer.core;

import android.app.Activity;

/*
 * Extension of android activity for typesafe global data access.
 */
public abstract class TravelClaimerActivity extends Activity {

	
	protected TravelClaimerApp getApp() {
		return (TravelClaimerApp) this.getApplication();
	}
}
