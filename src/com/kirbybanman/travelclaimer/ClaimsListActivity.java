package com.kirbybanman.travelclaimer;

import com.kirbybanman.travelclaimer.R;
import com.kirbybanman.travelclaimer.R.id;
import com.kirbybanman.travelclaimer.R.layout;
import com.kirbybanman.travelclaimer.R.menu;
import com.kirbybanman.travelclaimer.callbacks.ModelMutator;
import com.kirbybanman.travelclaimer.core.TravelClaimerActivity;
import com.kirbybanman.travelclaimer.model.Claim;
import com.kirbybanman.travelclaimer.model.ClaimsList;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

public class ClaimsListActivity extends TravelClaimerActivity {

	private ClaimsListAdapter claimsAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_claims_list);
		
		claimsAdapter = new ClaimsListAdapter(this, R.layout.claims_list_item, getApp().getClaimsList().getList());
		
		ListView claimsList = (ListView) findViewById(R.id.claimsListView);
		claimsList.setAdapter(claimsAdapter);
		claimsList.setOnItemClickListener(new ClaimClickListener());
		claimsList.setOnItemLongClickListener(new ClaimLongClickListener());
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		// update data after coming back from anything
		claimsAdapter.notifyDataSetChanged();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	/*
	 * Transition to activity to make a new if the 'new' button is pressed
	 */
	public void newClaimButtonClicked(View view) {
		startActivity(new Intent(this, NewClaimActivity.class));
	}

	/* 
	 * On a claim tap, transition to claim overview activity.
	 */
	private class ClaimClickListener implements OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			Intent intent = new Intent(ClaimsListActivity.this, IndividualClaimActivity.class);
			intent.putExtra("claimPosition", position);
			startActivity(intent);
		}
	}
	
	/*
	 * On a long click, delete the claim with confirmation dialog
	 */
	private class ClaimLongClickListener implements OnItemLongClickListener {
		@Override
		public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
			new ConfirmDelete(claimsAdapter.getItem(position)).show(getFragmentManager(), "Confirm_Delete_Claim");;
			return true;
		}
	}
	
	/* Delete confirmation dialog adapted from
	 * http://stackoverflow.com/questions/12912181/simplest-yes-no-dialog-fragment
	 */
	private class ConfirmDelete extends DialogFragment {
		private Claim claim;
		
		public ConfirmDelete(Claim claim) {
			this.claim = claim;
		}
		
	    @Override
	    public Dialog onCreateDialog(Bundle savedInstanceState) {
	        return new AlertDialog.Builder(getActivity())
	                .setMessage("Delete Claim?")
	                .setNegativeButton("No", null)
	                .setPositiveButton("Yes", new OnClickListener() {
						@Override 
						public void onClick(DialogInterface dialog, int which) {
							if (which == DialogInterface.BUTTON_POSITIVE) {
								getApp().mutateModel(new ModelMutator() {

									@Override
									public void mutate(ClaimsList claimsList) {
										claimsList.remove(claim);
									}
									
								});
								claimsAdapter.notifyDataSetChanged();
							}
						}
	                })
	                .create();
	    }

	}
}
