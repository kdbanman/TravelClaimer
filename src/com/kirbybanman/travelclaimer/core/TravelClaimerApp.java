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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kirbybanman.travelclaimer.interfaces.ModelMutator;
import com.kirbybanman.travelclaimer.model.ClaimsList;

import android.app.Application;
import android.content.Context;
import android.util.Log;

/**
 * Main application container.  Houses the root of the model, a single ClaimsList.
 * Knows how to serialize and deserialze the model with Gson.  Exposes save and mutate
 * methods, whose purpose could've been better thought out:
 * 
 * - the ClaimsListAdapter needed access to the model root directly, so there is now
 *   global access to the model.
 * - the *plan* was to hide the model and expose all model mutations to the rest of
 *   the app through the ModelMutator closure class, so I could just mutate the model
 *   willy-nilly and know that it would save itself.
 * - I ran out of time for implementing that callback command pattern, and it already
 *   felt moot with the getClaimsList() slip up.
 *   
 * @author kdbanman
 *
 */
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
}
