package domain;

import java.util.ArrayList;
import java.util.Arrays;
import domain.VariedParent;

public class ChildCharacter extends Character{

	protected String fixedParent;						// Name of the child character's fixed parent
	protected VariedParent variedParents;
	protected int startLevel;
	
	public int getStartLevel() {
		return startLevel;
	}

	public void setStartLevel(int startLevel) {
		this.startLevel = startLevel;
	}

	public String getFixedParent() {
		return fixedParent;
	}
	
	public VariedParent getVariedParents() {
		return variedParents;
	}


	public String toString() {
		String allRoutes = Arrays.toString(routes);
		String allMaxMods = Arrays.toString(maxMods);
		String allGrowths = Arrays.toString(growths);
		String allValidSpecials = Arrays.toString(validSpecials);
		return "name: " + name + "\nroutes: " + allRoutes + "\nBaseStats: " + baseStats.toString() + "\nmaxMods: " + allMaxMods +
				"\ngrowths: " + allGrowths + "\nvalidSpecials: " + allValidSpecials + "\nareYouABoy: " + areYouABoy + "\nisChild: " + 
				isChild + "\nfixedParent: " + fixedParent + "\nvariedParents: " + variedParents.toString();
	}
}


