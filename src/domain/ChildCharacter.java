package domain;

import java.util.ArrayList;
import java.util.Arrays;
import domain.VariedParent;

public class ChildCharacter extends Character{

	protected String fixedParent;						// Name of the child character's fixed parent
	protected VariedParent variedParents;				// Object to hold the arrays of the child's varied parents

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
		String allValidSpecials = Arrays.toString(invalidClasses);
		return "name: " + name + "\nroutes: " + allRoutes + "\nBaseStats: " + baseStats.toString() + "\nmaxMods: " + allMaxMods +
				"\ngrowths: " + allGrowths + "\nvalidSpecials: " + allValidSpecials + "\nareYouABoy: " + "\nisChild: " + 
				isChild + "\nfixedParent: " + fixedParent + "\nvariedParents: " + variedParents.toString();
	}
}


