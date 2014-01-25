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
 */
public class CounterList implements CounterListInterface {
	private static final String DATABASEFILE = "counters.save";
	private ArrayList<Counter> counterModels;
	private Context context;

	public CounterList(Context context) {
		this.context = context;
		
		this.load();
	}
	
	private void init() {
		this.counterModels = new ArrayList<Counter>();	
	}
	
	public void sortList() {
		Collections.sort(counterModels, new Comparator<Counter>() {
			public int compare(Counter arg0, Counter arg1) {
				// TODO Auto-generated method stub
				return arg1.getCount() - arg0.getCount();
			}
		});
	}
	
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
	
	private void save() {
		try {
			FileOutputStream fileOut = context.openFileOutput(CounterList.DATABASEFILE, Context.MODE_PRIVATE);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(this.counterModels);
			out.close();
			fileOut.close();
			System.out.println("Objects saved.");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	private void load() {
		try {
			File fh = new File(context.getFilesDir(), CounterList.DATABASEFILE);
			
			// Check if the file exists
			if (!fh.exists()) {
				// Doesn't exist, setup a blank one
				System.out.println("No files saved, creating a new one!");
				this.init();
				return;
			}
			
			// Load the file
			FileInputStream fileIn = context.openFileInput(CounterList.DATABASEFILE);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			this.counterModels = (ArrayList<Counter>) in.readObject();
			in.close();
			fileIn.close();
			System.out.println("Objects loaded.");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassCastException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Counter getCounter(int index) {
		return this.counterModels.get(index);
	}

	@Override
	public ArrayList<Counter> getList() {
		return counterModels;
	}
	
	public int getListSize() {
		return counterModels.size();
	}

	@Override
	public void incrementCounterAtIndex(int index) {
		Counter theCounter = getCounter(index);
		theCounter.addCount();
		
		this.save();
	}

	@Override
	public void resetCounterAtIndex(int index) {
		Counter theCounter = getCounter(index);
		theCounter.reset();
		
		this.save();
	}

	@Override
	public void deleteCounterAtIndex(int index) {
		this.counterModels.remove(index);
		
		this.save();
	}
}
