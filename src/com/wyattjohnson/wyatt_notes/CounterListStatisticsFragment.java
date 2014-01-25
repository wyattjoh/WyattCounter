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
	
	/**
	 * @return the current activity as a CounterListStatisticsActivity
	 */
	private CounterListStatisticsActivity currentActivity() {
		return (CounterListStatisticsActivity)getActivity();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_counter_list_stats, container, false);
				
		// Set name
		getActivity().setTitle(currentActivity().getSelectedCounter().getName() + " Stats");
		
		int sectionNumber = getArguments().getInt(ARG_SECTION_NUMBER);
		
		// Get the right fragment data
		switch (sectionNumber) {
			case 1:
				populateFragment(rootView, currentActivity().getStats().getHourlyStats());
				break;
			case 2:
				populateFragment(rootView, currentActivity().getStats().getDailyStats());
				break;
			case 3:
				populateFragment(rootView, currentActivity().getStats().getWeeklyStats());
				break;
			case 4:
				populateFragment(rootView, currentActivity().getStats().getMonthlyStats());
				break;
		}
		
		// Return the view
		return rootView;
	}
	
	private void populateFragment(View rootView, ArrayList<String[]> resource) {
		// Build Adapter
		ArrayAdapter<String[]> adapter = new CounterListStatisticsAdapter(this, resource);
		
		// Get the list view
		ListView list = (ListView) rootView.findViewById(R.id.fragmentListView);
		
		// Set the adapter
		list.setAdapter(adapter);
	}
}