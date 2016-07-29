package domain;

import java.util.Arrays;

public class Job {

	private String name;					// Name of the Job
	private int[] baseStats;				// The base stats of the Job. Used to calculate bonuses when class changing (includes HP, but not Level)
	private int[] maxStats;					// Sat caps of the job (includes both Level and HP) - level is included to check for classes with max level of 40
	private int[] growths;					// Base growths of the Job - is added to the growths of a Character to determine total (includes HP, but not Level)
	private boolean isPromoted;				// Checks if the classes is a promoted class or not. **Special classes are not considered promoted classes**
	private String[] promotions;			// ArrayList holding the  names of the classes this class Promotes to. EMPTY ARRAY if none
	private boolean isSpecial;				// Checks if the class qualifies as a special class
	private String genderLock; 				// Used to determine if the class is locked to a sex. Options are: "male", "female", "none"
		
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
	
	public String toString() {
		String allBaseStats = Arrays.toString(baseStats);
		String allMaxStats = Arrays.toString(maxStats);
		String allGrowths = Arrays.toString(growths);
		String allPromotions = Arrays.toString(promotions);
		return "name: " + name + "\nbaseStats: " + allBaseStats + "\nmaxStats: " + allMaxStats + "\ngrowths: " + allGrowths +
				"\nisPromoted: " + isPromoted + "\npromotions: " + allPromotions + "\nisSpecial: " + isSpecial + "\ngenderLock: " + genderLock;
	}
}
