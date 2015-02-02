package com.kirbybanman.travelclaimer.core;

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

import android.app.Activity;

/**
 * Extension of android's Activity for typesafe method access.
 * 
 * @author kdbanman
 */
public abstract class TravelClaimerActivity extends Activity {

	
	protected TravelClaimerApp getApp() {
		return (TravelClaimerApp) this.getApplication();
	}
}
