package com.kirbybanman.travelclaimer.core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kirbybanman.travelclaimer.interfaces.ModelMutator;
import com.kirbybanman.travelclaimer.model.Claim;
import com.kirbybanman.travelclaimer.model.ClaimsList;
import com.kirbybanman.travelclaimer.model.Expense;

import android.app.Application;
import android.content.Context;
import android.util.Log;

public class TravelClaimerApp extends Application {

	private static final String MODEL_FILENAME = "claims_list.gson";
	
	private static ClaimsList claimsList;
	
	@Override
	public void onCreate() {
		//attempt to GSON deserialize from save file.
		//will be null if invalid or nonexistent
		
		claimsList = loadModel();
		
		// if the file didn't exist, probably the app hasn't been opened.
		// create a new claims list model
		if (claimsList == null) {
			claimsList = new ClaimsList();
		}
		
		//addFixtureData();
	}
	
	public ClaimsList getClaimsList() {
		return claimsList;
	}
	
	/**
	 * Calls the mutate function on the passed ModelMutator.
	 * Immediately saves afterward.
	 * @param mutator Closure for safe model mutation
	 */
	public void mutateModel(ModelMutator mutator) {
		mutator.mutate(claimsList);
		saveModel();
	}
	
	/* 
	 * Adapted from 
	 * https://github.com/joshua2ua/lonelyTwitter
	 * on 1 Feb 2015
	 */
	public void saveModel() {
		Gson gson = new Gson();
		
		try {
			FileOutputStream fos = openFileOutput(MODEL_FILENAME, Context.MODE_PRIVATE);
			OutputStreamWriter writer = new OutputStreamWriter(fos);
			gson.toJson(claimsList, writer);
			writer.flush();
			fos.close();
		} catch (FileNotFoundException e) {
			Log.w("IO error", "file not found");
		} catch (IOException e) {
			Log.e("IO error", "file read failed");
		}
	}
	
	/* 
	 * Adapted from 
	 * https://github.com/joshua2ua/lonelyTwitter
	 * on 1 Feb 2015
	 */
	private ClaimsList loadModel() {
		ClaimsList claimsList = null;
		Gson gson = new Gson();
		try {
			FileInputStream fis = openFileInput(MODEL_FILENAME);
			InputStreamReader reader = new InputStreamReader(fis);
			
			claimsList = gson.fromJson(reader, (new TypeToken<ClaimsList>() {}).getType());
			
			fis.close();
		} catch (FileNotFoundException e) {
			Log.w("IO error", "file not found");
		} catch (IOException e) {
			Log.e("IO error", "file read failed");
		}
		return claimsList;
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
