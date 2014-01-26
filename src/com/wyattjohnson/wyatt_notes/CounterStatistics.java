package com.wyattjohnson.wyatt_notes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import android.annotation.SuppressLint;
import android.util.LongSparseArray;

/**
 * @author wyatt
 * 
 * Responsible for generating statistics data from
 * a counter
 *
 */
public class CounterStatistics {
	private Counter theCounter;

	private ArrayList<String[]> hourlyStats;
	private ArrayList<String[]> dailyStats;
	private ArrayList<String[]> weeklyStats;
	private ArrayList<String[]> monthlyStats;

	public CounterStatistics(Counter theCounter) {
		this.theCounter = theCounter;
		
		// Generate the stats
		generateStats();
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
			
			// Save the data as a string
			String statsEntry[] = new String[2];
			statsEntry[0] = preFormat + theDateString;
			statsEntry[1] = Integer.toString(count);
			
			arrayListStats.add(statsEntry);
		}
		
		return arrayListStats;
	}
	
	/**
	 * 
	 * Generates statistics via binning in SparseArray's and
	 * formamtting into string arrays
	 * 
	 */
	private void generateStats() {
		LongSparseArray<Integer> hourlySparseArray = new LongSparseArray<Integer>();
		LongSparseArray<Integer> dailySparseArray = new LongSparseArray<Integer>();
		LongSparseArray<Integer> weeklySparseArray = new LongSparseArray<Integer>();
		LongSparseArray<Integer> monthlySparseArray = new LongSparseArray<Integer>();
		
		Calendar calendar = Calendar.getInstance();
		
		for (long theDateMillis : this.theCounter.getHistory()) {
			calendar.setTime(new Date(theDateMillis));
			
			calendar.set(Calendar.MILLISECOND, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MINUTE, 0);
			
			setSparseArray(hourlySparseArray, calendar);
			
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			
			setSparseArray(dailySparseArray, calendar);
			
			int monthOfYear = calendar.get(Calendar.MONTH);
			
			calendar.set(Calendar.DAY_OF_WEEK, 1);
			
			setSparseArray(weeklySparseArray, calendar);
			
			calendar.set(Calendar.MONTH, monthOfYear);
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			
			setSparseArray(monthlySparseArray, calendar);
		}
		
		this.hourlyStats = formatSparseArray(hourlySparseArray, "MMM d, yyyy h:mma", "");
		this.dailyStats = formatSparseArray(dailySparseArray, "MMM d, yyyy", "");
		this.weeklyStats = formatSparseArray(weeklySparseArray, "MMM d, yyyy", "Week of ");
		this.monthlyStats = formatSparseArray(monthlySparseArray, "MMMM, yyyy", "Month of ");
	}
	
	public ArrayList<String[]> getDailyStats() {
		return this.dailyStats;
	}
	
	public ArrayList<String[]> getHourlyStats() {
		return this.hourlyStats;
	}
	
	public ArrayList<String[]> getMonthlyStats() {
		return this.monthlyStats;
	}
	
	public ArrayList<String[]> getWeeklyStats() {
		return this.weeklyStats;
	}

	/**
	 * 
	 * Finishes some repetitive content
	 * 
	 * @param theArray
	 * @param calendarDate
	 */
	private void setSparseArray(LongSparseArray<Integer> theArray, Calendar calendarDate) {
		Long timeIndex = calendarDate.getTimeInMillis();
		
		Integer dateStats = theArray.get(timeIndex);
		
		if (dateStats == null) {
			dateStats = 0;
		}
		
		dateStats = dateStats + 1;
		
		theArray.put(timeIndex, dateStats);
	}
}