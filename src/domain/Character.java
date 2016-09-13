package domain;

import java.io.Serializable;
import java.util.Arrays;

public class Character {
	
	// The following block of variables match the names of the variables in the json file so that gson can create objects of this class
	protected String name;					// Name of the character
	protected String baseClass;				// The name of the class the character starts in
	protected boolean[] routes;				// Boolean array of fixed sized 3 of what routes the characters are in. [conquest, birthright, revelations]
	protected BaseStats baseStats;			// Object that holds baseStats - is an object because bases can vary depending on routes (includes both level and HP)
	protected int[] maxMods;				// Mods to the class stat caps unique to each character (includes Level, but not HP)
	protected int[] growths;				// Character's base growths (includes HP, but not level)
	protected String[] validSpecials;		// Holds the names of all special classes the character can be - EMPTY ARRAY if none
	protected boolean areYouABoy;			// True - character is male, False - character is female
	protected boolean isChild;				// True - character is a child unit, False - character is a parent unit

	//getters
	public String getName() {
		return name;
	}

	public String getBaseClass() {
		return baseClass;
	}

	public boolean[] getRoutes() {
		return routes;
	}

	public boolean getAreYouABoy() {
		return areYouABoy;
	}
	
	public boolean getIsChild() {
		return isChild;
	}
		
	public String[] getValidSpecials() {
		return validSpecials;
	}

	public int[] getMaxMods() {
		return maxMods;
	}
	
	public int getMaxMods(int i) {
		return maxMods[i];
	}
	
	public int getGrowths(int i) {
		return growths[i];
	}
	
	public int[] getGrowths() {
		return growths;
	}
	
	public BaseStats getBaseStats() {
		return baseStats;
	}

	public String toString() {
		String allRoutes = Arrays.toString(routes);
		String allMaxMods = Arrays.toString(maxMods);
		String allGrowths = Arrays.toString(growths);
		String allValidSpecials = Arrays.toString(validSpecials);
		return "name: " + name + "\nroutes: " + allRoutes + "\nBaseStats: " + baseStats.toString() + "\nmaxMods: " + allMaxMods +
				"\ngrowths: " + allGrowths + "\nvalidSpecials: " + allValidSpecials + "\nareYouABoy: " + areYouABoy + "\nisChild: " + 
				isChild;
	}
}
