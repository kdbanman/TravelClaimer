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

import java.util.Date;

import com.kirbybanman.travelclaimer.interfaces.Stringable;

public class Claim {
	
	public enum Status implements Stringable<Status> {
		IN_PROGRESS("IN PROGRESS"),
		SUBMITTED("SUBMITTED"),
		RETURNED("RETURNED"),
		APPROVED("APPROVED");
		
		private String asString;
		
		Status(String asString) {
			this.asString = asString;
		}
		
		@Override
		public String toString() {
			return this.asString;
		}
	}
	
	private String description;
	private ExpenseList expenses;
	private Status status;
	private Date startDate;
	private Date endDate;
	
	public Claim() {
		this("", new Date(), new Date());
	}
	
	public Claim(String description, Date startDate, Date endDate) {
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		
		expenses = new ExpenseList();
		setStatus(Status.IN_PROGRESS);
	}
	
	public void addExpense(Expense expense) {
		expenses.addExpense(expense);
	}
	
	public boolean removeExpense(Expense expense) {
		return expenses.removeExpense(expense);
	}
	
	public ExpenseList getExpenses() {
		return expenses;
	}

	public boolean isEditable() {
		return this.status == Status.IN_PROGRESS || this.status == Status.RETURNED;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String title) {
		this.description = title;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
}
