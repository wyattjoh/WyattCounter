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
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

/**
 * @author wyatt
 *
 * Responsible for displaying a list of counters that can be
 * incremented via clicking in the active area, or access
 * detailed information via the right hand side button.
 *
 */
public class CounterListActivity extends Activity {
	private CounterListController counterListController;
	private ArrayAdapter<Counter> counterListAdapter;
	
	/**
	 * 
	 * Activated when the add a counter new button is pressed. Opens a dialog to display a form.
	 * 
	 * @param item
	 * @return Did complete the menu action
	 */
	public boolean addCounterAction(MenuItem item) {
		// Header for function: http://stackoverflow.com/questions/11245829/inflateexception-couldnt-resolve-menu-item-onclick-handler
		// Dialog from http://stackoverflow.com/questions/12402983/i-want-to-show-my-login-form-in-dialog-box-or-in-pop-up-box-using-android

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
            	   
            	   if (counterName.length() <= 0) {
            		   Toast toast = Toast.makeText(getApplicationContext(), R.string.blank_counter_name_msg, Toast.LENGTH_SHORT);
            		   toast.show();
            	   }
            	   else {
	            	   // Add a counter with this name
	            	   counterListController.addCounter(counterName);
	            	   
	            	   // Refresh the list
	            	   refreshList(true);
            	   }
               }
	        });

		// Get the AlertDialog from create()
		AlertDialog dialog =  builder.create();
		
		// Show it
		dialog.show();
        
		// Say that we have been completed successfully
		return true;
	}
	
	
	/**
	 * 
	 * Sorts the list on button press
	 * 
	 * @param item
	 * @return
	 */
	public boolean sortListAction(MenuItem item) {
		// Refresh the list
		refreshList(true);
		
		return true;
	}

	/**
	 * 
	 * Activated when the details button is pressed
	 * 
	 * @param v
	 */
	public void detailsButton(View v) {
		// Get the selected position
		Integer position = (Integer) v.getTag();
		
		// Update singleton on selected tag
		counterListController.setSelectedCounterForIndex(position.intValue());
		
		// Create an intent to start a new activity
		Intent intent = new Intent(CounterListActivity.this, CounterStatisticsActivity.class);
		
		// Start the intent
		startActivity(intent);
	}
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 * 
	 * Added list population
	 * 
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_list);
		
		// Custom list view from: http://www.youtube.com/watch?v=WRANgDgM2Zg
		populateListView();
	}
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 * 
	 * Added the menu inflator
	 * 
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.counter_list, menu);
		return true;
	}
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onResume()
	 * 
	 * Added a refresh call
	 * 
	 */
	@Override
	protected void onResume() {
		super.onResume();
		
		// Refresh the list
		this.refreshList(true);
	}
	
	/**
	 * 
	 * Fills the list view and sets up the adapter
	 * 
	 */
	private void populateListView() {
		this.counterListController = CounterListController.shared(getApplicationContext());
		this.counterListAdapter = new CounterListArrayAdapter(this, counterListController);
		
		ListView list = (ListView) findViewById(R.id.counterListActivity);
		list.setAdapter(this.counterListAdapter);
	}

	/**
	 * 
	 * Informs that the list should be updated as some modification has occured
	 * 
	 * @param shouldSort
	 */
	void refreshList(Boolean shouldSort) {
		// Check if should sort the list
		if (shouldSort) {
			// Sort the list
			this.counterListController.sortList();
		}
		
		// Update data views
		this.counterListAdapter.notifyDataSetChanged();
	}
	
	

}
