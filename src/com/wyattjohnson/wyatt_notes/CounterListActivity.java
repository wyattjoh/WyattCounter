package com.wyattjohnson.wyatt_notes;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class CounterListActivity extends Activity {
	private CounterListController counterListController;
	private ArrayAdapter<Counter> counterListAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_list);
		
		// Custom list view from: http://www.youtube.com/watch?v=WRANgDgM2Zg
		populateListView();
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		// Refresh the list
		this.refreshList(true);
	}

	private void populateListView() {
		this.counterListController = CounterListController.shared(getApplicationContext());
		this.counterListAdapter = new MyListAdapter();
		
		ListView list = (ListView) findViewById(R.id.counterListActivity);
		list.setAdapter(this.counterListAdapter);
	}
	
	private void refreshList(Boolean shouldSort) {
		// Check if should sort the list
		if (shouldSort) {
			// Sort the list
			this.counterListController.sortList();
		}
		
		// Update data views
		this.counterListAdapter.notifyDataSetChanged();
	}
	
	private class MyListAdapter extends ArrayAdapter<Counter> {

		public MyListAdapter() {
			super(CounterListActivity.this, R.layout.activity_main_list_cell, counterListController.getList());
			// TODO Auto-generated constructor stub
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// Make sure we have a view to work with (may have been null)
			View itemView = convertView;
			
			if (itemView == null) {
				itemView = getLayoutInflater().inflate(R.layout.activity_main_list_cell, parent, false);
			}
			
			// Find the Counter to work with
			Counter currentCounter = counterListController.getCounter(position);
			
			// Fill the view
			TextView titleText = (TextView) itemView.findViewById(R.id.counterTitle);
			titleText.setText(currentCounter.getName());
			
			TextView counterText = (TextView) itemView.findViewById(R.id.counterCount);
			counterText.setText(currentCounter.getCount().toString());
			
			LinearLayout clickAreaLayout = (LinearLayout) itemView.findViewById(R.id.counterIncrementArea);
			clickAreaLayout.setTag(position);
			
			// Setup events
			clickAreaLayout.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // do you work here
                	LinearLayout clickAreaLayout = (LinearLayout) v.findViewById(R.id.counterIncrementArea);
                	int index = ((Integer)clickAreaLayout.getTag()).intValue();
    				
                	// increment the counter
                	counterListController.incrementCounterAtIndex(index);
                	
                	// Refresh the view
                	refreshList(false);
                 }
			});
			
			ImageButton detailsButton = (ImageButton) itemView.findViewById(R.id.detailsButton);
			
			Integer positionInteger = Integer.valueOf(position);
			detailsButton.setTag(positionInteger);
			
			return itemView;
		}

		@Override
		public int getCount() {
			return counterListController.getListSize();
		}
		
	}
	
	public void detailsButton(View v) {
		// Get the selected position
		Integer position = (Integer) v.getTag();
		
		// Update singleton on selected tag
		counterListController.setSelectedCounterForIndex(position.intValue());
		
		// Create an intent to start a new activity
		Intent intent = new Intent(CounterListActivity.this, CounterListStats.class);
		
		// Start the intent
		startActivity(intent);
	}
	
	private AlertDialog getAddCounterAlertDialog() {
		// From the Android Documentation: http://developer.android.com/guide/topics/ui/dialogs.html
		
		// Generate a alert dialog builder
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		
		// Get the layout inflater
	    LayoutInflater inflater = this.getLayoutInflater();
		
	    // Setup view and chain together various setter methods to set the dialog characteristics
		builder.setView(inflater.inflate(R.layout.action_add_new_counter, null))
			.setNegativeButton("Cancel", null)
			.setPositiveButton("Add Counter", new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialog, int id) {
                   // Get the edit box
            	   // http://stackoverflow.com/questions/9771228/android-dialoginterface-get-inner-dialog-views
            	   EditText edit = (EditText) ((AlertDialog) dialog).findViewById(R.id.addCounterTextField);
            	   
            	   // Extract the counter name
            	   String counterName = edit.getText().toString();
            	   
            	   // Add a counter with this name
            	   counterListController.addCounter(counterName);
            	   
            	   // Refresh the list
            	   refreshList(true);
               }
	        });

		// Get the AlertDialog from create()
		return builder.create();
	}
	
	// Header for function: http://stackoverflow.com/questions/11245829/inflateexception-couldnt-resolve-menu-item-onclick-handler
	public boolean addCounterAction(MenuItem item) {
		// Dialog from http://stackoverflow.com/questions/12402983/i-want-to-show-my-login-form-in-dialog-box-or-in-pop-up-box-using-android

		// Get the dialog
		AlertDialog dialog = this.getAddCounterAlertDialog();
		
		// Show it
		dialog.show();
        
		// Say that we have been completed successfully
		return true;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.counter_list, menu);
		return true;
	}
	
	

}
