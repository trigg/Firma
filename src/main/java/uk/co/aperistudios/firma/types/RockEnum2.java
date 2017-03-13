package uk.co.aperistudios.firma.types;

import net.minecraft.util.IStringSerializable;

public enum RockEnum2 implements IStringSerializable {
	Rhyolite(0, "rhyolite"), RockSalt(1, "rocksalt"), Schist(2, "schist"), Shale(3, "shale"), Slate(4, "slate");
	private String name;
	private int meta;

	RockEnum2(int meta, String name) {
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
		RockEnum2 a = get(itemDamage);
		if (a == null) {
			return null;
		}
		return a.getName();
	}

	public static RockEnum2 get(int itemDamage) {
		for (RockEnum2 e : RockEnum2.values()) {
			if (e.meta == itemDamage) {
				return e;
			}
		}
		return null;
	}
}
