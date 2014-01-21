/**
 * 
 */
package com.wyattjohnson.wyatt_notes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author wyatt
 *
 */
public class Counter implements Serializable {
	private String name;
	private ArrayList<Date> history;

	/**
	 * @param name
	 * @param history
	 */
	public Counter(String name, ArrayList<Date> history) {
		super();
		this.name = name;
		
		if (history == null) {
			this.history = new ArrayList<Date>();
		}
		else {
			this.history = history;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Date> getHistory() {
		return history;
	}

	public void addCount() {
		this.history.add(new Date());
	}
	
	public Integer getCount() {
		return this.history.size();
	}
	
	public void reset() {
		this.history.clear();
	}
}
