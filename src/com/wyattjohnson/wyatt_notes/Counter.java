/**
 * 
 */
package com.wyattjohnson.wyatt_notes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
/**
 * @author wyatt
 *
 * Stores the data for an individual Counter
 *
 */
public class Counter implements Serializable {	
	private static final long serialVersionUID = 8011330310685327200L;
	private String name;
	private ArrayList<Long> history;

	public Counter(String name, ArrayList<Long> history) {
		super();
		this.name = name;
		
		if (history == null) {
			this.history = new ArrayList<Long>();
		}
		else {
			this.history = history;
		}
	}

	/**
	 * Adds a new count + date info to the Counter's ArrayList
	 */
	public void addCount() {
		Calendar theCalendarDate = Calendar.getInstance();
		theCalendarDate.setTime(new Date());
		this.history.add(theCalendarDate.getTimeInMillis());
	}

	/**
	 * @return Current count
	 */
	public Integer getCount() {
		return this.history.size();
	}

	/**
	 * @return The history of the Counter
	 */
	public ArrayList<Long> getHistory() {
		return history;
	}

	/**
	 * @return Current name of the Counter
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Clears the history of the Counter
	 */
	public void resetCounts() {
		this.history.clear();
	}
	
	/**
	 * Sets the current name of the counter
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
}
