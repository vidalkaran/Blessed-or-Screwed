package logic;

//note, may reconsider building characters from scratch. Could just call on the character from JSON and edit it as needed.

import java.util.ArrayList;
import domain.Unit;
import domain.Character;
import domain.Job;

public class UnitController {
	
	private ArrayList<Unit> UnitArray;
	
	public UnitController()
	{
		
	}
	
	public Unit buildUnit(Character character, Job job, int level, String route)
	{
		Unit newUnit = new Unit(character, job, level, route);
		return newUnit;
	}
}
