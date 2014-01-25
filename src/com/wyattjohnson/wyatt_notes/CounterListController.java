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
	
	private CounterList counterList = null;
	private Counter selectedCounter = null;
	private int selectedCounterIndex;
	
	public static CounterListController shared(Context context) {
		if (CounterListController.singleton == null) {
			CounterListController.singleton = new CounterListController(context);
		}
		return CounterListController.singleton;
	}
	
	private CounterListController(Context context) {
		super();
//		this.context = context;
		this.counterList = new CounterList(context);
	}
	
	public void setSelectedCounterForIndex(int index) {
		this.selectedCounterIndex = index;
		this.selectedCounter = this.getCounter(index);
	}
	
	public Counter getSelectedCounter() {
		return this.selectedCounter;
	}
	
	public void removeSelectedCounter() {
		deleteCounterAtIndex(selectedCounterIndex);
		selectedCounterIndex = -1;		
	}

	public Counter addCounter(String aName) {
		// TODO Auto-generated method stub
		return this.counterList.addCounter(aName);
	}

	public Counter getCounter(int index) {
		// TODO Auto-generated method stub
		return this.counterList.getCounter(index);
	}

	@Override
	public ArrayList<Counter> getList() {
		return counterList.getList();
	}

	@Override
	public void sortList() {
		counterList.sortList();
	}

	@Override
	public void incrementCounterAtIndex(int index) {
		counterList.incrementCounterAtIndex(index);
	}

	@Override
	public void resetCounterAtIndex(int index) {
		counterList.resetCounterAtIndex(index);
	}

	@Override
	public void deleteCounterAtIndex(int index) {
		if (index < 0) { 
			// No counter selected
			return;
		}

		counterList.deleteCounterAtIndex(index);
	}

	@Override
	public int getListSize() {
		return counterList.getListSize();
	}
}
