package uk.co.aperistudios.firma.types;

import net.minecraft.util.IStringSerializable;

public enum WoodEnum2 implements IStringSerializable {
	Willow(0, "willow");
	private String name;
	private int meta;

	WoodEnum2(int meta, String name) {
		this.name = name;
		this.meta = meta;
	}

	@Override
	public String getName() {
		return name;
	}

	public int getMeta() {
		return meta;
	}

	public static String getName(int itemDamage) {
		WoodEnum2 a = get(itemDamage);
		if (a == null) {
			return null;
		}
		return a.getName();
	}

	public static WoodEnum2 get(int itemDamage) {
		for (WoodEnum2 e : WoodEnum2.values()) {
			if (e.meta == itemDamage) {
				return e;
			}
		}
		return null;
	}
}
