package domain;

import java.io.Serializable;

import json.DataStorage;

// note: two uses of unit, one for player input and one for auto generation.

public class Unit implements Serializable{
	
	private Character myCharacter;
	private Job myJob;
	private int level;
	private double[] baseStats = new double[8];
	private double growths[] = new double[8];
	private int maxstats[] = new int[8];
	private String statblock[] = {"HP", "Str", "Mag", "Skl", "Spd", "Lck", "Def", "Res"};
	private String route;
	private boolean isChild;
	
	// These variables will only be used for children
	private ChildCharacter myChildCharacter;
	DataStorage data = DataStorage.getInstance(); //This is the data
	private double[] fixedParentStats;
	private Job fixedParentJob;
	private double[] variedParentStats;
	private Job variedParentJob;
	
	public Unit(Character inputCharacter, Job inputJob, String inputRoute)
	{
		myCharacter = inputCharacter;
		myJob = inputJob;
		route = inputRoute;
		level = inputCharacter.getBaseStats().getStats(route, 0);
		isChild = false;
		
		calculateBaseStats();
		calculateGrowths();
		calculateMaxStats();
	}
	
	public Unit(ChildCharacter inputCharacter, Job inputJob, String inputRoute, double[] fixedParentStats, Job fixedParentJob, double[] variedParentStats, Job variedParentJob)
	{
		myChildCharacter = inputCharacter;
		myJob = inputJob;
		route = inputRoute;
		level = inputCharacter.getStartLevel();
		isChild = true;
		this.fixedParentStats = fixedParentStats;
		this.fixedParentJob = fixedParentJob;
		this.variedParentStats = variedParentStats;
		this.variedParentJob = variedParentJob;
		
		calculateGrowths();
		calculateBaseStats();
		calculateMaxStats();
	}
	
	private void calculateBaseStats()
	{
		// Calculate baseStats for Avatar
		if(myCharacter != null && myCharacter instanceof Avatar)
		{
		
		}
		// Calculate baseStats for adult characters
		else if(myCharacter != null && !isChild)
		{
			for(int i = 0; i< baseStats.length; i++)
			{
				baseStats[i] = myCharacter.getBaseStats().getStats(route, i+1) - myJob.getBaseStats(i);
				//System.out.println(myCharacter.getBaseStats().getStats(route, i+1) + "+" + myJob.getBaseStats(i));
			}
		}
		// Calculate baseStats for child characters
		else if(myChildCharacter != null  && isChild)
		{
			// Get the unit's base job and then get that base job's growths
			Job tempBaseJob = data.getJobs().get(myChildCharacter.getBaseClass());
			int[] tempGrowths = tempBaseJob.getGrowths();
			
			// Get the number of levels over 10 the unit is. If n < 0, something in the input is invalid.
			int n = myChildCharacter.getStartLevel() - 10;
			
			// calculate each stat
			for(int i = 0; i< baseStats.length; i++)
			{
				// FORUMLA: c = Child's absolute base stats + {(n x Child's full growth rates) rounded down}
				//      Child's absolute base stat							 + ROUNDED DOWN	  (n * full growths while in the character's base class)
				double c = myChildCharacter.getBaseStats().getStats(route, i+1) + n * ((growths[i] - myJob.getGrowths(i) + tempGrowths[i])/100.0);
				System.out.println(statblock[i] + ": " + c);
				// Calculate the fixedParent's and variedParent's personal stats by subtracting the base stats of their job
				double fixedParentPersonal = fixedParentStats[i] - fixedParentJob.getBaseStats(i);
				double variedParentPersonal = variedParentStats[i] - variedParentJob.getBaseStats(i);
				System.out.println("fixedParentPersonal" + statblock[i] + ": " + fixedParentPersonal);
				System.out.println("variedParentPersonal" + statblock[i] + ": " + variedParentPersonal);
				
				// fatherCalc and motherCalc are used to calculate inheritanceTotal. Neither value can be negative and any negative values will become 0.
				double fixedParentCalc = fixedParentPersonal - c;
				double variedParentCalc = variedParentPersonal - c;
				
				if(fixedParentCalc < 0)
					fixedParentCalc = 0;
				
				if(variedParentCalc < 0)
					variedParentCalc = 0;
				
				System.out.println("fixedParentCalc" + statblock[i] + ": " + fixedParentCalc);
				System.out.println("variedParentCalc" + statblock[i] + ": " + variedParentCalc);
				// FORMULA: inheritanceTotal = {[(fixedParent's personal stats – c; 0 if < 0) + (variedParent's personal stats – c; 0 if < 0)] / 4; cannot be > than (c/10) +2 rounded down}
				double inheritanceTotal = (fixedParentCalc + variedParentCalc)/4.0;
				
				if(inheritanceTotal > Math.floor((c/10.0) + 2.0))
					inheritanceTotal = Math.floor((c/10.0) + 2.0);
				
				
				System.out.println("InheritanceTotal" + statblock[i] + ": " + inheritanceTotal);
				// FORMULA: Job base stats + c (see above) + inheritanceTotal (see above)
				baseStats[i] = myJob.getBaseStats(i) + c + inheritanceTotal;
			}
		}
	}
	
