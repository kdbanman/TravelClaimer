package com.kirbybanman.travelclaimer.adapter;

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

import com.kirbybanman.travelclaimer.model.Expense.Category;

import android.content.Context;

/**
 * Minimal extension to turn the Expense.Category enumeration
 * into a spinner dropdown view.
 * 
 * @author kdbanman
 *
 */
public class CategoryAdapter extends SpinnerEnumAdapter<Category> {

	public CategoryAdapter(Context context) {
		super(context);
	}

	@Override
	protected Category[] getValues() {
		return Category.values();
	}

}
