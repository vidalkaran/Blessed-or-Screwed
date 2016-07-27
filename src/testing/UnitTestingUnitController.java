package testing;

import domain.Unit;
import json.DataStorage;

public class UnitTestingUnitController {

public static void main(String[]args)
	{
	//builds data library
	DataStorage data = DataStorage.getInstance();
	data.ParseJsonCharacters();
	data.ParseJsonJobs();
	
	//test
	System.out.println("TESTING UNIT");
	
	//base level
	Unit unitA = new Unit(data.getCharacters().get("Silas"), data.getJobs().get("Cavalier"),6, "conquest");	
	unitA.printUnit();

	System.out.println("\n");
	
	//level 10
	Unit unitB = new Unit(data.getCharacters().get("Silas"), data.getJobs().get("Cavalier"),10, "conquest");	
	unitB.printUnit();
	}
}
