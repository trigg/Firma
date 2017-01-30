package uk.co.aperistudios.firma.blocks.boring;

import net.minecraft.util.IStringSerializable;

public enum RockEnum implements IStringSerializable {
	Andesite(0,"andesite"), 
	Basalt(1,"basalt"), 
	Chalk(2,"chalk"), 
	Chert(3,"chert"), 
	Claystone(4,"claystone"), 
	Conglomerate(5,"conglomerate"), 
	Dacite(6,"dacite"), 
	Diorite(7,"diorite"), 
	Dolomite(8,"dolomite"), 
	Gabbro(9,"gabbro"), 
	Granite(10,"granite"), 
	Limestone(11,"limestone"), 
	Marble(12,"marble"), 
	Phyllite(13,"phyllite"),
	Quartzite(14,"quartzite"),
	Rhyolite(15,"rhyolite");
	//RockSalt(16,"rocksalt"),
	//Schist(17,"schist"),
	//Shale(18,"shale"),
	//Slate(19,"slate");
	private String name;
	private int meta;

	RockEnum(int meta, String name) {
		this.name = name;
		this.meta = meta;
	}

	@Override
	public String getName() {
		return name;
	}
	
	public int getMeta(){
		return meta;
	}
}
