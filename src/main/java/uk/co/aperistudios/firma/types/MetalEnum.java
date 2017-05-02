package uk.co.aperistudios.firma.types;

import net.minecraft.util.IStringSerializable;

public enum MetalEnum  implements IStringSerializable {
	Stone("stone",0), Copper("copper",1), Bronze("bronze",2),BismuthBronze("bismuthbronze",3),BlackBronze("blackbronze",4),WroughtIron("wroughtiron",5),Steel("steel",6),BlackSteel("blacksteel",7),BlueSteel("bluesteel",8),RedSteel("redsteel",9);
	
	private int meta;
	private String name;

	MetalEnum(String name, int meta){
		this.name = name; this.meta = meta;
	}

	@Override
	public String getName() {
		return name;
	}
	
	public int getMeta() {
		return meta;
	}

	public static String getName(int itemDamage) {
		MetalEnum a = get(itemDamage);
		if (a == null) {
			return null;
		}
		return a.getName();
	}

	public static MetalEnum get(int itemDamage) {
		for (MetalEnum e : MetalEnum.values()) {
			if (e.meta == itemDamage) {
				return e;
			}
		}
		return null;
	}
}