	private void calculateGrowths()
	{
		// Calculate growths for Avatar
		if(myCharacter != null && myCharacter instanceof Avatar) {
			
		}
		// Calculate growths for adult characters
		else if(myCharacter != null && !isChild)
		{
			for(int i = 0; i< growths.length; i++)
			{
				growths[i] = myCharacter.getGrowths(i) + myJob.getGrowths(i);
				//debug: Use this to print the equation to the console
				//System.out.println(myCharacter.getGrowths(i)+ "+" + myJob.getGrowths(i));
			}
		}
		// Calculate growths for children characters
		else if(myChildCharacter != null  && isChild) {
			// Calculate growths for Kana
			if(myChildCharacter instanceof Kana) {
				
			}
			else {
				Character temp = data.getCharacters().get(myChildCharacter.getVariedParent());
				for(int i = 0; i< growths.length; i++)
				{
					growths[i] = (myChildCharacter.getGrowths(i) + temp.getGrowths(i))/2.0 + myJob.getGrowths(i);
					//debug: Use this to print the equation to the console
					//System.out.println(myCharacter.getGrowths(i)+ "+" + myJob.getGrowths(i));
				}
			}
		}
	}
	
	private void calculateMaxStats()//maxmods in job starts with level :o
	{
		// Calculate maxStats for Avatar
		if(myCharacter != null && myCharacter instanceof Avatar)
		{
		
		}
		// Calculate maxStats for adult characters
		else if(myCharacter != null && !isChild) {
			for(int i = 0; i< maxstats.length; i++)
			{
				maxstats[i] = myCharacter.getMaxMods(i) + myJob.getMaxStats(i+1);
				//debug: Use this to print the equation to the console
				//System.out.println(myCharacter.getMaxMods(i)+ "+" + myJob.getMaxStats(i+1));
			}
		}
		// Calculate maxStats for children characters
		else if(myChildCharacter != null && isChild) {
			Character fixedParent = data.getCharacters().get(myChildCharacter.getFixedParent());
			Character variedParent = data.getCharacters().get(myChildCharacter.getVariedParent());
			
			// Calculate maxStats for Kana if variedParent is a child
			if(myChildCharacter instanceof Kana && variedParent.getIsChild()) {
				for(int i = 0; i < maxstats.length; i++) {
					// FORMULA FOR KANA'S WITH CHILD CHARACTER AS VARIEDPARENT MAXMODS: fixedParent's mods + variedParent's mods
					if(i == 0)
					{
						maxstats[i] =  myJob.getMaxStats(i+1);
					}
					else
					{
						maxstats[i] = (fixedParent.getMaxMods(i) + variedParent.getMaxMods(i)) + myJob.getMaxStats(i+1);
					}
				}
			}
			// Calculate maxStats for all children (including Kana if variedParent is not a child
			else
			{
				for(int i = 0; i < maxstats.length; i++) {
					// FORMULA FOR CHILD'S MAXMODS: fixedParent's mods + variedParent's mods + 1
					if(i == 0)
					{
						maxstats[i] =  myJob.getMaxStats(i+1);
					}
					else
					{
						maxstats[i] = (fixedParent.getMaxMods(i) + variedParent.getMaxMods(i) + 1) + myJob.getMaxStats(i+1);
					}
				}
			}
		}
	}	
	
	//This function reclasses a Unit, and recalculates the growths and maxes without changing the base stats.
	public void reClass(Job newJob)
	{
		myJob = newJob;
		calculateGrowths();
		calculateMaxStats();
	}
	
	public void printUnit()
	{
		if(myCharacter != null)
		{
			System.out.println("Name: "+myCharacter.getName()
								+"\n"+"Class: "+myJob.getName()
								+"\n" + "Route: " + route
								+"\n" + "Base level:" + level);
		}
		else if(myChildCharacter != null)
		{
			System.out.println("Name: "+myChildCharacter.getName()
			+"\n"+"Class: "+myJob.getName()
			+"\n" + "Route: " + route
			+"\n" + "Base level:" + level
			+"\nVariedParent: " + myChildCharacter.getVariedParent());
		}

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

	public double[] getBaseStats() {
		return baseStats;
	}

	public void setBaseStats(double[] baseStats) {
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
	
	public String[] getStatblock() {
		return statblock;
	}

	public void setStatblock(String[] statblock) {
		this.statblock = statblock;
	}

}
