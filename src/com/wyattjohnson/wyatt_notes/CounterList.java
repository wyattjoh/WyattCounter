/**
 * 
 */
package com.wyattjohnson.wyatt_notes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import android.content.Context;

/**
 * @author wyatt
 *
 * Used to store and manage the list of Counters. Responsible for saving and
 * loading the data from the filesystem.
 *
 */
public abstract class CounterList {
	private static final String DATABASEFILE = "counters.save";
	private ArrayList<Counter> counterModels;
	private Context context;

	public CounterList(Context context) {
		this.context = context;
		
		this.load();
	}
	
	/**
	 * @param theName
	 * @return the newly created counter
	 */
	public Counter addCounter(String theName) {
		// Create a counter with the name
		Counter theCounter = new Counter(theName, null);
		
		// Add it to the list
		this.counterModels.add(theCounter);
		
		// Save the list now!
		this.save();
		
		// Return a reference to the counter
		return theCounter;
	}
	
	/**
	 * 
	 * Deletes a counter at a location in the array
	 * 
	 * @param index
	 */
	public void deleteCounterAtIndex(int index) {
		this.counterModels.remove(index);
		
		this.save();
	}
	
	/**
	 * @param index
	 * @return the counter requested for index
	 */
	public Counter getCounterAtIndex(int index) {
		return this.counterModels.get(index);
	}
	
	/**
	 * @return the list of counters
	 */
	public ArrayList<Counter> getList() {
		return counterModels;
	}

	/**
	 * @return the size of the list
	 */
	public int getListSize() {
		return counterModels.size();
	}

	/**
	 * 
	 * Increments the counter with index
	 * 
	 * @param index
	 */
	public void incrementCounterAtIndex(int index) {
		Counter theCounter = getCounterAtIndex(index);
		theCounter.addCount();
		
		this.save();
	}

	/**
	 * 
	 * Resets the counter at index
	 * 
	 * @param index
	 */
	public void resetCounterAtIndex(int index) {
		Counter theCounter = getCounterAtIndex(index);
		theCounter.resetCounts();
		
		this.save();
	}

	/**
	 * 
	 * Sorts the list according to the count
	 * 
	 */
	public void sortList() {
		Collections.sort(counterModels, new Comparator<Counter>() {
			@Override
			public int compare(Counter arg0, Counter arg1) {
				return arg1.getCount() - arg0.getCount();
			}
		});
	}

	/**
	 * 
	 * Saves the current list of counters to disk
	 * 
	 */
	private void save() {
		try {
			FileOutputStream fileOut = context.openFileOutput(CounterList.DATABASEFILE, Context.MODE_PRIVATE);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(this.counterModels);
			out.close();
			fileOut.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * Loads the list of counters from disk
	 * 
	 */
	@SuppressWarnings("unchecked")
	private void load() {
		try {
			File fh = new File(context.getFilesDir(), CounterList.DATABASEFILE);
			
			// Check if the file exists
			if (!fh.exists()) {				
				// Create a new ArrayList
				this.counterModels = new ArrayList<Counter>();
				
				// Break out! We have nothing to save!
				return;
			}
			
			// Load the file
			FileInputStream fileIn = context.openFileInput(CounterList.DATABASEFILE);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			this.counterModels = (ArrayList<Counter>) in.readObject();
			in.close();
			fileIn.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassCastException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
