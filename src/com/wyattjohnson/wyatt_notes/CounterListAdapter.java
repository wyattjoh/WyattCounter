/**
 * 
 */
package com.wyattjohnson.wyatt_notes;

import com.wyattjohnson.wyatt_notes.R;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * @author wyatt
 *
 * Responsible for managing the list of counters from CounterList
 *
 */
class CounterListAdapter extends ArrayAdapter<Counter> {
	/**
	 * @author wyatt
	 *
	 * Increments the counter when an area is clicked
	 *
	 */
	private final class IncrementListener implements View.OnClickListener {
		@Override
		public void onClick(View v) {
		    // do you work here
			LinearLayout clickAreaLayout = (LinearLayout) v.findViewById(R.id.counterIncrementArea);
			int index = ((Integer)clickAreaLayout.getTag()).intValue();
			
			// increment the counter
			counterListController.incrementCounterAtIndex(index);
			
			// Refresh the view
			counterListActivity.refreshList(false);
		 }
	}

	private CounterListActivity counterListActivity;
	private CounterListController counterListController;

	public CounterListAdapter(CounterListActivity counterListActivity, CounterListController counterListController) {
		super(counterListActivity, R.layout.activity_main_list_cell, counterListController.getList());
		
		this.counterListActivity = counterListActivity;
		this.counterListController = counterListController;
	}

	/* (non-Javadoc)
	 * @see android.widget.ArrayAdapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// Make sure we have a view to work with (may have been null)
		View itemView = convertView;
		
		if (itemView == null) {
			itemView = counterListActivity.getLayoutInflater().inflate(R.layout.activity_main_list_cell, parent, false);
		}
		
		// Populate the view
		populateView(itemView, position);
		
		return itemView;
	}

	/**
	 * 
	 * Populates view with current counter information
	 * 
	 * @param itemView
	 * @param position
	 */
	private void populateView(View itemView, int position) {
		// Find the Counter to work with
		Counter currentCounter = counterListController.getCounterAtIndex(position);
		
		// Fill the view
		TextView titleText = (TextView) itemView.findViewById(R.id.counterTitle);
		titleText.setText(currentCounter.getName());
		
		TextView counterText = (TextView) itemView.findViewById(R.id.counterCount);
		counterText.setText(currentCounter.getCount().toString());
		
		LinearLayout clickAreaLayout = (LinearLayout) itemView.findViewById(R.id.counterIncrementArea);
		clickAreaLayout.setTag(position);
		
		// Setup events
		clickAreaLayout.setOnClickListener(new IncrementListener());
		
		ImageButton detailsButton = (ImageButton) itemView.findViewById(R.id.detailsButton);
		
		Integer positionInteger = Integer.valueOf(position);
		detailsButton.setTag(positionInteger);
	}
	
}