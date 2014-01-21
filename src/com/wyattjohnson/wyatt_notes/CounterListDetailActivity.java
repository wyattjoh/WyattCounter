package com.wyattjohnson.wyatt_notes;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

public class CounterListDetailActivity extends Activity {
	private CounterListController counterListController;
	private Counter selectedCounter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_counter_list_detail);
		// Show the Up button in the action bar.
		setupActionBar();
		
		// Create the controller
		counterListController = CounterListController.shared(getApplicationContext());
		

	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		// Get the currently selected Counter
		this.selectedCounter = counterListController.getSelectedCounter();
		
		// Update the views
		this.populateView();
	}

	private void populateView() {
		
		if (selectedCounter == null) {
			Toast.makeText(this, "Activity has null counter.", Toast.LENGTH_LONG).show();
		}
		else {
			// Counter name
			String counterName = selectedCounter.getName();
			
			// Update top bar
			getActionBar().setTitle(counterName + " Counter");
			
			// Update Title
			TextView counterTitle = (TextView) findViewById(R.id.counterDetailTitle);
			counterTitle.setText(counterName);
			
			// Update Count
			TextView counterCount = (TextView) findViewById(R.id.counterDetailCount);
			counterCount.setText(selectedCounter.getCount().toString());
		}
		
		
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.counter_list_detail, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
