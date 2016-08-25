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
	double[] inputStats = {20,20,20,20,20,20,20,20};
	
	ArrayList<String> ClassHistory = new ArrayList();
	ClassHistory.add("Cavalier");
	ClassHistory.add("Cavalier");
	ClassHistory.add("Songstress");
	ClassHistory.add("Songstress");
	ClassHistory.add("Songstress");

	//This is just testing Unit.java.
	//test.testUnit(Silas, Cavalier, "conquest");
	
	//This is testing UnitController
	//test.testUnitController(Silas, Cavalier, "conquest", ClassHistory);
	
	//This is testing calculating avg. stats
	//test.testCalculateAvgStats(new Unit(Silas, Cavalier, "conquest"));
	
	//this is testing UnitController's buildUnit 
	//test.testUnitController(Silas, Cavalier, "conquest", ClassHistory, 15, inputStats);

	}
	
//This method is just testing unit.java
public void testUnit(Character inputChar, Job inputJob, String Route)
{
	Unit newUnit = new Unit(inputChar, inputJob, Route);
	newUnit.printUnit();
}

public void testUnitController(Character inputChar, Job inputJob, String Route, ArrayList<String> inputClassHistory)
{
	UnitController unitController = UnitController.getInstance();
	Unit inputUnit = unitController.buildUnit(inputChar, inputJob, Route);
	
	unitController.buildLocalUnitSheet(inputUnit, inputClassHistory);
	unitController.printLocalSheet();
}

public void testUnitController(Character inputChar, Job inputJob, String Route, ArrayList<String> inputClassHistory, int level, double[]inputStats)
{
	UnitController unitController = UnitController.getInstance();
	Unit inputUnit = unitController.buildUnit(inputChar, inputJob, Route, level, inputStats);
	
	unitController.buildLocalUnitSheet(inputUnit, inputClassHistory);
	unitController.printLocalSheet();
}

public void testCalculateAvgStats(Unit inputUnit)
{
	UnitController unitController = UnitController.getInstance();

	unitController.addBaseClassMods(inputUnit);
	inputUnit.printUnit();
	unitController.CalculateAverageStats(inputUnit, 12);
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
