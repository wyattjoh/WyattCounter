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
//	private Context context;
	private Counter selectedCounter;
	
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
		this.selectedCounter = this.getCounter(index);
	}
	
	public Counter getSelectedCounter() {
		return this.selectedCounter;
	}

	public Counter addCounter(String aName) {
		// TODO Auto-generated method stub
		return this.counterList.addCounter(aName);
	}

	public void removeCounter(int index) {
		// TODO Auto-generated method stub
		this.counterList.removeCounter(index);
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
}
