package com.wyattjohnson.wyatt_notes;

import java.util.ArrayList;

public interface CounterListInterface {
	public Counter addCounter(String aName);
	public void removeCounter(int index);
	public Counter getCounter(int index);
	public ArrayList<Counter> getList();
	public void sortList();
}
