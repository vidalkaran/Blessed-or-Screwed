//TODO:
//Need to format decimals and check for max stats!! :O

package logic;

import java.util.ArrayList;
import java.util.TreeMap;
import com.rits.cloning.Cloner;
import domain.Unit;
import json.DataStorage;
import domain.Character;
import domain.ChildCharacter;
import domain.Job;

public class UnitController {

	private static UnitController instance = null; // UnitController singleton
													// instance
	DataStorage data = DataStorage.getInstance(); // This is the data
	ArrayList<Unit> localUnitSheet = new ArrayList<Unit>(); // this unitSheet is
															// calculated to
															// compare the input
															// one against.
	ArrayList<Unit> inputUnitSheet = new ArrayList<Unit>(); // this is unitSheet
															// that users will
															// input. This is
															// compared against
															// localUnitSheet.

	// THIS IS WHERE THE CURRENT CHARACTER AND JOB ARE DEFINED AS PER THE GUI.
	public Character currentChar;
	public Job currentJob;
	public String currentRoute;
	// classHistory hold's a unit's history of its jobs at each level
	// the key for classHistory is the unit's level and the value is the name of the job at the key's level
	public TreeMap<Integer, String> classHistory; // this is shared between the
											// localUnitSheet and
											// inputUnitSheet.
	public Unit fixedParent;
	public Unit variedParent;
	public double[] fixedParentInputStats;
	public double[] variedParentInputStats;
	public int startLevel;					// This is the unit's first level it starts at as an inner level (so promoted units will have +20 to this)

	// prevents instantiation
	private UnitController() {

	}

	// establish singleton pattern - only one one instance of UnitController to
	// exist in the project
	public static UnitController getInstance() {
		if (instance == null) {
			instance = new UnitController();
		}
		return instance;
	}

	// BUILDS A LOCALUNITSHEET
	public void buildLocalUnitSheet(int inputLevel) {
		Job newJob = data.getJobs().get(classHistory.get(inputLevel));
		//System.out.println("InitialJob " + newJob.getName());

		// Creating the unit
		Unit localUnit;
		if (currentChar instanceof ChildCharacter) {
			localUnit = new Unit((ChildCharacter) currentChar, newJob, currentRoute, fixedParentInputStats, fixedParent,
					variedParentInputStats, variedParent, startLevel);
		} else {
			localUnit = new Unit(currentChar, newJob, currentRoute, inputLevel);
		}
		// Making the unitSheet
		localUnitSheet.clear();
		buildSheet(localUnit, classHistory, localUnitSheet, inputLevel);
	}

	// BUILDS A INPUTUNITSHEET
	public void buildInputUnitSheet(int inputLevel, double[] inputStats) {
		TreeMap<Integer, String> inputClassHistory = new TreeMap<Integer, String>();

		Job newJob = data.getJobs().get(classHistory.get(inputLevel));
		//System.out.println("InitialJob " + newJob.getName());

		// setting the base level
		int baseLevel;
		if (currentChar.getIsChild()) {
			baseLevel = startLevel;
		} else {
			baseLevel = currentChar.getBaseStats().getStats(currentRoute, 0);
		}
		
		boolean tempIsPromoted = newJob.getIsPromoted();
		String baseJobName = currentChar.getBaseClass();
		int promotedLevel = 1;	// Used to set levels of a user-promoted unit in the jobHistory in the GUI from 1 - job's max level
		if(tempIsPromoted && !baseJobName.equals(newJob.getName())) {
			
		}
		
		// Creating a temporary job history array to match the level of the
		// inputUnit(Ignoring the previous levels)
		int levelMod = inputLevel - baseLevel;
		for (int i = inputLevel; i <= classHistory.lastKey(); i++) {
			inputClassHistory.put(i, classHistory.get(i));
		}

		// Making the unit
		Unit inputUnit;
		if (currentChar instanceof ChildCharacter) {
			inputUnit = new Unit((ChildCharacter) currentChar, newJob, currentRoute, fixedParentInputStats, fixedParent,
					variedParentInputStats, variedParent, startLevel);
		} else {
			inputUnit = new Unit(currentChar, newJob, currentRoute, inputLevel);
		}

		// Correcting the unit's stats in accordance to input
		inputUnit.setLevel(inputLevel);
		inputUnit.setBaseStats(inputStats);

		// Making the unitSheet
		inputUnitSheet.clear();
		buildSheet(inputUnit, inputClassHistory, inputUnitSheet, inputLevel);
	}

	// =================================================================THIS IS
	// THE MATH ZONE. BEWARE

