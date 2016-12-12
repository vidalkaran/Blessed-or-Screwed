package domain;

import java.util.Arrays;

public class Kana extends ChildCharacter{
	public String toString() {
		String allRoutes = Arrays.toString(routes);
		String allMaxMods = Arrays.toString(maxMods);
		String allGrowths = Arrays.toString(growths);
		String allValidSpecials = Arrays.toString(invalidClasses);
		return "name: " + name + "\nroutes: " + allRoutes + "\nBaseStats: " + baseStats.toString() + "\nmaxMods: " + allMaxMods +
<<<<<<< HEAD
				"\ngrowths: " + allGrowths + "\nvalidSpecials: " + allValidSpecials + "\nareYouABoy: " + "\nisChild: " + 
				isChild + "\nfixedParent: " + fixedParent + "\nvariedParents: " + variedParents.toString()/* + "\nboon: " + boon + "\nbane: " + bane*/;
=======
				"\ngrowths: " + allGrowths + "\nvalidSpecials: " + allValidSpecials + "\nareYouABoy: " + areYouABoy + "\nisChild: " + 
				isChild + "\nfixedParent: " + fixedParent + "\nvariedParents: " + variedParents.toString();
>>>>>>> refs/remotes/origin/master
	}
}
