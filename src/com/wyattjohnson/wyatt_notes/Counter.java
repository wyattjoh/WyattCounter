/**
 * 
 */
package com.wyattjohnson.wyatt_notes;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import android.annotation.SuppressLint;
import android.util.LongSparseArray;

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
		
	private void setSparseArray(LongSparseArray<Integer> theArray, Long timeIndex) {
		Integer dateStats = theArray.get(timeIndex);
		
		if (dateStats == null) {
			dateStats = 0;
		}
		
		dateStats = dateStats + 1;
		
		theArray.put(timeIndex, dateStats);
	}
	
	@SuppressLint("SimpleDateFormat")
	private ArrayList<String[]> formatSparseArray(LongSparseArray<Integer> statsLongSparseArray, String format, String preFormat) {
		ArrayList<String[]> arrayListStats = new ArrayList<String[]>();
		
		long timeInMillis = 0;
		int count = 0;
		for(int i = 0; i < statsLongSparseArray.size(); i++) {
			timeInMillis = statsLongSparseArray.keyAt(i);
		   
			// get the object by the key.
			count = statsLongSparseArray.get(timeInMillis);
		   
			// Date to string http://stackoverflow.com/questions/4772425/format-date-in-java
			String theDateString = new SimpleDateFormat(format).format(new Date(timeInMillis));
			
			String statsEntry[] = new String[2];
			statsEntry[0] = preFormat + theDateString;
			statsEntry[1] = Integer.toString(count);
			
			arrayListStats.add(statsEntry);
		}
		
		return arrayListStats;
	}
	
	public ArrayList<String[]> getMonthlyStats() {
		LongSparseArray<Integer> statsLongSparseArray = new LongSparseArray<Integer>();
		Calendar calendar = GregorianCalendar.getInstance();
		
		for (long theDateMillis : this.history) {
			calendar.setTime(new Date(theDateMillis));
			
			// http://stackoverflow.com/questions/1908387/java-date-cut-off-time-information
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			Long timeIndex = calendar.getTimeInMillis();
			
			setSparseArray(statsLongSparseArray, timeIndex);
		}
		
		return formatSparseArray(statsLongSparseArray, "MMMM, yyyy", "Month of ");
	}
	
	public ArrayList<String[]> getWeeklyStats() {
		LongSparseArray<Integer> statsLongSparseArray = new LongSparseArray<Integer>();
		Calendar calendar = GregorianCalendar.getInstance();
		
		for (long theDateMillis : this.history) {
			calendar.setTime(new Date(theDateMillis));
			
			// http://stackoverflow.com/questions/1908387/java-date-cut-off-time-information
			calendar.set(Calendar.DAY_OF_WEEK, 1);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			Long timeIndex = calendar.getTimeInMillis();
			
			setSparseArray(statsLongSparseArray, timeIndex);
		}
		
		return formatSparseArray(statsLongSparseArray, "MMM d, yyyy", "Week of ");
	}
	
	public ArrayList<String[]> getDailyStats() {
		LongSparseArray<Integer> statsLongSparseArray = new LongSparseArray<Integer>();
		Calendar calendar = GregorianCalendar.getInstance();
		
		for (long theDateMillis : this.history) {
			calendar.setTime(new Date(theDateMillis));
			
			// http://stackoverflow.com/questions/1908387/java-date-cut-off-time-information
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			Long timeIndex = calendar.getTimeInMillis();
			
			setSparseArray(statsLongSparseArray, timeIndex);
		}
		
		return formatSparseArray(statsLongSparseArray, "MMM d, yyyy", "");
	}

	public ArrayList<String[]> getHourlyStats() {
		LongSparseArray<Integer> statsLongSparseArray = new LongSparseArray<Integer>();
		Calendar calendar = GregorianCalendar.getInstance();
		
		for (long theDateMillis : this.history) {
			calendar.setTime(new Date(theDateMillis));
			
			// http://stackoverflow.com/questions/1908387/java-date-cut-off-time-information
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			Long timeIndex = calendar.getTimeInMillis();
			
			setSparseArray(statsLongSparseArray, timeIndex);
		}
		
		return formatSparseArray(statsLongSparseArray, "MMM d, yyyy h:mma", "");
	}
}
