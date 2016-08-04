package domain;

// note: two uses of unit, one for player input and one for auto generation.

public class Unit {
	
	private Character myCharacter;
	private Job myJob;
	private int level;
	private int baseStats[] = new int[8];
	private double growths[] = new double[8];
	private int maxstats[] = new int[8];
	private String statblock[] = {"HP", "Str", "Mag", "Skl", "Spd", "Lck", "Def", "Res"};
	private String route;
	
	public Unit(Character inputCharacter, Job inputJob, String inputRoute)
	{
		myCharacter = inputCharacter;
		myJob = inputJob;
		route = inputRoute;
		
		calculateBaseStats();
		calculateGrowths();
		calculateMaxStats();
		
	}
	
	private void calculateBaseStats()
	{
		for(int i = 0; i< baseStats.length; i++)
		{
			baseStats[i] = myCharacter.getBaseStats().getStats(route, i+1) - myJob.getBaseStats(i);
			//System.out.println(myCharacter.getBaseStats().getStats(route, i+1) + "+" + myJob.getBaseStats(i));
		}
	}
	
	private void calculateGrowths()
	{
		for(int i = 0; i< growths.length; i++)
		{
			growths[i] = myCharacter.getGrowths(i) + myJob.getGrowths(i);
			//debug: Use this to print the equation to the console
			//System.out.println(myCharacter.getGrowths(i)+ "+" + myJob.getGrowths(i));
		}
	}
	
	private void calculateMaxStats()//maxmods in job starts with level :o
	{
		for(int i = 0; i< maxstats.length; i++)
		{
			maxstats[i] = myCharacter.getMaxMods(i) + myJob.getMaxStats(i+1);
			//debug: Use this to print the equation to the console
			//System.out.println(myCharacter.getMaxMods(i)+ "+" + myJob.getMaxStats(i+1));
		}
	}	
	
	//This function reclasses a Unit, and recalculates the growths and maxes without changing the base stats.
	private void reClass(Job newJob)
	{
		myJob = newJob;
		calculateGrowths();
		calculateMaxStats();
	}
	
	public void printUnit()
	{
		System.out.println("Name: "+myCharacter.getName()
							+"\n"+"Class: "+myJob.getName()
							+"\n" + "Route: " + route);

		System.out.println("GROWTHS...");
		for(int i = 0; i<growths.length; i++)
		{
			System.out.println(statblock[i] + ": " + growths[i]);
		}

		System.out.println("MAX STATS...");
		for(int i = 0; i<maxstats.length; i++)
		{
			System.out.println(statblock[i] + ": " + maxstats[i]);
		}

		System.out.println("BASE STATS...");
		for(int i = 0; i<baseStats.length; i++)
		{
			System.out.println(statblock[i] + ": " + baseStats[i]);
		}
	}

	
	//SETTERS AND GETTERSS
	public Character getMyCharacter() {
		return myCharacter;
	}

	public void setMyCharacter(Character myCharacter) {
		this.myCharacter = myCharacter;
	}

	public Job getMyJob() {
		return myJob;
	}

	public void setMyJob(Job myJob) {
		this.myJob = myJob;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int[] getBaseStats() {
		return baseStats;
	}

	public void setBaseStats(int[] baseStats) {
		this.baseStats = baseStats;
	}

	public double[] getGrowths() {
		return growths;
	}

	public void setGrowths(double[] growths) {
		this.growths = growths;
	}

	public int[] getMaxstats() {
		return maxstats;
	}

	public void setMaxstats(int[] maxstats) {
		this.maxstats = maxstats;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}
	
	
}
