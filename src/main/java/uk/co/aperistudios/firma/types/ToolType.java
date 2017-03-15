package uk.co.aperistudios.firma.types;

public enum ToolType {
	Axe("axe"),Chisel("chisel"),Hammer("hammer"),Hoe("hoe"),Javelin("javelin"),Knife("knife"),Mace("mace"),Pick("pick"),ProPick("propick"),Saw("saw"),Scythe("scythe"),Shovel("shovel"),Sword("sword")
	;
	
	private String name;

	ToolType(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
}
