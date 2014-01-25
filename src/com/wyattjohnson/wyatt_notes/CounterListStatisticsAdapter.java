/**
 * 
 */
package com.wyattjohnson.wyatt_notes;

import java.util.ArrayList;

import com.wyattjohnson.wyatt_notes.R;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

class CounterListStatisticsAdapter extends ArrayAdapter<String[]> {
	/**
	 * 
	 */
	private CounterListStatisticsFragment counterListStatisticsFragment;

	public CounterListStatisticsAdapter(CounterListStatisticsFragment counterListStatisticsFragment, ArrayList<String[]> statsArray) {
		super(counterListStatisticsFragment.getActivity(), R.layout.fragment_counter_list_stats_cell, statsArray);
		// TODO Auto-generated constructor stub
		this.counterListStatisticsFragment = counterListStatisticsFragment;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// Make sure we have a view to work with (may have been null)
		View itemView = convertView;
		
		if (itemView == null) {
			itemView = this.counterListStatisticsFragment.getActivity().getLayoutInflater().inflate(R.layout.fragment_counter_list_stats_cell, parent, false);
		}
		
		// Populate the view
		populateView(itemView, position);

		return itemView;
	}

	/**
	 * 
	 * Populates the view
	 * 
	 * @param itemView
	 * @param position
	 */
	private void populateView(View itemView, int position) {
		String theStat[] = this.getItem(position);
		
		// Set the title
		TextView theDateView = (TextView) itemView.findViewById(R.id.dateField);
		theDateView.setText(theStat[0]);
		
		// Set the count
		TextView theCount = (TextView) itemView.findViewById(R.id.countField);
		theCount.setText(theStat[1]);
	}
	
}