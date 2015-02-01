package com.kirbybanman.travelclaimer.model;

import java.util.Date;

public class Claim {

	private String description;
	private ExpenseList expenses;
	private Status status;
	private Date startDate;
	private Date endDate;
	
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
