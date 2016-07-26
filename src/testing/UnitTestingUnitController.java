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
	Unit unitA = new Unit(data.getCharacters().get("Silas"), data.getJobs().get("Cavalier"), 20, "conquest");	
	unitA.printUnit();
	}
}
