package uk.co.aperistudios.firma.types;

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
	Gneiss(10,"gneiss"),
	Granite(11,"granite"),
	Limestone(12,"limestone"), 
	Marble(13,"marble"), 
	Phyllite(14,"phyllite"),
	Quartzite(15,"quartzite");
	//Rhyolite(16,"rhyolite");
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

	public static String getName(int itemDamage) {
		RockEnum a = get(itemDamage);
		if (a==null){ return null; }
		return a.getName();
	}

	public static RockEnum get(int itemDamage) {
		for(RockEnum e : RockEnum.values()){
			if(e.meta == itemDamage){
				return e;
			}
		}
		return null;
	}
}
