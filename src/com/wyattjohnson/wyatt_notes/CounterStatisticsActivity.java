package com.wyattjohnson.wyatt_notes;


import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

/**
 * @author wyatt
 * 
 * Responsible for displaying a list of counters allowing users to sort, add new
 * and increment from this activity window
 *
 */
public class CounterStatisticsActivity extends FragmentActivity implements
		ActionBar.TabListener {
	private CounterListController counterListController;

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	CounterStatisticsPagerAdapter counterListStatisticsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;
	
	private void getRenameCounterDialog() {
		// From the Android Documentation: http://developer.android.com/guide/topics/ui/dialogs.html
		
		// Generate a alert dialog builder
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		
		// Get the layout inflater
	    LayoutInflater inflater = this.getLayoutInflater();
	    
		// Setup view and chain together various setter methods to set the dialog characteristics
		builder.setView(inflater.inflate(R.layout.action_name_counter, null))
			.setNegativeButton("Cancel", null)
			.setPositiveButton("Rename Counter", new DialogInterface.OnClickListener() {
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
	            	   // Update the name
	            	   counterListController.getSelectedCounter().setName(counterName);
	            	   
	            	   // Finish the activity
	            	   finish();
            	   }
               }
	        });

		// Get the AlertDialog from create()
		AlertDialog dialog = builder.create();
		
		// Show it
		dialog.show();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_counter_list_stats);
		
		// Set up the action bar.
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		// Show the Up button in the action bar.
		actionBar.setDisplayHomeAsUpEnabled(true);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		counterListStatisticsPagerAdapter = new CounterStatisticsPagerAdapter(
				this, getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(counterListStatisticsPagerAdapter);

		// When swiping between different sections, select the corresponding
		// tab. We can also use ActionBar.Tab#select() to do this if we have
		// a reference to the Tab.
		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						actionBar.setSelectedNavigationItem(position);
					}
				});

		// For each of the sections in the app, add a tab to the action bar.
		for (int i = 0; i < counterListStatisticsPagerAdapter.getCount(); i++) {
			// Create a tab with text corresponding to the page title defined by
			// the adapter. Also specify this Activity object, which implements
			// the TabListener interface, as the callback (listener) for when
			// this tab is selected.
			actionBar.addTab(actionBar.newTab()
					.setText(counterListStatisticsPagerAdapter.getPageTitle(i))
					.setTabListener(this));
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.counter_list_stats, menu);
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
		case R.id.action_reset:
			
			// Reset the counter
			this.counterListController.resetSelectedCounter();
			
			// End the current detail activity
			finish();
			
			// We handled the menu call
			return true;
		case R.id.action_delete:
			// Remove the selected counter
			this.counterListController.removeSelectedCounter();
			
			// End the current detail activity
			finish();
			
			// We handled the menu call
			return true;
		case R.id.action_rename:
			// Pop-up for renaming the counter
			getRenameCounterDialog();
			
			// We handled the menu call
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/* (non-Javadoc)
	 * @see android.support.v4.app.FragmentActivity#onResume()
	 */
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		// Create the controller
		this.counterListController = CounterListController.shared(getApplicationContext());
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	@Override
	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		// When the given tab is selected, switch to the corresponding page in
		// the ViewPager.
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

}
