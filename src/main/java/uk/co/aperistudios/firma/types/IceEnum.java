package uk.co.aperistudios.firma.types;

import net.minecraft.util.IStringSerializable;

public enum IceEnum implements IStringSerializable {
	FRESH(0,"fresh"),SALT(1,"salt");
	
	private int num;
	private String name;

	IceEnum(int i, String name){
		this.num=i;
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	public int getMeta() {
		return num;
	}

	public static String getName(int itemDamage) {
		IceEnum a = get(itemDamage);
		if (a == null) {
			return null;
		}
		return a.getName();
	}

	public static IceEnum get(int itemDamage) {
		for (IceEnum e : IceEnum.values()) {
			if (e.num == itemDamage) {
				return e;
			}
		}
		return null;
	}}
