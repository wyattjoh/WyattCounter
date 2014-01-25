package com.wyattjohnson.wyatt_notes;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * A dummy fragment representing a section of the app, but that simply
 * displays dummy text.
 */
@SuppressLint("ValidFragment")
public class CounterListStatisticsFragment extends Fragment {

	/**
	 * The fragment argument representing the section number for this
	 * fragment.
	 */
	public static final String ARG_SECTION_NUMBER = "section_number";
	
	public CounterListStatisticsFragment() {
		
	}
	
	private CounterListStatisticsActivity currentActivity() {
		return (CounterListStatisticsActivity)getActivity();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_counter_list_stats, container, false);
				
		// Set name
		getActivity().setTitle(currentActivity().getSelectedCounter().getName() + " Stats");
		
		int sectionNumber = getArguments().getInt(ARG_SECTION_NUMBER);
		
		switch (sectionNumber) {
			case 1:
				return setupSummaryView(rootView, currentActivity().getStats().getHourlyStats());
			case 2:
				return setupSummaryView(rootView, currentActivity().getStats().getDailyStats());
			case 3:
				return setupSummaryView(rootView, currentActivity().getStats().getWeeklyStats());
			case 4:
				return setupSummaryView(rootView, currentActivity().getStats().getMonthlyStats());
		}
		
		// Should never happen
		return rootView;
	}
	
	private View setupSummaryView(View rootView, ArrayList<String[]> resource) {
		// Build Adapter
		ArrayAdapter<String[]> adapter = new MyListAdapter(resource);
		
		// Get the list view
		ListView list = (ListView) rootView.findViewById(R.id.fragmentListView);
		
		// Set the adapter
		list.setAdapter(adapter);
		
		return rootView;
	}
	
	private class MyListAdapter extends ArrayAdapter<String[]> {
		public MyListAdapter(ArrayList<String[]> statsArray) {
			super(getActivity(), R.layout.fragment_counter_list_stats_cell, statsArray);
			// TODO Auto-generated constructor stub
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// Make sure we have a view to work with (may have been null)
			View itemView = convertView;
			
			if (itemView == null) {
				itemView = getActivity().getLayoutInflater().inflate(R.layout.fragment_counter_list_stats_cell, parent, false);
			}
			
			String theStat[] = this.getItem(position);
			
			TextView theDateView = (TextView) itemView.findViewById(R.id.dateField);
			theDateView.setText(theStat[0]);
			
			TextView theCount = (TextView) itemView.findViewById(R.id.countField);
			theCount.setText(theStat[1]);

			return itemView;
		}
		
	}
}