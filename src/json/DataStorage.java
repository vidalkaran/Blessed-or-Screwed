package json;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;
import java.util.Collections;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import domain.Character;
import domain.ChildCharacter;
import domain.Avatar;
import domain.Kana;
import domain.Job;

// THIS CLASS IS A SINGLETON
public class DataStorage implements Serializable{
	
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
	
	// Arrays to hold the names of all of the possible boons and banes. These are final because they will never change
	private final String[] BOONS = {"Robust (HP)", "Strong (Str)", "Clever (Mag)", "Deft (Skl)", "Quick (Spd)", "Lucky (Lck)", "Sturdy (Def)", "Calm (Res)"}; 
	private final String[] BANES = {"Sickly (HP)", "Weak  (Str)", "Dull (Mag)", "Clumsy (Skl)", "Slow (Spd)", "Unlucky (Lck)", "Fragile (Def)", "Excitable (Res)"}; 
	
	private Map<String, Character> characters;			// Map for storing all characters. <key, value> = <character name, Character object>
	private ArrayList<String> conquestCharacters;		// ArrayList to hold all characters in the Conquest route
	private ArrayList<String> birthrightCharacters;		// ArrayList to hold all characters in the Birthright route
	private ArrayList<String> revelationsCharacters;	// ArrayList to hold all characters in the Revelations route
	
	private Job[] jobArray;						// Array used for JSON parsing jobs
	private Map<String, Job> jobs;				// Map based off of the jobArray Array. <key, value> = <job name, Job object>
	private ArrayList<String> specialClasses; 	// ArrayList to hold the names of any special classes
	private ArrayList<String> jobNames;					// ArrayList to hold all of the jobNames to be accessed for filling out the GUI list of jobs
	private ArrayList<String> promotedJobNames;			// ArrayList to hold all of the promoted classes to be accessed for filling out the GUI list of jobs
	private ArrayList<String> nonpromotedJobNames;		// ArrayList to hold all of the non-promoted classes to be accessed for filling out the GUI list of jobs
	
	// OUTDATED
	// Arrays representing locked Marriage Options - for example, Xander and Leo cannot marry their sisters (Camilla and Elise)
	private final String[] AVATAR_LOCKED = {"Gunter", "Shura", "Izana", "Flora", "Scarlet", "Yukimura", "Fuga", "Anna"};
	private final String[] NOHR_ROYALS_LOCKED = {"Camilla", "Elise"};
	private final String[] HOSHIDO_ROYALS_LOCKED = {"Hinoka", "Sakura"};
	
	// prevents instantiation of the class
	private DataStorage() {
		
	}
	
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
	
	public ArrayList<String> getConquestCharacters() {
		return conquestCharacters;
	}

	public ArrayList<String> getBirthrightCharacters() {
		return birthrightCharacters;
	}

	public ArrayList<String> getRevelationsCharacters() {
		return revelationsCharacters;
	}
	
	public Map<String, Job> getJobs() {
		return jobs;
	}
	
	public ArrayList<String> getSpecialClasses() {
		return specialClasses;
	}
	
	public ArrayList<String> getJobNames() {
		return jobNames;
	}
	
	public ArrayList<String> getPromotedJobNames() {
		return promotedJobNames;
	}

	public ArrayList<String> getNonpromotedJobNames() {
		return nonpromotedJobNames;
	}

	public String[] getBOONS() {
		return BOONS;
	}

