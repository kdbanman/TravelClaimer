package com.kirbybanman.travelclaimer.model;

import java.util.Date;

import com.kirbybanman.travelclaimer.callbacks.Stringable;

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
		this("New Claim", new Date(), new Date());
	}
	
	public Claim(String title, Date startDate, Date endDate) {
		this.description = title;
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
