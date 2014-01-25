/**
 * 
 */
package com.wyattjohnson.wyatt_notes;

import java.util.ArrayList;

import android.content.Context;

/**
 * @author wyatt
 *
 */
public class CounterListController implements CounterListInterface {
	private static CounterListController singleton = null;
	
	public static CounterListController shared(Context context) {
		if (CounterListController.singleton == null) {
			CounterListController.singleton = new CounterListController(context);
		}
		return CounterListController.singleton;
	}
	private CounterList counterList = null;
	private Counter selectedCounter = null;
	
	private int selectedCounterIndex = -1;
	
	private CounterListController(Context context) {
		super();
		this.counterList = new CounterList(context);
	}
	
	/* (non-Javadoc)
	 * @see com.wyattjohnson.wyatt_notes.CounterListInterface#addCounter(java.lang.String)
	 */
	@Override
	public Counter addCounter(String aName) {
		return this.counterList.addCounter(aName);
	}
	
	/* (non-Javadoc)
	 * @see com.wyattjohnson.wyatt_notes.CounterListInterface#deleteCounterAtIndex(int)
	 */
	@Override
	public void deleteCounterAtIndex(int index) {
		counterList.deleteCounterAtIndex(index);
	}
	
	/* (non-Javadoc)
	 * @see com.wyattjohnson.wyatt_notes.CounterListInterface#getCounter(int)
	 */
	@Override
	public Counter getCounter(int index) {
		// TODO Auto-generated method stub
		return this.counterList.getCounter(index);
	}

	/* (non-Javadoc)
	 * @see com.wyattjohnson.wyatt_notes.CounterListInterface#getList()
	 */
	@Override
	public ArrayList<Counter> getList() {
		return counterList.getList();
	}

	/* (non-Javadoc)
	 * @see com.wyattjohnson.wyatt_notes.CounterListInterface#getListSize()
	 */
	@Override
	public int getListSize() {
		return counterList.getListSize();
	}

	/**
	 * @return the currently selected counter
	 */
	public Counter getSelectedCounter() {
		return this.selectedCounter;
	}

	/* (non-Javadoc)
	 * @see com.wyattjohnson.wyatt_notes.CounterListInterface#incrementCounterAtIndex(int)
	 */
	@Override
	public void incrementCounterAtIndex(int index) {
		counterList.incrementCounterAtIndex(index);
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

	/* (non-Javadoc)
	 * @see com.wyattjohnson.wyatt_notes.CounterListInterface#resetCounterAtIndex(int)
	 */
	@Override
	public void resetCounterAtIndex(int index) {
		counterList.resetCounterAtIndex(index);
	}

	/**
	 * @param index
	 */
	public void setSelectedCounterForIndex(int index) {
		this.selectedCounterIndex = index;
		this.selectedCounter = this.getCounter(index);
	}

	/* (non-Javadoc)
	 * @see com.wyattjohnson.wyatt_notes.CounterListInterface#sortList()
	 */
	@Override
	public void sortList() {
		counterList.sortList();
	}
}
