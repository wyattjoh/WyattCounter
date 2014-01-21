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

import android.content.Context;

/**
 * @author wyatt
 *
 */
public class CounterList implements CounterListInterface {
	private static final String DATABASEFILE = "counters.db";
	private ArrayList<Counter> counterModels;
	private Context context;

	public CounterList(Context context) {
		this.context = context;
		
		this.load();
	}
	
	private void init() {
		this.counterModels = new ArrayList<Counter>();	
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
	
	public void removeCounter(int index) {
		this.counterModels.remove(index);
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
}