	// RECURSION WHAT :O
	public void buildSheet(Unit unit, TreeMap<Integer, String> inputClassHistory, ArrayList<Unit> inputSheet, int i) {
		Cloner cloner = new Cloner();
		Unit newUnit = cloner.deepClone(unit); // this is the deep clone.

		if (i > inputClassHistory.lastKey()) // ends recursion
		{
			// do nothing
			System.out.println("Complete");
		} 
		else if (i == inputClassHistory.firstKey()) // adds an initial base unit if list is empty
		{
			inputSheet.add(newUnit);
			i++;
			buildSheet(newUnit, inputClassHistory, inputSheet, i);
		} 
		else // Leveling up the new Unit
		{
			// Handle reclassing
			if (inputClassHistory.get(i).equals(unit.getMyJob().getName()) != true) {
				newUnit.reclass(data.getJobs().get(inputClassHistory.get(i)));
			}

			// Calculate the average stat for the correct level
			CalculateAverageStats(newUnit, (newUnit.getLevel() + 1));
			inputSheet.add(newUnit);

			// increase the level of the unit by 1 so we can map out each level
			newUnit.setLevel(newUnit.getLevel() + 1);
			i++;
			buildSheet(newUnit, inputClassHistory, inputSheet, i);
		}
	}

	// This adds the base class mods
	public void addBaseClassMods(Unit unit) {
		for (int i = 0; i < unit.getBaseStats().length; i++) {
			double[] baseStats = unit.getBaseStats();
			int[] Mods = data.getJobs().get(unit.getMyJob().getName()).getBaseStats();

			baseStats[i] = baseStats[i] + Mods[i];

			unit.setBaseStats(baseStats);
		}
	}

	// calculates averagestats for a unit
	public void CalculateAverageStats(Unit inputUnit, int inputLevel) {
		int levelDifference = (inputLevel - inputUnit.getLevel());

		if (levelDifference < 0) {
			levelDifference = 0;
		}

		double[] tempBaseStats = inputUnit.getBaseStats();

		for (int q = 0; q < tempBaseStats.length; q++) {
			//System.out.println(tempBaseStats[q]);
		}
		double[] tempGrowths = inputUnit.getGrowths();

		for (int i = 0; i < inputUnit.getBaseStats().length; i++) {
			tempBaseStats[i] += ((tempGrowths[i] / 100) * levelDifference);
		}

	}
	
	// THIS NEEDS TO BE MODIFIED TO NOT OVERWRITE OTHER RECLASSES
	// Method for reclassing, only affects jobHistory
	// @Params
	// newJob - the job we want to reclass to
	// changeLevel - the level we're reclassing at
	public void reclass(String newJob, int changeLevel) {
		Job tempNewJob = data.getJobs().get(newJob);						// the new job
		Job tempOldJob = data.getJobs().get(classHistory.get(changeLevel));	// the old job
		
		// change all levels from the one we reclassed to the last in the classHistory
		// do not override promoted levels
		for (int i = changeLevel; i <= classHistory.lastKey(); i++) {
			// check to see if we hit a promoted level while changing a non promoted job
			//if(!tempNewJob.getIsPromoted() && data.getJobs().get(classHistory.get(i)).getIsPromoted() && 
				//	(!tempNewJob.getIsSpecial() || (tempNewJob.getIsSpecial() && tempNewJob.getMaxStats(0) != data.SPECIAL_MAX_LEVEL)))
			
			// if we hit a different job from the one we're reclassing from, stop reclassing
			if(!tempOldJob.getName().equals(classHistory.get(i)))
				break;
			classHistory.put(i, newJob);
		}
		
		// if we're reclassing to a special class and we don't have the minimum number of values, add until we do
		if(tempNewJob.getIsSpecial() && classHistory.lastKey() < tempNewJob.getMaxStats(0)) {
			for (int i = classHistory.lastKey() + 1; i <= tempNewJob.getMaxStats(0); i++) {
				classHistory.put(i, newJob);
			}
		}
	}
	
	public void promote(String promotedJob, int changeLevel) {
		// remove the classes from the end of classHistory to the index the user is promoting at, including the index itself
		for (int i = classHistory.lastKey(); i > changeLevel; i--) {
			classHistory.remove(i);
		}
		
		int maxLevel = data.getJobs().get(promotedJob).getMaxStats(0);
		int tempLastKey = classHistory.lastKey();	// get the current last key for calculations (lest we end up in an infinite loop if we use classHistory.lastKey())
		// Add the promoted job to classHistory a number of levels equal to its max stats
		for (int i = classHistory.lastKey() + 1; i < tempLastKey + 1 + maxLevel; i++) {
			classHistory.put(i, promotedJob);
		}
	}
	
	public void eternalSeal(int levelsToAdd) {
		String lastJobName = classHistory.get(classHistory.lastKey());
		int lastLevel = classHistory.lastKey();
		
		for(int i = lastLevel + 1; i < lastLevel + levelsToAdd + 1; i++) {
			// Make sure we don't add more levels than our cap
			if(i > data.ETERNAL_SEAL_CAP)
				break;
			classHistory.put(i, lastJobName);
		}
	}
	
