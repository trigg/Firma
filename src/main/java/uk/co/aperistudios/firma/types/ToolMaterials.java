package uk.co.aperistudios.firma.types;

public enum ToolMaterials {
	Stone("stone",3f), Copper("copper",4f), Bronze("bronze",13f),BismuthBronze("bismuthbronze",13f),BlackBronze("blackbronze",13f),WroughtIron("wroughtiron",16f),Steel("steel",20f),BlackSteel("blacksteel",30f),BlueSteel("bluesteel",50f),RedSteel("redsteel",50f)
	;
	
	
	String name;
	float harvestLevel;
	ToolMaterials(String name, float harvestLevel){
		this.name=name;
		this.harvestLevel=harvestLevel;
	}
	public String getName() {
		return name;
	}
	public float getHarvestLevel() {
		return harvestLevel;
	}
}
