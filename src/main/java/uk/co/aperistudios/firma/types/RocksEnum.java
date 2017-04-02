package uk.co.aperistudios.firma.types;

import net.minecraft.util.IStringSerializable;

public enum RocksEnum implements IStringSerializable {
	Andesite(0, "andesite"), Basalt(1, "basalt"), Chalk(2, "chalk"), Chert(3, "chert"), Claystone(4, "claystone"), Conglomerate(5, "conglomerate"), Dacite(6,
			"dacite"), Diorite(7, "diorite"), Dolomite(8, "dolomite"), Gabbro(9, "gabbro"), Gneiss(10,
					"gneiss"), Granite(11, "granite"), Limestone(12, "limestone"), Marble(13, "marble"), Phyllite(14, "phyllite"), Quartzite(15, "quartzite"),
	Rhyolite(0, "rhyolite"), RockSalt(1, "rocksalt"), Schist(2, "schist"), Shale(3, "shale"), Slate(4, "slate");
	
	private String name;


	RocksEnum(int ignore, String name){
		this.name = name;
	}


	@Override
	public String getName() {
		return name;
	}


	public static RocksEnum get(String name) {
		for(RocksEnum re : RocksEnum.values()){
			if(re.getName().equals(name)){
				return re;
			}
		}
		return null;
		
	}
	
	public static int indexOf(RocksEnum ore) {
		for(int i =0 ;i<RocksEnum.values().length; i++){
			if(RocksEnum.values()[i]==ore){
				return i;
			}
		}
		return -1;
	}
}
