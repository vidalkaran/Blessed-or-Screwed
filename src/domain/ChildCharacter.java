package domain;

import java.util.ArrayList;
import java.util.Arrays;

public class ChildCharacter extends Character{

	protected ArrayList<String> possibleParents;
	protected String fixedParent;						// Name of the child character's fixed parent
	protected String variedParent;
	protected int startLevel;
	

	public int getStartLevel() {
		return startLevel;
	}

	public void setStartLevel(int startLevel) {
		this.startLevel = startLevel;
	}

	public ArrayList<String> getPossibleParents() {
		return possibleParents;
	}

	public void setPossibleParents(ArrayList<String> possibleParents) {
		this.possibleParents = possibleParents;
	}

	public String getFixedParent() {
		return fixedParent;
	}
	
	public String getVariedParent() {
		return variedParent;
	}

	public void setVariedParent(String variedParent) {
		this.variedParent = variedParent;
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


