package testing;

import json.DataStorage;
import java.util.Arrays;
import domain.ChildCharacter;
import domain.Unit;
import domain.Avatar;
import domain.Kana;

public class BlessedOrScrewed {
	public static void main(String[] args) {
		DataStorage data = DataStorage.getInstance();
		// ALWAYS PARSE FILES FIRST BEFORE DOING ANYTHING ELSE
		data.ParseJsonCharacters();
		data.ParseJsonJobs();
		data.InitializeBoonsAndBanes();
		
		System.out.println("ALL CHARACTERS: ");
		data.printAllCharacters();
		/*
		System.out.println("\nALL JOBS: ");
		data.printAllJobs();
		*/
		
		// LET'S DO SOME TESTS
		/*
		// Characters test
		System.out.println("\nCHARACTER TESTS");
		
		String path = "Conquest";
		String tmp;

		System.out.println("In path: " + path);
		System.out.println("Silas BaseRes should be 5 in conquest: " + data.getCharacters().get("Silas").getBaseStats().getStats(path, DataStorage.CharBaseStatsEnums.RES.ordinal()));
		System.out.println("Silas HpGrowth should be 40: " + data.getCharacters().get("Silas").getGrowths()[DataStorage.CharGrowthsEnums.HP.ordinal()]);
		System.out.println("Peri SklMod should be -1: " + data.getCharacters().get("Peri").getMaxMods()[DataStorage.CharMaxModsEnums.SKL.ordinal()]);
		// very verbose way to change the ArrayList data to a String
		tmp = Arrays.toString(data.getCharacters().get("Silas").getBaseStats().getConquest());
		System.out.println("These are all of Silas' CONQUEST baseStats: " + tmp);
		tmp = Arrays.toString(data.getCharacters().get("Rinkah").getBaseStats().getConquest());
		System.out.println("These are all of Rinkah's CONQUEST baseStats: " + tmp);
		
		path = "Revelations";
		
		System.out.println("\nSwitching to new path. Now in: " + path);
		System.out.println("Silas BaseRes should be 14 in revelations: " + data.getCharacters().get("Silas").getBaseStats().getStats(path, DataStorage.CharBaseStatsEnums.RES.ordinal()));
		System.out.println("Silas HpGrowth should still be 40: " + data.getCharacters().get("Silas").getGrowths()[DataStorage.CharGrowthsEnums.HP.ordinal()]);
		System.out.println("Peri SklMod should still be -1: " + data.getCharacters().get("Peri").getMaxMods()[DataStorage.CharMaxModsEnums.SKL.ordinal()]);
		
		System.out.println("\nSilas is a boy! : " + data.getCharacters().get("Silas").getAreYouABoy());
		System.out.println("Peri is not a boy! : " + data.getCharacters().get("Peri").getAreYouABoy());
		tmp = Arrays.toString(data.getCharacters().get("Silas").getValidSpecials());
		System.out.println("Silas can be these specials classes: " + tmp + " ;)");
		System.out.println("Peri may act like a child, but she really isn't one! : " + data.getCharacters().get("Peri").getIsChild());
		tmp = Arrays.toString(data.getCharacters().get("Silas").getBaseStats().getRevelations());
		System.out.println("These are all of Silas' REVELATIONS baseStats: " + tmp);
		System.out.println();
		*/
		
		/*
		//Jobs tests
		System.out.println("JOB TESTS");
		System.out.println("Cavalier BaseSpd should be 5: " + data.getJobs().get("Cavalier").getBaseStats()[DataStorage.JobBaseStatsEnums.SPD.ordinal()]);
		System.out.println("Cavalier MagGrowth should be 0: " + data.getJobs().get("Cavalier").getGrowths()[DataStorage.JobGrowthsEnums.MAG.ordinal()]);
		tmp = Arrays.toString(data.getJobs().get("Cavalier").getPromotions());
		System.out.println("Cavalier promotions should be Paladin and Great Knight: " + tmp);
		System.out.println("Cavalier is not genderLocked! : " + data.getJobs().get("Cavalier").getGenderLock());
		
		System.out.println();
		System.out.println("Songstress maxLevel should be 40: " + data.getJobs().get("Songstress").getMaxStats()[DataStorage.JobMaxStatsEnums.LEVEL.ordinal()]);
		System.out.println("Songstress should not be a promoted class: " + data.getJobs().get("Songstress").getIsPromoted());
		System.out.println("Songstress is indeed a special class! : " + data.getJobs().get("Songstress").getIsSpecial());
		
		System.out.println();
		System.out.println("Printing the name of Great Master: " + data.getJobs().get("Great Master").getName());
		System.out.println("Great Master is genderLocked to male! : " + data.getJobs().get("Great Master").getGenderLock());
		tmp = Arrays.toString(data.getJobs().get("Great Master").getMaxStats());
		System.out.println("These are the Great Master's max stat caps: " + tmp);
		System.out.println();
		*/
		
		
		// Test Children Math
		System.out.println("Testing Children Math");
		//USING ACTUAL IN-GAME DATA!
		//ACTUAL: LEVEL: 20, HP: 26, STR: 7, MAG: 20, SKL: 12, SPD: 21, LCK: 13, DEF: 8, RES: 20
		System.out.println("AZURA!RHAJAT");
		ChildCharacter tempC = (ChildCharacter) data.getCharacters().get("Rhajat");
		int startLevel = 20;
		double[] fixedParentStats = {27,11,14,13,20,20,12,8};
		double[] variedParentStats = {18,17,5,26,25,17,7,13};
		Unit fixedParent = new Unit(data.getCharacters().get("Hayato"), data.getJobs().get("Diviner"), "Birthright");
		Unit variedParentAzura = new Unit(data.getCharacters().get("Azura"), data.getJobs().get("Songstress"), "Birthright");
		
		Unit tempUnit = new Unit(tempC, data.getJobs().get("Diviner"), "Birthright", fixedParentStats, fixedParent, variedParentStats, variedParentAzura, startLevel);
		Unit variedParentRhajat = tempUnit;	// This is being done for the Kana test, not for testing Rhajat
		tempUnit.printUnit();
		System.out.println();
		
		System.out.println("AZURA!SOPHIE");
		tempC = (ChildCharacter) data.getCharacters().get("Sophie");
		startLevel = 10;
		fixedParentStats = new double[] {22, 11, 0, 9, 8, 7, 10, 5};
		variedParentStats = new double[] {16, 5, 2, 8, 8, 6, 4, 7};
		fixedParent = new Unit(data.getCharacters().get("Silas"), data.getJobs().get("Cavalier"), "Conquest");
		variedParentAzura = new Unit(data.getCharacters().get("Azura"), data.getJobs().get("Songstress"), "Conquest");
		tempUnit = new Unit(tempC, data.getJobs().get("Cavalier"), "Conquest", fixedParentStats, fixedParent, variedParentStats, variedParentAzura, startLevel);
		tempUnit.printUnit();
		System.out.println();
		
		System.out.println("NYX!SOPHIE");
		variedParentStats = new double[] {20, 1, 12, 5, 11, 3, 4, 8};
		Unit variedParentNyx = new Unit(data.getCharacters().get("Nyx"), data.getJobs().get("Dark Mage"), "Conquest");
		tempUnit = new Unit(tempC, data.getJobs().get("Cavalier"), "Conquest", fixedParentStats, fixedParent, variedParentStats, variedParentNyx, startLevel);
		tempUnit.printUnit();
		System.out.println();
		
		/*
		// Avatar Test
		System.out.println("TEST AVATAR");
		Avatar av = (Avatar) data.getCharacters().get("Avatar");
		av.setBoon(data.getBoons()[1]);
		av.setBane(data.getBanes()[5]);
		
		Unit tempAvatar = new Unit(av, data.getJobs().get("Nohr Prince/ss"), "Conquest");
		System.out.println("Boon: " + data.getBoons()[1] + ", Bane: " + data.getBanes()[5]);
		tempAvatar.printUnit();
		
		System.out.println();
		
		av.setBoon(data.getBoons()[2]);
		av.setBane(data.getBanes()[6]);
		
		tempAvatar = new Unit(av, data.getJobs().get("Nohr Prince/ss"), "Conquest");
		System.out.println("Boon: " + data.getBoons()[2] + ", Bane: " + data.getBanes()[6]);
		tempAvatar.printUnit();
		System.out.println();
		
		// Kana Test
		System.out.println("TEST KANA");
		Kana ka = (Kana) data.getCharacters().get("Kana");
		startLevel = 20;
		Unit variedParentPeri = new Unit(data.getCharacters().get("Peri"), data.getJobs().get("Cavalier"), "Conquest");
		fixedParentStats = new double[] {20,20,20,20,20,20,20,20};
		variedParentStats = new double[] {20,20,20,20,20,20,20,20};
		
		tempUnit = new Unit(ka, data.getJobs().get("Nohr Prince/ss"), "Conquest", fixedParentStats, tempAvatar, variedParentStats, variedParentPeri, startLevel);
		System.out.println("Peri as mother");
		tempUnit.printUnit();
		System.out.println();
		
		System.out.println("Rhajat, who is a child character, as mother. Use values from above childCharacter test");
		tempUnit = new Unit(ka, data.getJobs().get("Nohr Prince/ss"), "Conquest", fixedParentStats, tempAvatar, variedParentStats, variedParentRhajat, startLevel);
		tempUnit.printUnit();
		*/
	}
}
