package domain;

// note: two uses of unit, one for player input and one for auto generation.

public class Unit {
	
	private Character myCharacter;
	private Job myJob;
	private int level;
	private int stat[];
	private int growths[];
	private int maxstats[];
	private String route;
	
	public Unit(Character inputCharacter, Job inputJob, int Level)
	{
		myCharacter = inputCharacter;
		myJob = inputJob;
	}
	
	private void calculateStats(int inputLevel, String route) // gonna need to track route also
	{
		for (int i = 0; i<=7; i++)
		{
			stat[i] = ((inputLevel - (myCharacter.getBaseStats().getStats(route, 0))) * growths[i]) + myCharacter.getBaseStats().getStats(route,i+1);
		}
	}
	
	private void calculateGrowths()
	{
		for(int i = 0; i<= 7; i++)
		{
			growths[i] = myCharacter.getGrowths(i) + myJob.getGrowths(i);
		}
	}
	
	private void calculateMaxStats()
	{
		for(int i = 0; i<= 7; i++)
		{
			maxstats[i] = myCharacter.getMaxMods(i) + myJob.getMaxStats(i);
		}
	}
	
	
}
