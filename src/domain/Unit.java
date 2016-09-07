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
			Avatar avatar = (Avatar) myCharacter;
			for(int i = 0; i< baseStats.length; i++)
			{
				baseStats[i] = myCharacter.getBaseStats().getStats(route, i+1) - myJob.getBaseStats(i);
				//System.out.println(myCharacter.getBaseStats().getStats(route, i+1) + "+" + myJob.getBaseStats(i));
			}
			
			// Add Boons
			// HP, Mag, Skill, Lck +3
			// Str, Speed +2
			// Def, Res +1
			if(avatar.getBoon().equals("HP")) {
				baseStats[0] += 3;
			}
			else if(avatar.getBoon().equals("Str")) {
				baseStats[1] += 2;
			}
			else if(avatar.getBoon().equals("Mag")) {
				baseStats[2] += 3;
			}
			else if(avatar.getBoon().equals("Skl")) {
				baseStats[3] += 3;
			}
			else if(avatar.getBoon().equals("Spd")) {
				baseStats[4] += 2;
			}
			else if(avatar.getBoon().equals("Lck")) {
				baseStats[5] += 3;
			}
			else if(avatar.getBoon().equals("Def")) {
				baseStats[6] += 1;
			}
			else if(avatar.getBoon().equals("Res")) {
				baseStats[7] += 1;
			}
			
			// Subtract Banes
			// HP, Mag, Skill, Lck -2
			// Str, Speed -1
			// Def, Res -1
			if(avatar.getBane().equals("HP")) {
				baseStats[0] -= 2;
			}
			else if(avatar.getBane().equals("Str")) {
				baseStats[1] -= 1;
			}
			else if(avatar.getBane().equals("Mag")) {
				baseStats[2] -= 2;
			}
			else if(avatar.getBane().equals("Skl")) {
				baseStats[3] -= 2;
			}
			else if(avatar.getBane().equals("Spd")) {
				baseStats[4] -= 1;
			}
			else if(avatar.getBane().equals("Lck")) {
				baseStats[5] -= 2;
			}
			else if(avatar.getBane().equals("Def")) {
				baseStats[6] -= 1;
			}
			else if(avatar.getBane().equals("Res")) {
				baseStats[7] -= 1;
			}
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
			Avatar avatar = (Avatar) myCharacter;
			
			for(int i = 0; i< growths.length; i++)
			{
				growths[i] = myCharacter.getGrowths(i) + myJob.getGrowths(i);
				//debug: Use this to print the equation to the console
				//System.out.println(myCharacter.getGrowths(i)+ "+" + myJob.getGrowths(i));
			}
			
			/*	Add Boons
				HP = +15 HP[0], +5 Def[6], +5 Res[7]
				Str = +15 Str[1], +5 Skl[3], +5 Def[6]
				Mag = +20 Mag[2], +5 Spd[4], +5 Res[7]
				Skl = +5 Str[1], +25 Skl[3], +5 Def[6]
				Spd = +5 Skl[3], +15 Spd[4], +5 Lck[5]
				Lck = +5 Str[1], +5 Mag[2], +25 Lck[5]
				Def = +5 Lck[5], +10 Def[6], +5 Res[7]
				Res = +5 Mag[2], +5 Spd[4], +10 Res[7]
			*/

			// HP = +15 HP[0], +5 Def[6], +5 Res[7]
			if(avatar.getBoon().equals("HP")) {
				growths[0] += 15;
				growths[6] += 5;
				growths[7] += 5;
			}
			// Str = +15 Str[1], +5 Skl[3], +5 Def[6]
			else if(avatar.getBoon().equals("Str")) {
				growths[1] += 15;
				growths[3] += 5;
				growths[6] += 5;
			}
			// Mag = +20 Mag[2], +5 Spd[4], +5 Res[7]
			else if(avatar.getBoon().equals("Mag")) {
				growths[2] += 20;
				growths[5] += 5;
				growths[7] += 5;
			}
			// Skl = +5 Str[1], +25 Skl[3], +5 Def[6]
			else if(avatar.getBoon().equals("Skl")) {
				growths[1] += 5;
				growths[3] += 25;
				growths[6] += 5;
			}
			// Spd = +5 Skl[3], +15 Spd[4], +5 Lck[5]
			else if(avatar.getBoon().equals("Spd")) {
				growths[3] += 5;
				growths[4] += 15;
				growths[6] += 5;
			}
			// Lck = +5 Str[1], +5 Mag[2], +25 Lck[5]
			else if(avatar.getBoon().equals("Lck")) {
				growths[1] += 5;
				growths[2] += 5;
				growths[5] += 25;
			}
			// Def = +5 Lck[5], +10 Def[6], +5 Res[7]
			else if(avatar.getBoon().equals("Def")) {
				growths[5] += 5;
				growths[6] += 10;
				growths[7] += 5;
			}
			// Res = +5 Mag[2], +5 Spd[4], +10 Res[7]
			else if(avatar.getBoon().equals("Res")) {
				growths[2] += 5;
				growths[4] += 5;
				growths[7] += 10;
			}
			
			/*	Subtract Banes
				HP = -10 HP[0], -5 Def[6], -5 Res[7]
				Str = -10 Str[1], -5 Skl[3], -5 Def[6]
				Mag = -15 Mag[2], -5 Spd[4], -5 Res[7]
				Skl = -5 Str[1], -20 Skl[3], -5 Def[6]
				Spd = -5 Skl[3], -10 Spd[4], -5 Lck[5]
				Lck = -5 Str[1], -5 Mag[2], -20 Lck[5]
				Def = -5 Lck[5], -10 Def[6], -5 Res[7]
				Res = -5 Mag[2], -5 Spd[4], -10 Res[7]
			*/

			// HP = -10 HP[0], -5 Def[6], -5 Res[7]
			if(avatar.getBane().equals("HP")) {
				growths[0] -= 10;
				growths[6] -= 5;
				growths[7] -= 5;
			}
			// Str = -10 Str[1], -5 Skl[3], -5 Def[6]
			else if(avatar.getBane().equals("Str")) {
				growths[1] -= 10;
				growths[3] -= 5;
				growths[6] -= 5;
			}
			// Mag = -15 Mag[2], -5 Spd[4], -5 Res[7]
			else if(avatar.getBane().equals("Mag")) {
				growths[2] -= 15;
				growths[4] -= 5;
				growths[7] -= 5;
			}
			// Skl = -5 Str[1], -20 Skl[3], -5 Def[6]
			else if(avatar.getBane().equals("Skl")) {
				growths[1] -= 5;
				growths[3] -= 20;
				growths[5] -= 5;
			}
			// Spd = -5 Skl[3], -10 Spd[4], -5 Lck[5]
			else if(avatar.getBane().equals("Spd")) {
				growths[3] -= 5;
				growths[4] -= 10;
				growths[5] -= 5;
			}
			// Lck = -5 Str[1], -5 Mag[2], -20 Lck[5]
			else if(avatar.getBane().equals("Lck")) {
				growths[1] -= 5;
				growths[2] -= 5;
				growths[5] -= 20;
			}
			// Def = -5 Lck[5], -10 Def[6], -5 Res[7]
			else if(avatar.getBane().equals("Def")) {
				growths[5] -= 5;
				growths[6] -= 10;
				growths[7] -= 5;
			}
			// Res = -5 Mag[2], -5 Spd[4], -10 Res[7]
			else if(avatar.getBane().equals("Res")) {
				growths[2] -= 5;
				growths[4] -= 5;
				growths[7] -= 10;
			}
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
			Avatar avatar = (Avatar) myCharacter;
			
			for(int i = 0; i< maxstats.length; i++)
			{
				maxstats[i] = myCharacter.getMaxMods(i) + myJob.getMaxStats(i+1);
				//debug: Use this to print the equation to the console
				//System.out.println(myCharacter.getMaxMods(i)+ "+" + myJob.getMaxStats(i+1));
			}
			
			/*	Add Boons
				HP = +1 Str[1], +1 Mag[2], +2 Lck[5], +2 Def[6], +2 Res[7]
				Str = +4 Str[1], +2 Skl[3], +2 Def[6]
				Mag = +4 Mag[2], +2 Spd[4], +2 Res[7]
				Skl = +2 Str[1], +4 Skl[3], +2 Def[6]
				Spd = +2 Skl[3], +4 Spd[4], +2 Lck[5]
				Lck = +2 Str[1], +2 Mag[2], +4 Lck[5]
				Def = +2 Lck[5], +4 Def[6], +2 Res[7]
				Res = +2 Mag[2], +2 Spd[4], +4 Res[7]
			*/
			
			// HP = +1 Str[1], +1 Mag[2], +2 Lck[5], +2 Def[6], +2 Res[7]
			if(avatar.getBoon().equals("HP")) {
				maxstats[1] += 1;
				maxstats[2] += 1;
				maxstats[5] += 2;
				maxstats[6] += 2;
				maxstats[7] += 2;
			}
			// Str = +4 Str[1], +2 Skl[3], +2 Def[6]
			else if(avatar.getBoon().equals("Str")) {
				maxstats[1] += 4;
				maxstats[3] += 2;
				maxstats[6] += 2;
			}
			// Mag = +4 Mag[2], +2 Spd[4], +2 Res[7]
			else if(avatar.getBoon().equals("Mag")) {
				maxstats[2] += 4;
				maxstats[5] += 2;
				maxstats[7] += 2;
			}
			// Skl = +2 Str[1], +4 Skl[3], +2 Def[6]
			else if(avatar.getBoon().equals("Skl")) {
				maxstats[1] += 2;
				maxstats[3] += 4;
				maxstats[6] += 2;
			}
			// Spd = +2 Skl[3], +4 Spd[4], +2 Lck[5]
			else if(avatar.getBoon().equals("Spd")) {
				maxstats[3] += 2;
				maxstats[4] += 4;
				maxstats[6] += 2;
			}
			// Lck = +2 Str[1], +2 Mag[2], +4 Lck[5]
			else if(avatar.getBoon().equals("Lck")) {
				maxstats[1] += 2;
				maxstats[2] += 2;
				maxstats[5] += 4;
			}
			// Def = +2 Lck[5], +4 Def[6], +2 Res[7]
			else if(avatar.getBoon().equals("Def")) {
				maxstats[5] += 2;
				maxstats[6] += 4;
				maxstats[7] += 2;
			}
			// Res = +2 Mag[2], +2 Spd[4], +4 Res[7]
			else if(avatar.getBoon().equals("Res")) {
				maxstats[2] += 2;
				maxstats[4] += 2;
				maxstats[7] += 4;
			}
			
			/*	Subtract Banes
				HP = -1 Str[1], -1 Mag[2], -1 Lck[5], -1 Def[6], -1 Res[7]
				Str = -3 Str[1], -1 Skl[3], -1 Def[6]
				Mag = -3 Mag[2], -1 Spd[4], -1 Res[7]
				Skl = -1 Str[1], -3 Skl[3], -1 Def[6]
				Spd = -1 Skl[3], -3 Spd[4], -1 Lck[5]
				Lck = -1 Str[1], -1 Mag[2], -3 Lck[5]
				Def = -1 Lck[5], -3 Def[6], -1 Res[7]
				Res = -1 Mag[2], -1 Spd[4], -3 Res[7]
			*/
			
			// HP = -1 Str[1], -1 Mag[2], -1 Lck[5], -1 Def[6], -1 Res[7]
			if(avatar.getBane().equals("HP")) {
				maxstats[1] -= 1;
				maxstats[2] -= 1;
				maxstats[5] -= 1;
				maxstats[6] -= 1;
				maxstats[7] -= 1;
			}
			// Str = -3 Str[1], -1 Skl[3], -1 Def[6]
			else if(avatar.getBane().equals("Str")) {
				maxstats[1] -= 3;
				maxstats[3] -= 1;
				maxstats[6] -= 1;
			}
			// Mag = -3 Mag[2], -1 Spd[4], -1 Res[7]
			else if(avatar.getBane().equals("Mag")) {
				maxstats[2] -= 3;
				maxstats[4] -= 1;
				maxstats[7] -= 1;
			}
			// Skl = -1 Str[1], -3 Skl[3], -1 Def[6]
			else if(avatar.getBane().equals("Skl")) {
				maxstats[1] -= 1;
				maxstats[3] -= 3;
				maxstats[5] -= 1;
			}
			// Spd = -1 Skl[3], -3 Spd[4], -1 Lck[5]
			else if(avatar.getBane().equals("Spd")) {
				maxstats[3] -= 1;
				maxstats[4] -= 3;
				maxstats[5] -= 1;
			}
			// Lck = -1 Str[1], -1 Mag[2], -3 Lck[5]
			else if(avatar.getBane().equals("Lck")) {
				maxstats[1] -= 1;
				maxstats[2] -= 1;
				maxstats[5] -= 3;
			}
			// Def = -1 Lck[5], -3 Def[6], -1 Res[7]
			else if(avatar.getBane().equals("Def")) {
				maxstats[5] -= 1;
				maxstats[6] -= 3;
				maxstats[7] -= 1;
			}
			// Res = -1 Mag[2], -1 Spd[4], -3 Res[7]
			else if(avatar.getBane().equals("Res")) {
				maxstats[2] -= 1;
				maxstats[4] -= 1;
				maxstats[7] -= 3;
			}
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
