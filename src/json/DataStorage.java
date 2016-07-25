package json;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import domain.Character;
import domain.Job;

// THIS CLASS IS A SINGLETON
public class DataStorage {
	private static DataStorage instance = null;
	private Character[] chars;					// Array used for JSON parsing characters
	private Map<String, Character> characters;	// Map based off of the chars Array. <key, value> = <character name, Character object>
	private Job[] jobArray;						// Array used for JSON parsing jobs
	private Map<String, Job> jobs;				// Map based off of the jobArray Array. <key, value> = <job name, Job object>
	private ArrayList<String> specialClasses; 	// ArrayList to hold the names of any special classes
	
	// establish singleton pattern - only one one instance of DataStorage to exist in the project
	public static DataStorage getInstance() {
		if(instance == null) {
			instance = new DataStorage();
		}
		return instance;
	}
	
	// getters
	public Map<String, Character> getCharacters() {
		return characters;
	}
	
	public Map<String, Job> getJobs() {
		return jobs;
	}
	
	public ArrayList<String> getSpecialClasses() {
		return specialClasses;
	}
	
	// methods
	// parsing for characters.json
	public void ParseJsonCharacters() {
		try
		{
			Reader reader = new InputStreamReader(DataStorage.class.getResourceAsStream("/resources/characters.json"), "UTF-8");
			Gson gson = new GsonBuilder().create();
			chars = gson.fromJson(reader, Character[].class);
			characters = new HashMap<String, Character>();
			for(int i = 0; i < chars.length; i++) {
				// parse array data here to properly set up getters - See Character.class ParseDataArray() method for more info
				chars[i].parseArrayData();
				characters.put(chars[i].getName(), chars[i]);
			}
		}
		catch (IOException e)
		{
			System.err.println("Caught IOException: " + e.getMessage());
		}
	}
	
	public void ParseJsonJobs() {
		try
		{
			Reader reader = new InputStreamReader(DataStorage.class.getResourceAsStream("/resources/jobs.json"), "UTF-8");
			Gson gson = new GsonBuilder().create();
			jobArray = gson.fromJson(reader, Job[].class);
			jobs = new HashMap<String, Job>();
			specialClasses = new ArrayList<String>();
			for(int i = 0; i < jobArray.length; i++) {
				// parse array data here to properly set up getters - See Job.class ParseDataArray() method for more info
				jobArray[i].parseArrayData();
				// fill the specialClasses array
				if(jobArray[i].getIsSpecial())
					specialClasses.add(jobArray[i].getName());
				jobs.put(jobArray[i].getName(), jobArray[i]);
			}
		}
		catch (IOException e)
		{
			System.err.println("Caught IOException: " + e.getMessage());
		}
	}
	
	// Sets the based stats of all characters based on the path we are searching
	// This method is in DataStorage rather than Character as it should be called as the user changes the path they are checking stats for
	public void setBaseStats(String path){
		for(Character c : characters.values()) {
			c.getDesiredBaseStats(path);
		}
	}
	
	
	// Printing methods for testing purposes
	public void printAllCharacters() {
		for(Character c: chars) {
			System.out.println(c);
			System.out.println();
		}
	}
	
	public void printAllJobs() {
		for(Job j: jobArray) {
			System.out.println(j);
			System.out.println();
		}
		
		String theSpecialClasses = Arrays.toString(specialClasses.toArray());
		System.out.println("Printing specialClasses array: " + theSpecialClasses);
		System.out.println();
	}
}
