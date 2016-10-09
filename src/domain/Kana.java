package domain;

import java.util.Arrays;

public class Kana extends ChildCharacter{
	
	/*
	private String boon;
	private String bane;

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
	}*/
	
	public String toString() {
		String allRoutes = Arrays.toString(routes);
		String allMaxMods = Arrays.toString(maxMods);
		String allGrowths = Arrays.toString(growths);
		String allValidSpecials = Arrays.toString(validSpecials);
		return "name: " + name + "\nroutes: " + allRoutes + "\nBaseStats: " + baseStats.toString() + "\nmaxMods: " + allMaxMods +
				"\ngrowths: " + allGrowths + "\nvalidSpecials: " + allValidSpecials + "\nareYouABoy: " + areYouABoy + "\nisChild: " + 
				isChild + "\nfixedParent: " + fixedParent + "\nvariedParents: " + variedParents.toString()/* + "\nboon: " + boon + "\nbane: " + bane*/;
	}
}
