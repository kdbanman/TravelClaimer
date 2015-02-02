package com.kirbybanman.travelclaimer.callbacks;

import com.kirbybanman.travelclaimer.model.ClaimsList;

public interface ModelMutator {
	/**
	 * 
	 * @param claimsList model to be mutated
	 */
	public void mutate(ClaimsList claimsList);
}
