package uk.co.aperistudios.firma.types;

public enum ToolMetals {
	Copper("copper",0), Bronze("bronze",0),BismuthBronze("bismuthbronze",0),BlackBronze("blackbronze",0),WroughtIron("wroughtiron",1),Steel("steel",1),BlackSteel("blacksteel",2),BlueSteel("bluesteel",2),RedSteel("redsteel",2)
	;
	
	
	String name;
	int harvestLevel;
	ToolMetals(String name, int harvestLevel){
		this.name=name;
		this.harvestLevel=harvestLevel;
	}
	public String getName() {
		return name;
	}
	public int getHarvestLevel() {
		return harvestLevel;
	}
}
