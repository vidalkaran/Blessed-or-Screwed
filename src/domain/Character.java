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
	
	private int levelMod;
	private int strMod;
	private int magMod;
	private int sklMod;
	private int spdMod;
	private int lckMod;
	private int defMod;
	private int resMod;
	
	private int hpGrowth;
	private int strGrowth;
	private int magGrowth;
	private int sklGrowth;
	private int spdGrowth;
	private int lckGrowth;
	private int defGrowth;
	private int resGrowth;
	
	private int baseLevel;
	private int baseHp;
	private int baseStr;
	private int baseMag;
	private int baseSkl;
	private int baseSpd;
	private int baseLck;
	private int baseDef;
	private int baseRes;
	

	public void getDesiredBaseStats(String path) {
		int[] temp = new int[9];
		
		if(path.toLowerCase().equals("conquest"))
			temp = baseStats.getConquest();
		else if(path.toLowerCase().equals("birthright"))
			temp = baseStats.getBirthright();
		else {
			temp = baseStats.getRevelations();
		}
		
		baseLevel = temp[0];
		baseHp = temp[1];
		baseStr = temp[2];
		baseMag = temp[3];
		baseSkl = temp[4];
		baseSpd = temp[5];
		baseLck = temp[6];
		baseDef = temp[7];
		baseRes = temp[8];
	}
	
	// splits up the maxMods and growths arrays into their individual values
	// should be called as part of the JSON parsing process.
	public void parseArrayData() {
		levelMod = maxMods[0];
		strMod = maxMods[1];
		magMod = maxMods[2];
		sklMod = maxMods[3];
		spdMod = maxMods[4];
		lckMod = maxMods[5];
		defMod = maxMods[6];
		resMod = maxMods[7];
		
		hpGrowth = growths[0];
		strGrowth = growths[1];
		magGrowth = growths[2];
		sklGrowth = growths[3];
		spdGrowth = growths[4];
		lckGrowth = growths[5];
		defGrowth = growths[6];
		resGrowth = growths[7];
	}
	
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
	
	public int getLevelMod() {
		return levelMod;
	}
	
	public int getStrMod() {
		return strMod;
	}

	public int getMagMod() {
		return magMod;
	}

	public int getSklMod() {
		return sklMod;
	}

	public int getSpdMod() {
		return spdMod;
	}

	public int getLckMod() {
		return lckMod;
	}

	public int getDefMod() {
		return defMod;
	}

	public int getResMod() {
		return resMod;
	}

	public int getHpGrowth() {
		return hpGrowth;
	}
	
	public int getStrGrowth() {
		return strGrowth;
	}

	public int getMagGrowth() {
		return magGrowth;
	}
	
	public int getSklGrowth() {
		return sklGrowth;
	}

	public int getSpdGrowth() {
		return spdGrowth;
	}

	public int getLckGrowth() {
		return lckGrowth;
	}

	public int getDefGrowth() {
		return defGrowth;
	}

	public int getResGrowth() {
		return resGrowth;
	}

	public int getBaseLevel() {
		return baseLevel;
	}
	
	public int getBaseHp() {
		return baseHp;
	}
		
	public int getBaseStr() {
		return baseStr;
	}

	public int getBaseMag() {
		return baseMag;
	}

	public int getBaseSkl() {
		return baseSkl;
	}

	public int getBaseSpd() {
		return baseSpd;
	}

	public int getBaseLck() {
		return baseLck;
	}

	public int getBaseDef() {
		return baseDef;
	}
	
	public int getBaseRes() {
		return baseRes;
	}	


	public String toString() {
		String allRoutes = Arrays.toString(routes);
		String allMaxMods = Arrays.toString(maxMods);
		String allGrowths = Arrays.toString(growths);
		String allValidSpecials = Arrays.toString(validSpecials);
		return "name: " + name + "\nroutes: " + allRoutes + "\nBaseStats: " + baseStats.toString() + "\nmaxMods: " + allMaxMods +
				"\ngrowths: " + allGrowths + "\nvalidSpecials: " + allValidSpecials + "\nareYouABoy: " + areYouABoy + "\nisChild: " + isChild;
	}
}
