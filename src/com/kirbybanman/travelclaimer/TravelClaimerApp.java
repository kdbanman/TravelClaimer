package com.kirbybanman.travelclaimer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.kirbybanman.travelclaimer.model.Claim;
import com.kirbybanman.travelclaimer.model.Expense;

import android.app.Application;

public class TravelClaimerApp extends Application {

	private static ArrayList<Claim> claimsList;
	
	@Override
	public void onCreate() {
		
		//TODO: attempt to GSON deserialize from save file.  will be null if invalid or nonexistent
		
		if (TravelClaimerApp.claimsList == null) {
			TravelClaimerApp.claimsList = new ArrayList<Claim>();
		}
		
		addFixtureData();
	}
	
	public List<Claim> getClaimsList() {
		return claimsList;
	}
	
	/**
	 * Testing function to populate gui without GSON or repeatedly entering stuff;
	 */
	private static void addFixtureData() {
		Claim temp1 = new Claim("first one", new Date(2), new Date(36));
		temp1.addExpense(new Expense("buy airlane", 75.0f, "GBP", "747-400 cheap!", new Date()));
		temp1.addExpense(new Expense("buy airlane again", 15.0f, "GBP", "F14 cheap!", new Date(33)));
		temp1.addExpense(new Expense("buy airlanery", 705.0f, "CAD", "cheap!", new Date(10000)));
		
		Claim temp2 = new Claim("second one", new Date(400), new Date(3600));
		temp2.addExpense(new Expense("buy airlane", 75.0f, "CAD", "747-400 cheap!", new Date()));
		temp2.addExpense(new Expense("buy airlane again", 15.0f, "CAD", "F14 cheap!", new Date(33)));
		temp2.addExpense(new Expense("buy airlanery", 705.0f, "CAD", "cheap!", new Date(10000)));
		
		claimsList.add(temp1);
		claimsList.add(temp2);
	}
}
