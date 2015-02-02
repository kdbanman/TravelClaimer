package com.kirbybanman.travelclaimer.model;

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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Root of application Model.  A single ClaimsList has many Claims,
 * and each Claim has many Expenses.  An expense may belong only
 * to one Claim.
 * 
 * This ended up only being a wrapper for List<Claim>, but I still think
 * this is a useful class for the sake of decoupling my app from the
 * implementation of my model.
 * 
 * @author kdbanman
 *
 */
public class ClaimsList implements Iterable<Claim> {
	private ArrayList<Claim> claimsList;
	
	public ClaimsList() {
		claimsList = new ArrayList<Claim>();
	}
	
	public boolean add(Claim claim) {
		return claimsList.add(claim);
	}
	
	public boolean remove(Claim claim) {
		return claimsList.remove(claim);
	}
	
	public Claim get(int index) {
		return claimsList.get(index);
	}
	
	public Claim set(int index, Claim claim) {
		return claimsList.set(index, claim);
	}
	
	public int indexOf(Claim claim) {
		return claimsList.indexOf(claim);
	}
	
	public Claim remove(int index) {
		return claimsList.remove(index);
	}
	
	public int size() {
		return claimsList.size();
	}
	
	public List<Claim> getList() {
		return claimsList;
	}
	
	public boolean contains(Claim claim) {
		return claimsList.contains(claim);
	}

	@Override
	public Iterator<Claim> iterator() {
		return claimsList.iterator();
	}

}
