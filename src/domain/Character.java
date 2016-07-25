package domain;

import java.util.ArrayList;
import java.util.Arrays;

public class Character {

	private String name;						// Name of the character
	private String baseClass;					// The name of the class the character starts in
	private boolean[] routes;					// Boolean array of fixed sized 3 of what routes the characters are in. [conquest, birthright, revelations]
	private BaseStats baseStats;				// Object that holds baseStats - is an object because bases can vary depending on routes (includes both level and HP)
	private int[] maxMods;						// Mods to the class stat caps unique to each character (includes Level, but not HP)
	private int[] growths;						// Character's base growths (includes HP, but not level)
	private ArrayList<String> validSpecials;	// Holds the names of all special classes the character can be - EMPTY ARRAY if none
	private boolean areYouABoy;					// True - character is male, False - character is female
	private boolean isChild;					// True - character is a child unit, False - character is a parent unit
	
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
	
	/*
	//constructor
	public Character(String name, String baseClass, boolean areYouABoy, boolean isChild, boolean[] routes, ArrayList<String> validSpecials,
					 int baseLevel, int baseHp, int baseStr, int baseMag, int baseSkl, int baseSpd, int baseLck, int baseDef, int baseRes,
					 int levelMod, int strMod, int magMod, int sklMod, int spdMod, int lckMod, int defMod, int resMod,
					 int hpGrowth, int strGrowth, int magGrowth, int sklGrowth, int spdGrowth, int lckGrowth, int defGrowth, int resGrowth)
	{
		
		name = this.name;
		baseClass = this.baseClass;
		areYouABoy = this.areYouABoy;
		isChild = this.isChild;
		routes = this.routes;
		validSpecials = this.validSpecials;	
		
		baseLevel = this.baseLevel;
		baseHp = this.baseHp;
		baseStr = this.baseStr;
		baseMag = this.baseMag;
		baseSkl = this.baseSkl;
		baseSpd = this.baseSpd;
		baseLck = this.baseLck;
		baseDef = this.baseDef;
		baseRes = this.baseRes;
		
		//THESE MODS ARE FOR MAX STATS
		levelMod = this.levelMod;
		strMod = this.strMod;
		magMod = this.magMod;
		sklMod = this.sklMod;
		spdMod = this.spdMod;
		lckMod = this.lckMod;
		defMod = this.defMod;
		resMod = this.resMod;
		
		hpGrowth = this.hpGrowth;
		strGrowth = this.strGrowth;
		magGrowth = this.magGrowth;
		sklGrowth = this.sklGrowth;
		spdGrowth = this.spdGrowth;
		lckGrowth = this.lckGrowth;
		defGrowth = this.defGrowth;
		resGrowth = this.resGrowth;
	}*/
	
	// set the base stats appropriately using the baseStats class data parsed from the JSON.
	// path is a String that represents which route is being checked. This method should be called from Unit.
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
		
	public ArrayList<String> getValidSpecials() {
		return validSpecials;
	}

	public int[] getMaxMods() {
		return maxMods;
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
		String allValidSpecials = Arrays.toString(validSpecials.toArray());
		return "name: " + name + "\nroutes: " + allRoutes + "\nBaseStats: " + baseStats.toString() + "\nmaxMods: " + allMaxMods +
				"\ngrowths: " + allGrowths + "\nvalidSpecials: " + allValidSpecials + "\nareYouABoy: " + areYouABoy + "\nisChild: " + isChild;
	}
}
