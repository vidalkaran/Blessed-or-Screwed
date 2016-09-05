//TODO:
//Need to format decimals and check for max stats!! :O

package logic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DecimalFormat;

import java.util.ArrayList;
import domain.Unit;
import json.DataStorage;
import domain.Character;
import domain.Job;

public class UnitController {

	private static UnitController instance = null;	// DataStorage singleton instance	
	DataStorage data = DataStorage.getInstance(); //This is the data
	ArrayList<Unit> localUnitSheet = new ArrayList(); //this unitSheet is calculated to compare the input one against.
	ArrayList<Unit> inputUnitSheet = new ArrayList(); //this is unitSheet that users will input. This is compared against localUnitSheet.
	
	//THIS IS WHERE THE CURRENT CHARACTER AND JOB ARE DEFINED AS PER THE GUI.
	public Character currentChar;
	public Job currentJob;
	public String currentRoute;
	public int currentLevel;
	public double[] inputStats;
	public ArrayList<String> classHistory; //the class history, this is shared between the localUnitSheet and inputUnitSheet.

	// prevents instantiation
	private UnitController() {
		
	}
	
	// establish singleton pattern - only one one instance of UnitController to exist in the project
	public static UnitController getInstance() {
		if(instance == null) {
			instance = new UnitController();
		}
		return instance;
	}

	//BUILDS A LOCALUNITSHEET
	public void buildLocalUnitSheet()
	{		
		Unit localUnit = new Unit(currentChar, currentJob, currentRoute);
		addBaseClassMods(localUnit);
		localUnitSheet.clear();
		buildSheet(localUnit, classHistory, localUnitSheet, 0);
	}
	
	//BUILDS A INPUTUNITSHEET
	public void buildInputUnitSheet()
	{
		Unit inputUnit = new Unit(currentChar, currentJob, currentRoute);
		inputUnit.setLevel(currentLevel);
		inputUnit.setBaseStats(inputStats);		
		inputUnitSheet.clear();
		buildSheet(inputUnit, classHistory, localUnitSheet, 0);
	}

	
//THIS IS THE MATH ZONE. BEWARE
	
	//RECURSION WHAT :O
	public void buildSheet(Unit unit, ArrayList<String> inputClassHistory, ArrayList<Unit> inputSheet, int i)
	{
		Unit newUnit = (Unit)deepClone(unit); //this is the deep clone. Very important!!
		
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
	public void checkJob(Unit unit, ArrayList<String> inputClassHistory, int i)
	{
		if(inputClassHistory.get(i).equals(unit.getMyJob().getName()) != true)
		{
			Job newJob = new Job();
			Job oldJob = new Job();

			newJob = data.getJobs().get(inputClassHistory.get(i));
			oldJob = unit.getMyJob();
			
			int[] newStatMods = newJob.getBaseStats();
			int[] oldStatMods = oldJob.getBaseStats();
			
			double[] unitBaseStats = unit.getBaseStats();
			
			for(int j = 0; j < newStatMods.length; j++)
			{
				newStatMods[j]-=oldStatMods[j];
				unitBaseStats[j]+=newStatMods[j];
			}
			unit.reClass(newJob);
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
	
	//PRINTER METHOD FOR TESTING 
	public void printLocalSheet()
	{
		for(int i = 0; i<localUnitSheet.size();i++)
		{
			System.out.println("--------------------------------------------");
			localUnitSheet.get(i).printUnit();	
		}
		
	}
	
	//Method for reclassing, only affects jobHistory
	public void reclass(String newJob, int changeLevel)
	{
		int levelVector = changeLevel-currentChar.getBaseStats().getStats(currentRoute, 0);
		
		for(int i = 0; i<(classHistory.size()-levelVector); i++)
		{
			String input = ("Lvl "+(changeLevel+i)+". "+newJob);
			classHistory.set((levelVector+i), input);
		}
	}
	
//ALL GETTERS/SETTERS
	public ArrayList<Unit> getLocalUnitSheet() {
		return localUnitSheet;
	}

	public ArrayList<Unit> getInputUnitSheet() {
		return inputUnitSheet;
	}

	public Character getCurrentChar() {
		return currentChar;
	}

	public void setCurrentChar(Character currentChar) {
		this.currentChar = currentChar;
	}

	public Job getCurrentJob() {
		return currentJob;
	}

	public void setCurrentJob(Job currentJob) {
		this.currentJob = currentJob;
	}

	public String getCurrentRoute() {
		return currentRoute;
	}

	public void setCurrentRoute(String currentRoute) {
		this.currentRoute = currentRoute;
	}

	public int getCurrentLevel() {
		return currentLevel;
	}

	public void setCurrentLevel(int currentLevel) {
		this.currentLevel = currentLevel;
	}

	public double[] getInputStats() {
		return inputStats;
	}

	public void setInputStats(double[] inputStats) {
		this.inputStats = inputStats;
	}

	public ArrayList<String> getClassHistory() {
		return classHistory;
	}

	public void setClassHistory(ArrayList<String> classHistory) {
		this.classHistory = classHistory;
	}
}

