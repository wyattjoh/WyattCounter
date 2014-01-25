/**
 * 
 */
package com.wyattjohnson.wyatt_notes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author wyatt
 *
 */
public class Counter implements Serializable {	
	private static final long serialVersionUID = 8011330310685327200L;
	private String name;
	private ArrayList<Long> history;

	/**
	 * @param name
	 * @param history
	 */
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Long> getHistory() {
		return history;
	}

	public void addCount() {
		Calendar theCalendarDate = GregorianCalendar.getInstance();
		theCalendarDate.setTime(new Date());
		this.history.add(theCalendarDate.getTimeInMillis());
	}
	
	public Integer getCount() {
		return this.history.size();
	}
	
	public void reset() {
		this.history.clear();
	}
}
