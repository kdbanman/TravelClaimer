package com.kirbybanman.travelclaimer.adapter;

import com.kirbybanman.travelclaimer.model.Expense.Category;

import android.content.Context;

public class CategoryAdapter extends SpinnerEnumAdapter<Category> {

	public CategoryAdapter(Context context) {
		super(context);
	}

	@Override
	protected Category[] getValues() {
		return Category.values();
	}

}
