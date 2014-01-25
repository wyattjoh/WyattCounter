/**
 * 
 */
package com.wyattjohnson.wyatt_notes;

import java.util.Locale;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

	/**
	 * 
	 */
	private final CounterListStatisticsActivity counterListStatisticsActivity;

	public SectionsPagerAdapter(CounterListStatisticsActivity counterListStatisticsActivity, FragmentManager fm) {
		super(fm);
		this.counterListStatisticsActivity = counterListStatisticsActivity;
	}

	@Override
	public int getCount() {
		// Show 4 total pages.
		return 4;
	}

	@Override
	public Fragment getItem(int position) {
		// getItem is called to instantiate the fragment for the given page.
		// Return a DummySectionFragment (defined as a static inner class
		// below) with the page number as its lone argument.
		Fragment fragment = new CounterListStatisticsFragment();
		Bundle args = new Bundle();
		args.putInt(CounterListStatisticsFragment.ARG_SECTION_NUMBER, position + 1);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		Locale l = Locale.getDefault();
		switch (position) {
		case 0:
			return this.counterListStatisticsActivity.getString(R.string.title_section1).toUpperCase(l);
		case 1:
			return this.counterListStatisticsActivity.getString(R.string.title_section2).toUpperCase(l);
		case 2:
			return this.counterListStatisticsActivity.getString(R.string.title_section3).toUpperCase(l);
		case 3:
			return this.counterListStatisticsActivity.getString(R.string.title_section4).toUpperCase(l);
		}
		return null;
	}
}