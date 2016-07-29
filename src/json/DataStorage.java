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
	
	// Enums for accessing the arrays of: Character BaseStats, Character MaxMods, Character Growths
	// Job BaseStats, Job MaxStats, and JobGrowths
	public static enum CharBaseStatsEnums {
		LEVEL, HP, STR, MAG, SKL, SPD, LCK, DEF, RES
	}
	public static enum CharMaxModsEnums {
		LEVEL, STR, MAG, SKL, SPD, LCK, DEF, RES
	}
	public static enum CharGrowthsEnums {
		HP, STR, MAG, SKL, SPD, LCK, DEF, RES
	}
	public static enum JobBaseStatsEnums {
		HP, STR, MAG, SKL, SPD, LCK, DEF, RES
	}
	public static enum JobMaxStatsEnums {
		LEVEL, HP, STR, MAG, SKL, SPD, LCK, DEF, RES
	}
	public static enum JobGrowthsEnums {
		HP, STR, MAG, SKL, SPD, LCK, DEF, RES
	}
	
	private static DataStorage instance = null;	// DataStorage singleton instance
	private Character[] chars;					// Array used for JSON parsing characters
	private Map<String, Character> characters;	// Map based off of the chars Array. <key, value> = <character name, Character object>
	private Job[] jobArray;						// Array used for JSON parsing jobs
	private Map<String, Job> jobs;				// Map based off of the jobArray Array. <key, value> = <job name, Job object>
	private ArrayList<String> specialClasses; 	// ArrayList to hold the names of any special classes
	
	// Arrays representing locked Marriage Options - for example, Xander and Leo cannot marry their sisters (Camilla and Elise)
	private final String[] AVATAR_LOCKED = {"Gunter", "Shura", "Izana", "Flora", "Scarlet", "Yukimura", "Fuga", "Anna"};
	private final String[] NOHR_ROYALS_LOCKED = {"Camilla", "Elise"};
	private final String[] HOSHIDO_ROYALS_LOCKED = {"Hinoka", "Sakura"};
	
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
	
	public String[] getAVATAR_LOCKED() {
		return AVATAR_LOCKED;
	}

	public String[] getNOHR_ROYALS_LOCKED() {
		return NOHR_ROYALS_LOCKED;
	}

	public String[] getHOSHIDO_ROYALS_LOCKED() {
		return HOSHIDO_ROYALS_LOCKED;
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
				characters.put(chars[i].getName(), chars[i]);
			}
			reader.close();
		}
		catch (IOException e)
		{
			System.err.println("Caught IOException: " + e.getMessage());
		}
	}
	
	// parsing for jobs.json
	public void ParseJsonJobs() {
		try
		{
			Reader reader = new InputStreamReader(DataStorage.class.getResourceAsStream("/resources/jobs.json"), "UTF-8");
			Gson gson = new GsonBuilder().create();
			jobArray = gson.fromJson(reader, Job[].class);
			jobs = new HashMap<String, Job>();
			specialClasses = new ArrayList<String>();
			for(int i = 0; i < jobArray.length; i++) {
				// fill the specialClasses array
				if(jobArray[i].getIsSpecial())
					specialClasses.add(jobArray[i].getName());
				jobs.put(jobArray[i].getName(), jobArray[i]);
			}
			reader.close();
		}
		catch (IOException e)
		{
			System.err.println("Caught IOException: " + e.getMessage());
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
