package domain;

// note: two uses of unit, one for player input and one for auto generation.

public class Unit {
	
	private Character myCharacter;
	private Job myJob;
	private int level;
	private double avgStat[] = new double[8];
	private int growths[] = new int[8];
	private int maxstats[] = new int[8];
	private String route;
	
	public Unit(Character inputCharacter, Job inputJob, int inputLevel, String inputRoute)
	{
		myCharacter = inputCharacter;
		myJob = inputJob;
		level = inputLevel;
		route = inputRoute;
		
		calculateGrowths();
		calculateMaxStats();
		calculateAverageStats(level, route);
	}
	
	private void calculateAverageStats(int inputLevel, String route) // calculates average stats
	{
		int level = inputLevel - (myCharacter.getBaseStats().getStats(route, 0));//gets the level difference.
		
		for (int i = 0; i<=7; i++)
		{
			avgStat[i] = (level * (growths[i]/100)) + myCharacter.getBaseStats().getStats(route,i+1); //(level difference * growth + base) 
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
	
	public void printUnit()
	{
		System.out.println("Name: "+myCharacter.getName()
							+"\n"+"Class: "+myJob.getName()
							+"\n"+"Level: "+level
							+"\n" + "Route: " + route);
		for(int i = 0; i<=7; i++)
		{
			System.out.println("GROWTHS...");
			System.out.println(growths[i]);
		}
		for(int i = 0; i<=7; i++)
		{
			System.out.println("MAX STATS...");
			System.out.println(maxstats[i]);
		}
		for(int i = 0; i<=7; i++)
		{
			System.out.println("STATS...");
			System.out.println(avgStat[i]);
		}
	}
	
}