	public String[] getBANES() {
		return BANES;
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
		Character[] adults;			// Array used for parsing the adults in characters.json
		Avatar[] avatar;				// Used for parsing avatar.json
		ChildCharacter[] children;	// Array used for parsing children.json
		Kana[] kana;					// Used for parsing kana.json
		
		try
		{
			Gson gson = new GsonBuilder().create();
			// Parse adult characters
			Reader reader = new InputStreamReader(DataStorage.class.getResourceAsStream("/resources/characters.json"), "UTF-8");
			adults = gson.fromJson(reader, Character[].class);
			// Parse Avatar
			reader = new InputStreamReader(DataStorage.class.getResourceAsStream("/resources/avatar.json"), "UTF-8");
			avatar = gson.fromJson(reader, Avatar[].class);
			// Parse children
			reader = new InputStreamReader(DataStorage.class.getResourceAsStream("/resources/children.json"), "UTF-8");
			children = gson.fromJson(reader, ChildCharacter[].class);
			// Parse Kana
			reader = new InputStreamReader(DataStorage.class.getResourceAsStream("/resources/kana.json"), "UTF-8");
			kana = gson.fromJson(reader, Kana[].class);
			
			// Implement characters map and route arrays
			characters = new HashMap<String, Character>();
			conquestCharacters = new ArrayList<String>();
			birthrightCharacters = new ArrayList<String>();
			revelationsCharacters = new ArrayList<String>();

			// Place both Avatars in the map
			for(int i = 0; i < avatar.length; i++) {
				characters.put(avatar[i].getName(), avatar[i]);
				
				// Both Avatars are in every route
				conquestCharacters.add(avatar[i].getName());
				birthrightCharacters.add(avatar[i].getName());
				revelationsCharacters.add(avatar[i].getName());
			}
			
			// Place all adults in the map
			for(int i = 0; i < adults.length; i++) {
				characters.put(adults[i].getName(), adults[i]);
				
				// Add adults to proper lists of characters in each route
				if(adults[i].getRoutes()[0]) {
					conquestCharacters.add(adults[i].getName());
				}
				if(adults[i].getRoutes()[1]) {
					birthrightCharacters.add(adults[i].getName());
				}
				if(adults[i].getRoutes()[2]) {
					revelationsCharacters.add(adults[i].getName());
				}
			}

			// Place both Kana in the map
			for(int i = 0; i < kana.length; i++) {
				characters.put(kana[i].getName(), kana[i]);
				
				// Both Kana are in every route
				conquestCharacters.add(kana[i].getName());
				birthrightCharacters.add(kana[i].getName());
				revelationsCharacters.add(kana[i].getName());
			}
			
			// Place all of the children in the map
			for(int i = 0; i < children.length; i++) {
				characters.put(children[i].getName(), children[i]);
				
				// Add children to proper lists of characters in each route
				if(children[i].getRoutes()[0]) {
					conquestCharacters.add(children[i].getName());
				}
				if(children[i].getRoutes()[1]) {
					birthrightCharacters.add(children[i].getName());
				}
				if(children[i].getRoutes()[2]) {
					revelationsCharacters.add(children[i].getName());
				}
			}
			
			
			// Sort all character arraylists alphabetically
			Collections.sort(conquestCharacters);
			Collections.sort(birthrightCharacters);
			Collections.sort(revelationsCharacters);
			
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
			jobNames = new ArrayList<String>();
			promotedJobNames = new ArrayList<String>();
			nonpromotedJobNames = new ArrayList<String>();
			specialClasses = new ArrayList<String>();
			for(int i = 0; i < jobArray.length; i++) {
				// fill the specialClasses array
				if(jobArray[i].getIsSpecial())
					specialClasses.add(jobArray[i].getName());
				jobs.put(jobArray[i].getName(), jobArray[i]);
				jobNames.add(jobArray[i].getName());
				if(jobArray[i].getIsPromoted()) {
					promotedJobNames.add(jobArray[i].getName());
				}
				else {
					nonpromotedJobNames.add(jobArray[i].getName());
				}
			}
			// Sort all job names alphabetically
			Collections.sort(jobNames);
			Collections.sort(promotedJobNames);
			Collections.sort(nonpromotedJobNames);
			
			reader.close();
		}
		catch (IOException e)
		{
			System.err.println("Caught IOException: " + e.getMessage());
		}
	}
	
	// Printing methods for testing purposes
	public void printAllCharacters() {
		for(Character c: characters.values()) {
			System.out.println(c.toString());
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
