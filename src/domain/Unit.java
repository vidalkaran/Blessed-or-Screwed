package domain;

// note: two uses of unit, one for player input and one for auto generation.

public class Unit {
	
	private Character myCharacter;
	private Job myJob;
	private int level;
	private int baseStats[] = new int[8];
	private double growths[] = new double[8];
	private int maxstats[] = new int[9];
	private String statblock[] = {"HP", "Str", "Mag", "Skl", "Spd", "Lck", "Def", "Res"};
	private String route;
	
	public Unit(Character inputCharacter, Job inputJob, String inputRoute)
	{
		myCharacter = inputCharacter;
		myJob = inputJob;
		route = inputRoute;
		
		calculateGrowths();
		calculateMaxStats();
		
	}
	
	private void calculateBaseStats()
	{
		for(int i = 0; i<= baseStats.length; i++)
		{
			baseStats[i] = myCharacter.getBaseStats().getStats(route, i) - myJob.getBaseStats(i);
		}
	}
	
	private void calculateGrowths()
	{
		for(int i = 0; i<= growths.length; i++)
		{
			growths[i] = myCharacter.getGrowths(i) + myJob.getGrowths(i);
			//debug: Use this to print the equation to the console
			//System.out.println(myCharacter.getGrowths(i)+ "+" + myJob.getGrowths(i));
		}
	}
	
	private void calculateMaxStats()//maxmods in job starts with level :o
	{
		for(int i = 0; i<= maxstats.length; i++)
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

		System.out.println("AVERAGE STATS...");
		for(int i = 0; i<=7; i++)
		{
			System.out.println(statblock[i] + ": " + baseStats[i]);
		}
	}
	
}
