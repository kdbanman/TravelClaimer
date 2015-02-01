package com.kirbybanman.travelclaimer.model;

import java.util.ArrayList;
import java.util.List;

public class ClaimsList {
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
	
	public List<Claim> getList() {
		return claimsList;
	}

}
