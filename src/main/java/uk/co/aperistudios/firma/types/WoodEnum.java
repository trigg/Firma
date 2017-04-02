package uk.co.aperistudios.firma.types;

import net.minecraft.util.IStringSerializable;

public enum WoodEnum implements IStringSerializable {
	Acacia(0, "acacia"), Ash(1, "ash"), Aspen(2, "aspen"), Birch(3, "birch"), Chestnut(4, "chestnut"), Douglasfir(5, "douglasfir"), Hickory(6,
			"hickory"), Kapok(7, "kapok"), Maple(8, "maple"), Oak(9, "oak"), Pine(10,
					"pine"), Sequoia(11, "sequoia"), Spruce(12, "spruce"), Sycamore(13, "sycamore"), Whitecedar(14, "whitecedar"), Whiteelm(15, "whiteelm");
	// Rhyolite(16,"willow");
	// RockSalt(16,"rocksalt"),
	// Schist(17,"schist"),
	// Shale(18,"shale"),
	// Slate(19,"slate");
	private String name;
	private int meta;

	WoodEnum(int meta, String name) {
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
		WoodEnum a = get(itemDamage);
		if (a == null) {
			return null;
		}
		return a.getName();
	}

	public static WoodEnum get(int itemDamage) {
		for (WoodEnum e : WoodEnum.values()) {
			if (e.meta == itemDamage) {
				return e;
			}
		}
		return null;
	}

	public static WoodEnum get(String string) {
		for(WoodEnum e : WoodEnum.values()){
			if(e.getName() == string){
				return e;
			}
		}
		return null;
	}
}
