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
	
	Character Silas = data.getCharacters().get("Silas");
	Job Cavalier = data.getJobs().get("Cavalier");
	double[] inputStats = {20,20,20,20,20,20,20,20};
	
	ArrayList<String> ClassHistory = new ArrayList();
	ClassHistory.add("Lvl 6. Cavalier");// level 6
	ClassHistory.add("Lvl 7. Cavalier");// level 7
	ClassHistory.add("Lvl 8. Cavalier");// level 8
	ClassHistory.add("Lvl 9. Cavalier");// level 9
	ClassHistory.add("Lvl 10. Cavalier");// level 10
	ClassHistory.add("Lvl 11. Cavalier");// level 11
	ClassHistory.add("Lvl 12. Cavalier");// level 12
	ClassHistory.add("Lvl 13. Cavalier");// level 13
	ClassHistory.add("Lvl 14. Cavalier");// level 14
	ClassHistory.add("Lvl 15. Cavalier");// level 15

	UnitController unitController = UnitController.getInstance();
	
	unitController.setCurrentChar(Silas);
	unitController.setCurrentLevel(6);
	unitController.setCurrentJob(Cavalier);
	unitController.setCurrentRoute("Conquest");
	unitController.setClassHistory(ClassHistory);
	
	//Testing Reclass
	unitController.reclass("Dancer", 10);
	
	System.out.println("CLASS HISTORY: ");	
	for(int i = 0; i<ClassHistory.size();i++)
	{
		System.out.println(i+", "+ClassHistory.get(i));
		System.out.println("Done");
	}
	
	
	//TESTING CALCULATIONS
//	unitController.buildLocalUnitSheet();
//	unitController.printLocalSheet();
	
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
