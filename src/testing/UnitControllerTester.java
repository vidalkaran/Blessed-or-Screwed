package testing;

import java.util.ArrayList;

import domain.Character;
import domain.Job;
import domain.Unit;
import json.DataStorage;
import logic.UnitController;

public class UnitControllerTester {

public static void main(String[]args)
{
	DataStorage data = DataStorage.getInstance();
	data.ParseJsonCharacters();
	data.ParseJsonJobs();
	
	UnitControllerTester test = new UnitControllerTester();
	
	Character Silas = data.getCharacters().get("Silas");
	Job Cavalier = data.getJobs().get("Cavalier");
	Unit SilasUnit = new Unit(Silas, Cavalier, "conquest");
	
	ArrayList<String> ClassHistory = new ArrayList();
	ClassHistory.add("Cavalier");
	ClassHistory.add("Cavalier");
	ClassHistory.add("Cavalier");
	
	//This is just testing Unit.java.
	//test.testUnit(Silas, Cavalier, "conquest");
	
	//This is testing UnitController
	test.testUnitController(SilasUnit, ClassHistory, data);
	
	//This is testing calculating avg. stats
	//test.testCalculateAvgStats(SilasUnit, data);
	
	}
	
//This method is just testing unit.java
public void testUnit(Character inputChar, Job inputJob, String Route)
{
	Unit newUnit = new Unit(inputChar, inputJob, Route);
	newUnit.printUnit();
}

public void testUnitController(Unit inputUnit, ArrayList<String> inputClassHistory, DataStorage inputData)
{
	UnitController UnitController = new UnitController(inputData);
	
	ArrayList<Unit> unitSheet = UnitController.buildUnitSheet(inputUnit, inputClassHistory);
	
	UnitController.print(unitSheet);
}

public void testCalculateAvgStats(Unit inputUnit, DataStorage inputData)
{
	UnitController UnitController = new UnitController(inputData);
	
	for(int i = 0; i<inputUnit.getBaseStats().length;i++)
	{
		double[] baseStats = inputUnit.getBaseStats();
		int[] Mods = inputData.getJobs().get(inputUnit.getMyJob().getName()).getBaseStats();
		
		baseStats[i] = baseStats[i] + Mods[i];
		
		inputUnit.setBaseStats(baseStats);
	}

	UnitController.CalculateAverageStats(inputUnit, 6);
	inputUnit.printUnit();
}

/*
Silas should look like this:
BASE
22 - 17 = 5
11 - 6 = 5
0 - 0 = 0
9 - 5 = 4
8 - 5 = 3 
7 - 3 = 4
10 - 5 = 5
5 - 3 = 2
GROWTHS
40 + 10 = 50
45 + 15 = 60
5 + 0 = 5
50 + 10 = 60
40 + 10 = 50
40 + 15 = 55
40 + 10 = 50
25 + 5 = 30
MAXs
22 + 1 = 23
15 + 0 = 15
21 + 2 = 24
20 + 0 = 20
24 - 1 = 25
22 + 0 = 22
21 - 1 = 20
 */
}
