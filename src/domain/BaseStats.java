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
	
	public int getStats(String route, int stat)
	{
		int output = 0;
		switch(route)
		{
			case "conquest":
			{
				output = conquest[stat];
			}
			case "birthright":
			{
				output = birthright[stat];
			}
			case "revelations":
			{
				output = revelations[stat];
			}
		}
		return output;
	}
	
	public String toString() {
		return "Conquest bases: " + Arrays.toString(conquest) + ", Birthright bases: " + Arrays.toString(birthright) + ", Revelations bases: " + Arrays.toString(revelations);
	}
}