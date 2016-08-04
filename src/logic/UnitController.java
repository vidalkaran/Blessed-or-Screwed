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
import domain.Character;
import domain.Job;

public class UnitController {
	
	private ArrayList<String> classHistory; 
	
	public UnitController()
	{
		
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
		//begin loop. Loop should be equal to history length(The length of this array should be equal to the max level of a unit.
		//check job array to make sure unit job matches the history.
		//add correct job base stats to the unit's stats.
		//calculate average stats.
		//repeat until end end.
		
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