	public String[] getFormattedClassHistory() {
		String[] newClassHistory = new String[classHistory.size()];
		boolean tempIsPromoted;
		Job tempJob;
		String baseJobName = currentChar.getBaseClass();
		
		// Used to set levels of a user-promoted unit in the jobHistory in the GUI from
		// 1 - job's max level if the unit is not a prepromoted unit or from the unit's base level - job's max level if the unit is prepromoted
		int promotedLevel = data.getJobs().get(baseJobName).getIsPromoted() ? currentChar.getBaseStats().getStats(currentRoute, 0) : 1;
		
		// iterate from the base level to the last key in the classhistory
		for (int i = startLevel; i <= classHistory.lastKey(); i++) {
			int tempIndex = i-startLevel;	// Used to start at 0 for the newClassHistory array
			tempJob = data.getJobs().get(classHistory.get(i));
			tempIsPromoted = tempJob.getIsPromoted();
	
			// checks to see if the unit is a promoted unit so it displays the inner level with the non-inner level
			// do some extra checks for special class (aka if it's a special class without a level 40 cap
			if(((tempJob.getIsSpecial() && tempJob.getMaxStats(0) == data.BASE_MAX_LEVEL) || !tempJob.getIsSpecial()) && i > data.BASE_MAX_LEVEL)
			{
				newClassHistory[tempIndex] = "Lvl (" + i + ") " + promotedLevel + ". " + classHistory.get(i);
			}
			// if the unit isn't promoted, then the levels range from the base level of the unit to the max level of the job
			else
				newClassHistory[tempIndex] = "Lvl " + i + ". " + classHistory.get(i);
			
			// if the current job is a promoted one OR if the top is special, has a max level of 40 and we're above level 20 (aka dealing with promoted jobs)
			// then increase promoted level by 1
			// This ensures that reclassing from a special 40-max-level job to a non-special promoted job will display correctly
			if(promotedLevel < data.DISPLAY_MAX_LEVEL && (tempIsPromoted || (tempJob.getIsSpecial() && tempJob.getMaxStats(0) == data.SPECIAL_MAX_LEVEL) && i > data.BASE_MAX_LEVEL)) {
				promotedLevel++;
			}
		}
		return newClassHistory;
	}
	
	// ===========================================================STUFF FOR GUI
	// AND TESTING

	// This returns an array of stats from the local sheet
	// REFERENCE: 0=HP, 1=STR, 2=MAG, 3=SPD, 4=SKL, 5=LUK, 6=DEF, 7=RES
	public double[] getLocalStatSpread(int stat) {
		double[] output = new double[localUnitSheet.size()];

		for (int i = 0; i < localUnitSheet.size(); i++) {
			double[] tempArray = localUnitSheet.get(i).getBaseStats();
			output[i] = tempArray[stat];
		}

		return output;
	}

	public double[] getInputStatSpread(int stat) {
		double[] output = new double[inputUnitSheet.size()];

		for (int i = 0; i < inputUnitSheet.size(); i++) {
			double[] tempArray = inputUnitSheet.get(i).getBaseStats();
			output[i] = tempArray[stat];
		}

		return output;
	}
	
	public double[] getLocalRating() 
	{
		double[] output = new double[localUnitSheet.size()];

		for (int i = 0; i < localUnitSheet.size(); i++) 
		{
			int inputStat = 0;
			
			for(int j = 0; j<8; j++)
			{
				double tempStat = localUnitSheet.get(i).getStats(j);
				inputStat+=tempStat;
			}
			
			output[i] = inputStat;
		}

		return output;
	}
	
	public double[] getInputRating() 
	{
		double[] output = new double[inputUnitSheet.size()];

		for (int i = 0; i < inputUnitSheet.size(); i++) 
		{
			int inputStat = 0;
			
			for(int j = 0; j<8; j++)
			{
				double tempStat = inputUnitSheet.get(i).getStats(j);
				inputStat+=tempStat;
			}
			
			output[i] = inputStat;
		}

		return output;
	}
	
	// PRINTER METHOD FOR TESTING
	public void printLocalSheet() {
		for (int i = 0; i < localUnitSheet.size(); i++) {
			System.out.println("--PRINTING LOCAL SHEET--");
			System.out.println("--------------------------------------------");
			localUnitSheet.get(i).printUnit();
		}

	}

	// PRINTER METHOD FOR TESTING
	public void printInputSheet() {
		for (int i = 0; i < inputUnitSheet.size(); i++) {
			System.out.println("--PRINTING INPUT SHEET--");
			System.out.println("--------------------------------------------");
			inputUnitSheet.get(i).printUnit();
		}

	}

	// ALL GETTERS/SETTERS
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

	public TreeMap<Integer, String> getClassHistory() {
		return classHistory;
	}
	
	public String getClassHistoryValueByKey(int key) {
		return classHistory.get(key);
	}

	public void setClassHistory(TreeMap<Integer, String> classHistory) {
		this.classHistory = classHistory;
	}

	public void setFixedParent(Unit fixedParent) {
		this.fixedParent = fixedParent;
	}

	public void setVariedParent(Unit variedParent) {
		this.variedParent = variedParent;
	}

	public void setFixedParentInputStats(double[] fixedParentInputStats) {
		this.fixedParentInputStats = fixedParentInputStats;
	}

	public void setVariedParentInputStats(double[] variedParentInputStats) {
		this.variedParentInputStats = variedParentInputStats;
	}

	public int getStartLevel() {
		return startLevel;
	}
	
	public void setStartLevel(int startLevel) {
		this.startLevel = startLevel;
	}
}
