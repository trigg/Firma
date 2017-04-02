package uk.co.aperistudios.firma.types;

import net.minecraft.util.IStringSerializable;

public enum OresEnum implements IStringSerializable {
	NATIVECOPPER("nativecopper",RockEnum.values(), RockEnum2.values(),20),
	NATIVEGOLD("nativegold",new RockEnum[] {RockEnum.Andesite, RockEnum.Basalt,RockEnum.Dacite,RockEnum.Diorite,RockEnum.Gabbro,RockEnum.Granite}, new RockEnum2[] {RockEnum2.Rhyolite}, 5),
	NATIVESILVER("nativesilver", new RockEnum[] {RockEnum.Granite, RockEnum.Gneiss}, new RockEnum2[] {}, 8),
	BISMUTHINITE("bismuthinite", new RockEnum[] {RockEnum.Andesite, RockEnum.Basalt, RockEnum.Dacite, RockEnum.Chalk, RockEnum.Chert, RockEnum.Claystone, RockEnum.Conglomerate,RockEnum.Dolomite, RockEnum.Limestone}, new RockEnum2[] {RockEnum2.Rhyolite,RockEnum2.RockSalt, RockEnum2.Shale}, 20),
	CASSITERITE("cassiterite", new RockEnum[] {RockEnum.Diorite, RockEnum.Granite, RockEnum.Gabbro}, new RockEnum2[] {}, 15),
	GARNIERITE("garnierite", new RockEnum[] {RockEnum.Gabbro}, new RockEnum2[] {}, 5),
	HEMATITE("hematite", new RockEnum[] {RockEnum.Andesite, RockEnum.Basalt, RockEnum.Dacite}, new RockEnum2[] {RockEnum2.Rhyolite}, 10),
	LIMONITE("limonite", new RockEnum[] {RockEnum.Chalk, RockEnum.Chert, RockEnum.Claystone, RockEnum.Conglomerate, RockEnum.Dolomite, RockEnum.Limestone}, new RockEnum2[] {RockEnum2.RockSalt, RockEnum2.Shale}, 10),
	MAGNETITE("magnetite", new RockEnum[] {RockEnum.Chalk, RockEnum.Chert, RockEnum.Claystone, RockEnum.Conglomerate, RockEnum.Dolomite, RockEnum.Limestone}, new RockEnum2[] {RockEnum2.RockSalt, RockEnum2.Shale}, 10),
	MALACHITE("malachite", new RockEnum[] {RockEnum.Marble, RockEnum.Limestone}, new RockEnum2[] {}, 25),
	SPHALERITE("sphalerite", new RockEnum[] {RockEnum.Gneiss,RockEnum.Marble,RockEnum.Phyllite,RockEnum.Quartzite}, new RockEnum2[] {RockEnum2.Schist,RockEnum2.Slate },15),
	TETRAHEDRITE("tetrahedrite", new RockEnum[] {RockEnum.Gneiss,RockEnum.Marble,RockEnum.Phyllite,RockEnum.Quartzite}, new RockEnum2[] {RockEnum2.Schist,RockEnum2.Slate },30),
	;
	
	private String name;
	private RockEnum[] rock1;
	private RockEnum2[] rock2;
	private int rarity;

	OresEnum(String name, RockEnum[] rock1, RockEnum2[] rock2, int rarity){
		this.name = name;
		this.rock1 = rock1;
		this.rock2 = rock2;
		this.rarity = rarity;
	}
	@Override
	public String getName() {
		return name;
	}
	
	public RockEnum2[] getRock2() {
		return rock2;
	}
	
	public RockEnum[] getRock1() {
		return rock1;
	}
	
	public int getRarity() {
		return rarity;
	}
	public static int indexOf(OresEnum ore) {
		for(int i =0 ;i<OresEnum.values().length; i++){
			if(OresEnum.values()[i]==ore){
				return i;
			}
		}
		return -1;
	}
}
