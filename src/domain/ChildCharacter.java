package domain;

import java.util.ArrayList;

public class ChildCharacter extends Character{

	private ArrayList<String> possibleParents;
	private String fixedParent;
	private String variedParent;
	
	/*
	public ChildCharacter(String name, String baseClass, boolean areYouABoy,boolean isChild,
			boolean[] routes, ArrayList<String> validSpecials, int baseLevel, int baseHp, 
			int baseStr, int baseMag, int baseSkl, int baseSpd, int baseLck, int baseDef,
			int baseRes, int levelMod, int strMod, int magMod, int sklMod, int spdMod,
			int lckMod, int defMod, int resMod, int hpGrowth, int strGrowth, int magGrowth,
			int sklGrowth, int spdGrowth, int lckGrowth, int defGrowth,
			int resGrowth, String variedParent, String fixedParent, ArrayList<String> possibleParents) {
		super(name, baseClass, areYouABoy, isChild, routes, validSpecials, baseLevel, baseHp, baseStr, baseMag,
				baseSkl, baseSpd, baseLck, baseDef, baseRes, levelMod, strMod, magMod, sklMod,
				spdMod, lckMod, defMod, resMod, hpGrowth, strGrowth, magGrowth, sklGrowth,
				spdGrowth, lckGrowth, defGrowth, resGrowth);

		possibleParents = this.possibleParents;
		fixedParent = this.fixedParent;
		variedParent = this.variedParent;

	}*/

	public ArrayList<String> getPossibleParents() {
		return possibleParents;
	}

	public void setPossibleParents(ArrayList<String> possibleParents) {
		this.possibleParents = possibleParents;
	}

	public String getFixedParent() {
		return fixedParent;
	}

	public void setFixedParent(String fixedParent) {
		this.fixedParent = fixedParent;
	}

	public String getVariedParent() {
		return variedParent;
	}

	public void setVariedParent(String variedParent) {
		this.variedParent = variedParent;
	}


}


