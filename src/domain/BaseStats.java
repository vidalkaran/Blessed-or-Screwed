package domain;

import java.util.Arrays;

public class BaseStats {
	private int[] conquest;
	private int[] birthright;
	private int[] revelations;
	
	public int[] getConquest() {
		return conquest;
	}
	
	public int[] getBirthright() {
		return birthright;
	}
	
	public int[] getRevelations() {
		return revelations;
	}
	
	// 0 = Level, 1 = HP, 2 = Str, 3 = Mag, 4 = Skl, 5 = Spd, 6 = Lck, 7 = Def, 8 = Res
	public int getStats(String route, int stat)
	{
		int output = 0;
		switch(route.toLowerCase())
		{
			case "conquest":
			{
				output = conquest[stat];
				break;
			}
			case "birthright":
			{
				output = birthright[stat];
				break;
			}
			case "revelations":
			{
				output = revelations[stat];
				break;
			}
		}
		return output;
	}
	
	public String toString() {
		return "Conquest bases: " + Arrays.toString(conquest) + ", Birthright bases: " + Arrays.toString(birthright) + ", Revelations bases: " + Arrays.toString(revelations);
	}
}