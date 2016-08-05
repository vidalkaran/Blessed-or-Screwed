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
	
	public ArrayList<Unit> buildUnitSheet(Unit unit, ArrayList<String> inputClassHistory)
	{
		ArrayList<Unit> unitSheet = new ArrayList();

		//1. adds class mods
		for(int i = 0; i<unit.getBaseStats().length;i++)
		{
			double[] baseStats = unit.getBaseStats();
			int[] Mods = data.getJobs().get(unit.getMyJob().getName()).getBaseStats();
			
			baseStats[i] = baseStats[i] + Mods[i];
			
			unit.setBaseStats(baseStats);
		}
		
		//3. THIS STARTS THE LOOP.
		for(int i = 0; i<inputClassHistory.size();i++)
		{	
			//4. Ensures job is the same, reclasses the unit if not, and adjusts base stats as necessary
			if(inputClassHistory.get(i) != unit.getMyJob().getName())
			{
				Job newJob = data.getJobs().get(inputClassHistory.get(i));
				Job oldJob = unit.getMyJob();
				
				//5. Reclass
				unit.reClass(newJob);
				
				int[] tempStatMods = newJob.getBaseStats();
				double[] statMods = unit.getBaseStats();
				
				//6. this adjusts the mods if the class has changed.
				for(int j = 0; j<tempStatMods.length;j++)
				{
					tempStatMods[i] = tempStatMods[i] - oldJob.getBaseStats(i);
					statMods[i] = statMods[i] + tempStatMods[i];
				}	
			}
			//7 Calculate!! Also update the unit's new level.
			CalculateAverageStats(unit, (unit.getLevel()+1));
			unitSheet.add(unit);
		}
		return unitSheet;
	}
	

	//calculates averagestats for a unit
	public void CalculateAverageStats(Unit inputUnit, int inputLevel)
	{
		int levelDifference = (inputLevel - inputUnit.getLevel());
		
		for(int i = 0; i< inputUnit.getBaseStats().length; i++)
		{
			double[] tempBaseStats = inputUnit.getBaseStats();
			double[] tempGrowths = inputUnit.getGrowths();
			
			tempBaseStats[i] = (((tempGrowths[i]/100) * levelDifference) +  tempBaseStats[i]);
		}
		
		inputUnit.setLevel(inputLevel);
	}
	
	//PRINTER METHOD FOR TESTING 
	public void print(ArrayList<Unit> inputSheet)
	{
		for(int i = 0; i<inputSheet.size();i++)
		{
			inputSheet.get(i).printUnit();	
		}
		
	}
}
