package com.kirbybanman.travelclaimer.core;

import java.util.Date;

import com.kirbybanman.travelclaimer.interfaces.ModelMutator;
import com.kirbybanman.travelclaimer.model.Claim;
import com.kirbybanman.travelclaimer.model.ClaimsList;
import com.kirbybanman.travelclaimer.model.Expense;

import android.app.Application;

public class TravelClaimerApp extends Application {

	private static ClaimsList claimsList;
	
	@Override
	public void onCreate() {
		
		//TODO: attempt to GSON deserialize from save file.  will be null if invalid or nonexistent
		
		if (TravelClaimerApp.claimsList == null) {
			TravelClaimerApp.claimsList = new ClaimsList();
		}
		
		addFixtureData();
	}
	
	public ClaimsList getClaimsList() {
		return claimsList;
	}
	
	public void mutateModel(ModelMutator mutator) {
		// TODO should throw on bad save
		mutator.mutate(claimsList);
		saveModel();
		
	}
	
	public void saveModel() {
		//TODO GSON to/from json return bool and log on errrrrrrrrrbody in da club

	}
	
	/**
	 * Testing function to populate gui without GSON or repeatedly entering stuff;
	 */
	private static void addFixtureData() {
		Claim temp1 = new Claim("first one", new Date(2), new Date(36));
		temp1.addExpense(new Expense(75.0f, "GBP", "747-400 cheap!", new Date(), Expense.Category.AIR_FARE));
		temp1.addExpense(new Expense(15.0f, "GBP", "F14 cheap!", new Date(33), Expense.Category.AIR_FARE));
		temp1.addExpense(new Expense(705.0f, "CAD", "cheap!", new Date(10000), Expense.Category.AIR_FARE));
		
		Claim temp2 = new Claim("second one", new Date(400), new Date(3600));
		temp2.addExpense(new Expense(75.0f, "CAD", "747-400 cheap!", new Date(), Expense.Category.AIR_FARE));
		temp2.addExpense(new Expense(15.0f, "CAD", "F14 cheap!", new Date(33), Expense.Category.AIR_FARE));
		temp2.addExpense(new Expense(705.0f, "CAD", "cheap!", new Date(10000), Expense.Category.AIR_FARE));
		
		claimsList.add(temp1);
		claimsList.add(temp2);
	}
}
