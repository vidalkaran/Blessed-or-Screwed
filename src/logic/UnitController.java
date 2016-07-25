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
	
	public Unit buildUnit(Character character, Job job, int level)
	{
		Unit newUnit = new Unit(character, job, level);
		
		//set max stats
		newUnit.setMaxStr(character.getStrMod()+job.getMaxStr());
		newUnit.setMaxMag(character.getMagMod()+job.getMaxMag());
		newUnit.setMaxSkl(character.getSklMod()+job.getMaxSkl());
		newUnit.setMaxSpd(character.getSpdMod()+job.getMaxSpd());
		newUnit.setMaxDef(character.getDefMod()+job.getMaxDef());
		newUnit.setMaxRes(character.getResMod()+job.getMaxRes());
		
		//set growths
		newUnit.setStrGrowth(character.getStrGrowth()+job.getStrGrowth());
		newUnit.setMagGrowth(character.getMagGrowth()+job.getMagGrowth());
		newUnit.setSklGrowth(character.getSklGrowth()+job.getSklGrowth());
		newUnit.setSpdGrowth(character.getSpdGrowth()+job.getSpdGrowth());
		newUnit.setDefGrowth(character.getDefGrowth()+job.getDefGrowth());
		newUnit.setResGrowth(character.getResGrowth()+job.getResGrowth());
		
		//set Base
		newUnit.setStr(character.getBaseStr()+job.getBaseStr());
		newUnit.setMag(character.getBaseMag()+job.getBaseMag());
		newUnit.setSkl(character.getBaseSkl()+job.getBaseSkl());
		newUnit.setSpd(character.getBaseSpd()+job.getBaseSpd());
		newUnit.setDef(character.getBaseDef()+job.getBaseDef());
		newUnit.setRes(character.getBaseRes()+job.getBaseRes());

		
		return newUnit;
	}

}
