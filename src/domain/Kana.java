package domain;

import java.util.Arrays;

public class Kana extends ChildCharacter{
	
	private String boon;
	private String bane;
	
	/*
	public Kana(String name, String baseClass, boolean areYouABoy, boolean isChild,
			boolean[] routes, ArrayList<String> validSpecials, int baseLevel, int baseHp, 
			int baseStr, int baseMag, int baseSkl, int baseSpd, int baseLck, int baseDef,
			int baseRes, int levelMod, int strMod, int magMod, int sklMod, int spdMod,
			int lckMod, int defMod, int resMod, int hpGrowth, int strGrowth, int magGrowth,
			int sklGrowth, int spdGrowth, int lckGrowth, int defGrowth,
			int resGrowth, String boon, String bane, String variedParent, ArrayList<String> possibleParents) {
		super(name, baseClass, areYouABoy, isChild, routes, validSpecials, baseLevel, baseHp, baseStr, baseMag,
				baseSkl, baseSpd, baseLck, baseDef, baseRes, levelMod, strMod, magMod, sklMod,
				spdMod, lckMod, defMod, resMod, hpGrowth, strGrowth, magGrowth, sklGrowth,
				spdGrowth, lckGrowth, defGrowth, resGrowth);
		
		boon = this.boon;
		bane = this.bane;
		possibleParents = this.possibleParents;
		variedParent = this.variedParent;
	}*/
	

	public String getBoon() {
		return boon;
	}

	public void setBoon(String boon) {
		this.boon = boon;
	}

	public String getBane() {
		return bane;
	}

	public void setBane(String bane) {
		this.bane = bane;
	}

	public String toString() {
		String allRoutes = Arrays.toString(routes);
		String allMaxMods = Arrays.toString(maxMods);
		String allGrowths = Arrays.toString(growths);
		String allValidSpecials = Arrays.toString(validSpecials);
		return "name: " + name + "\nroutes: " + allRoutes + "\nBaseStats: " + baseStats.toString() + "\nmaxMods: " + allMaxMods +
				"\ngrowths: " + allGrowths + "\nvalidSpecials: " + allValidSpecials + "\nareYouABoy: " + areYouABoy + "\nisChild: " + 
				isChild + "\nfixedParent: " + fixedParent + "\nboon: " + boon + "\nbane: " + bane;
	}
}
