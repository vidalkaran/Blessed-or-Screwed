package logic;

/*Consider the components
 * Must be capable of creating, accessing, returning, and editing two seperate lists of two units. 
 * These units will be inputUnit and localUnit. These units will be stored in a inputSheet and localSheet, which will be lists. 
 * The lists will be of variable length and will simply keep track of a unit throughout it's history. It will have to calculate stats
 * based on their growths and job. 
 * 
 * General concept: Generate Unit --> Generate unitSheet. unitSHeet is generated in a loop that just creates new permutations of the original unit
 * and adds them to a list in order. In each case the unit's level will increment by one.  
 * 
 * Things we might need:
 * Job Change component- Job changes occur at certain levels. A unit can have a seperate array of what job it was at what level. This can be used
 * to check and make sure the unitSheet is the correct length, and also within the loop to dynamically change jobs.
 * 
 * Functions:
 * 
 * buildUnit(), buildUnitSheet
 * 
 * CalculateAverageStats
 * 
 */

import java.util.ArrayList;
import domain.Unit;
import json.DataStorage;
import domain.Character;
import domain.Job;

public class UnitController {
	
	DataStorage data = DataStorage.getInstance(); //This is the data
	
	private ArrayList<String> classHistory; 
	
	public UnitController(DataStorage inputData)
	{
		data = inputData;
	}
	
	//builds a unit
	public Unit buildUnit(Character character, Job job, String route)
	{
		Unit newUnit = new Unit(character, job, route);
		return newUnit;
	}
	
	//builds a unitSheet
	public ArrayList<Unit> builtUnitSheet(Unit unit, ArrayList<String> inputClassHistory)
	{
		//steps
		//1.add class mods
		//2.begin loop. Loop should be equal to history length(The length of this array should be equal to the max level of a unit.
		//3.check job array to make sure unit job matches the history.
		//4.Reclass, changing the maxes and the growths.
		//5.add correct job base stats to the unit's stats.
		//6.calculate average stats.
		//repeat until end end.
		
		//1. adds class mods
		for(int i = 0; i<unit.getBaseStats().length;i++)
		{
			int[] baseStats = unit.getBaseStats();
			int[] Mods = data.getJobs().get(unit.getMyJob().getName()).getBaseStats();
			
			baseStats[i] = baseStats[i] + Mods[i];
			
			unit.setBaseStats(baseStats);
		}
		
		//2. THIS STARTS THE LOOP
		for(int i = 0; i<inputClassHistory.size();i++)
		{
			//3. Ensures job is the same, reclasses the unit if not, and adjusts base stats as necessary
			if(inputClassHistory.get(i) != unit.getMyJob().getName())
			{
				Job newJob = data.getJobs().get(inputClassHistory.get(i));
				Job oldJob = unit.getMyJob();
				
				//4. Reclass
				unit.reClass(newJob);
				
				int[] tempStatMods = newJob.getBaseStats();
				int[] statMods = unit.getBaseStats();
				
				//5. this adjusts the mods if the class has changed.
				for(int j = 0; j<tempStatMods.length;j++)
				{
					tempStatMods[i] = tempStatMods[i] - oldJob.getBaseStats(i);
					statMods[i] = statMods[i] + tempStatMods[i];
				}
			}
		}
		
		ArrayList<Unit> unitSheet = new ArrayList();
		return unitSheet;
	}
	
	//calculates averagestats for a unit
	public void CalculateAverageStats(Unit inputUnit, int inputLevel)
	{
		int levelDifference = (inputLevel - inputUnit.getLevel());
		
		for(int i = 0; i<= inputUnit.getBaseStats().length; i++)
		{
			int[] tempBaseStats = inputUnit.getBaseStats();
			double[] tempGrowths = inputUnit.getGrowths();
			
			tempBaseStats[i] = (((int) tempGrowths[i] * levelDifference)+  tempBaseStats[i]);
		}
	}
	
}
