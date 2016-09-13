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
	
	// These variables will only be used for children
	DataStorage data = DataStorage.getInstance(); //This is the data
	private double[] fixedParentStats;
	private Unit fixedParent;
	private double[] variedParentStats;
	private Unit variedParent;
	
	// These variables will only be used for Avatar
	private String myBoon;
	private String myBane;
	
	// Constructor for a Parent Unit (non Avatar)
	public Unit(Character inputCharacter, Job inputJob, String inputRoute)
	{
		myCharacter = inputCharacter;
		myJob = inputJob;
		route = inputRoute;
		level = inputCharacter.getBaseStats().getStats(route, 0);
		
		calculateBaseStats();
		calculateGrowths();
		calculateMaxStats();
	}
	
	// Constructor for a Avatar Unit
	public Unit(Avatar inputCharacter, Job inputJob, String inputRoute, String boon, String bane)
	{
		myCharacter = inputCharacter;
		myJob = inputJob;
		route = inputRoute;
		level = inputCharacter.getBaseStats().getStats(route, 0);
		myBoon = boon;
		myBane = bane;
		
		calculateBaseStats();
		calculateGrowths();
		calculateMaxStats();
	}
	
	// Constructor for a Child Unit
	public Unit(ChildCharacter inputCharacter, Job inputJob, String inputRoute, double[] fixedParentInputStats, Unit fixedParent, double[] variedParentInputStats, Unit variedParent, int startLevel)
	{
		myCharacter = inputCharacter;
		myJob = inputJob;
		route = inputRoute;
		level = startLevel;
		this.fixedParentStats = fixedParentInputStats;
		this.fixedParent = fixedParent;
		this.variedParentStats = variedParentInputStats;
		this.variedParent = variedParent;
		calculateGrowths();
		calculateBaseStats();
		calculateMaxStats();
	}
	
	private void calculateBaseStats()
	{
		// Calculate baseStats for Avatar
		if(myCharacter instanceof Avatar)
		{
			Avatar avatar = (Avatar) myCharacter;
			avatar.setBoon(myBoon);
			avatar.setBane(myBane);
			
			for(int i = 0; i< baseStats.length; i++)
			{
				baseStats[i] = myCharacter.getBaseStats().getStats(route, i+1) /*- myJob.getBaseStats(i)*/;
				//System.out.println(myCharacter.getBaseStats().getStats(route, i+1) + "+" + myJob.getBaseStats(i));
			}
			
			// Add Boons
			// HP, Mag, Skill, Lck +3
			// Str, Speed +2
			// Def, Res +1
			if(avatar.getBoon().equals(data.getBoons()[0])) {
				baseStats[0] += 3;
			}
			else if(avatar.getBoon().equals(data.getBoons()[1])) {
				baseStats[1] += 2;
			}
			else if(avatar.getBoon().equals(data.getBoons()[2])) {
				baseStats[2] += 3;
			}
			else if(avatar.getBoon().equals(data.getBoons()[3])) {
				baseStats[3] += 3;
			}
			else if(avatar.getBoon().equals(data.getBoons()[4])) {
				baseStats[4] += 2;
			}
			else if(avatar.getBoon().equals(data.getBoons()[5])) {
				baseStats[5] += 3;
			}
			else if(avatar.getBoon().equals(data.getBoons()[6])) {
				baseStats[6] += 1;
			}
			else if(avatar.getBoon().equals(data.getBoons()[7])) {
				baseStats[7] += 1;
			}
			
			// Subtract Banes
			// HP, Mag, Skill, Lck -2
			// Str, Speed -1
			// Def, Res -1
			if(avatar.getBane().equals(data.getBanes()[0])) {
				baseStats[0] -= 2;
			}
			else if(avatar.getBane().equals(data.getBanes()[1])) {
				baseStats[1] -= 1;
			}
			else if(avatar.getBane().equals(data.getBanes()[2])) {
				baseStats[2] -= 2;
			}
			else if(avatar.getBane().equals(data.getBanes()[3])) {
				baseStats[3] -= 2;
			}
			else if(avatar.getBane().equals(data.getBanes()[4])) {
				baseStats[4] -= 1;
			}
			else if(avatar.getBane().equals(data.getBanes()[5])) {
				baseStats[5] -= 2;
			}
			else if(avatar.getBane().equals(data.getBanes()[6])) {
				baseStats[6] -= 1;
			}
			else if(avatar.getBane().equals(data.getBanes()[7])) {
				baseStats[7] -= 1;
			}
		}
		// Calculate baseStats for adult characters //MIGHT BE UNECESSARY
		else if(!myCharacter.getIsChild())
		{
			for(int i = 0; i< baseStats.length; i++)
			{
				baseStats[i] = myCharacter.getBaseStats().getStats(route, i+1);// - myJob.getBaseStats(i);
				//System.out.println(myCharacter.getBaseStats().getStats(route, i+1) + "+" + myJob.getBaseStats(i));
			}
			
		}
		// Calculate baseStats for child characters
		else if(myCharacter instanceof ChildCharacter)
		{
			ChildCharacter myChildCharacter = (ChildCharacter) myCharacter;
			
			// Get the unit's base job and then get that base job's growths
			Job tempBaseJob = data.getJobs().get(myChildCharacter.getBaseClass());
			int[] tempGrowths = tempBaseJob.getGrowths();
			
			// Get the number of levels over 10 the unit is. If n < 0, something in the input is invalid.
			int n = level - 10;
			
			// calculate each stat
			for(int i = 0; i< baseStats.length; i++)
			{
				// FORUMLA: c = Child's absolute base stats + {(n x Child's full growth rates) rounded down}
				//      Child's absolute base stat							 + ROUNDED DOWN	  (n * full growths while in the character's base class)
				double c = myChildCharacter.getBaseStats().getStats(route, i+1) + n * ((growths[i] - myJob.getGrowths(i) + tempGrowths[i])/100.0);
				//System.out.println(statblock[i] + ": " + c);
				// Calculate the fixedParent's and variedParent's personal stats by subtracting the base stats of their job
				double fixedParentPersonal = fixedParentStats[i] - fixedParent.getMyJob().getBaseStats(i);
				double variedParentPersonal = variedParentStats[i] - variedParent.getMyJob().getBaseStats(i);
				//System.out.println("fixedParentPersonal" + statblock[i] + ": " + fixedParentPersonal);
				//System.out.println("variedParentPersonal" + statblock[i] + ": " + variedParentPersonal);
				
				// fatherCalc and motherCalc are used to calculate inheritanceTotal. Neither value can be negative and any negative values will become 0.
				double fixedParentCalc = fixedParentPersonal - c;
				double variedParentCalc = variedParentPersonal - c;
				
				if(fixedParentCalc < 0)
					fixedParentCalc = 0;
				
				if(variedParentCalc < 0)
					variedParentCalc = 0;
				
				//System.out.println("fixedParentCalc" + statblock[i] + ": " + fixedParentCalc);
				//System.out.println("variedParentCalc" + statblock[i] + ": " + variedParentCalc);
				// FORMULA: inheritanceTotal = {[(fixedParent's personal stats – c; 0 if < 0) + (variedParent's personal stats – c; 0 if < 0)] / 4; cannot be > than (c/10) +2 rounded down}
				double inheritanceTotal = (fixedParentCalc + variedParentCalc)/4.0;
				
				if(inheritanceTotal > Math.floor((c/10.0) + 2.0))
					inheritanceTotal = Math.floor((c/10.0) + 2.0);
				
				
				//System.out.println("InheritanceTotal" + statblock[i] + ": " + inheritanceTotal);
				// FORMULA: Job base stats + c (see above) + inheritanceTotal (see above)
				baseStats[i] = myJob.getBaseStats(i) + c + inheritanceTotal;
				
				if(baseStats[i] < 0)
					baseStats[i] = 0;
			}
		}
	}
	
	private void calculateGrowths()
	{
		// Calculate growths for Avatar
		if(myCharacter instanceof Avatar) {
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
			if(avatar.getBoon().equals(data.getBoons()[0])) {
				growths[0] += 15;
				growths[6] += 5;
				growths[7] += 5;
			}
			// Str = +15 Str[1], +5 Skl[3], +5 Def[6]
			else if(avatar.getBoon().equals(data.getBoons()[1])) {
				growths[1] += 15;
				growths[3] += 5;
				growths[6] += 5;
			}
			// Mag = +20 Mag[2], +5 Spd[4], +5 Res[7]
			else if(avatar.getBoon().equals(data.getBoons()[2])) {
				growths[2] += 20;
				growths[4] += 5;
				growths[7] += 5;
			}
			// Skl = +5 Str[1], +25 Skl[3], +5 Def[6]
			else if(avatar.getBoon().equals(data.getBoons()[3])) {
				growths[1] += 5;
				growths[3] += 25;
				growths[6] += 5;
			}
			// Spd = +5 Skl[3], +15 Spd[4], +5 Lck[5]
			else if(avatar.getBoon().equals(data.getBoons()[4])) {
				growths[3] += 5;
				growths[4] += 15;
				growths[6] += 5;
			}
			// Lck = +5 Str[1], +5 Mag[2], +25 Lck[5]
			else if(avatar.getBoon().equals(data.getBoons()[5])) {
				growths[1] += 5;
				growths[2] += 5;
				growths[5] += 25;
			}
			// Def = +5 Lck[5], +10 Def[6], +5 Res[7]
			else if(avatar.getBoon().equals(data.getBoons()[6])) {
				growths[5] += 5;
				growths[6] += 10;
				growths[7] += 5;
			}
			// Res = +5 Mag[2], +5 Spd[4], +10 Res[7]
			else if(avatar.getBoon().equals(data.getBoons()[7])) {
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
			if(avatar.getBane().equals(data.getBanes()[0])) {
				growths[0] -= 10;
				growths[6] -= 5;
				growths[7] -= 5;
			}
			// Str = -10 Str[1], -5 Skl[3], -5 Def[6]
			else if(avatar.getBane().equals(data.getBanes()[1])) {
				growths[1] -= 10;
				growths[3] -= 5;
				growths[6] -= 5;
			}
			// Mag = -15 Mag[2], -5 Spd[4], -5 Res[7]
			else if(avatar.getBane().equals(data.getBanes()[2])) {
				growths[2] -= 15;
				growths[4] -= 5;
				growths[7] -= 5;
			}
			// Skl = -5 Str[1], -20 Skl[3], -5 Def[6]
			else if(avatar.getBane().equals(data.getBanes()[3])) {
				growths[1] -= 5;
				growths[3] -= 20;
				growths[5] -= 5;
			}
			// Spd = -5 Skl[3], -10 Spd[4], -5 Lck[5]
			else if(avatar.getBane().equals(data.getBanes()[4])) {
				growths[3] -= 5;
				growths[4] -= 10;
				growths[5] -= 5;
			}
			// Lck = -5 Str[1], -5 Mag[2], -20 Lck[5]
			else if(avatar.getBane().equals(data.getBanes()[5])) {
				growths[1] -= 5;
				growths[2] -= 5;
				growths[5] -= 20;
			}
			// Def = -5 Lck[5], -10 Def[6], -5 Res[7]
			else if(avatar.getBane().equals(data.getBanes()[6])) {
				growths[5] -= 5;
				growths[6] -= 10;
				growths[7] -= 5;
			}
			// Res = -5 Mag[2], -5 Spd[4], -10 Res[7]
			else if(avatar.getBane().equals(data.getBanes()[7])) {
				growths[2] -= 5;
				growths[4] -= 5;
				growths[7] -= 10;
			}
		}
		// Calculate growths for adult characters
		else if(!myCharacter.getIsChild())
		{
			for(int i = 0; i< growths.length; i++)
			{
				growths[i] = myCharacter.getGrowths(i) + myJob.getGrowths(i);
				//debug: Use this to print the equation to the console
				//System.out.println(myCharacter.getGrowths(i)+ "+" + myJob.getGrowths(i));
			}
		}
		// Calculate growths for children characters
		// NOTE: Kana is UNAFFECTED by Avatar in terms of growths!!!!!
		else {
			//Character temp = data.getCharacters().get(myChildCharacter.getVariedParent());
			for(int i = 0; i< growths.length; i++)
			{
				growths[i] = (myCharacter.getGrowths(i) + variedParent.getGrowths()[i])/2.0 + myJob.getGrowths(i);
				//debug: Use this to print the equation to the console
				//System.out.println(myCharacter.getGrowths(i)+ "+" + myJob.getGrowths(i));
			}
		}
	}
	
	private void calculateMaxStats()//maxmods in job starts with level :o
	{
		// Calculate maxStats for Avatar
		if(myCharacter instanceof Avatar)
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
			if(avatar.getBoon().equals(data.getBoons()[0])) {
				maxstats[1] += 1;
				maxstats[2] += 1;
				maxstats[5] += 2;
				maxstats[6] += 2;
				maxstats[7] += 2;
			}
			// Str = +4 Str[1], +2 Skl[3], +2 Def[6]
			else if(avatar.getBoon().equals(data.getBoons()[1])) {
				maxstats[1] += 4;
				maxstats[3] += 2;
				maxstats[6] += 2;
			}
			// Mag = +4 Mag[2], +2 Spd[4], +2 Res[7]
			else if(avatar.getBoon().equals(data.getBoons()[2])) {
				maxstats[2] += 4;
				maxstats[4] += 2;
				maxstats[7] += 2;
			}
			// Skl = +2 Str[1], +4 Skl[3], +2 Def[6]
			else if(avatar.getBoon().equals(data.getBoons()[3])) {
				maxstats[1] += 2;
				maxstats[3] += 4;
				maxstats[6] += 2;
			}
			// Spd = +2 Skl[3], +4 Spd[4], +2 Lck[5]
			else if(avatar.getBoon().equals(data.getBoons()[4])) {
				maxstats[3] += 2;
				maxstats[4] += 4;
				maxstats[6] += 2;
			}
			// Lck = +2 Str[1], +2 Mag[2], +4 Lck[5]
			else if(avatar.getBoon().equals(data.getBoons()[5])) {
				maxstats[1] += 2;
				maxstats[2] += 2;
				maxstats[5] += 4;
			}
			// Def = +2 Lck[5], +4 Def[6], +2 Res[7]
			else if(avatar.getBoon().equals(data.getBoons()[6])) {
				maxstats[5] += 2;
				maxstats[6] += 4;
				maxstats[7] += 2;
			}
			// Res = +2 Mag[2], +2 Spd[4], +4 Res[7]
			else if(avatar.getBoon().equals(data.getBoons()[7])) {
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
			if(avatar.getBane().equals(data.getBanes()[0])) {
				maxstats[1] -= 1;
				maxstats[2] -= 1;
				maxstats[5] -= 1;
				maxstats[6] -= 1;
				maxstats[7] -= 1;
			}
			// Str = -3 Str[1], -1 Skl[3], -1 Def[6]
			else if(avatar.getBane().equals(data.getBanes()[1])) {
				maxstats[1] -= 3;
				maxstats[3] -= 1;
				maxstats[6] -= 1;
			}
			// Mag = -3 Mag[2], -1 Spd[4], -1 Res[7]
			else if(avatar.getBane().equals(data.getBanes()[2])) {
				maxstats[2] -= 3;
				maxstats[4] -= 1;
				maxstats[7] -= 1;
			}
			// Skl = -1 Str[1], -3 Skl[3], -1 Def[6]
			else if(avatar.getBane().equals(data.getBanes()[3])) {
				maxstats[1] -= 1;
				maxstats[3] -= 3;
				maxstats[5] -= 1;
			}
			// Spd = -1 Skl[3], -3 Spd[4], -1 Lck[5]
			else if(avatar.getBane().equals(data.getBanes()[4])) {
				maxstats[3] -= 1;
				maxstats[4] -= 3;
				maxstats[5] -= 1;
			}
			// Lck = -1 Str[1], -1 Mag[2], -3 Lck[5]
			else if(avatar.getBane().equals(data.getBanes()[5])) {
				maxstats[1] -= 1;
				maxstats[2] -= 1;
				maxstats[5] -= 3;
			}
			// Def = -1 Lck[5], -3 Def[6], -1 Res[7]
			else if(avatar.getBane().equals(data.getBanes()[6])) {
				maxstats[5] -= 1;
				maxstats[6] -= 3;
				maxstats[7] -= 1;
			}
			// Res = -1 Mag[2], -1 Spd[4], -3 Res[7]
			else if(avatar.getBane().equals(data.getBanes()[7])) {
				maxstats[2] -= 1;
				maxstats[4] -= 1;
				maxstats[7] -= 3;
			}
		}
		// Calculate maxStats for adult characters
		else if(!myCharacter.getIsChild()) {
			for(int i = 0; i< maxstats.length; i++)
			{
				maxstats[i] = myCharacter.getMaxMods(i) + myJob.getMaxStats(i+1);
				//debug: Use this to print the equation to the console
				//System.out.println(myCharacter.getMaxMods(i)+ "+" + myJob.getMaxStats(i+1));
			}
		}
		// Calculate maxStats for children characters
		else {
			//Character fixedParent = data.getCharacters().get(myChildCharacter.getFixedParent());
			//Character variedParent = data.getCharacters().get(myChildCharacter.getVariedParent());
			
			// Calculate maxStats for Kana if variedParent is a child
			if(myCharacter instanceof Kana && variedParent.getMyCharacter().getIsChild()) {
				for(int i = 0; i < maxstats.length; i++) {
					// FORMULA FOR KANA'S WITH CHILD CHARACTER AS VARIEDPARENT MAXMODS: fixedParent's mods + variedParent's mods
					if(i == 0)
					{
						maxstats[i] =  myJob.getMaxStats(i+1);
					}
					else
					{
						maxstats[i] = ((fixedParent.getMaxstats()[i] - fixedParent.getMyJob().getMaxStats(i+1)) + (variedParent.getMaxstats()[i] - variedParent.getMyJob().getMaxStats(i+1))) + myJob.getMaxStats(i+1);
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
						maxstats[i] = ((fixedParent.getMaxstats()[i] - fixedParent.getMyJob().getMaxStats(i+1)) + (variedParent.getMaxstats()[i] - variedParent.getMyJob().getMaxStats(i+1)) + 1) + myJob.getMaxStats(i+1);
					}
				}
			}
		}
	}	
	
	//This function reclasses a Unit, and recalculates the growths and maxes without changing the base stats.
	public void reclass(Job newJob)
	{				
		//Adjusts base stats
		int[] newStatMods = newJob.getBaseStats();
		int[] oldStatMods = myJob.getBaseStats();
				
		for(int j = 0; j < newStatMods.length; j++)
		{
			baseStats[j] -= oldStatMods[j];
			baseStats[j] += newStatMods[j];
		}
		
		//Sets the current class as the new class, and readjusts growths and maxes
		myJob = newJob;
		calculateGrowths();
		calculateMaxStats();
	}
	
	public void printUnit()
	{
		if(myCharacter instanceof ChildCharacter)
		{
			ChildCharacter myChildCharacter = (ChildCharacter) myCharacter;
			System.out.println("Name: "+myChildCharacter.getName()
			+"\n"+"Class: "+myJob.getName()
			+"\n" + "Route: " + route
			+"\n" + "Base level:" + level
			+"\nVariedParent: " + variedParent.getMyCharacter().getName());
		}
		else 
		{
			System.out.println("Name: "+myCharacter.getName()
								+"\n"+"Class: "+myJob.getName()
								+"\n" + "Route: " + route
								+"\n" + "Base level:" + level);
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
	public void setFixedParentStats(double[] fixedParentStats) {
		this.fixedParentStats = fixedParentStats;
	}

	public void setFixedParent(Unit fixedParent) {
		this.fixedParent = fixedParent;
	}

	public void setVariedParentStats(double[] variedParentStats) {
		this.variedParentStats = variedParentStats;
	}

	public void setVariedParent(Unit variedParent) {
		this.variedParent = variedParent;
	}

	public void setMyBoon(String myBoon) {
		this.myBoon = myBoon;
	}

	public void setMyBane(String myBane) {
		this.myBane = myBane;
	}
}
