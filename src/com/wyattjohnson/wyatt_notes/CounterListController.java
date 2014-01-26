/**
 * 
 */
package com.wyattjohnson.wyatt_notes;

import android.content.Context;

/**
 * @author wyatt
 *
 * Responsible for handling the counter list and selected items
 * as well as managing the global data state
 *
 */
public class CounterListController extends CounterList {
	private static CounterListController singleton = null;
	
	public static CounterListController shared(Context context) {
		if (CounterListController.singleton == null) {
			CounterListController.singleton = new CounterListController(context);
		}
		return CounterListController.singleton;
	}

	private Counter selectedCounter = null;
	private int selectedCounterIndex = -1;
	
	private CounterListController(Context context) {
		super(context);
	}
	
	/**
	 * Resets the selected counter
	 */
	public void resetSelectedCounter() {
		if (selectedCounterIndex < 0) { 
			// No counter selected
			return;
		}
		
		resetCounterAtIndex(selectedCounterIndex);
	}
	
	/**
	 * @return the currently selected counter
	 */
	public Counter getSelectedCounter() {
		return this.selectedCounter;
	}

	/**
	 * Removes the currently selected counter
	 */
	public void removeSelectedCounter() {
		if (selectedCounterIndex < 0) { 
			// No counter selected
			return;
		}

		deleteCounterAtIndex(selectedCounterIndex);
		selectedCounterIndex = -1;		
	}

	/**
	 * @param index
	 */
	public void setSelectedCounterForIndex(int index) {
		this.selectedCounterIndex = index;
		this.selectedCounter = this.getCounterAtIndex(index);
	}
}
