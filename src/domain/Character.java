package domain;

import java.util.Arrays;

public class Character {
	
	// The following block of variables match the names of the variables in the json file so that gson can create objects of this class
	private String name;						// Name of the character
	private String baseClass;					// The name of the class the character starts in
	private boolean[] routes;					// Boolean array of fixed sized 3 of what routes the characters are in. [conquest, birthright, revelations]
	private BaseStats baseStats;				// Object that holds baseStats - is an object because bases can vary depending on routes (includes both level and HP)
	private int[] maxMods;						// Mods to the class stat caps unique to each character (includes Level, but not HP)
	private int[] growths;						// Character's base growths (includes HP, but not level)
	private String[] validSpecials;				// Holds the names of all special classes the character can be - EMPTY ARRAY if none

	private boolean areYouABoy;					// True - character is male, False - character is female
	private boolean isChild;					// True - character is a child unit, False - character is a parent unit
	private String fixedParent;					// Name of the child character's fixed parent

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

	public String getFixedParent() {
		return fixedParent;
	}

	public String toString() {
		String allRoutes = Arrays.toString(routes);
		String allMaxMods = Arrays.toString(maxMods);
		String allGrowths = Arrays.toString(growths);
		String allValidSpecials = Arrays.toString(validSpecials);
		return "name: " + name + "\nroutes: " + allRoutes + "\nBaseStats: " + baseStats.toString() + "\nmaxMods: " + allMaxMods +
				"\ngrowths: " + allGrowths + "\nvalidSpecials: " + allValidSpecials + "\nareYouABoy: " + areYouABoy + "\nisChild: " + 
				isChild + "\nfixedParent: " + fixedParent;
	}
}
