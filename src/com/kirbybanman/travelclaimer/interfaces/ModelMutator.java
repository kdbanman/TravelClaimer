package com.kirbybanman.travelclaimer.interfaces;

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

import com.kirbybanman.travelclaimer.model.ClaimsList;

/**
 * Interface for the main application to make changes to the model.
 * Very incomplete - see TravelClaimerApp.java's javadoc for more
 * details.
 * 
 * @author kdbanman
 *
 */
public interface ModelMutator {
	/**
	 * 
	 * @param claimsList model to be mutated
	 */
	public void mutate(ClaimsList claimsList);
}
