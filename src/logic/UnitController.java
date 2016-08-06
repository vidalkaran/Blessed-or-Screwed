package logic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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
	private ArrayList<String> classHistory; //the class history, this is shared between the localUnitSheet and inputUnitSheet.
	ArrayList<Unit> localUnitSheet = new ArrayList(); //this unitSheet is calculated to compare the input one against.
	ArrayList<Unit> inputUnitSheet = new ArrayList(); //this is unitSheet that users will input. This is compared against localUnitSheet.
	
	public UnitController(DataStorage inputData)
	{
		data = inputData;
	}
	
	public Unit buildUnit(Character inputCharacter, Job inputJob, String route)
	{
		Unit outputUnit = new Unit(inputCharacter, inputJob, route);
		addBaseClassMods(outputUnit);
		return outputUnit;
	}
	
	//THIS IS INVOKED WHENEVER A NEW LOCALUNITSHEET IS GENERATED. IT CLEARS THE PREVIOUS ONE FIRST.
	public void buildLocalUnitSheet(Unit unit, ArrayList<String> inputClassHistory)
	{		
		localUnitSheet.clear();
		buildSheet(unit, inputClassHistory, localUnitSheet, 0);
	}
	
//RECURSION WHAT :O
	public void buildSheet(Unit unit, ArrayList<String> inputClassHistory, ArrayList<Unit> inputSheet, int i)
	{
		Unit newUnit = (Unit)deepClone(unit);
		if(i == inputClassHistory.size()) //ends recursion
		{
			System.out.println("Complete"); 
		}
		else if(i == 0) //adds an initial base unit if list is empty
		{
			inputSheet.add(newUnit);
			i++;
			buildSheet(newUnit, inputClassHistory, localUnitSheet, i);
		}
		else //this does the fun stuff!
		{
			checkJob(newUnit, inputClassHistory, i);
			CalculateAverageStats(newUnit, (newUnit.getLevel()+1));
			inputSheet.add(newUnit);
			i++;
			buildSheet(newUnit, inputClassHistory, localUnitSheet, i);
		}
	}
	
	//This adds the base class mods
	public void addBaseClassMods(Unit unit)
	{
		for(int i = 0; i<unit.getBaseStats().length;i++)
		{
			double[] baseStats = unit.getBaseStats();
			int[] Mods = data.getJobs().get(unit.getMyJob().getName()).getBaseStats();
			
			baseStats[i] = baseStats[i] + Mods[i];
			
			unit.setBaseStats(baseStats);
		}
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
	
	//this checks the job, and adjusts base, growths, and maxes accordingly.
	public void checkJob(Unit unit, ArrayList<String> inputClassHistory, int loopCheck)
	{
		if(inputClassHistory.get(loopCheck).equals(unit.getMyJob().getName()) != true)
		{
			Job newJob = new Job();
			Job oldJob = new Job();

			newJob = data.getJobs().get(inputClassHistory.get(loopCheck));
			oldJob = unit.getMyJob();
			
			int[] newStatMods = newJob.getBaseStats();
			int[] oldStatMods = oldJob.getBaseStats();
			
			double[] unitBaseStats = unit.getBaseStats();
			
			for(int i = 0; i < newStatMods.length; i++)
			{
				newStatMods[i]-=oldStatMods[i];
				unitBaseStats[i]+=newStatMods[i];
			}
			unit.reClass(newJob);
		}
	}
	
	//PRINTER METHOD FOR TESTING 
	public void printLocalSheet()
	{
		for(int i = 0; i<localUnitSheet.size();i++)
		{
			System.out.println("--------------------------------------------");
			localUnitSheet.get(i).printUnit();	
		}
		
	}

	//Code for performing a deep clone
	public static Object deepClone(Object object) 
	{
	    try {
	      ByteArrayOutputStream baos = new ByteArrayOutputStream();
	      ObjectOutputStream oos = new ObjectOutputStream(baos);
	      oos.writeObject(object);
	      ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
	      ObjectInputStream ois = new ObjectInputStream(bais);
	      return ois.readObject();
	    }
	    catch (Exception e) {
	      e.printStackTrace();
	      return null;
	    }
	}

}

