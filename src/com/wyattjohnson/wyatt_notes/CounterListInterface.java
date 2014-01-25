package com.wyattjohnson.wyatt_notes;

import java.util.ArrayList;

public interface CounterListInterface {
	public Counter addCounter(String aName);
	public Counter getCounter(int index);
	public ArrayList<Counter> getList();
	public void sortList();
	public int getListSize();
	
	public void incrementCounterAtIndex(int index);
	public void resetCounterAtIndex(int index);
	public void deleteCounterAtIndex(int index);
}
