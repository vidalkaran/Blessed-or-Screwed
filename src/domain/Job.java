package domain;

import java.util.Arrays;

public class Job {

	private String name;					// Name of the Job
	private int[] baseStats;				// The base stats of the Job. Used to calculate bonuses when class changing (includes HP, but not Level)
	private int[] maxStats;					// Sat caps of the job (includes both Level and HP) - level is included to check for classes with max level of 40
	private int[] growths;					// Base growths of the Job - is added to the growths of a Character to determine total (includes HP, but not Level)
	private boolean isPromoted;				// Checks if the classes is a promoted class or not. **Special classes are not considered promoted classes**
	private String[] promotions;	// ArrayList holding the  names of the classes this class Promotes to. EMPTY ARRAY if none
	private boolean isSpecial;				// Checks if the class qualifies as a special class
	private String genderLock; 				// Used to determine if the class is locked to a sex. Options are: "male", "female", "none"
	
	private int baseHp;
	private int baseStr;
	private int baseMag;
	private int baseSkl;
	private int baseSpd;
	private int baseLck;
	private int baseDef;
	private int baseRes;	
	
	private int maxLevel;
	private int maxHp;
	private int maxStr;
	private int maxMag;
	private int maxSkl;
	private int maxSpd;
	private int maxLck;
	private int maxDef;
	private int maxRes;
	
	private int hpGrowth;
	private int strGrowth;
	private int magGrowth;
	private int sklGrowth;
	private int spdGrowth;
	private int lckGrowth;
	private int defGrowth;
	private int resGrowth;
	
	
	/*
	//constructor
	public Job(String name, String genderLocked, String promotion,
					 int baseStr, int baseMag, int baseSkl, int baseSpd, int baseLck, int baseDef, int baseRes,
					 int maxStr, int maxMag, int maxSkl, int maxSpd, int maxLck, int maxDef, int maxRes,
					 int strGrowth, int magGrowth, int sklGrowth, int spdGrowth, int lckGrowth, int defGrowth, int resGrowth)
	{
		
		name = this.name;
		genderLocked = this.genderLocked;
		promotion = this.promotion;
		
		baseStr = this.baseStr;
		baseMag = this.baseMag;
		baseSkl = this.baseSkl;
		baseSpd = this.baseSpd;
		baseLck = this.baseLck;
		baseDef = this.baseDef;
		baseRes = this.baseRes;
		
		maxStr = this.maxStr;
		maxMag = this.maxMag;
		maxSkl = this.maxSkl;
		maxSpd = this.maxSpd;
		maxLck = this.maxLck;
		maxDef = this.maxDef;
		maxRes = this.maxRes;
		
		strGrowth = this.strGrowth;
		magGrowth = this.magGrowth;
		sklGrowth = this.sklGrowth;
		spdGrowth = this.spdGrowth;
		lckGrowth = this.lckGrowth;
		defGrowth = this.defGrowth;
		resGrowth = this.resGrowth;
	}*/


	// splits up the baseStats, maxStats, and growths arrays into their individual values
	// should be called as part of the JSON parsing process.
	public void parseArrayData() {
		baseHp = baseStats[0];
		baseStr = baseStats[1];
		baseMag = baseStats[2];
		baseSkl = baseStats[3];
		baseSpd = baseStats[4];
		baseLck = baseStats[5];
		baseDef = baseStats[6];
		baseRes = baseStats[7];
		
		maxLevel = maxStats[0];
		maxHp = maxStats[1];
		maxStr = maxStats[2];
		maxMag = maxStats[3];
		maxSkl = maxStats[4];
		maxSpd = maxStats[5];
		maxLck = maxStats[6];
		maxDef = maxStats[7];
		maxRes = maxStats[8];
		
		hpGrowth = growths[0];
		strGrowth = growths[1];
		magGrowth = growths[2];
		sklGrowth = growths[3];
		spdGrowth = growths[4];
		lckGrowth = growths[5];
		defGrowth = growths[6];
		resGrowth = growths[7];
	}
	
	// getters
	public String getName() {
		return name;
	}

	public String getGenderLock() {
		return genderLock;
	}

	public String[] getPromotions() {
		return promotions;
	}
	
	public boolean getIsPromoted() {
		return isPromoted;
	}
	
	public boolean getIsSpecial() {
		return isSpecial;
	}
	
	public int[] getBaseStats() {
		return baseStats;
	}
	
	public int[] getMaxStats() {
		return maxStats;
	}
	
	public int getMaxStats(int i) {
		return maxStats[i];
	}
	
	public int[] getGrowths() {
		return growths;
	}
	
	public int getGrowths(int i) {
		return growths[i];
	}
	
	public int getMaxLevel() {
		return maxLevel;
	}
	
	public int getMaxHp() {
		return maxHp;
	}
	
	public int getMaxStr() {
		return maxStr;
	}

	public int getMaxMag() {
		return maxMag;
	}

	public int getMaxSkl() {
		return maxSkl;
	}

	public int getMaxSpd() {
		return maxSpd;
	}

	public int getMaxLck() {
		return maxLck;
	}

	public int getMaxDef() {
		return maxDef;
	}

	public int getMaxRes() {
		return maxRes;
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
		String allBaseStats = Arrays.toString(baseStats);
		String allMaxStats = Arrays.toString(maxStats);
		String allGrowths = Arrays.toString(growths);
		String allPromotions = Arrays.toString(promotions);
		return "name: " + name + "\nbaseStats: " + allBaseStats + "\nmaxStats: " + allMaxStats + "\ngrowths: " + allGrowths +
				"\nisPromoted: " + isPromoted + "\npromotions: " + allPromotions + "\nisSpecial: " + isSpecial + "\ngenderLock: " + genderLock;
	}
}
