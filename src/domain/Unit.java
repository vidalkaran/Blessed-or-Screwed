package domain;

// note: two uses of unit, one for player input and one for auto generation.

public class Unit {
	
	private Character myCharacter;
	private Job myJob;
	
	private int level;
	
	private int maxHp;
	private int maxStr;
	private int maxMag;
	private int maxSkl;
	private int maxSpd;
	private int maxLuk;
	private int maxDef;
	private int maxRes;
	
	private int hpGrowth;
	private int strGrowth;
	private int magGrowth;
	private int sklGrowth;
	private int spdGrowth;
	private int lukGrowth;
	private int defGrowth;
	private int resGrowth;
	
	private int hpMod;
	private int strMod;
	private int magMod;
	private int sklMod;
	private int spdMod;
	private int lukMod;
	private int defMod;
	private int resMod;
	
	private int hp;
	private int str;
	private int mag;
	private int skl;
	private int spd;
	private int luk;
	private int def;
	private int res;

	//THIS CONSTRUCTOR IS FOR AUTOGENERATION
	public Unit(Character myCharacter, Job myJob, int level) 
	{
		myCharacter = this.myCharacter;
		myJob = this.myJob;
		level = this.level;
	}
	
	//THIS CONSTRUCTOR IS FOR MANUAL ENTRY
	public Unit(Character myCharacter, Job myJob, int level,
				int str, int mag, int skl, int spd, int luk, int def, int res) 
	{
		myCharacter = this.myCharacter;
		myJob = this.myJob;
		level = this.level;
		str = this.str;
		mag = this.mag;
		skl = this.skl;
		spd = this.spd;
		luk = this.luk;
		def = this.def;
		res = this.res;
	}

	public Character getMyCharacter() {
		return myCharacter;
	}

	public void setMyCharacter(Character myCharacter) {
		this.myCharacter = myCharacter;
	}

	public Job getMyJob() {
		return myJob;
	}

	public void setMyJob(Job myJob) {
		this.myJob = myJob;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getMaxStr() {
		return maxStr;
	}

	public void setMaxStr(int maxStr) {
		this.maxStr = maxStr;
	}

	public int getMaxMag() {
		return maxMag;
	}

	public void setMaxMag(int maxMag) {
		this.maxMag = maxMag;
	}

	public int getMaxSkl() {
		return maxSkl;
	}

	public void setMaxSkl(int maxSkl) {
		this.maxSkl = maxSkl;
	}

	public int getMaxSpd() {
		return maxSpd;
	}

	public void setMaxSpd(int maxSpd) {
		this.maxSpd = maxSpd;
	}

	public int getMaxLuk() {
		return maxLuk;
	}

	public void setMaxLuk(int maxLuk) {
		this.maxLuk = maxLuk;
	}

	public int getMaxDef() {
		return maxDef;
	}

	public void setMaxDef(int maxDef) {
		this.maxDef = maxDef;
	}

	public int getMaxRes() {
		return maxRes;
	}

	public void setMaxRes(int maxRes) {
		this.maxRes = maxRes;
	}

	public int getStrGrowth() {
		return strGrowth;
	}

	public void setStrGrowth(int strGrowth) {
		this.strGrowth = strGrowth;
	}

	public int getMagGrowth() {
		return magGrowth;
	}

	public void setMagGrowth(int magGrowth) {
		this.magGrowth = magGrowth;
	}

	public int getSklGrowth() {
		return sklGrowth;
	}

	public void setSklGrowth(int sklGrowth) {
		this.sklGrowth = sklGrowth;
	}

	public int getSpdGrowth() {
		return spdGrowth;
	}

	public void setSpdGrowth(int spdGrowth) {
		this.spdGrowth = spdGrowth;
	}

	public int getLukGrowth() {
		return lukGrowth;
	}

	public void setLukGrowth(int lukGrowth) {
		this.lukGrowth = lukGrowth;
	}

	public int getDefGrowth() {
		return defGrowth;
	}

	public void setDefGrowth(int defGrowth) {
		this.defGrowth = defGrowth;
	}

	public int getResGrowth() {
		return resGrowth;
	}

	public void setResGrowth(int resGrowth) {
		this.resGrowth = resGrowth;
	}

	public int getStrMod() {
		return strMod;
	}

	public void setStrMod(int strMod) {
		this.strMod = strMod;
	}

	public int getMagMod() {
		return magMod;
	}

	public void setMagMod(int magMod) {
		this.magMod = magMod;
	}

	public int getSklMod() {
		return sklMod;
	}

	public void setSklMod(int sklMod) {
		this.sklMod = sklMod;
	}

	public int getSpdMod() {
		return spdMod;
	}

	public void setSpdMod(int spdMod) {
		this.spdMod = spdMod;
	}

	public int getLukMod() {
		return lukMod;
	}

	public void setLukMod(int lukMod) {
		this.lukMod = lukMod;
	}

	public int getDefMod() {
		return defMod;
	}

	public void setDefMod(int defMod) {
		this.defMod = defMod;
	}

	public int getResMod() {
		return resMod;
	}

	public void setResMod(int resMod) {
		this.resMod = resMod;
	}

	public int getStr() {
		return str;
	}

	public void setStr(int str) {
		this.str = str;
	}

	public int getMag() {
		return mag;
	}

	public void setMag(int mag) {
		this.mag = mag;
	}

	public int getSkl() {
		return skl;
	}

	public void setSkl(int skl) {
		this.skl = skl;
	}

	public int getSpd() {
		return spd;
	}

	public void setSpd(int spd) {
		this.spd = spd;
	}

	public int getLuk() {
		return luk;
	}

	public void setLuk(int luk) {
		this.luk = luk;
	}

	public int getDef() {
		return def;
	}

	public void setDef(int def) {
		this.def = def;
	}

	public int getRes() {
		return res;
	}

	public void setRes(int res) {
		this.res = res;
	}
	
}
