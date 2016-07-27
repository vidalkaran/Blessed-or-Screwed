package domain;

// note: two uses of unit, one for player input and one for auto generation.

public class Unit {
	
	private Character myCharacter;
	private Job myJob;
	private int level;
	private double avgStat[] = new double[8];
	private double growths[] = new double[8];
	private int maxstats[] = new int[9];
	private String statblock[] = {"HP", "Str", "Mag", "Skl", "Spd", "Lck", "Def", "Res"};
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
		System.out.println("Level Difference: " + inputLevel + " - " + myCharacter.getBaseStats().getStats(route, 0) + " = " + level);
		
		for (int i = 0; i<=7; i++)
		{
			avgStat[i] = (level * (growths[i]/100)) + myCharacter.getBaseStats().getStats(route,i+1); //(level difference * growth + base) 
			//debug : Use this to print the equation to the console
			//System.out.println(level + " x " + (growths[i]/100) + " + " + myCharacter.getBaseStats().getStats(route,i+1));
		}
	}
	
	private void calculateGrowths()
	{
		for(int i = 0; i<= 7; i++)
		{
			growths[i] = myCharacter.getGrowths(i) + myJob.getGrowths(i);
			//debug: Use this to print the equation to the console
			//System.out.println(myCharacter.getGrowths(i)+ "+" + myJob.getGrowths(i));
		}
	}
	
	private void calculateMaxStats()//maxmods in job starts with level :o
	{
		for(int i = 0; i<= 7; i++)
		{
			maxstats[i] = myCharacter.getMaxMods(i) + myJob.getMaxStats(i+1);
			//debug: Use this to print the equation to the console
			//System.out.println(myCharacter.getMaxMods(i)+ "+" + myJob.getMaxStats(i));
		}
	}
	
	public void printUnit()
	{
		System.out.println("Name: "+myCharacter.getName()
							+"\n"+"Class: "+myJob.getName()
							+"\n"+"Level: "+level
							+"\n" + "Route: " + route);

		System.out.println("GROWTHS...");
		for(int i = 0; i<=7; i++)
		{
			System.out.println(statblock[i] + ": " + growths[i]);
		}

		System.out.println("MAX STATS...");
		for(int i = 0; i<=7; i++)
		{
			System.out.println(statblock[i] + ": " + maxstats[i]);
		}

		System.out.println("STATS...");
		for(int i = 0; i<=7; i++)
		{
			System.out.println(statblock[i] + ": " + avgStat[i]);
		}
	}
	
}
